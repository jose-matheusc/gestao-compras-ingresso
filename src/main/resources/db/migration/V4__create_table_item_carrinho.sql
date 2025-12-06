CREATE TABLE item_carrinho (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    CONSTRAINT fk_item_carrinho_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);

