package com.chrisspace.fancymall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.chrisspace.fancymall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class FancymallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(FancymallMemberApplication.class, args);
    }

}
