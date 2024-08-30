set search_path = project;

-- Insert stops from Skopje to Ohrid
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.9981, 21.4254, 'Skopje', 'Capital city of North Macedonia'),
                                               (41.8770, 21.0720, 'Tetovo', 'City in western North Macedonia'),
                                               (41.7150, 20.9620, 'Gostivar', 'City in western North Macedonia'),
                                               (41.5886, 20.6305, 'Kicevo', 'City in western North Macedonia'),
                                               (41.5120, 20.2690, 'Struga', 'City in southwestern North Macedonia'),
                                               (41.1136, 20.8016, 'Ohrid', 'City in southwestern North Macedonia');
-- Insert stops for Line 2 (Skopje - Ohrid)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                                   (2, 25, 1, 3),  -- Skopje
                                                                                   (2, 26, 2, 3),  -- Tetovo
                                                                                   (2, 27, 3, 3),  -- Gostivar
                                                                                   (2, 28, 4, 3),  -- Kicevo
                                                                                   (2, 29, 5, 3),  -- Struga
                                                                                   (2, 30, 6, 3);  -- Ohrid

-- Insert stops from Ohrid to Skopje
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.1136, 20.8016, 'Ohrid', 'City in southwestern North Macedonia'),
                                               (41.5910, 20.6520, 'Debarca', 'Municipality near Ohrid'),
                                               (41.6340, 20.8940, 'Makedonski Brod', 'Town in central North Macedonia'),
                                               (41.6980, 21.1290, 'Samokov', 'Village in western North Macedonia'),
                                               (41.7820, 21.3340, 'Tetovo', 'City in western North Macedonia'),
                                               (41.9981, 21.4254, 'Skopje', 'Capital city of North Macedonia');
-- Insert stops for Line 2 (Ohrid - Skopje)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                                   (2, 31, 1, 4),  -- Ohrid
                                                                                   (2, 32, 2, 4),  -- Debarca
                                                                                   (2, 33, 3, 4),  -- Makedonski Brod
                                                                                   (2, 34, 4, 4),  -- Samokov
                                                                                   (2, 35, 5, 4),  -- Tetovo
                                                                                   (2, 36, 6, 4);  -- Skopje
-- Insert stops from Skopje to Bitola
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.9981, 21.4254, 'Skopje', 'Capital city of North Macedonia'),    -- id 37
                                               (41.9202, 21.5511, 'Veles', 'City in central North Macedonia'),     -- id 38
                                               (41.7361, 21.7744, 'Prilep', 'City in southern North Macedonia'),   -- id 39
                                               (41.6130, 21.5523, 'Krushevo', 'Town in southern North Macedonia'), -- id 40
                                               (41.4442, 21.2761, 'Bitola', 'City in southwestern North Macedonia'); -- id 41
-- Insert stops for Line 3 (Skopje - Bitola)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                                   (3, 37, 1, 5),  -- Skopje
                                                                                   (3, 38, 2, 5),  -- Veles
                                                                                   (3, 39, 3, 5),  -- Prilep
                                                                                   (3, 40, 4, 5),  -- Krushevo
                                                                                   (3, 41, 5, 5);  -- Bitola
-- Insert stops from Bitola to Skopje
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.4442, 21.2761, 'Bitola', 'City in southwestern North Macedonia'), -- id 42
                                               (41.6130, 21.5523, 'Krushevo', 'Town in southern North Macedonia'),   -- id 43
                                               (41.7361, 21.7744, 'Prilep', 'City in southern North Macedonia'),     -- id 44
                                               (41.9202, 21.5511, 'Veles', 'City in central North Macedonia'),       -- id 45
                                               (41.9981, 21.4254, 'Skopje', 'Capital city of North Macedonia');      -- id 46
-- Insert stops for Line 3 (Bitola - Skopje)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                                   (3, 42, 1, 6),  -- Bitola
                                                                                   (3, 43, 2, 6),  -- Krushevo
                                                                                   (3, 44, 3, 6),  -- Prilep
                                                                                   (3, 45, 4, 6),  -- Veles
                                                                                   (3, 46, 5, 6);  -- Skopje

