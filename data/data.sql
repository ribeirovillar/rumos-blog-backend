INSERT IGNORE INTO `persons` (`birth_date`, `id`, `first_name`, `last_name`) VALUES ('1985-09-26 22:15:24.000000', UNHEX(REPLACE('f1d1658e-09c1-4a4c-84ae-6517d409aa7f', '-', '')), 'Demostenis', 'Villar');

INSERT IGNORE INTO users (id, email, password, person_id) VALUES (UNHEX(REPLACE('6c6ff46c-9102-49a4-a0dc-3ef822937621', '-', '')), 'demostenis@gmail.com', '1234', UNHEX(REPLACE('f1d1658e-09c1-4a4c-84ae-6517d409aa7f', '-', '')));
