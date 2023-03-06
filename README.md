
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
