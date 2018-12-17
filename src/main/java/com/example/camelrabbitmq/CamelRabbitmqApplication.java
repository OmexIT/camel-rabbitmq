package com.example.camelrabbitmq;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:META-INF/spring/integration-context.xml")
public class CamelRabbitmqApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CamelRabbitmqApplication.class, args);
        CamelSpringBootApplicationController applicationController =
                applicationContext.getBean(CamelSpringBootApplicationController.class);
        applicationController.blockMainThread();
    }

}

