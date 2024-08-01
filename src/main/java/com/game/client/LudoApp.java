package com.game.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

@SpringBootApplication
public class LudoApp {

    public static void main(String[] args) {
        SpringApplication.run(LudoApp.class, args);
        Application.launch(LudoFx.class, args);
    }
}