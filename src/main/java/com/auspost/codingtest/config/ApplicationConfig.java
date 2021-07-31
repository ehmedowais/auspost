package com.auspost.codingtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.tools.Server;
@Configuration
public class ApplicationConfig {
    @Bean
    Server h2Server() {
        Server server = new Server();
        try {
            server.runTool("-tcp");
            server.runTool("-tcpAllowOthers");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;

    }
}
