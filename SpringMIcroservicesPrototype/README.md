# SpringMicroservicesPrototype
Prototype of spring microservices architecture

## Configuration service
http://localhost:8888/{service-name}/{profile}
e.g.: http://localhost:8888/team-data-service/default

## Eureka discovery service
http://localhost:8761/

## Hystrix dashboard
http://localhost/hystrix
Enter URL: http://localhost:9003/actuator/hystrix.stream
