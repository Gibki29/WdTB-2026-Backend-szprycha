-- Dane startowe aplikacji. Tabele tworzy Hibernate z encji, a ten plik Spring Boot uruchamia zaraz potem.
-- LAB 01: dopiszcie tu konta wszystkich osób z zespołu (reguła R1.03).
-- LAB 02: dopiszcie dane do swoich tabel (kolejność: najpierw tabele, na które wskazują klucze obce).
insert into user_account (email, display_name, role, created_at) values
    ('wrx82042@student.wroclaw.merito.pl',   'Kacper',   'USER',    current_timestamp),
    ('wrx82050@student.wroclaw.merito.pl',  'Bartosz',  'USER',    current_timestamp),
    ('wrx82032@student.wroclaw.merito.pl',   'Alan',   'USER',    current_timestamp),
    ('firma@example.com',       'Firma', 'PARTNER', current_timestamp),
    ('admin@example.com',       'Admin', 'ADMIN',   current_timestamp);

-- LAB 01: Wasze konta (zmieńcie na swoje dane, rola USER)
-- insert into user_account (email, display_name, role, created_at) values
--     ('imie.nazwisko@student.merito.pl', 'Imię', 'USER', current_timestamp);

-- ===== LAB 02 · repair_order =====
insert into repair_order (customer_name, customer_phone, problem_description, status, received_at, estimated_cost, bike_brand, bike_model, frame_number) values
    ('Marek Zieliński',  '601234567', 'Piszczą hamulce tarczowe, słaba siła hamowania', 'DIAGNOSED',   '2026-10-20 09:15:00', 140.00, 'Kross',       'Level 5.0',    'WTU123456789'),
    ('Katarzyna Lis',    '602345678', 'Przeskakuje łańcuch, przegląd przed zimą',       'IN_PROGRESS', '2026-10-21 11:40:00', 260.00, 'Trek',        'Marlin 7',     'WTU987654321'),
    ('Piotr Wójcik',     '603456789', 'Scentrowane koło tylne, wymiana dętki',          'RECEIVED',    '2026-10-24 16:05:00', null,   'Romet',       'Orkan 2',      null),
    ('Agnieszka Kamińska','604567890','Wyciek oleju z amortyzatora',                    'READY',       '2026-10-15 10:30:00', 350.00, 'Specialized', 'Rockhopper',   'WSBC604512345');

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
