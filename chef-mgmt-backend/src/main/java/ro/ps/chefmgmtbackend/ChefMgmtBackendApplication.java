package ro.ps.chefmgmtbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ChefMgmtBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChefMgmtBackendApplication.class, args);
    }

}
