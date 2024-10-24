package com.desktopapp;

import java.net.URL;
import java.nio.DoubleBuffer;
import java.util.List;

import com.desktopapp.model.Produto;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class DeleteSceneController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = NewProductSceneController.class
                .getResource("DeleteScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button btLogin;

    @FXML
    protected TextField myID;

    @FXML
    protected Button goBack;

    @FXML
    protected void voltar(MouseEvent e) throws Exception {
        Stage crrStage = (Stage) goBack
                .getScene()
                .getWindow();

        Scene newScene = MarketSceneController.CreateScene();
        crrStage.setScene(newScene);
    }


    @FXML
    protected void confirmar(MouseEvent e) throws Exception {
        Context ctx = new Context();

        var product = ctx.find(Produto.class, "SELECT u FROM Produto u WHERE u.id = :arg0", myID.getText());
        ctx.delete(product.get(0));

        var crrStage = (Stage) btLogin
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = MarketSceneController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }
}