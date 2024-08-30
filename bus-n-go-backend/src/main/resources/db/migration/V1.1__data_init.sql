set search_path = project;

INSERT INTO Korisnik (is_admin, adresa, email, ime, lozinka, telefon) VALUES
                                                                          (true, 'Bulevar Partizanski Odredi 45, Skopje', 'admin@example.com', 'Nikola Stojanovski', 'admin_password', '070123456'),
                                                                          (false, 'Ulica Makedonija 12, Skopje', 'user1@example.com', 'Elena Petrovska', 'user1_password', '071234567'),
                                                                          (false, 'Bulevar Sveti Kliment Ohridski 23, Skopje', 'user2@example.com', 'Marija Trajkovska', 'user2_password', '072345678'),
                                                                          (false, 'Ulica Vasil Glavinov 10, Skopje', 'user3@example.com', 'Ivana Risteska', 'user3_password', '073456789'),
                                                                          (false, 'Bulevar Krste Misirkov 56, Skopje', 'user4@example.com', 'Stefan Dimitrovski', 'user4_password', '074567890');
INSERT INTO Role (description, name) VALUES
                                         ('Administrator with full access rights', 'ROLE_ADMIN'),
                                         ('Driver responsible for operating the bus', 'ROLE_DRIVER'),
                                         ('Conductor responsible for checking tickets', 'ROLE_CONDUCTOR'),
                                         ('Commuter using the public transport services', 'ROLE_PASSENGER');
-- Nikola Stojanovski (Admin)
INSERT INTO Korisnik_Role (korisnik_id, role_id) VALUES
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 1); -- Admin role

-- Elena Petrovska (Commuter)
INSERT INTO Korisnik_Role (korisnik_id, role_id) VALUES
    (2, 4); -- Commuter role

-- Marija Trajkovska (Driver)
INSERT INTO Korisnik_Role (korisnik_id, role_id) VALUES
    (3, 2); -- Driver role

-- Ivana Risteska (Conductor)
INSERT INTO Korisnik_Role (korisnik_id, role_id) VALUES
    (4, 3); -- Conductor role

-- Stefan Dimitrovski (Commuter)
INSERT INTO Korisnik_Role (korisnik_id, role_id) VALUES
    (5, 4); -- Commuter role

-- Elena Petrovska (Commuter)
INSERT INTO Patnik (korisnik_id) VALUES
    (2);

-- Stefan Dimitrovski (Commuter)
INSERT INTO Patnik (korisnik_id) VALUES
    (5);

-- Nikola Stojanovski (Admin)
INSERT INTO Patnik (korisnik_id) VALUES
    (1); -- Assuming Nikola Stojanovski has vraboten_id 1

-- Nikola Stojanovski (Admin)
INSERT INTO Vraboten (datum_na_vrabotuvanje, datum_prekin_vrabotuvanje, plata, korisnik_id) VALUES
    ('2020-01-01', NULL, 60000, 1);

-- Marija Trajkovska (Driver)
INSERT INTO Vraboten (datum_na_vrabotuvanje, datum_prekin_vrabotuvanje, plata, korisnik_id) VALUES
    ('2021-05-15', NULL, 35000, 3);

-- Ivana Risteska (Conductor)
INSERT INTO Vraboten (datum_na_vrabotuvanje, datum_prekin_vrabotuvanje, plata, korisnik_id) VALUES
    ('2022-03-20', NULL, 30000, 4);

-- Marija Trajkovska (Driver)
INSERT INTO Vozac (vraboten_id) VALUES
    (2); -- Assuming Marija Trajkovska has vraboten_id 2

-- Nikola Stojanovski (Admin)
INSERT INTO Vozac (vraboten_id) VALUES
    (1); -- Assuming Nikola Stojanovski has vraboten_id 1

-- Insert employees into the Kondukter table

-- Ivana Risteska (Conductor)
INSERT INTO Kondukter (vraboten_id) VALUES
    (3); -- Assuming Ivana Risteska has vraboten_id 3

-- Nikola Stojanovski (Admin)
INSERT INTO Kondukter (vraboten_id) VALUES
    (1); -- Assuming Nikola Stojanovski has vraboten_id 1

INSERT INTO Avtobus (broj_sedishta, registracija, seriski_broj) VALUES
                                                                    (50, 'SK-1234-AB', 'SN-000001'),
                                                                    (45, 'SK-2345-BC', 'SN-000002'),
                                                                    (50, 'SK-3456-CD', 'SN-000003'),
                                                                    (40, 'SK-4567-DE', 'SN-000004'),
                                                                    (50, 'SK-5678-EF', 'SN-000005'),
                                                                    (55, 'SK-6789-FG', 'SN-000006'),
                                                                    (50, 'SK-7890-GH', 'SN-000007'),
                                                                    (45, 'SK-8901-HI', 'SN-000008'),
                                                                    (50, 'SK-9012-IJ', 'SN-000009'),
                                                                    (40, 'SK-0123-JK', 'SN-000010'),
                                                                    (50, 'SK-1234-KL', 'SN-000011'),
                                                                    (55, 'SK-2345-LM', 'SN-000012'),
                                                                    (50, 'SK-3456-MN', 'SN-000013'),
                                                                    (45, 'SK-4567-NO', 'SN-000014'),
                                                                    (50, 'SK-5678-OP', 'SN-000015'),
                                                                    (40, 'SK-6789-PQ', 'SN-000016'),
                                                                    (50, 'SK-7890-QR', 'SN-000017'),
                                                                    (55, 'SK-8901-RS', 'SN-000018'),
                                                                    (50, 'SK-9012-ST', 'SN-000019'),
                                                                    (45, 'SK-0123-TU', 'SN-000020');

