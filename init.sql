CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date DATE NOT NULL,
    type VARCHAR(50),
    category VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Optional: Pre-fill some data to test with
INSERT INTO transactions (description, amount, transaction_date, type, category)
VALUES 
('Initial Test Fund', 10000.00, '2026-01-20', 'INCOME', 'Funding'),
('Office Rent', 1200.00, '2026-02-01', 'EXPENSE', 'Operations');
