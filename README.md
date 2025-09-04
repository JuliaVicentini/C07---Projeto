# C07---Projeto
# Sistema de Gerenciamento de Faculdade

## 1. Escopo do Projeto
Este projeto tem como objetivo modelar uma plataforma acadêmica para gerenciamento de alunos, professores, cursos, disciplinas e matrículas.  
O modelo foi desenvolvido no **MySQL Workbench** e segue as instruções da disciplina, contemplando no mínimo 5 entidades, além de relacionamentos dos tipos **1:1, 1:N e N:M**.  

---

## 2. Entidades e Descrições

- **Aluno**: representa os estudantes da instituição, armazenando dados pessoais, contato e vínculo com um curso.  
- **Professor**: representa os docentes da instituição, com nome e titulação acadêmica.  
- **Curso**: catálogo de cursos oferecidos, cada um com duração definida e um professor coordenador.  
- **Disciplina**: componentes curriculares oferecidos em cada curso, com carga horária definida e um professor responsável.  
- **Matrícula**: representa a inscrição de alunos em disciplinas específicas, permitindo a associação N:M entre Aluno e Disciplina.  

---

## 3. Relacionamentos

- **Curso – Professor (1:1)**: cada curso possui exatamente um professor coordenador, e cada professor pode coordenar apenas um curso.  
- **Curso – Disciplina (1:N)**: um curso pode conter várias disciplinas, mas cada disciplina pertence a apenas um curso.  
- **Professor – Disciplina (1:N)**: um professor pode lecionar várias disciplinas, mas cada disciplina possui apenas um professor responsável.  
- **Aluno – Curso (1:N)**: um curso pode ter vários alunos matriculados, mas cada aluno está vinculado a apenas um curso.  
- **Aluno – Disciplina (N:M)**: representado pela tabela **Matrícula**. Um aluno pode se inscrever em várias disciplinas, e uma disciplina pode ter vários alunos matriculados.  

---

## 4. Regras de Negócio

- Todo curso deve possuir um professor coordenador.  
- Cada disciplina deve obrigatoriamente estar vinculada a um curso e a um professor responsável.  
- Um aluno deve estar vinculado a exatamente um curso.  
- A matrícula só pode existir se o aluno e a disciplina forem válidos e vinculados à instituição.  
- Não pode haver matrícula duplicada do mesmo aluno na mesma disciplina.  

## 5. Autores

- João Pedro Torrano Dias
- Júlia do Amaral Vicentini