INSERT INTO Linija (ime) VALUES
                                     ('Line 1'),
                                     ('Line 2'),
                                     ('Line 3'),
                                     ('Line 4'),
                                     ('Line 5'),
                                     ('Line 6'),
                                     ('Line 7'),
                                     ('Line 8'),
                                     ('Line 9'),
                                     ('Line 10');

INSERT INTO Pravec (pravec, opis) VALUES
                                      ('Skopje - Tetovo', 'Skopje to Tetovo'),
                                      ('Tetovo - Skopje', 'Tetovo to Skopje'),
                                      ('Skopje - Ohrid', 'Skopje to Ohrid'),
                                      ('Ohrid - Skopje', 'Ohrid to Skopje'),
                                      ('Skopje - Bitola', 'Skopje to Bitola'),
                                      ('Bitola - Skopje', 'Bitola to Skopje'),
                                      ('Skopje - Kumanovo', 'Skopje to Kumanovo'),
                                      ('Kumanovo - Skopje', 'Kumanovo to Skopje'),
                                      ('Skopje - Strumica', 'Skopje to Strumica'),
                                      ('Strumica - Skopje', 'Strumica to Skopje'),
                                      ('Skopje - Veles', 'Skopje to Veles'),
                                      ('Veles - Skopje', 'Veles to Skopje'),
                                      ('Skopje - Prilep', 'Skopje to Prilep'),
                                      ('Prilep - Skopje', 'Prilep to Skopje'),
                                      ('Skopje - Gostivar', 'Skopje to Gostivar'),
                                      ('Gostivar - Skopje', 'Gostivar to Skopje'),
                                      ('Skopje - Stip', 'Skopje to Stip'),
                                      ('Stip - Skopje', 'Stip to Skopje'),
                                      ('Skopje - Kavadarci', 'Skopje to Kavadarci'),
                                      ('Kavadarci - Skopje', 'Kavadarci to Skopje');

-- Line 1: Skopje - Tetovo
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (1, 1), -- Skopje to Tetovo
                                                     (2, 1); -- Tetovo to Skopje

-- Line 2: Skopje - Ohrid
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (3, 2), -- Skopje to Ohrid
                                                     (4, 2); -- Ohrid to Skopje

-- Line 3: Skopje - Bitola
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (5, 3), -- Skopje to Bitola
                                                     (6, 3); -- Bitola to Skopje

-- Line 4: Skopje - Kumanovo
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (7, 4), -- Skopje to Kumanovo
                                                     (8, 4); -- Kumanovo to Skopje

-- Line 5: Skopje - Strumica
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (9, 5), -- Skopje to Strumica
                                                     (10, 5); -- Strumica to Skopje

-- Line 6: Skopje - Veles
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (11, 6), -- Skopje to Veles
                                                     (12, 6); -- Veles to Skopje

-- Line 7: Skopje - Prilep
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (13, 7), -- Skopje to Prilep
                                                     (14, 7); -- Prilep to Skopje

-- Line 8: Skopje - Gostivar
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (15, 8), -- Skopje to Gostivar
                                                     (16, 8); -- Gostivar to Skopje

-- Line 9: Skopje - Stip
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (17, 9), -- Skopje to Stip
                                                     (18, 9); -- Stip to Skopje

-- Line 10: Skopje - Kavadarci
INSERT INTO linija_pravec (pravec_id, linija_id) VALUES
                                                     (19, 10), -- Skopje to Kavadarci
                                                     (20, 10); -- Kavadarci to Skopje

INSERT INTO Instanca_Na_Linija (linija_id, avtobus_id, start_date, end_date, vozac_id)
VALUES
-- Line 1: Skopje - Tetovo
(1, 1, '2024-06-24 08:00:00', '2024-06-24 09:00:00', 1),
(1, 1, '2024-06-24 09:00:00', '2024-06-24 10:00:00', 1),
(1, 1, '2024-06-24 10:00:00', '2024-06-24 11:00:00', 1),
(1, 1, '2024-06-24 11:00:00', '2024-06-24 12:00:00', 1),
(1, 1, '2024-06-24 12:00:00', '2024-06-24 13:00:00', 1);


