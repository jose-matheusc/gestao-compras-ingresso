# Ticket Management API

API Spring Boot para gestão de eventos, produtos, clientes, carrinho de compras e pagamentos.

## Tecnologias

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Flyway
- MySQL 8+

## Requisitos para rodar o projeto

1. **Java 17** instalado e configurado no `PATH`.
2. **Maven** (ou usar o Maven Wrapper `mvnw.cmd` que já está no projeto).
3. **MySQL 8** instalado e em execução na sua máquina:
   - Serviço MySQL rodando (porta padrão `3306`).
   - Usuário com permissão para criar banco e tabelas (ex.: `root`).
4. Criar um banco de dados para a aplicação (ex.: `ingressos` ou `cineboot`). Exemplo de comando no MySQL:

```sql
CREATE DATABASE ingressos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

> Obs.: Não é necessário criar as tabelas manualmente. O **Flyway** cria as tabelas (`event`, `cliente`, `produto`, `item_carrinho`) e insere alguns ingressos em `produto` automaticamente a partir das migrations em `src/main/resources/db/migration`.

## Configuração do banco

Edite o arquivo `src/main/resources/application.properties` com os dados do seu MySQL, por exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ingressos?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=sua_senha_aqui
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

spring.flyway.enabled=true
spring.flyway.url=jdbc:mysql://localhost:3306/ingressos?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.flyway.user=root
spring.flyway.password=sua_senha_aqui
spring.flyway.driver-class-name=com.mysql.cj.jdbc.Driver
```

Se o seu MySQL **não tiver senha** para o usuário `root`, deixe `spring.datasource.password` e `spring.flyway.password` vazios:

```properties
spring.datasource.password=
spring.flyway.password=
```

## Domínios

### Event
- Entidade: `br.com.projeto.gestaoingressos.ticketmanagement.entity.Event`
- Tabela: `event` (migration `V1__create_table_event.sql`)
- Endpoint principal: `POST /api/event` (via `EventController`)

### Cliente
- Entidade JPA: `br.com.projeto.gestaoingressos.ticketmanagement.entity.Cliente`
- Tabela: `cliente` (migration `V2__create_table_cliente.sql`)
- Endpoints principais (via `ClienteController`):
  - `POST /api/clientes` – cadastro de cliente com `nome`, `email`, `senha`.
  - `POST /api/clientes/login` – login com body `{ "email": "...", "senha": "..." }`.
  - `GET /api/clientes` – lista todos
  - `GET /api/clientes/{id}` – busca por id

### Produto (Ingressos)
- Entidade JPA: `br.com.projeto.gestaoingressos.ticketmanagement.entity.Produto`
- Tabela: `produto` (migration `V3__create_table_produto.sql`)
- Migration `V5__insert_ingressos_iniciais.sql` insere alguns ingressos de exemplo na tabela `produto`.
- Endpoints principais (via `ProdutoController`):
  - `GET /api/produtos` – lista produtos/ingressos
  - `POST /api/produtos` – cria produto
  - `GET /api/produtos/{id}` – busca por id

### Carrinho
- Entidade JPA: `br.com.projeto.gestaoingressos.ticketmanagement.entity.ItemCarrinho`
- Tabela: `item_carrinho` (migration `V4__create_table_item_carrinho.sql`)
- Endpoints (via `CarrinhoController`):
  - `GET /api/carrinho` – lista itens do carrinho
  - `POST /api/carrinho` – adiciona item ao carrinho com `{ "produtoId": 1, "quantidade": 2 }`
  - `DELETE /api/carrinho/{id}` – remove item específico
  - `DELETE /api/carrinho` – limpa o carrinho
  - `GET /api/carrinho/total` – retorna o valor total do carrinho

### Pagamento
- DTO: `br.com.projeto.gestaoingressos.ticketmanagement.dto.PagamentoDTO`
- Service: `PagamentoService` (limpa o carrinho após pagamento)
- Endpoint (via `PagamentoController`):
  - `POST /api/pagamento` – processa o pagamento com body, por exemplo:

```http
POST http://localhost:8080/api/pagamento
Content-Type: application/json

{
  "metodo": "Cartão de Crédito",
  "dados": "123123123123",
  "valorTotal": 40
}
```

Após o pagamento, o serviço de pagamento chama o serviço de carrinho para **limpar o carrinho** no banco.

## Como rodar o projeto

Na raiz do projeto, com MySQL 8 rodando e o banco criado:

```cmd
mvnw.cmd clean spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Exemplos rápidos de requisições

### Criar Cliente (cadastro)

```http
POST http://localhost:8080/api/clientes
Content-Type: application/json

{
  "nome": "João",
  "email": "joao@example.com",
  "senha": "minha_senha"
}
```

### Login de Cliente

```http
POST http://localhost:8080/api/clientes/login
Content-Type: application/json

{
  "email": "joao@example.com",
  "senha": "minha_senha"
}
```

### Listar Produtos (Ingressos)

```http
GET http://localhost:8080/api/produtos
```

### Adicionar item ao Carrinho

```http
POST http://localhost:8080/api/carrinho
Content-Type: application/json

{
  "produtoId": 1,
  "quantidade": 2
}
```

### Ver total do Carrinho

```http
GET http://localhost:8080/api/carrinho/total
```

### Limpar Carrinho

```http
DELETE http://localhost:8080/api/carrinho
```

## Próximos passos sugeridos

- Habilitar autenticação mais forte (hash de senha, JWT, etc.).
- Adicionar testes de integração para os endpoints principais.
- Evoluir o front-end (HTML/JS) para usar o fluxo completo de login, carrinho e pagamento.
