package pl.tj.tanks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import pl.tj.tanks.controller.TankConnector;
import pl.tj.tanks.controller.usb.ArduinoSerialPortConnector;
import pl.tj.tanks.services.StubbedConnector;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.tj.tanks.services", "pl.tj.tanks.resources"})
@EnableAutoConfiguration
public class TanksApiConfiguration {
    public static final Logger LOG = LoggerFactory.getLogger(TanksApiConfiguration.class);

    public static void main(String[] args) {
        SpringApplication.run(TanksApiConfiguration.class, args);
    }


    @Bean
    public TankConnector connector(
            @Value("${arduino.connector.enabled}") boolean serialPortConnectorEnabled,
            @Value("${arduino.connector.serial_port}") String serialPortIdentifier) {
        if (!serialPortConnectorEnabled) {
            LOG.info("Stubbed arduino connector initialisation");
            return new StubbedConnector();
        } else {
            LOG.info("ArduinoSerialPortConnector initialisation");
            return new ArduinoSerialPortConnector(serialPortIdentifier);
        }
    }

}