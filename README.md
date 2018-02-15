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

## API

### Current state
```bash
curl localhost:8080/tank-controller/movement
```

### Move tank in a direction
```bash
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"FORWARD"}' localhost:8080/tank-controller/movement
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"BACKWARD"}' localhost:8080/tank-controller/movement
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"LEFT"}' localhost:8080/tank-controller/movement
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"RIGHT"}' localhost:8080/tank-controller/movement
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"STOP"}' localhost:8080/tank-controller/movement
```