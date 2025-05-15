CREATE TABLE user_data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);
INSERT INTO user_data (name, email) VALUES ('testuser1', 'testuser1@example.com');
INSERT INTO user_data (name, email) VALUES ('testuser2', 'testuser2@example.com');