DROP TABLE IF EXISTS product;

CREATE TABLE product (
    product_id UUID PRIMARY KEY,
    code VARCHAR(255),
    name VARCHAR(255),
    description TEXT,
    image VARCHAR(512),
    category VARCHAR(255),
    price NUMERIC(19, 2),
    quantity INTEGER,
    internal_reference VARCHAR(255),
    shell_id BIGINT,
    inventory_status VARCHAR(20),
    rating BIGINT,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE
);

INSERT INTO product (
  product_id, code, name, description, image, category, price,
  quantity, internal_reference, shell_id, inventory_status,
  rating, created_at, updated_at
) VALUES
(
  'ed0f83b8-baba-47b4-83fa-b5a19f6076cf', 'PRD-001', 'Wireless Mouse',
  'Ergonomic wireless mouse with USB receiver.', 'https://example.com/images/mouse.jpg',
  'Electronics', 29.99, 150, 'INT-REF-001', 101, 'INSTOCK', 4,
  '2025-04-01T10:15:00+01:00', '2025-04-10T09:30:00+01:00'
),
(
  'bd0f83b8-baba-47b4-83fa-b5a19f6076c2', 'PRD-002', 'Mechanical Keyboard',
  'RGB backlit mechanical keyboard with blue switches.', 'https://example.com/images/keyboard.jpg',
  'Electronics', 79.50, 35, 'INT-REF-002', 102, 'LOWSTOCK', 5,
  '2025-03-15T14:45:00+01:00', '2025-04-11T11:00:00+01:00'
),
(
  'fd0f83b8-baba-47b4-83fa-b5a19f6076d3', 'PRD-003', 'Smartphone Stand',
  'Adjustable aluminum phone holder for desk.', 'https://example.com/images/stand.jpg',
  'Accessories', 12.00, 0, 'INT-REF-003', 103, 'OUTOFSTOCK', 3,
  '2025-02-10T08:00:00+01:00', '2025-04-09T13:20:00+01:00'
);


