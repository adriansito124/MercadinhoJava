package com.desktopapp;

import com.desktopapp.model.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {

        User user = new User();
        user.setName("adm");
        user.setPassword("123");

        Context ctx = new Context();

        ctx.begin();
        ctx.persist(user);
        ctx.commit();
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = HomeSceneController.CreateScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
