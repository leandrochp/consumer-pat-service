
# consumer-service

O objetivo deste desafio é avaliar suas habilidades em realizar um 'code review' e melhorar a qualidade do código de
uma API desenvolvida em Spring Boot, utilizada para gerenciar os consumidores e as transações de cartão de benefícios dos consumidores.

## Requisitos 

* JDK `11` ou `17`

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

## Acesso ao Banco de Dados em memória (H2)

* URL: [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)
* JDBC URL: `jdbc:h2:mem:consumer_pat_service`
* User Name: `sa`
* Password: `sa`
