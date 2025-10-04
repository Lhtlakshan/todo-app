-- Create the table
CREATE TABLE task (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    is_done BOOLEAN NOT NULL DEFAULT FALSE
);

-- sample data
INSERT INTO task (title, description, is_done) VALUES
('Meditation', 'Today I want to do meditation at least 10 min', false),
('Morning Exercise', 'Do a 30-minute workout session to stay healthy', false),
('Build Spring Boot App', 'Implement a simple Todo application using Spring Boot and React', true),
('Bug fixing', 'Want to some bugs of a sample application', false),
('Playing tt', 'Want to play table tennis', false);
