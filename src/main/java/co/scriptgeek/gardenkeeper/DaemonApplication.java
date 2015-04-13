package co.scriptgeek.gardenkeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DaemonApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DaemonApplication.class, args);

        System.out.println("Spring boot application started!!!");
    }

}