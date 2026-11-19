-- Dane startowe aplikacji. Tabele tworzy Hibernate z encji, a ten plik Spring Boot uruchamia zaraz potem.
-- LAB 01: dopiszcie tu konta wszystkich osób z zespołu (reguła R1.03).
-- LAB 02: dopiszcie dane do swoich tabel (kolejność: najpierw tabele, na które wskazują klucze obce).
insert into user_account (email, display_name, role, created_at) values
    ('wrx82042@student.wroclaw.merito.pl',   'Kacper',   'USER',    current_timestamp),
    ('wrx82050@student.wroclaw.merito.pl',  'Bartosz',  'USER',    current_timestamp),
    ('wrx82032@student.wroclaw.merito.pl',   'Alan',   'USER',    current_timestamp),
    ('firma@example.com',       'Firma', 'PARTNER', current_timestamp),
    ('admin@example.com',       'Admin', 'ADMIN',   current_timestamp),
    -- LAB 03: konta klientów serwisu (6 = Marek, 7 = Katarzyna), wskazywane z tabeli customer
    ('marek.zielinski@example.com', 'Marek',     'USER', current_timestamp),
    ('katarzyna.lis@example.com',   'Katarzyna', 'USER', current_timestamp);

-- LAB 01: Wasze konta (zmieńcie na swoje dane, rola USER)
-- insert into user_account (email, display_name, role, created_at) values
--     ('imie.nazwisko@student.merito.pl', 'Imię', 'USER', current_timestamp);

-- ===== LAB 03 · customer =====
-- user_account_id: konto klienta z sekcji user_account (6 = Marek, 7 = Katarzyna); null = klient bez konta
insert into customer (full_name, phone, email, registered_on, user_account_id) values
    ('Marek Zieliński',    '601234567', 'marek.zielinski@example.com', '2026-10-20', 6),
    ('Katarzyna Lis',      '602345678', 'katarzyna.lis@example.com',   '2026-03-12', 7),
    ('Piotr Wójcik',       '603456789', null,                          '2026-10-24', null),
    ('Agnieszka Kamińska', '604567890', null,                          '2025-06-02', null);

-- ===== LAB 02 · repair_order =====
-- customer_id: klient z sekcji customer (1 = Marek, 2 = Katarzyna, 3 = Piotr, 4 = Agnieszka)
-- estimated_cost: suma usług z repair_task i części z used_part danego zgłoszenia
insert into repair_order (customer_id, problem_description, status, received_at, estimated_cost, bike_brand, bike_model, frame_number) values
    (1, 'Piszczą hamulce tarczowe, słaba siła hamowania', 'DIAGNOSED',   '2026-10-20 09:15:00', 175.00, 'Kross',       'Level 5.0',    'WTU123456789'),
    (2, 'Przeskakuje łańcuch, przegląd przed zimą',       'IN_PROGRESS', '2026-10-21 11:40:00', 343.90, 'Trek',        'Marlin 7',     'WTU987654321'),
    (3, 'Scentrowane koło tylne, wymiana dętki',          'RECEIVED',    '2026-10-24 16:05:00', null,   'Romet',       'Orkan 2',      null),
    (4, 'Wyciek oleju z amortyzatora',                    'READY',       '2026-10-15 10:30:00', 180.00, 'Specialized', 'Rockhopper',   'WSBC604512345');

-- ===== LAB 02 · service_item =====
insert into service_item (name, category, price, estimated_minutes, active) values
    ('Regulacja hamulców',            'BRAKES',     40.00,  30, true),
    ('Odpowietrzenie hamulców',       'BRAKES',     90.00,  60, true),
    ('Wymiana łańcucha',              'DRIVETRAIN', 35.00,  20, true),
    ('Centrowanie koła',              'WHEELS',     50.00,  40, true),
    ('Serwis amortyzatora',           'SUSPENSION', 180.00, 120, true),
    ('Przegląd podstawowy',           'GENERAL',    120.00, 90, false);

-- ===== LAB 02 · spare_part =====
insert into spare_part (name, manufacturer, sku, unit_price, stock_quantity, active) values
    ('Klocki hamulcowe żywiczne',     'Shimano', 'SH-B01S',      45.00, 12, true),
    ('Łańcuch 10-rzędowy',            'KMC',     'KMC-X10',      89.90,  6, true),
    ('Dętka 29x2.2',                  'Schwalbe','SCH-DV19',     32.50, 20, true),
    ('Kaseta 11-42T',                 'SRAM',    'SRAM-PG1130', 219.00,  2, true),
    ('Linka przerzutki',              null,      'GEN-LP-2100',  12.00,  0, false);

-- ===== LAB 03 · repair_task =====
-- usługi w zgłoszeniu: repair_order_id z sekcji repair_order, service_item_id z sekcji service_item
-- zgłoszenie 3 (status RECEIVED) nie ma jeszcze diagnozy, więc nie ma usług
insert into repair_task (repair_order_id, service_item_id, price, done) values
    (1, 2,  90.00, false),      -- Marek: odpowietrzenie hamulców
    (1, 1,  40.00, false),      -- Marek: regulacja hamulców
    (2, 3,  35.00, true),       -- Katarzyna: wymiana łańcucha
    (4, 5, 180.00, true);       -- Agnieszka: serwis amortyzatora

-- ===== LAB 03 · used_part =====
-- części w zgłoszeniu: repair_order_id z sekcji repair_order, spare_part_id z sekcji spare_part
insert into used_part (repair_order_id, spare_part_id, quantity, unit_price) values
    (1, 1, 1,  45.00),          -- Marek: klocki hamulcowe
    (2, 2, 1,  89.90),          -- Katarzyna: łańcuch 10-rzędowy
    (2, 4, 1, 219.00);          -- Katarzyna: kaseta 11-42T
