# web-tank-controller-api
Agile University - Building Web Application course - tank controller API

## Run a service locally:

```bash
./gradlew clean build && java -jar tank-controller-api/build/libs/tank-controller-api*.jar
```
 To test is server is up:
 
 ```bash
curl localhost:8080/actuator/health
```

## API

### Current state
```bash
curl localhost:8080/api/tank-controller/movement
```

### Move tank in a direction
```bash
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"FORWARD"}' localhost:8080/api/tank-controller/movement
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"BACKWARD"}' localhost:8080/api/tank-controller/movement
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"LEFT"}' localhost:8080/api/tank-controller/movement
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"RIGHT"}' localhost:8080/api/tank-controller/movement
curl -H 'Content-Type: application/json' -X PUT -d '{"movement":"STOP"}' localhost:8080/api/tank-controller/movement
```
or

```bash
http GET localhost:8080/api/tank-controller/movement Accept:application/json
http PUT localhost:8080/api/tank-controller/movement Content-Type:application/json direction=FORWARD
http PUT localhost:8080/api/tank-controller/movement Content-Type:application/json direction=BACKWARD
http PUT localhost:8080/api/tank-controller/movement Content-Type:application/json direction=LEFT
http PUT localhost:8080/api/tank-controller/movement Content-Type:application/json direction=RIGHT
```
## Tank Controller

In tank submodule you can find a code to upload onto tank arduino device.
It waits for steering characters on USB serial port, in order to move:
- 'w' - move forward
- 's' - move backwards
- 'a' - turn left, 
- 'd' - turn right

You can upload the code using Arduino IDE and test it with Arduino Serial Monitor.
Remember to choose Arduino Uno device and set baud rate to 9600.

You can review tank specification: https://www.robotshop.com/en/dfrobotshop-rover-tracked-robot-basic-kit.html

## Lesson 4

- Checkout branch lesson_4

- start application server from prepared binaries:

```bash
java -jar lesson_4/tank-controller-api.jar
```

(!) Assumption: Java Runtime Environment is installed on a computer
```bash
java -version
```
should print installed java version on a console

- test your API using Postman HTTP client:
```https://app.getpostman.com```
You can import postman API collection from: lesson5/tank-api.postman_collection.json

- There are 4 mistakes in a postman collection.

As an exercise try to figure out, what's the mistake (based on a API response or application logs)

## Lesson 5

- Follow the instructions for a lesson 4

## Lesson 6

- Checkout branch lesson_6

- start application server from prepared binaries:

```bash
java -jar lesson_6/tank-controller-api.jar
```



