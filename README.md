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
or

```bash
http PUT localhost:8080/tank-controller/movement Content-Type:application/json movementDirection=FORWARD
http PUT localhost:8080/tank-controller/movement Content-Type:application/json movementDirection=BACKWARD
http PUT localhost:8080/tank-controller/movement Content-Type:application/json movementDirection=LEFT
http PUT localhost:8080/tank-controller/movement Content-Type:application/json movementDirection=RIGHT
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

## TODO

https://sourceforge.net/projects/javaarduinolibrary/ 
   