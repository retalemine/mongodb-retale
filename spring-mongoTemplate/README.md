Spring-MongoDB project(app):
============================
 * Uses Spring Data MongoDB
 * Uses MongoDB Template approach
 * Uses MongoDB Repository approach
 * Uses Custom converters
 * Uses MongoDB Repository approach with a custom impl for one class

Maven settings and commands:
============================
 * Dependency
```
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-mongodb</artifactId>
        <version>1.3.3.RELEASE</version>
    </dependency>
```
 * mvn clean package
 * mvn exec:java -Dexec.mainClass="in.retalemine.App"
 * mvn exec:java -Dexec.mainClass="in.retalemine.App" -Dexec.args="'first arg'"
 * -Dmaven.test.skip=true __skips compilation and execution__ 
 * -DskipTests=true __skips execution__
