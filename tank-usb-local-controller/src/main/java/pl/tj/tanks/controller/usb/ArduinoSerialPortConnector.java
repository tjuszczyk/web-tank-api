package pl.tj.tanks.controller.usb;

import arduino.*;

public class ArduinoSerialPortConnector {


    public static void main(String[] args) {
        Arduino arduinoConnector = new Arduino("", 9600);
    }
}
