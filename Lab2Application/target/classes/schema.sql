CREATE TABLE IF NOT EXISTS pizzeria (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS pizza (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    size VARCHAR(50) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    pizzeria_id BIGINT,
    FOREIGN KEY (pizzeria_id) REFERENCES pizzeria(id)
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    pizza_id BIGINT NOT NULL,
    pizzeria_id BIGINT NOT NULL,
    FOREIGN KEY (pizza_id) REFERENCES pizza(id),
    FOREIGN KEY (pizzeria_id) REFERENCES pizzeria(id)
);

INSERT INTO pizzeria (name, address) VALUES
('Pizzeria Napoli', 'Main Street 1'),
('Mama Pizza', 'Central Avenue 42');

INSERT INTO pizza (name, size, price, pizzeria_id) VALUES
('Margherita', 'Medium', 120.0, 1),
('Pepperoni', 'Large', 160.0, 2);