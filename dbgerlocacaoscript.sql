
CREATE TABLE usuarios(
iduser INT AUTO_INCREMENT NOT NULL UNIQUE,
login VARCHAR(15) NOT NULL UNIQUE,
senha VARCHAR(15) NOT NULL,
usuario VARCHAR(50) NOT NULL,
fone VARCHAR(15),
nivel_de_acesso VARCHAR(15) NOT NULL);

DESCRIBE usuarios;

CREATE TABLE clientes(
idclient INT NOT NULL AUTO_INCREMENT UNIQUE,
nome_empresa VARCHAR(50) NOT NULL,
cpf_cnpj VARCHAR(20) NOT NULL UNIQUE,
fone VARCHAR(15),
email VARCHAR(20));

ALTER TABLE clientes ADD
cidade VARCHAR(50);
ALTER TABLE clientes ADD
estado VARCHAR(50);
ALTER TABLE clientes ADD
bairro VARCHAR(50);
ALTER TABLE clientes ADD
endereco VARCHAR(50);

DESCRIBE  clientes;

CREATE TABLE equipamentos(
id_equipamento INT PRIMARY KEY NOT NULL AUTO_INCREMENT, descricao VARCHAR(50) NOT NULL, valor_diaria DECIMAL(10,2) NOT NULL);


CREATE TABLE locacoes(
registrolocacao INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
valor_aloc DECIMAL(20,2) NOT NULL,
inicio_data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
fim_Data TIMESTAMP NULL,
observacoes TEXT,
status_loc VARCHAR(15),
idclient INT NOT NULL,
FOREIGN KEY(idclient) REFERENCES clientes(idclient));

ALTER TABLE usuarios ADD PRIMARY KEY(iduser);
ALTER TABLE clientes ADD PRIMARY KEY(idclient);

CREATE TABLE locacoes_equipamentos(
id_equipamento INT NOT NULL,
registrolocacao INT NOT NULL,
quantidade INT NOT NULL,
FOREIGN KEY(id_equipamento) REFERENCES equipamentos(id_equipamento),
FOREIGN KEY(registrolocacao) REFERENCES locacoes(registrolocacao));

