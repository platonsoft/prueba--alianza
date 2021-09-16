# Web services Alianza

![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack

![](https://img.shields.io/badge/java_8-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/h2-✓-blue.svg)
![](https://img.shields.io/badge/swagger_2-✓-blue.svg)

# Como usar este proyecto?

1. Asegurese de tener [Java 8](https://www.java.com/download/) y [Maven](https://maven.apache.org) instalado

2. Clone este repositorio

```
$ git clone 
```

3. Navegue a la carpeta

```
$ cd pueba-alianza\back
```

4. Instale las dependencias

```
$ mvn install
```

5. Inicie el proyeto

```
$ mvn spring-boot:run
```

6. Navegue a `http://localhost:8191/swagger-ui.html`

7. Cree una peticion GET a `/clients` para ver el listado de los clientes

```
$ curl -X GET "http://localhost:8191/api/clients" -H "accept: */*"
```

8. Cree una peticion POST a `/client` 

```
$ curl -X POST "http://localhost:8191/api/client" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"businessId\": \"aaaa\", \"email\": \"aaa@aaa.com\", \"idClient\": 0, \"phone\": \"123123123\", \"sharedKey\": \"ddddddddd\"}"
```

9. Cree una peticion GET a `/clients/search` pasando como parametro el sharedKeySearch=ddddddddd

```
$ curl -X GET "http://localhost:8191/api/clients/search?sharedKeySearch=ddddddddd" -H "accept: */*"
```

# Autor

- Jesus Alcala <jesusj.alcalap@gmail.com>
