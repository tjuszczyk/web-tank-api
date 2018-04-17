package pl.tj.tanks.controller.usb;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;

public class ArduinoSerialPortConnector {
    public static final Logger LOG = LoggerFactory.getLogger(ArduinoSerialPortConnector.class);

    private SerialPort serialPort;

    public ArduinoSerialPortConnector() {
        Optional<SerialPort> port = findArduinoUnoSerialPort();
        serialPort = port.orElse(null);
        connectToArduinoSerialPort();
    }

    public void connectToArduinoSerialPort() {

        if (serialPort != null) {
            serialPort.setBaudRate(9600);
            serialPort.openPort();

            LOG.info("Sleeping while arduino resetting");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {/*NOOP*/ }

            LOG.info("Port open     :" + serialPort.isOpen());
            LOG.info("Port baud rate:" + serialPort.getBaudRate());
            LOG.info("Data bits     :" + serialPort.getNumDataBits());
            LOG.info("Stop bits     :" + serialPort.getNumStopBits());

        }

    }


    public String sendTankCommand(Character character) {
        try {
            serialPort.getOutputStream().write(character.toString().getBytes());
            serialPort.getOutputStream().flush();
            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
            return "FAILED";
        } catch (InterruptedException e) {
            return "FAILED";
        }
        return "OK";
    }

    public static void main(String[] args) throws IOException {
        ArduinoSerialPortConnector arduino = new ArduinoSerialPortConnector();
       char[] chars = new char[]{'w','s','a','d'};
        for (int i=0;i<8;i++){
            arduino.sendTankCommand(chars[i%4]);
        }
        arduino.sendTankCommand('q');

//        try {
//            readStream(serialPort);
//            while (true) {
//                serialPort.getOutputStream().write("wsda".getBytes());
//                serialPort.getOutputStream().flush();
//                Thread.sleep(500);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("Closing");
//            port.ifPresent(SerialPort::closePort);
//        }
//

//        Arduino arduinoConnector = new Arduino("/dev/cu.usbmodem1431", 9600);
//        arduinoConnector.openConnection();
//        arduinoConnector.serialWrite('w');
//        arduinoConnector.closeConnection();
    }

    private static void readStream(SerialPort serialPort) throws IOException {
        System.out.println("Reading...");

        new Thread(() -> {
            InputStream reader = serialPort.getInputStream();
            byte[] buffer = new byte[1000];
            try {
                while (true) {
                    if (reader.available() > 0) {
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

    public void addDataListener(SerialPort serialPort) {

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
        System.out.println(":::" + dataListenerAdded);
    }
}
///dev/cu.usbmodem1431