-- Insert stops from Skopje to Kumanovo
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.9981, 21.4254, 'Skopje', 'Capital city of North Macedonia'),       -- id 47
                                               (41.9945, 21.5770, 'Butel', 'Municipality in Skopje'),                 -- id 48
                                               (42.0153, 21.7000, 'Aracinovo', 'Village near Skopje'),                -- id 49
                                               (42.0892, 21.7184, 'Lipkovo', 'Village in northeastern North Macedonia'), -- id 50
                                               (42.1322, 21.7144, 'Kumanovo', 'City in northeastern North Macedonia'); -- id 51

-- Insert stops for Line 4 (Skopje - Kumanovo)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                                   (4, 47, 1, 7),  -- Skopje
                                                                                   (4, 48, 2, 7),  -- Butel
                                                                                   (4, 49, 3, 7),  -- Aracinovo
                                                                                   (4, 50, 4, 7),  -- Lipkovo
                                                                                   (4, 51, 5, 7);  -- Kumanovo
-- Insert stops from Kumanovo to Skopje
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (42.1322, 21.7144, 'Kumanovo', 'City in northeastern North Macedonia'), -- id 52
                                               (42.0892, 21.7184, 'Lipkovo', 'Village in northeastern North Macedonia'), -- id 53
                                               (42.0153, 21.7000, 'Aracinovo', 'Village near Skopje'),                -- id 54
                                               (41.9945, 21.5770, 'Butel', 'Municipality in Skopje'),                 -- id 55
                                               (41.9981, 21.4254, 'Skopje', 'Capital city of North Macedonia');       -- id 56
-- Insert stops for Line 4 (Kumanovo - Skopje)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                                   (4, 52, 1, 8),  -- Kumanovo
                                                                                   (4, 53, 2, 8),  -- Lipkovo
                                                                                   (4, 54, 3, 8),  -- Aracinovo
                                                                                   (4, 55, 4, 8),  -- Butel
                                                                                   (4, 56, 5, 8);  -- Skopje
-- Insert stops from Skopje to Strumica
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.9981, 21.4254, 'Skopje', 'Capital city of North Macedonia'),               -- id 57
                                               (41.8833, 21.5065, 'Katlanovo', 'Village near Skopje'),                        -- id 58
                                               (41.7466, 21.3600, 'Veles Rest Area', 'Rest area near Veles'),                 -- id 59
                                               (41.6165, 22.1796, 'Demir Kapija', 'Town in central North Macedonia'),         -- id 60
                                               (41.3472, 22.5533, 'Radovish', 'Town in southeastern North Macedonia'),        -- id 61
                                               (41.4378, 22.6431, 'Strumica', 'City in southeastern North Macedonia');        -- id 62
-- Insert stops for Line 5 (Skopje - Strumica)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                                   (5, 57, 1, 9),  -- Skopje
                                                                                   (5, 58, 2, 9),  -- Katlanovo
                                                                                   (5, 59, 3, 9),  -- Veles Rest Area
                                                                                   (5, 60, 4, 9),  -- Demir Kapija
                                                                                   (5, 61, 5, 9),  -- Radovish
                                                                                   (5, 62, 6, 9);  -- Strumica
-- Insert stops from Strumica to Skopje
INSERT INTO Postojka (lat, lon, ime, opis) VALUES
                                               (41.4380, 22.6429, 'Strumica', 'City in southeastern North Macedonia'),       -- id 63
                                               (41.3480, 22.5525, 'Radovish Park', 'Park in Radovish'),                      -- id 64
                                               (41.6170, 22.1802, 'Demir Kapija South', 'Southern part of Demir Kapija'),    -- id 65
                                               (41.7470, 21.3610, 'Veles South Rest Area', 'Rest area south of Veles'),      -- id 66
                                               (41.8840, 21.5070, 'Katlanovo East', 'Eastern part of Katlanovo'),            -- id 67
                                               (41.9985, 21.4260, 'Skopje Center', 'Center of Skopje');                      -- id 68
-- Insert stops for Line 5 (Strumica - Skopje)
INSERT INTO Postojka_Na_Linija (linija_id, postojka_id, reden_broj, pravec_id) VALUES
                                                                                   (5, 63, 1, 10),  -- Strumica
                                                                                   (5, 64, 2, 10),  -- Radovish Park
                                                                                   (5, 65, 3, 10),  -- Demir Kapija South
                                                                                   (5, 66, 4, 10),  -- Veles South Rest Area
                                                                                   (5, 67, 5, 10),  -- Katlanovo East
                                                                                   (5, 68, 6, 10);  -- Skopje Center

