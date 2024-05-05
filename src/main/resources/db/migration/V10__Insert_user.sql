-- Insert person
INSERT INTO persons (id, first_name, last_name, birth_date)
VALUES ('08221a4f-bd73-43b7-be45-6a287f0e008e', 'Common', 'User', '1982-05-23');

-- Insert user
INSERT INTO users (id, email, password, person_id)
VALUES ('e2a47fab-ecb8-457c-89fa-5fb501d974b1', 'user@fake.com', '$2a$10$YGGDShq1oEi0ZVF6bFGnTenlpRbsp/9XD9/CDiHZ9Ia53e8B0g166', '08221a4f-bd73-43b7-be45-6a287f0e008e');

-- Insert user role
INSERT INTO users_roles (user_id, role_id)
VALUES ('e2a47fab-ecb8-457c-89fa-5fb501d974b1', '7d21a9af-64d2-4e2e-b21f-96ee4323dc37');