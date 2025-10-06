-- Script de dados iniciais para o sistema LS
-- Este script será executado automaticamente pelo Hibernate

-- Inserindo tipos de acesso
INSERT INTO intTipoAcesso (codTipoAcesso, descricao, versao) VALUES (1, 'Sem Acesso', 1);
INSERT INTO intTipoAcesso (codTipoAcesso, descricao, versao) VALUES (10, 'Acesso a Unidade Gestora', 1);
INSERT INTO intTipoAcesso (codTipoAcesso, descricao, versao) VALUES (20, 'Acesso Total', 1);

-- Inserindo tipos de permissão
INSERT INTO intTipoPermissao (codTipoPermissao, descricao) VALUES (1, 'Sem Acesso');
INSERT INTO intTipoPermissao (codTipoPermissao, descricao) VALUES (2, 'Acesso Personalizado');
INSERT INTO intTipoPermissao (codTipoPermissao, descricao) VALUES (20, 'Acesso Total');

-- Inserindo usuário administrador padrão
-- Senha: admin123 (criptografada)
INSERT INTO intUsuario (nome, cpf, senha, email, telefone, tipoAcesso, tipoPermissao, versao, habilitado, fornecedorMateria, publicador) 
VALUES ('Administrador do Sistema', '00000000000', 'YWRtaW4xMjM=', 'admin@ls.com.br', '(11) 99999-9999', 20, 20, 1, true, false, false);

-- Inserindo formulários básicos
INSERT INTO intFormulario (formulario, titulo, versao) VALUES ('dashboard', 'Dashboard Principal', 1);
INSERT INTO intFormulario (formulario, titulo, versao) VALUES ('usuario', 'Gestão de Usuários', 1);
INSERT INTO intFormulario (formulario, titulo, versao) VALUES ('formulario', 'Gestão de Formulários', 1);
INSERT INTO intFormulario (formulario, titulo, versao) VALUES ('permissao', 'Gestão de Permissões', 1);
INSERT INTO intFormulario (formulario, titulo, versao) VALUES ('parametro', 'Configurações do Sistema', 1);

-- Inserindo parâmetros do sistema
INSERT INTO parametro (entidade, cnpj, telefone, telefoneFixo, observacao, email, senhaEmail) 
VALUES ('Lopes Solutions', '00.000.000/0001-00', '(11) 99999-9999', '(11) 3333-3333', 'Sistema de Gestão LS', 'contato@ls.com.br', 'YWRtaW4xMjM=');

-- Inserindo permissões para o usuário administrador
INSERT INTO intPermissao (codUsuario, codFormulario, data, acesso, salvar, excluir, imprimir, versao) 
VALUES (1, 1, CURRENT_TIMESTAMP, true, true, true, true, 1);

INSERT INTO intPermissao (codUsuario, codFormulario, data, acesso, salvar, excluir, imprimir, versao) 
VALUES (1, 2, CURRENT_TIMESTAMP, true, true, true, true, 1);

INSERT INTO intPermissao (codUsuario, codFormulario, data, acesso, salvar, excluir, imprimir, versao) 
VALUES (1, 3, CURRENT_TIMESTAMP, true, true, true, true, 1);

INSERT INTO intPermissao (codUsuario, codFormulario, data, acesso, salvar, excluir, imprimir, versao) 
VALUES (1, 4, CURRENT_TIMESTAMP, true, true, true, true, 1);

INSERT INTO intPermissao (codUsuario, codFormulario, data, acesso, salvar, excluir, imprimir, versao) 
VALUES (1, 5, CURRENT_TIMESTAMP, true, true, true, true, 1);
