package pl.tj.tanks.controller.usb;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;

public class ArduinoSerialPortConnectorUtils {


    public static void main(String[] args) throws IOException {
        Optional<SerialPort> port = new ArduinoSerialPortConnectorUtils().findArduinoUnoSerialPort();
        SerialPort serialPort = port.orElseThrow(IllegalStateException::new);
        try {

            serialPort.setBaudRate(9600);

            serialPort.openPort();

            System.out.println("Sleeping while arduino resetting");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {/*NOOP*/ }

            System.out.println("Port open     :" + serialPort.isOpen());
            System.out.println("Port baud rate:" + serialPort.getBaudRate());
            System.out.println("Data bits     :" + serialPort.getNumDataBits());
            System.out.println("Stop bits     :" + serialPort.getNumStopBits());

            readStream(serialPort);
            while(true){
                serialPort.getOutputStream().write("wsda".getBytes());
                serialPort.getOutputStream().flush();
                Thread.sleep(500);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("Closing");
            port.ifPresent(SerialPort::closePort);
        }


//        Arduino arduinoConnector = new Arduino("/dev/cu.usbmodem1431", 9600);
//        arduinoConnector.openConnection();
//        arduinoConnector.serialWrite('w');
//        arduinoConnector.closeConnection();
    }

    private static void readStream(SerialPort serialPort) throws IOException {
        System.out.println("Reading...");

        new Thread(()->{
            InputStream reader = serialPort.getInputStream();
            byte[] buffer = new byte[1000];
            try {
                while (true) {
                    if(reader.available() > 0){
                        System.out.println("Available: " + serialPort.bytesAvailable());
                        int numRead = reader.read(buffer);
                        System.out.println("Read " + numRead + " bytes: " + new String(buffer, 0, numRead));
                    }
                    Thread.sleep(500);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public Optional<SerialPort> findArduinoUnoSerialPort() {
        //"/dev/cu.usbmodem1431"
        SerialPort[] commPorts = SerialPort.getCommPorts();
        return Arrays.stream(commPorts)
                .filter(port -> port.getDescriptivePortName().contains("Arduino Uno"))
                .findAny();
    }

    public void printCommPorts() {
        SerialPort[] commPorts = SerialPort.getCommPorts();
        for (int i = 0; i < commPorts.length; i++) {
            System.out.println(commPorts[i].getDescriptivePortName() +
                    "::" +
                    commPorts[i].getSystemPortName());
        }
    }

    public void addDataListener(SerialPort serialPort){

        boolean dataListenerAdded = serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE
                        & SerialPort.LISTENING_EVENT_DATA_RECEIVED
                        & SerialPort.LISTENING_EVENT_DATA_WRITTEN;

            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                System.out.println("Event1: " + event.getEventType());
                System.out.println("Event2: " + event.getSerialPort().getSystemPortName());
                System.out.println("Event3: " + new String(event.getReceivedData()));
            }
        });
        System.out.println(":::"+dataListenerAdded);
    }
}
///dev/cu.usbmodem1431