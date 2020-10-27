# limits-service
This is a test project to test getting the configuration for service in microservices from the cloud configuration server.
The configuration file include the cloud configuration server URL and application profile.
This application has also testing for hystrix and resilience circle breaker for fault tolerance.

# Example URLs

- Return the limits which retrieved from spring-cloud-config-server
http://localhost:8080/limits

- hystrix fault tolerance test
http://localhost:8080/limits-hystrix-fault-tolerance

- Resilience fault tolerance test
http://localhost:8080/limits-resilience-fault-tolerance

# Summary
This project is part from some projects created for testing microservice cloud. Those projects are
- spring-cloud-config-server

This service load the configuration for the microservices from repository on github called microservice-config and expose it as web services to be called by other microservices

- eureka naming server

This is a naming server to register the microservices on it to be able to
retrieve the URLs for different microservices by microservice name which defined inside application.properties file

- zuul-api-gateway-server

This is a gateway server to handle the authentication/authorization and the communication between microservices.
This project is created without implementing the business for authentication/authorization for testing.
This service register itself in eureka naming server.
The API gateway know the services URLs which should be called for each request from the eureka naming server.
This service register  the requests tracing by zipkins and rabbitMQ.
 
- limits-service

This service used to test loading configuration from spring-cloud-config-server. It is also used to test the hystrix and resilience circle breaker for fault tolerance
This service register itself in eureka naming server.

- currency-exchange-service

This service used to test the rest template, feign rest client, rubbon client load balancer. It callect the currency exchange rate information from external API
This service register itself in eureka naming server.
This service register  the requests tracing by zipkins and rabbitMQ.

- currency-conversion-service

This service used to call the currency-exchange-service to get the currency exchange rate.
This service created to test the rest template, feign rest client, rubbon client load balancer.
This service register itself in eureka naming server.
This service register  the requests tracing by zipkins and rabbitMQ.

Tested inside those projects:
- JPA
- Spring cloud configuration server read from git repository
- Rest template
- Feign rest client 
- Ribbon client load balancer
- Eureka naming server
- Zuul api gateway
- Spring cloud sleuth to add unique ID to logs
- Zipkin distributed tracing system using Rabbit MQ [required rabbitMQ and zipkin to be running]
- Hystrix and resilience circle breaker for fault tolerance.




