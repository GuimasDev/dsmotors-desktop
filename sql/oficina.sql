DROP SCHEMA IF EXISTS oficina;
CREATE SCHEMA IF NOT EXISTS oficina;

USE oficina;

CREATE TABLE usuario (
    codigo INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(32) NOT NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE veiculo (
    codigo INT NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(2000) NOT NULL,
    ano CHAR(4) NOT NULL,
    placa CHAR(7) NOT NULL UNIQUE,    
    id_usuario INT NOT NULL,
    PRIMARY KEY (codigo),
    CONSTRAINT FK_usuario_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(codigo)
);

CREATE TABLE agendamento (
    codigo INT NOT NULL AUTO_INCREMENT,
    data DATE NOT NULL,
    objetivo VARCHAR(2000) NOT NULL,
    id_veiculo INT NOT NULL,
    PRIMARY KEY (codigo),
    CONSTRAINT FK_veiculo_id_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculo(codigo)
);