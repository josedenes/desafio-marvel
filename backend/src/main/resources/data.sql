INSERT INTO tb_usuario (nome, email, cpf, data_nascimento ) VALUES ('lucas', 'lucas@gmail.com', '000.010.000-00', TIMESTAMP WITH TIME ZONE '1994-12-11T20:50:07.12345Z');
INSERT INTO tb_usuario (nome, email, cpf, data_nascimento ) VALUES ('joao', 'joao@gmail.com', '002.000.000-00', TIMESTAMP WITH TIME ZONE '1992-06-15T20:50:07.12345Z');
INSERT INTO tb_usuario (nome, email, cpf, data_nascimento ) VALUES ('pedro', 'pedro@gmail.com', '000.000.000-00', TIMESTAMP WITH TIME ZONE '1995-04-05T20:50:07.12345Z');

INSERT INTO tb_comic (comic_id, titulo, preco, autores, isbn, descricao, aplica_desconto, usuario_id ) VALUES (4, 'aranha', 10.0, 'marvel', '1234', 'descricao completa do quadrinho', true, 1);
INSERT INTO tb_comic (comic_id, titulo, preco, autores, isbn, descricao, aplica_desconto, usuario_id ) VALUES (7, 'hulk', 20.0, 'dc', '1234789', 'descricao resumida do quadrinho', false, 1);