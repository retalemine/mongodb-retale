Spring-MongoDB project(web):
============================
 * Uses Spring Data MongoDB
 * Uses Spring Boot
 * Uses MongoDB repository approach

Maven settings and commands:
============================
 * Dependency
```
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-mongodb</artifactId>
        <version>1.3.3.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
```
 * mvn clean package
 * mvn spring-boot:run
 * mvn exec:java

