-- Insert person
INSERT INTO persons (id, first_name, last_name, birth_date)
VALUES ('262d8a02-d633-4635-8b0c-6b1dbe577a32', 'Admin', 'User', '1980-12-13');

-- Insert user
INSERT INTO users (id, email, password, person_id)
VALUES ('d02f54b1-027e-4607-be3e-ba0e4a7aca49', 'admin@fake.com', '$2a$10$Od.H.0QMSqhbLXUYYKlZ0e6Hkv2cGzYHb5Q2uZrCneMJUpflD7te2', '262d8a02-d633-4635-8b0c-6b1dbe577a32');

-- Insert user roles
INSERT INTO users_roles (user_id, role_id)
VALUES ('d02f54b1-027e-4607-be3e-ba0e4a7aca49', '7d21a9af-64d2-4e2e-b21f-96ee4323dc37');
INSERT INTO users_roles (user_id, role_id)
VALUES ('d02f54b1-027e-4607-be3e-ba0e4a7aca49', '08f93b36-ebde-4d93-8d9f-354acad094ae');