# 📚 LibrarySystem - API REST

<br>


## 🎯 Objetivo da Atividade

Este é nossa **primeira atividade com API REST**, desenvolvido para consolidar os conceitos fundamentais de:

- ✅ **Arquitetura em camadas** (Controller → Service → Repository → Model)
- ✅ **REST API** com endpoints CRUD completos
- ✅ **Banco de dados relacional** com MySQL
- ✅ **Operações com banco de dados** (Create, Read, Update, Delete)
- ✅ **Relacionamentos entre entidades** (um-para-muitos)
- ✅ **Regras de negócio** no nível de serviço
- ✅ **Serialização/Desserialização** de dados JSON

<br>

## 📋 Descrição do Projeto

O **LibrarySystem** é uma API REST para gerenciar:
- **Livros**: cadastro, listagem e gerenciamento
- **Usuários**: dados e perfis de leitores
- **Empréstimos**: registro de quando um livro é emprestado e devolvido

<br>


## 🏗️ Arquitetura do Projeto

```
com.weg.LibrarySystem
├── controller/          # Endpoints REST (requisições HTTP)
│   ├── BookController
│   ├── UserController
│   └── LoanController
│
├── service/             # Regras de negócio
│   ├── BookService
│   ├── UserService
│   └── LoanService
│
├── repository/          # Acesso aos dados (DAO)
│   ├── BookRepository
│   ├── UserRepository
│   └── LoanRepository
│
├── model/               # Entidades de dados
│   ├── Book
│   ├── User
│   └── Loan
│
└── util/                # Utilitários
    └── ConnectionMysql
```

<br>


## 🗄️ Banco de Dados

### Estrutura SQL

```sql
CREATE DATABASE librarySystem;
USE librarySystem;

-- Tabela de Livros
CREATE TABLE Book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    yearPublication INT NOT NULL
);

-- Tabela de Usuários
CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Tabela de Empréstimos
CREATE TABLE Loan (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bookId BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    loanDate DATE NOT NULL,
    returnDate DATE,
    FOREIGN KEY (bookId) REFERENCES Book(id),
    FOREIGN KEY (userId) REFERENCES User(id)
);
```

<br>


### Relacionamentos

```
┌──────────────┐         ┌────────────────┐         ┌──────────────┐
│     Book     │         │      Loan      │         │     User     │
├──────────────┤         ├────────────────┤         ├──────────────┤
│ id (PK)      │◄────┤   │ bookId (FK)    │   │────►│ id (PK)      │
│ title        │         │ userId (FK)    │         │ name         │
│ author       │         │ loanDate       │         │ email        │
│ yearPub.     │         │ returnDate     │         │              │
└──────────────┘         └────────────────┘         └──────────────┘
  1 livro pode ter          muitos empréstimos       1 usuário pode ter
  muitos empréstimos                                muitos empréstimos
```

<br>


## 🌐 Endpoints REST

### 📕 **Livros** `/books`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/book` | Listar todos os livros |
| GET | `/book/{id}` | Buscar livro por ID |
| POST | `/book` | Cadastrar novo livro |
| PUT | `/book/{id}` | Atualizar informações do livro |
| DELETE | `/book/{id}` | Deletar um livro |

### 👥 **Usuários** `/users`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/user` | Listar todos os usuários |
| GET | `/user/{id}` | Buscar usuário por ID |
| POST | `/user` | Cadastrar novo usuário |
| PUT | `/user/{id}` | Atualizar dados do usuário |
| DELETE | `/user/{id}` | Deletar um usuário |

### 📤 **Empréstimos** `/loans`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/loan` | Listar todos os empréstimos |
| GET | `/loan/{id}` | Buscar empréstimo por ID |
| GET | `/loan/user/{userId}` | Listar empréstimos de um usuário |
| POST | `/loan` | Registrar novo empréstimo |
| PUT | `/loan/{id}` | Atualizar empréstimo |
| PUT | `/loan/{id}/return` | Registrar devolução do livro |
| DELETE | `/loan/{id}` | Deletar registro de empréstimo |

<br>


## 💾 Modelos (Classes)

### Book
```java
class Book {
    - id: Long
    - title: String
    - author: String
    - yearPublication: int
}
```

### User
```java
class User {
    - id: Long
    - name: String
    - email: String
}
```

### Loan
```java
class Loan {
    - id: Long
    - bookId: Long
    - userId: Long
    - loanDate: LocalDate
    - returnDate: LocalDate (nullable)
}
```

## 🔧 Como Executar

### 1. **Setup do Banco de Dados**
```sql
-- Execute o script SQL acima para criar o banco e as tabelas
```

### 2. **Configuração da Conexão**
Verifique as credenciais em `util/ConnectionMysql.java`:
```java
String url = "jdbc:mysql://localhost:3306/librarySystem";
String user = "root";
String password = "sua_senha";
```

### 3. **Executar a Aplicação**
```bash
# Compile e execute
java LibrarySystemApplication
```

<br>


## 📝 Exercícios de Fixação

Teste a API fazendo:

1. **Cadastrar 3 livros** usando POST `/books`
2. **Cadastrar 2 usuários** usando POST `/users`
3. **Criar um empréstimo** usando POST `/loans`
4. **Listar todos os empréstimos** usando GET `/loans`
5. **Registrar a devolução** usando PUT `/loans/{id}/return`
6. **Atualizar um usuário** usando PUT `/users/{id}`
7. **Listar empréstimos de um usuário** usando GET `/loans/user/{userId}`
8. **Deletar um livro** que não tenha empréstimos ativo usando DELETE `/books/{id}`


<br>

## 🎯 Desafios Extras

### 1️⃣ Validação de Empréstimo
Implementar regra que **impede emprestar um livro já emprestado**:
- Um livro só pode ter 1 empréstimo ativo por vez
- Validar se `returnDate` é `null` no empréstimo anterior

### 2️⃣ Histórico de Empréstimos por Usuário
Endpoint adicional:
- GET `/userloan/{id}` → listar todos os empréstimos (ativos e devolvidos) de um usuário

<br>


## 🛠️ Tecnologias Utilizadas

- **Java** - Linguagem de programação
- **MySQL** - Banco de dados relacional
- **JDBC** - Conexão e operações no banco
- **REST API** - Arquitetura de serviços web
- **Spring Boot** - Java framework para aplicações.

<br>


## 📚 Camadas da Aplicação

### 1. **Controller** 🎮
Recebe requisições HTTP e delega para a service
```
HTTP Request → Controller → Service
```

### 2. **Service** 🧠
Implementa regras de negócio e validações
```
Controller → Service → Repository
```

### 3. **Repository** 💾
Acessa o banco de dados (operações CRUD)
```
Service → Repository → MySQL
```

### 4. **Model** 📦
Define a estrutura dos dados
```
Entidades: Book, User, Loan
```


---

Primeira Atividade de API REST  
**Data:** 13-02-2026  
