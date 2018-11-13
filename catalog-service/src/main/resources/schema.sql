CREATE TABLE catalog_details(
	item_code BIGINT PRIMARY KEY,
	item_name VARCHAR(20) UNIQUE,
	description VARCHAR(50),
	item_price FLOAT,
	inventory INT
);