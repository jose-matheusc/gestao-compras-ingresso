CREATE TABLE event (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    start_time DATETIME,
    end_time DATETIME,
    ticket_price DECIMAL(10, 2),
    user_id BIGINT NOT NULL
);
