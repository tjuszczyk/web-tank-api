package pl.tj.tanks.controller.usb;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class ArduinoSerialPortConnector {


    public static void main(String[] args) throws IOException {
        SerialPort port = null;
        try {
            SerialPort[] commPorts = SerialPort.getCommPorts();
            for (int i = 0; i < commPorts.length; i++) {
                System.out.println(commPorts[i].getDescriptivePortName());
                System.out.println(commPorts[i].getSystemPortName());
            }

            port = SerialPort.getCommPort("/dev/cu.usbmodem1431");
//            port = SerialPort.getCommPort("/dev/tty.usbmodem1431");
            port.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING & SerialPort.TIMEOUT_WRITE_SEMI_BLOCKING, 500, 500);
            port.setBaudRate(9600);

            port.openPort();
            Thread.sleep(5000);
            System.out.println("Sleeping while arduino resetting");

            System.out.println("Port open     :" + port.isOpen());
            System.out.println("Port baud rate:" + port.getBaudRate());
            System.out.println("Data bits     :" + port.getNumDataBits());
            System.out.println("Stop bits     :" + port.getNumStopBits());

            port.addDataListener(new SerialPortDataListener() {
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

            while(true){
                OutputStream outputStream = port.getOutputStream();
                outputStream.write("test\r\n".getBytes(Charset.forName("ASCII")));
                outputStream.write("test\n".getBytes(Charset.forName("ASCII")));
                outputStream.flush();
                Thread.sleep(300);
                System.out.println(".");
            }

//            outputStream.close();

//            System.out.println("Reading...");
//            InputStream reader = port.getInputStream();
//            byte[] buffer = new byte[1000];
//            while (reader.available() > 0) {
////                outputStream.write('a');
////                outputStream.flush();
//                outputStream.write("Hellof\r\n".getBytes());
//                System.out.println("Available: " + port.bytesAvailable());
//                int numRead = reader.read(buffer);
//                System.out.println("Read " + numRead + " bytes: " + new String(buffer, 0, numRead));
//            }
//            Thread.sleep(4000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            System.out.println("Closing");
            port.closePort();
        }


//        Arduino arduinoConnector = new Arduino("/dev/cu.usbmodem1431", 9600);
//        arduinoConnector.openConnection();
//        arduinoConnector.serialWrite('w');
//        arduinoConnector.closeConnection();
    }
}
///dev/cu.usbmodem1431