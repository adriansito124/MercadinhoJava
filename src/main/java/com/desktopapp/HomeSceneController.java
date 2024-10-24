package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.User;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class HomeSceneController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = HomeSceneController.class
                .getResource("HomeScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button goLogin;

    @FXML
    protected Button goRegister;

    @FXML
    protected void logar(MouseEvent e) throws Exception {
        Stage crrStage = (Stage)goLogin
            .getScene()
            .getWindow();
        
        Scene newScene = LoginSceneController.CreateScene();
        crrStage.setScene(newScene);
    }

    @FXML
    protected void registrar(MouseEvent e) throws Exception {
        Stage crrStage = (Stage)goRegister
            .getScene()
            .getWindow();
        
        Scene newScene = RegisterSceneController.CreateScene();
        crrStage.setScene(newScene);
    }
}