# web-tank-controller-api
Agile University - Building Web Application course - tank controller API

## Run a service locally:

```bash
./gradlew clean build && java -jar tank-controller-api/build/libs/tank-controller-api*.jar
```
 To test is server is up:
 
 ```bash
curl localhost:8080/health
```