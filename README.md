# consumer-service

**consumer-service** é um microsserviço para gerenciar os consumidores e as transações de cartão de benefícios dos consumidores.

O microsserviço foi desenvolvido em Java e Spring Boot.

Essa API foi desenvolvida com:
- [Java (JDK 11)](https://www.java.com/)
- [Spring Boot](https://spring.io/)
- [OpenAPI 3](https://swagger.io/docs/specification/about/)

## Executando
Ambiente Linux/macOS:
```bash
./gradlew bootRun
```
Ambiente Windows:
```cmd
gradlew bootRun
```
## Documentação da API (Swagger)

* URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Testes
- Testes unitários para regras de negócio e
- Testes de componente para o contrato e respostas http esperadas.


## Acesso ao Banco de Dados em memória (H2)

* URL: [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)
* JDBC URL: `jdbc:h2:mem:consumer_service`
* User Name: `sa`
* Password: `sa`

## Decisões de projeto
O DDD (Domain Driven Design) foi utilizado para estruturar o microsserviço utilizando as 3 camadas:
- Aplicação
- Domínio e
- Infraestrutura

A camada de aplicação por receber as requisições, a domínio por armazenar a lógica de negócio e a camada de infraestrutura para persistência dos dados.

A requisição é passada para a interface do serviço do domínio, que converte a solicitação no objeto de domínio. A implementação do serviço possui todos os requisitos de negócios.
Se uma exceção for lançada, este próprio serviço a trata e retorna o resultado. A camada de aplicação é responsável por convertê-la em uma resposta mais apresentável.

A camada de domínio imprime o que está acontecendo nos logs, que podem fornecer a solução de problemas no ambiente de produção.

A camada de persistência é responsável por converter as entidades em documentos para persistir em banco de dados.
