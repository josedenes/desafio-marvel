INSERT INTO tb_usuario (nome, email, cpf, data_nascimento ) VALUES ('jose', 'jose@gmail.com', '038.625.523-73', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z');
INSERT INTO tb_usuario (nome, email, cpf, data_nascimento ) VALUES ('joao', 'joao@gmail.com', '156.565.513-34', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z');
INSERT INTO tb_usuario (nome, email, cpf, data_nascimento ) VALUES ('pedro', 'pedro@gmail.com', '000.000.000-00', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z');

INSERT INTO tb_comic (titulo, preco, autores, isbn, descricao, aplica_desconto ) VALUES ('aranha', 10.0, 'marvel', '1234', 'descricao completa do quadrinho', true);
INSERT INTO tb_comic (titulo, preco, autores, isbn, descricao, aplica_desconto ) VALUES ('hulk', 20.0, 'dc', '1234789', 'descricao resumida do quadrinho', false);