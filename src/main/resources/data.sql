/* Populando CATEGORIA */
INSERT INTO CATEGORIA (ID, NOME) VALUES (1, 'Informática');
INSERT INTO CATEGORIA (ID, NOME) VALUES (2, 'Escritório');
/* Populando PRODUTO */
INSERT INTO PRODUTO (ID, NOME, PRECO) VALUES (1, 'Computador', 2000.00);
INSERT INTO PRODUTO (ID, NOME, PRECO) VALUES (2, 'Impressora', 800.00);
INSERT INTO PRODUTO (ID, NOME, PRECO) VALUES (3, 'Mouse', 80.00);
/* Populando PRODUTO_CATEGORIA */
INSERT INTO PRODUTO_CATEGORIA  (PRODUTO_ID, CATEGORIA_ID) VALUES (1, 1);
INSERT INTO PRODUTO_CATEGORIA  (PRODUTO_ID, CATEGORIA_ID) VALUES (2, 1);
INSERT INTO PRODUTO_CATEGORIA  (PRODUTO_ID, CATEGORIA_ID) VALUES (3, 1);
INSERT INTO PRODUTO_CATEGORIA  (PRODUTO_ID, CATEGORIA_ID) VALUES (2, 2);
