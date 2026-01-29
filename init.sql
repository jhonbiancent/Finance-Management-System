DROP TABLE IF EXISTS _user;
-- Drop existing tables if they exist
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS subsidiary;
DROP TABLE IF EXISTS disbursement;
DROP TABLE IF EXISTS logistics;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS accounts_receivable;
DROP TABLE IF EXISTS controller;

-- Create new tables based on the ERD
CREATE TABLE subsidiary (
    sb_subsidiary_id SERIAL PRIMARY KEY,
    sb_subsidiary VARCHAR(255),
    sb_account_category VARCHAR(255),
    sb_po_wb_number_form_number VARCHAR(255),
    sb_client VARCHAR(255),
    sb_supplier VARCHAR(255),
    sb_billing_number VARCHAR(255),
    sb_client_id VARCHAR(255)
);

CREATE TABLE disbursement (
    ds_disbursement_id SERIAL PRIMARY KEY,
    ds_disbursement_voucher VARCHAR(255),
    ds_year INT,
    ds_month INT,
    ds_date DATE,
    ds_check_number VARCHAR(255)
);

CREATE TABLE logistics (
    lg_logistic_id SERIAL PRIMARY KEY,
    lg_fleet_type VARCHAR(255),
    lg_driver_name VARCHAR(255),
    lg_plate_num VARCHAR(255)
);

CREATE TABLE products (
    pr_products_id SERIAL PRIMARY KEY,
    pr_type VARCHAR(255),
    pr_unity_price DECIMAL(12, 2),
    pr_unit VARCHAR(255)
);

CREATE TABLE accounts_receivable (
    ar_id SERIAL PRIMARY KEY,
    ar_total_accounts_receivable DECIMAL(12, 2),
    ar_subtotal DECIMAL(12, 2),
    ar_collection DECIMAL(12, 2),
    ar_performance DECIMAL(12, 2),
    ar_excess_payment_from_client DECIMAL(12, 2),
    ar_write_off DECIMAL(12, 2)
);

CREATE TABLE controller (
    cr_id SERIAL PRIMARY KEY,
    cr_date_approved DATE
);

CREATE TABLE transactions (
    ts_id SERIAL PRIMARY KEY,
    sb_subsidiary_id INT REFERENCES subsidiary(sb_subsidiary_id),
    ds_disbursement_id INT REFERENCES disbursement(ds_disbursement_id),
    lg_logistic_id INT REFERENCES logistics(lg_logistic_id),
    pr_products_id INT REFERENCES products(pr_products_id),
    ar_id INT REFERENCES accounts_receivable(ar_id),
    cr_id INT REFERENCES controller(cr_id),
    ts_check_number VARCHAR(255),
    ts_check_date DATE,
    ts_status VARCHAR(255),
    ts_balance DECIMAL(12, 2),
    ts_due_date DATE,
    ts_terms_of_days INT,
    ts_mode VARCHAR(255),
    ts_type VARCHAR(255),
    ts_quantity INT,
    ts_fuel_purchase DECIMAL(12, 2),
    ts_invoice_billing_number VARCHAR(255),
    ts_freight_amount DECIMAL(12, 2),
    ts_freight_billing DECIMAL(12, 2),
    ts_commission_name VARCHAR(255),
    ts_commission_amount DECIMAL(12, 2),
    ts_commission_on_billing DECIMAL(12, 2),
    ts_payments DECIMAL(12, 2),
    ts_remarks TEXT,
    ts_backtrack VARCHAR(255),
    ts_status2 VARCHAR(255),
    ts_status3 VARCHAR(255),
    ts_group VARCHAR(255),
    ts_revenue_category VARCHAR(255),
    ts_billing_number VARCHAR(255),
    ts_description TEXT,
    ts_bmmi_customer VARCHAR(255),
    ts_tin VARCHAR(255),
    ts_vat_nonvat DECIMAL(12, 2),
    ts_vat DECIMAL(12, 2),
    ts_address TEXT,
    ts_gross_sales DECIMAL(12, 2),
    ts_discount DECIMAL(12, 2),
    ts_net_sales DECIMAL(12, 2),
    ts_sales_vat_exclusive DECIMAL(12, 2),
    ts_ewt DECIMAL(12, 2),
    ts_service_charge DECIMAL(12, 2),
    ts_stripping_fee DECIMAL(12, 2),
    ts_return DECIMAL(12, 2),
    ts_interest_income DECIMAL(12, 2),
    ts_balance2 DECIMAL(12, 2),
    ts_particulars TEXT,
    ts_bank VARCHAR(255),
    ts_from_bank_sub VARCHAR(255),
    ts_to_bank_sub VARCHAR(255),
    ts_amount DECIMAL(12, 2)
);

CREATE TABLE _user (
    id INT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(255),
    CONSTRAINT _user_role_check CHECK (role IN ('ROLE_FINANCE_VIEWER', 'ROLE_FINANCE_EDITOR', 'ROLE_FINANCE_MANAGER', 'ROLE_FINANCE_ADMIN', 'ROLE_FINANCE_AUDITOR', 'ROLE_FINANCE_CONTROLLER'))
);

-- Insert dummy data for transactions
INSERT INTO transactions (ts_check_number, ts_check_date, ts_status, ts_balance, ts_due_date, ts_terms_of_days, ts_mode, ts_type, ts_quantity, ts_fuel_purchase, ts_invoice_billing_number, ts_freight_amount, ts_freight_billing, ts_commission_name, ts_commission_amount, ts_commission_on_billing, ts_payments, ts_remarks, ts_backtrack, ts_status2, ts_status3, ts_group, ts_revenue_category, ts_billing_number, ts_description, ts_bmmi_customer, ts_tin, ts_vat_nonvat, ts_vat, ts_address, ts_gross_sales, ts_discount, ts_net_sales, ts_sales_vat_exclusive, ts_ewt, ts_service_charge, ts_stripping_fee, ts_return, ts_interest_income, ts_balance2, ts_particulars, ts_bank, ts_from_bank_sub, ts_to_bank_sub, ts_amount)
VALUES
('12345', '2024-01-15', 'PAID', 1000.00, '2024-02-15', 30, 'CHECK', 'EXPENSE', 10, 50.00, 'INV-001', 100.00, 110.00, 'John Doe', 10.00, 11.00, 500.00, 'Payment for office supplies', 'N/A', 'CLEARED', 'N/A', 'EXP-001', 'OFFICE', 'BILL-001', 'Office supplies for January', 'BMMI', '123-456-789', 120.00, 12.00, '123 Main St, Anytown, USA', 1200.00, 200.00, 1000.00, 900.00, 10.00, 5.00, 2.00, 0.00, 0.00, 500.00, 'Office supplies', 'Bank of America', 'BofA-123', 'BofA-456', 1000.00),
('67890', '2024-01-20', 'PENDING', 2500.00, '2024-02-20', 30, 'WIRE', 'INCOME', 1, 0.00, 'INV-002', 0.00, 0.00, 'N/A', 0.00, 0.00, 2500.00, 'Payment for services rendered', 'N/A', 'PENDING', 'N/A', 'INC-001', 'SERVICES', 'BILL-002', 'Web development services', 'ACME Corp', '987-654-321', 300.00, 30.00, '456 Oak St, Anytown, USA', 3000.00, 500.00, 2500.00, 2200.00, 25.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'Web development', 'Chase', 'Chase-123', 'Chase-456', 2500.00);