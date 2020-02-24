package tech.shadowland.marshall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "tech.shadowland")
public class MarshallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarshallApplication.class, args);
    }

}
