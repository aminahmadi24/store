CREATE TABLE IF NOT EXISTS carts(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	created_at DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS cart_items(
	id BIGSERIAL PRIMARY KEY,
	cart_id UUID NOT NULL REFERENCES carts(id),
	product_id BIGINT NOT NULL REFERENCES products(id),
	quantity INT NOT NULL DEFAULT 1,
	CONSTRAINT cart_items_cart_product_unique UNIQUE(cart_id, product_id)
);