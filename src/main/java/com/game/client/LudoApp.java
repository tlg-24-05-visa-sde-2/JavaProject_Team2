package com.game.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

@SpringBootApplication
public class LudoApp {

    public static void main(String[] args) {
<<<<<<< HEAD

//        GameController.execute();
        System.out.println("hello world");

=======
        SpringApplication.run(LudoApp.class, args);
        Application.launch(LudoFx.class, args);
>>>>>>> 6d5537e29b83bd3a48d0f3e60eb7116f65dd2b17
    }
}