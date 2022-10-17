package com.chrisspace.fancymall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FancymallThirdpartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FancymallThirdpartyApplication.class, args);
    }

}
