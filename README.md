# 🏥 API de Agendamento de Consultas Médicas

Este é um projeto avaliativo da disciplina **Programação Orientada a Objetos em Java**, com o objetivo de desenvolver uma API REST utilizando Spring Boot para o gerenciamento de agendamentos de consultas em uma clínica médica.

## 🎯 Objetivo

Permitir que pacientes agendem consultas médicas, que médicos visualizem suas agendas e que a clínica gerencie a disponibilidade de horários com base em especialidades e regras de negócio pré-definidas.

---

## ⚙️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de Dados (ex: H2, PostgreSQL)
- Maven

---

## 🗃️ Entidades Principais

- **Paciente**: nome, CPF, telefone, email.
- **Médico**: nome, CRM, especialidade.
- **Especialidade**: ex: Cardiologia, Pediatria.
- **Consulta**: paciente, médico, data/hora, status, observações.
- **Disponibilidade**: médico, dia da semana, horário de início e fim.

---

## 🔐 Regras de Negócio

- Agendamento só é permitido:
  - Dentro do horário comercial (ex: 09:00 às 17:00).
  - Se o médico estiver disponível naquele horário.
  - Com data/hora futura.
- Cancelamentos só são aceitos com **no mínimo 24 horas de antecedência**.
- Um médico não pode ser removido se tiver consultas futuras.
- Um paciente não pode ser excluído se tiver consultas agendadas.

---

## 🔁 Endpoints Principais

### 📌 Pacientes

- `POST /pacientes` – Cadastrar paciente.
- `GET /pacientes` – Listar pacientes.
- `GET /pacientes/{id}` – Buscar paciente.
- `PUT /pacientes/{id}` – Atualizar paciente.
- `DELETE /pacientes/{id}` – Remover paciente.

### 🧑‍⚕️ Médicos

- `POST /medicos` – Cadastrar médico.
- `GET /medicos` – Listar médicos (filtros por especialidade/disponibilidade).
- `GET /medicos/{id}` – Buscar médico.
- `PUT /medicos/{id}` – Atualizar médico.
- `DELETE /medicos/{id}` – Remover médico.

### 📅 Consultas

- `POST /consultas` – Agendar nova consulta.
- `GET /consultas` – Listar consultas (filtros por médico, paciente, status).
- `GET /consultas/{id}` – Buscar consulta.
- `PATCH /consultas/{id}/status` – Atualizar status.
- `DELETE /consultas/{id}` – Cancelar consulta.

### 🩺 Especialidades

- `POST /especialidades` – Cadastrar especialidade.
- `GET /especialidades` – Listar especialidades.
- `GET /especialidades/{id}` – Buscar especialidade.
- `PUT /especialidades/{id}` – Atualizar especialidade.
- `DELETE /especialidades/{id}` – Remover especialidade.

### ⏰ Disponibilidade

- `POST /medicos/{medicoId}/disponibilidades` – Registrar disponibilidade.
- `GET /medicos/{medicoId}/disponibilidades` – Listar disponibilidades.
- `DELETE /disponibilidades/{id}` – Remover disponibilidade.

---

## 🧪 Como Executar o Projeto

1. Clone o repositório:

```bash
  git clone https://github.com/gbrogio/api-agendamento-consultas-medicas
```

2. Acesse a pasta do projeto:

```bash
  cd api-agendamento-consultas-medicas
```

3. Compile e rode o projeto com o Maven:

```bash
  mvn spring-boot:run
```

4. Acesse o Swagger em:
```bash
  http://localhost:8080/docs
```

## 👨‍🏫 Informações Acadêmicas

**Disciplina:** Programação Orientada a Objetos em Java\
**Projeto Avaliativo:** API para Agendamento de Consultas Médicas\
**Alunos:** Antônio Neto, Guilherme Brogio, Lucas Gabriel, Matheus Guilherme, Leonardo Ribeiro\
**Professor:** Fabrício
