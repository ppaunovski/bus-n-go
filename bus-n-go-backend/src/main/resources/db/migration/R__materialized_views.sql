set search_path = project;

drop materialized view if exists avg_time_diffs;
CREATE MATERIALIZED VIEW avg_time_diffs AS
WITH ordered_instanca AS (SELECT ilpl.instanca_na_linija_id,
                                 ilpl.postojka_na_linija_id,
                                 pnl.reden_broj,
                                 ilpl.timestamp
                          FROM instanca_na_linija_postojka_na_linija ilpl
                                   JOIN
                               postojka_na_linija pnl ON ilpl.postojka_na_linija_id = pnl.id
                          ORDER BY ilpl.instanca_na_linija_id,
                                   pnl.reden_broj),
     time_differences AS (SELECT o1.instanca_na_linija_id,
                                 o1.postojka_na_linija_id                          AS start_station_id,
                                 o1.timestamp                                      AS start_time,
                                 o2.postojka_na_linija_id                          AS end_station_id,
                                 o2.timestamp                                      AS end_time,
                                 EXTRACT(EPOCH FROM (o2.timestamp - o1.timestamp)) AS time_diff
                          FROM ordered_instanca o1
                                   JOIN
                               ordered_instanca o2 ON o1.instanca_na_linija_id = o2.instanca_na_linija_id
                          WHERE o1.reden_broj + 1 = o2.reden_broj)
SELECT
    row_number() over () as id,
    start_station_id,
       end_station_id,
       AVG(time_diff)::double precision AS avg_time_diff_seconds
FROM time_differences
GROUP BY start_station_id,
         end_station_id
ORDER BY start_station_id,
         end_station_id;

create unique index avg_time_diffs_index on avg_time_diffs (id);

drop materialized view if exists kazna_po_linija;
create materialized view kazna_po_linija as
select row_number() over () as id, l.id as line_id, count(l.id)
from linija l
         left join instanca_na_linija inl on l.id = inl.linija_id
         left join kontrola k on inl.id = k.instanca_na_linija_id
         left join kazna kaz on k.id = kaz.kontrola_id
where extract(year from kaz.date_created) = extract(year from now())
group by l.id
order by count(l.id) desc;

create unique index index_kazna_po_linija on kazna_po_linija(id);

drop materialized view if exists most_busy_part_of_the_day;
create materialized view most_busy_part_of_the_day as
select row_number() over () as id, id as linija_id, interval_1h, count(id || '-' || interval_1h) broj_pati
from (select li.id,
             (to_char(extract(hour from v.start_date), '00') || ':00 -' || to_char(extract(hour from (v.start_date
                 + '1 hours'::interval))::int, '00') || ':00') as interval_1h
      from linija li
               left join instanca_na_linija inl on li.id = inl.linija_id
               left join vozenje v on inl.id = v.instanca_na_linija_id
      where v.start_date between (make_date(extract(year from (now()))::int, 1, 1)) and (make_date(extract(year from (now()))::int, 12, 31))) as hours
group by id, interval_1h
order by interval_1h desc;

create unique index index_most_busy_part_of_the_day on most_busy_part_of_the_day(id);

drop materialized view if exists commutes_by_line;
create materialized view commutes_by_line as
select row_number() over () as id, l.id as linija_id, count(v.id)
from linija l
         left join instanca_na_linija inl on l.id = inl.linija_id
         left join vozenje v on inl.id = v.instanca_na_linija_id
where v.start_date between date_trunc('year', now()) and date_trunc('month', date_trunc('year', now())+ interval '7 months')
group by l.id;


drop materialized view if exists number_passengers_per_line_and_station;
create materialized view number_passengers_per_line_and_station as
select row_number() over () as id, p.id as station_id, l.id as line_id, count(p.id)
from postojka p
         join postojka_na_linija pnl on p.id = pnl.postojka_id
         join vozenje v on pnl.id = v.postojka_na_linija_start_id
         join patnik pat on v.patnik_id = pat.id
         join linija l on pnl.linija_id = l.id
where v.start_date between (make_date(extract(year from (now() - '1 years'::interval))::int, 4, 1)) and (make_date(extract(year from (now() - '1 years'::interval))::int, 6, 30))
group by p.id, l.id
order by count(p.id) desc;


drop materialized view if exists ticket_sales;
create materialized view ticket_sales as
select row_number() over () as id, filtered.tip_id, filtered.interval_1h, filtered.broj_pati
from (select filter.tip_id, interval_1h, count(tip_id || '-' || interval_1h) broj_pati
      from (select *,
                   (to_char(extract(hour from b.datum_kupuvanje), '00') || ':00 -' ||
                    to_char(extract(hour from (b.datum_kupuvanje + '1 hours'::interval))::int, '00') ||
                    ':00') as interval_1h
            from tipbilet t
                     left join bilet b on t.id = b.tip_id
--     where  b.datum_kupuvanje between (make_date(extract(year from (now() - '1 years'::interval))::int, 1, 1)) and (make_date(extract(year from (now() - '1 years'::interval))::int, 12, 31))

           ) as filter
      group by filter.tip_id, interval_1h) filtered
where broj_pati = (select max(filtered.broj_pati)
                   from (select filter.tip_id, interval_1h, count(tip_id || '-' || interval_1h) broj_pati
                         from (select *,
                                      (to_char(extract(hour from b.datum_kupuvanje), '00') || ':00 -' ||
                                       to_char(extract(hour from (b.datum_kupuvanje + '1 hours'::interval))::int,
                                               '00') ||
                                       ':00') as interval_1h
                               from tipbilet t
                                        left join bilet b on t.id = b.tip_id
--     where  b.datum_kupuvanje between (make_date(extract(year from (now() - '1 years'::interval))::int, 1, 1)) and (make_date(extract(year from (now() - '1 years'::interval))::int, 12, 31))

                              ) as filter
                         group by filter.tip_id, interval_1h) filtered)
  and tip_id in (select id
                 from tipbilet
                 where ime in ('Hourly', 'Daily'));



drop materialized view if exists total_income;
create materialized view total_income as
WITH kazna_sum AS (
    SELECT
        EXTRACT(YEAR FROM k.date_payed) AS year,
        SUM(k.iznos) AS sum_kz
    FROM
        kazna k
    WHERE
        k.plateno = TRUE
    GROUP BY
        EXTRACT(YEAR FROM k.date_payed)
),
     bilet_sum AS (
         SELECT
             EXTRACT(YEAR FROM b.datum_kupuvanje) AS year,
             SUM(tb.cena) AS sum_b
         FROM
             bilet b
                 JOIN tipbilet tb ON tb.id = b.tip_id
         GROUP BY
             EXTRACT(YEAR FROM b.datum_kupuvanje)
     )
SELECT
    row_number() over () as id,
    COALESCE(k.year, b.year)::text AS year,
    COALESCE(sum_kz, 0) as fines_income,
    COALESCE(sum_b, 0) as ticket_income,
    COALESCE(sum_kz, 0) + COALESCE(sum_b, 0) AS total_income
FROM
    kazna_sum k
        FULL OUTER JOIN bilet_sum b ON k.year = b.year
ORDER BY
    year desc;

create unique index index_total_income on total_income(id);