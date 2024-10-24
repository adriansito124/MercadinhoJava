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
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class NewProductSceneController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = NewProductSceneController.class
                .getResource("NewProductScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button btLogin;

    @FXML
    protected TextField tfLogin;

    @FXML
    protected TextField pfPass;

    @FXML
    protected TextField qtd;

    @FXML
    protected CheckBox cbPass;
    

    @FXML
    protected void submit(ActionEvent e) throws Exception {
        Context ctx = new Context();
        Produto product = new Produto();
        
        product.setName(tfLogin.getText());

        Float a = Float.parseFloat(pfPass.getText());

        if ((a-a)==0) {
            product.setPrice(pfPass.getText());
        }

        int b = Integer.parseInt(qtd.getText());

        if ((b-b)==0) {
            product.setQtd(qtd.getText());
        }
        
        ctx.begin();
        ctx.persist(product);
        ctx.commit();

        var crrStage = (Stage) btLogin
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = MarketSceneController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }
}