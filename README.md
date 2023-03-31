# Autorizador VR - versão mini.
Teste técnico para desenvolvedor.
Desenvolvido utilizando Spring Boot.

## Recursos utilizados no desenvolvimento
- Padrão RESTful
- Testes unitários utilizando Mockito
- Testes de funcionalidades utilizando Postman

## Versão dos recursos
- Java 11
- Mysql versão 8
- Docker
- Maven

## Como rodar o projeto

Selecione o profile como desejar ex: local.

```
cd cartoes-vr
mvn clean spring-boot:run -Dspring-boot.run.profiles=local
```

## Documentação disponível

http://localhost:8080/swagger-ui/index.html

## Rodar os testes

```
cd cartoes-vr
mvn test
```

## Rodar com o Docker

```
cd docker
docker-compose up
```

Autor: Rodrigo Bueno


