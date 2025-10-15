package qivapi.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "qivapi.backend.repository.jpa")
public class QuiApiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuiApiBackendApplication.class, args);
    }

}
