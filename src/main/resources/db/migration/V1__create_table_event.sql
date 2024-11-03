    CREATE TABLE event (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    start_time DATETIME,
    end_time DATETIME,
    ticket_price DECIMAL(10, 2),
    available_tickets INT
);
