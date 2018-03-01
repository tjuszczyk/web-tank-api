package pl.tj.tanks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.tj.tanks.services","pl.tj.tanks.resources"})
public class TanksApiConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(TanksApiConfiguration.class, args);
    }

}