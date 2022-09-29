package com.chrisspace.fancymall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FancymallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(FancymallCouponApplication.class, args);
    }

}
