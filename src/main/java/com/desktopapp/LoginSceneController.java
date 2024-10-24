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

public class LoginSceneController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = LoginSceneController.class
                .getResource("LoginScreen.fxml");
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
        var users = ctx.find(User.class,
                "SELECT u FROM User u WHERE u.name = :arg0",
                tfLogin.getText());
        System.out.println(users);
        if (users.size() == 0) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Usuário não está cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }
        var user = users.get(0);
        if (!pfPass.getText().equals(user.getPassword())) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Senha incorreta!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }
        var crrStage = (Stage) btLogin
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = MarketSceneController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }
}