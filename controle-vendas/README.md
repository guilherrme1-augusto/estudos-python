# Controle de Vendas

## Descrição do projeto

Aplicação web para cadastro e controle de vendas (CRUD completo). O usuário pode
**cadastrar, listar, editar e excluir** vendas. O total de cada venda é calculado
no backend (**Java + Spring Boot**) e os dados são salvos em um banco
**PostgreSQL**. As telas são feitas com **Thymeleaf + Bootstrap**.

## Integrantes da equipe

- [JONATHAN FERREIRA RIBEIRO 1250200776] - https://github.com/Jonferr-dev
- [GUILHERME AUGUSTO DOS SANTOS 1250201885]
- [PATRICK GENOVEZ DA FONSECA ESTEVES 1250107234]


## Tecnologias utilizadas

- **Backend:** Java 17 + Spring Boot + Spring Data JPA
- **Frontend:** Thymeleaf + HTML5 + CSS3 + Bootstrap 5
- **Banco de dados:** PostgreSQL
- **Build:** Maven
- **Containerização:** Docker e Docker Compose

## Como executar a aplicação (com Docker Compose)

Sobe a aplicação **e o banco de dados** juntos, com um comando só:

```bash
docker compose up --build
```

Depois acesse no navegador: **http://localhost:8080**

Para parar:

```bash
docker compose down
```

## Como ver os dados direto no banco (PostgreSQL)

Com a aplicação rodando, abra outro terminal e execute:

```bash
docker exec -it vendas-db psql -U postgres -d vendas -c "SELECT * FROM venda;"
```

## Comandos Docker utilizados

| Ação | Comando |
|------|---------|
| Subir app + banco | `docker compose up --build` |
| Parar tudo | `docker compose down` |
| Ver containers ativos | `docker ps` |
| Acessar o banco (psql) | `docker exec -it vendas-db psql -U postgres -d vendas` |
| Consultar a tabela | `docker exec -it vendas-db psql -U postgres -d vendas -c "SELECT * FROM venda;"` |

