# Ticket Management API

API Spring Boot para gestão de eventos, produtos, clientes e carrinho de compras.

## Tecnologias

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Flyway
- MySQL

## Domínios

### Event
- Entidade: `br.com.projeto.gestaoingressos.ticketmanagement.entity.Event`
- Tabela: `event` (migration `V1__create_table_event.sql`)
- Endpoint principal: `POST /api/event` (via `EventController`)

### Cliente
- Entidade JPA: `br.com.projeto.gestaoingressos.ticketmanagement.entity.Cliente`
- Tabela: `cliente` (migration `V2__create_table_cliente.sql`)
- Controller in-memory: `br.com.projeto.gestaoingressos.ticketmanagement.controller.ClienteController`
- Endpoints:
  - `POST /api/clientes` – cria cliente em memória
  - `GET /api/clientes` – lista todos
  - `GET /api/clientes/{id}` – busca por id

### Produto
- Entidade JPA: `br.com.projeto.gestaoingressos.ticketmanagement.entity.Produto`
- Tabela: `produto` (migration `V3__create_table_produto.sql`)
- Controller mock/in-memory: `br.com.projeto.gestaoingressos.ticketmanagement.controller.ProdutoController`
- Endpoints:
  - `GET /api/produtos` – lista produtos mockados + criados em memória
  - `POST /api/produtos` – cria produto em memória
  - `GET /api/produtos/{id}` – busca por id

### Carrinho
- Entidade JPA: `br.com.projeto.gestaoingressos.ticketmanagement.entity.ItemCarrinho`
- Tabela: `item_carrinho` (migration `V4__create_table_item_carrinho.sql`)
- Controller in-memory: `br.com.projeto.gestaoingressos.ticketmanagement.controller.CarrinhoController`
- Endpoints:
  - `GET /api/carrinho` – lista itens do carrinho em memória
  - `POST /api/carrinho` – adiciona item ao carrinho
  - `DELETE /api/carrinho` – limpa o carrinho

> Observação: os controllers de Cliente/Produto/Carrinho hoje usam estruturas em memória (Map/List) para facilitar o uso com o front. As entidades e tabelas já estão prontas, então você pode futuramente trocar para serviços + repositórios JPA.

## Configuração do banco

Configure o MySQL em `src/main/resources/application.properties`, por exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestao_compras_ingresso?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.flyway.enabled=true
```

As migrations do Flyway estão em `src/main/resources/db/migration` e serão executadas automaticamente na inicialização.

## Como rodar o projeto

Na raiz do projeto, com Maven Wrapper e Java 17 instalados:

```cmd
mvnw.cmd clean spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## Exemplos rápidos de requisições

### Criar Cliente

```http
POST http://localhost:8080/api/clientes
Content-Type: application/json

{
  "nome": "João",
  "email": "joao@example.com"
}
```

### Listar Produtos

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

### Limpar Carrinho

```http
DELETE http://localhost:8080/api/carrinho
```

## Próximos passos sugeridos

- Criar `Repository` e `Service` para Cliente, Produto e ItemCarrinho usando Spring Data JPA.
- Alterar os controllers para persistir/buscar dados no banco ao invés de usar apenas memória.
- Adicionar testes de integração para os endpoints principais.
package br.com.projeto.gestaoingressos.ticketmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;
}

