create or replace function check_expiry_date() returns trigger
as
    $$
    begin
        if exists(
            select 1
            from vozenje v
            where v.patnik_id = new.patnik_id and v.end_date is null
        ) then
            update vozenje set end_date = now(), status = 'FINISHED' where id in (select v.id
                                                                                  from vozenje v
                                                                                  where v.patnik_id = new.patnik_id and v.end_date is null);
        end if;

        if exists(
            select 1
            from bilet
            where id = new.bilet_id and datum_aktivacija is null
        ) then
            update bilet set datum_aktivacija = now(), status = 'ACTIVE' where id = new.bilet_id;
        end if;

        if exists(
            select 1
            from bilet b
            join tipbilet tb on b.tip_id = tb.id
            where b.id = new.bilet_id and ((b.datum_aktivacija + (tb.trajnost || ' milliseconds')::interval) < now())
        ) then
            update bilet set status = 'EXPIRED' where id = new.bilet_id;
            end if;

        if exists(
            select 1
            from bilet b
                     join tipbilet tb on b.tip_id = tb.id
            where b.id = new.bilet_id and ((b.datum_aktivacija + (tb.trajnost || ' milliseconds')::interval) < now())
        ) then
            RAISE exception 'Ticket is expired';
        end if;
        return new;
    end;
    $$ language plpgsql;

create or replace trigger check_validity_of_ticket
    before insert on vozenje
    for each row
    execute function check_expiry_date();
