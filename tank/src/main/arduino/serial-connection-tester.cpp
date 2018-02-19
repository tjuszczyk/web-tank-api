char inputBuffer[10];

void setup() {
  Serial.begin(9600);
 }

void loop() {
//    while (true)
//    {
//                Serial.println("Hello");
//Serial.println("1");
      if (Serial.available() > 0) {
          Serial.readBytes(inputBuffer, Serial.available());
          delay(500);
          Serial.print("I got this ->");
          Serial.print(inputBuffer);
          Serial.println("<-");
      }
//    }
}