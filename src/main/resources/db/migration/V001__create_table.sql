CREATE TABLE rating(
  id SERIAL PRIMARY KEY,
  type VARCHAR(20),
  value_date DATE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO rating(type, value_date) values ('BOND', '2020-02-22'), ('ETF', '2020-02-25')