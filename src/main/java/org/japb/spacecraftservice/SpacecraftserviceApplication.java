package org.japb.spacecraftservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpacecraftserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpacecraftserviceApplication.class, args);
    }

}