-- Insert stops from Skopje to Tetovo
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.9981, 21.4254, 'Skopje', 'Capital city of North Macedonia'),
                                               (41.9643, 21.6228, 'Gazi Baba', 'Municipality near Skopje'),
                                               (42.0264, 21.5957, 'Tetovo', 'City in western North Macedonia');

-- Insert stops from Tetovo to Skopje
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (42.0264, 21.5957, 'Tetovo', 'City in western North Macedonia'),
                                               (41.9643, 21.6228, 'Gazi Baba', 'Municipality near Skopje'),
                                               (41.9981, 21.4254, 'Skopje', 'Capital city of North Macedonia');

-- Additional stops for Skopje to Tetovo
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.9808, 21.4631, 'Butel', 'Municipality in Skopje'),
                                               (42.0035, 21.4701, 'Kisela Voda', 'Municipality in Skopje'),
                                               (41.9866, 21.5666, 'Gostivar', 'City in western North Macedonia'),
                                               (41.9764, 21.6060, 'Zelino', 'Village near Tetovo'),
                                               (41.9939, 21.6481, 'Tetovo Bus Station', 'Central bus station in Tetovo'),
                                               (41.9861, 21.5705, 'Gostivar Train Station', 'Train station in Gostivar'),
                                               (42.0081, 21.4500, 'Skopje University', 'University campus in Skopje'),
                                               (42.0070, 21.4421, 'Kapishtec', 'Neighborhood in Skopje'),
                                               (41.9794, 21.4604, 'Skopje City Mall', 'Shopping mall in Skopje');

-- Additional stops for Tetovo to Skopje
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.9839, 21.4647, 'Butel', 'Municipality in Skopje'),
                                               (41.9934, 21.4752, 'Kisela Voda', 'Municipality in Skopje'),
                                               (41.9901, 21.5804, 'Gostivar', 'City in western North Macedonia'),
                                               (41.9798, 21.6093, 'Zelino', 'Village near Tetovo'),
                                               (41.9997, 21.6543, 'Tetovo Bus Station', 'Central bus station in Tetovo'),
                                               (41.9930, 21.5741, 'Gostivar Train Station', 'Train station in Gostivar'),
                                               (42.0132, 21.4578, 'Skopje University', 'University campus in Skopje'),
                                               (42.0111, 21.4491, 'Kapishtec', 'Neighborhood in Skopje'),
                                               (41.9822, 21.4679, 'Skopje City Mall', 'Shopping mall in Skopje');

-- Insert stops for Line 1 (Skopje - Tetovo)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                        (1, 1, 1, 1),   -- Skopje
                                                                        (1, 2, 2, 1),   -- Gazi Baba
                                                                        (1, 8, 3, 1),   -- Skopje University
                                                                        (1, 7, 4, 1),   -- Kapishtec
                                                                        (1, 3, 5, 1),   -- Tetovo
                                                                        (1, 6, 6, 1),   -- Gostivar Train Station
                                                                        (1, 5, 7, 1),   -- Gostivar
                                                                        (1, 4, 8, 1),   -- Zelino
                                                                        (1, 9, 9, 1),   -- Tetovo Bus Station
                                                                        (1, 10, 10, 1); -- Butel

-- Insert stops for Line 1 (Tetovo to Skopje)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                        (1, 10, 1, 2),   -- Butel
                                                                        (1, 9, 2, 2),    -- Tetovo Bus Station
                                                                        (1, 4, 3, 2),    -- Zelino
                                                                        (1, 5, 4, 2),    -- Gostivar
                                                                        (1, 6, 5, 2),    -- Gostivar Train Station
                                                                        (1, 3, 6, 2),    -- Tetovo
                                                                        (1, 7, 7, 2),    -- Kapishtec
                                                                        (1, 8, 8, 2),    -- Skopje University
                                                                        (1, 2, 9, 2),    -- Gazi Baba
                                                                        (1, 1, 10, 2);   -- Skopje


-- Insert ticket types
INSERT INTO TipBilet (cena, trajnost, ime) VALUES
                                               (50.00, 3600000, 'Hourly'),    -- Hourly ticket (1 hour = 3,600,000 milliseconds)
                                               (150.00, 86400000, 'Daily'),   -- Daily ticket (1 day = 86,400,000 milliseconds)
                                               (1000.00, 2592000000, 'Monthly'),  -- Monthly ticket (30 days = 2,592,000,000 milliseconds)
                                               (10000.00, 31536000000, 'Yearly'); -- Yearly ticket (365 days = 31,536,000,000 milliseconds)

-- Add tickets for commuter with patnik_id 1
INSERT INTO Bilet (datum_kupuvanje, patnik_id, tip_id, status) VALUES
                                                                   ('2024-06-24 10:00:00', 1, 1, 'INACTIVE'),  -- Hourly ticket (ID 1)
                                                                   ('2024-06-23 15:00:00', 1, 2, 'ACTIVE');    -- Daily ticket (ID 2)

