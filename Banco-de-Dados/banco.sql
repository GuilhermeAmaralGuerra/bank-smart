Create Database bancomonetário;
use bancomonetário;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    nome_completo VARCHAR(70) NOT NULL,
    senha VARCHAR(100) NOT NULL, 
    data_nascimento DATE NOT NULL,
    CPF CHAR(14) UNIQUE NOT NULL,
    status ENUM('ativo', 'inativo') DEFAULT 'ativo'
);

CREATE TABLE conta_bancaria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    numero_conta VARCHAR(20) UNIQUE NOT NULL,
    saldo DECIMAL(15, 2) DEFAULT 0.00,
    codigo_transferencia VARCHAR (10),
    data_abertura DATE NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) 
);

CREATE TABLE transacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    conta_id INT,
    tipo_transacao VARCHAR(20),
    valor DECIMAL(15, 2) NOT NULL,
    data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao VARCHAR(255),
    para_quem CHAR(10),
    FOREIGN KEY (conta_id) REFERENCES conta_bancaria(id) 
);

CREATE TABLE endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    rua VARCHAR(100),
    numero VARCHAR(10),
    complemento VARCHAR(99),
    cidade VARCHAR(50),
    estado VARCHAR(30),
    cep CHAR(9),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE saque (
	id INT AUTO_INCREMENT PRIMARY KEY,
    conta_id INT,
    valor_saque DECIMAL(15, 2),
    data_saque TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (conta_id) REFERENCES conta_bancaria(id)
);

CREATE TABLE deposito (
	id INT AUTO_INCREMENT PRIMARY KEY,
    conta_id INT,
    valor_deposito DECIMAL(15, 2),
    data_deposito TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (conta_id) REFERENCES conta_bancaria(id)
);

CREATE TABLE emprestimo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    valor DECIMAL(15, 2) NOT NULL,
    valor_juros DECIMAL(18, 2),
    data_emprestimo DATE NOT NULL,
    data_pagamento DATE,
    status ENUM('pendente', 'pago') NOT NULL DEFAULT 'pendente',
    pagamento DECIMAL(15,2) DEFAULT 0.00,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE emprestimo_pagamento_incompleto(
	id INT AUTO_INCREMENT PRIMARY KEY,
    conta_id INT,
    emprestimo_id INT,
    valor_abatido DECIMAL (15, 2),
    data_pagamento DATE,
	FOREIGN KEY(conta_id) REFERENCES conta_bancaria(id),
    FOREIGN KEY (emprestimo_id) REFERENCES emprestimo(id)
);
