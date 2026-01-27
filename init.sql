CREATE TABLE transactions (
                              id SERIAL PRIMARY KEY,
                              or_number VARCHAR(50) UNIQUE,  -- Official Receipt Number
                              description VARCHAR(255) NOT NULL,
                              amount DECIMAL(12, 2) NOT NULL, -- 12 digits, 2 decimals
                              category VARCHAR(50),           -- e.g., 'Travel', 'Supplies', 'Salary'
                              transaction_date DATE NOT NULL,
                              type VARCHAR(20) NOT NULL,      -- 'INCOME' or 'EXPENSE'
                              status VARCHAR(20) DEFAULT 'PENDING', -- 'PENDING', 'APPROVED'
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed some dummy data for Frontend to see
INSERT INTO transactions (or_number, description, amount, category, transaction_date, type, status)
VALUES
    ('OR-001', 'Initial Budget Injection', 50000.00, 'Funding', '2026-01-20', 'INCOME', 'APPROVED'),
    ('OR-002', 'Office Supplies - Paper', 1500.50, 'Supplies', '2026-01-22', 'EXPENSE', 'APPROVED'),
    ('OR-003', 'Staff Snacks', 500.00, 'Food', '2026-01-25', 'EXPENSE', 'PENDING');