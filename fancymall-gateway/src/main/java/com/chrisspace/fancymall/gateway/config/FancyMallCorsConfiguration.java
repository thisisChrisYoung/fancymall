package com.chrisspace.fancymall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class FancyMallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource() {};
        CorsConfiguration configuration = new CorsConfiguration();
        // 设置跨域
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowCredentials(true);

        configurationSource.registerCorsConfiguration("/**",configuration);
        return new CorsWebFilter(configurationSource);
    }
}
