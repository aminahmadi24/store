CREATE TABLE IF NOT EXISTS categories(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS products(
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	price BIGINT NOT NULL,
	category_id INT NOT NULL,
	CONSTRAINT products_categories_category_id_fk FOREIGN KEY(category_id) REFERENCES categories(id)
);
