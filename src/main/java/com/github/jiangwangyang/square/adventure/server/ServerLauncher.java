package com.github.jiangwangyang.square.adventure.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.jiangwangyang.square.adventure")
public final class ServerLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ServerLauncher.class, args);
    }

}
