DROP DATABASE IF EXISTS FaculdadeDB;
CREATE DATABASE FaculdadeDB;
USE FaculdadeDB;

DROP TABLE IF EXISTS Matricula;
DROP TABLE IF EXISTS Aluno;
DROP TABLE IF EXISTS Disciplina;
DROP TABLE IF EXISTS Curso;
DROP TABLE IF EXISTS Professor;

CREATE TABLE Professor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    titulacao VARCHAR(60),
    email VARCHAR(120),
    data_contratacao DATE
);

CREATE TABLE Curso (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    duracao_meses INT NOT NULL,
    professor_coordenador_id INT UNIQUE NOT NULL,
    CONSTRAINT fk_curso_professor FOREIGN KEY (professor_coordenador_id) REFERENCES Professor(id)
);

CREATE TABLE Disciplina (
    id INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(20) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT NOT NULL,
    curso_id INT NOT NULL,
    professor_id INT NOT NULL,
    CONSTRAINT fk_disciplina_curso FOREIGN KEY (curso_id) REFERENCES Curso(id),
    CONSTRAINT fk_disciplina_professor FOREIGN KEY (professor_id) REFERENCES Professor(id)
);

CREATE TABLE Aluno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    data_nascimento DATE,
    email VARCHAR(120),
    curso_id INT NOT NULL,
    CONSTRAINT fk_aluno_curso FOREIGN KEY (curso_id) REFERENCES Curso(id)
);

CREATE TABLE Matricula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    aluno_id INT NOT NULL,
    disciplina_id INT NOT NULL,
    data_matricula DATETIME DEFAULT CURRENT_TIMESTAMP,
    situacao ENUM('matriculado','trancado','cancelado','concluido') DEFAULT 'matriculado',
    CONSTRAINT fk_matricula_aluno FOREIGN KEY (aluno_id) REFERENCES Aluno(id),
    CONSTRAINT fk_matricula_disciplina FOREIGN KEY (disciplina_id) REFERENCES Disciplina(id),
    CONSTRAINT uq_aluno_disciplina UNIQUE (aluno_id, disciplina_id)
);

-- Inserções
INSERT INTO Professor (nome, titulacao, email, data_contratacao) VALUES
('Ana Silva', 'Doutora', 'ana.silva@faculdade.com', '2016-02-01'),
('Carlos Pereira', 'Mestre', 'carlos.pereira@faculdade.com', '2018-05-15'),
('Mariana Oliveira', 'Doutora', 'mariana.oliveira@faculdade.com', '2020-03-10');

INSERT INTO Curso (nome, descricao, duracao_meses, professor_coordenador_id) VALUES
('Engenharia de Software', 'Curso focado em desenvolvimento de software e sistemas computacionais', 60, 1),
('Engenharia de Telecomunicações', 'Curso voltado para redes de comunicação, sistemas de transmissão e telecomunicações', 60, 2),
('Engenharia de Computação', 'Curso com ênfase em hardware, software embarcado e arquitetura de computadores', 60, 3);

INSERT INTO Disciplina (codigo, nome, carga_horaria, curso_id, professor_id) VALUES
('C02', 'Algoritmos I', 80, 1, 1),
('C07', 'Banco de Dados', 60, 1, 1),
('M11', 'Probabilidade', 80, 2, 2),
('E09', 'Microcontrolador', 80, 2, 2),
('RC01', 'Redes I', 60, 3, 3),
('M01', 'Matematica', 80, 3, 3);

INSERT INTO Aluno (nome, cpf, data_nascimento, email, curso_id) VALUES
('João Santos', '123.456.789-00', '2000-05-10', 'joao.santos@aluno.com', 1),
('Maria Costa', '987.654.321-11', '1999-12-02', 'maria.costa@aluno.com', 1),
('Pedro Lima', '111.222.333-44', '2001-07-25', 'pedro.lima@aluno.com', 2),
('Ana Souza', '555.666.777-88', '2000-03-15', 'ana.souza@aluno.com', 3);

INSERT INTO Matricula (aluno_id, disciplina_id, situacao) VALUES
(1, 1, 'matriculado'),
(1, 2, 'matriculado'),
(2, 1, 'matriculado'),
(3, 3, 'matriculado'),
(4, 5, 'matriculado');

-- SELECTs com JOINs sem tabelas intermediárias
-- 1. Professores e seus cursos coordenados
SELECT p.nome as professor, c.nome as curso_coordenado 
FROM Professor p 
JOIN Curso c ON p.id = c.professor_coordenador_id;

-- 2. Disciplinas e seus professores
SELECT d.nome as disciplina, p.nome as professor 
FROM Disciplina d 
JOIN Professor p ON d.professor_id = p.id;

-- 3. Alunos e seus cursos
SELECT a.nome as aluno, c.nome as curso 
FROM Aluno a 
JOIN Curso c ON a.curso_id = c.id;

-- SELECTs com JOINs com tabelas intermediárias
-- 4. Matrículas completas (aluno -> disciplina -> curso)
SELECT a.nome as aluno, d.nome as disciplina, c.nome as curso, m.situacao
FROM Matricula m
JOIN Aluno a ON m.aluno_id = a.id
JOIN Disciplina d ON m.disciplina_id = d.id
JOIN Curso c ON d.curso_id = c.id;

-- 5. Disciplinas com curso e professor coordenador do curso
SELECT d.nome as disciplina, d.carga_horaria, c.nome as curso, p.nome as professor_coordenador
FROM Disciplina d
JOIN Curso c ON d.curso_id = c.id
JOIN Professor p ON c.professor_coordenador_id = p.id;

-- 6. Alunos com suas disciplinas matriculadas e situação
SELECT a.nome as aluno, d.nome as disciplina, m.situacao, p.nome as professor
FROM Matricula m
JOIN Aluno a ON m.aluno_id = a.id
JOIN Disciplina d ON m.disciplina_id = d.id
JOIN Professor p ON d.professor_id = p.id;

select * from professor;

select * from aluno;