package com.desktopapp;

import java.net.URL;
import java.util.List;

import com.desktopapp.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class RegisterSceneController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = RegisterSceneController.class
                .getResource("RegisterScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button btLogin;

    @FXML
    protected TextField tfLogin;

    @FXML
    protected PasswordField pfPass;

    @FXML
    protected CheckBox cbPass;

    @FXML
    protected void submit(ActionEvent e) throws Exception {
        Context ctx = new Context();
        User user = new User();
        
        user.setName(tfLogin.getText());
        user.setPassword(pfPass.getText());

        ctx.begin();
        ctx.persist(user);
        ctx.commit();

        var crrStage = (Stage) btLogin
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = HomeSceneController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }
}