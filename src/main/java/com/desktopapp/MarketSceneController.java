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

public class MarketSceneController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = MarketSceneController.class
                .getResource("MarketScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button newProduct;

    @FXML
    protected Button myProduct;

    @FXML
    protected Button goHome;

    @FXML
    protected void novoproduto(MouseEvent e) throws Exception {
        Stage crrStage = (Stage)newProduct
            .getScene()
            .getWindow();
        
        Scene newScene = NewProductSceneController.CreateScene();
        crrStage.setScene(newScene);
    }

    @FXML
    protected void casa(MouseEvent e) throws Exception {
        Stage crrStage = (Stage)goHome
            .getScene()
            .getWindow();
        
        Scene newScene = HomeSceneController.CreateScene();
        crrStage.setScene(newScene);
    }

    @FXML
    protected void meusprodutos(MouseEvent e) throws Exception {
        Stage crrStage = (Stage)myProduct
            .getScene()
            .getWindow();
        
        Scene newScene = MyProductSceneController.CreateScene();
        crrStage.setScene(newScene);
    }

    @FXML
    protected void deletar(MouseEvent e) throws Exception {
        Stage crrStage = (Stage)goHome
            .getScene()
            .getWindow();
        
        Scene newScene = DeleteSceneController.CreateScene();
        crrStage.setScene(newScene);
    }

    @FXML
    protected void editar(MouseEvent e) throws Exception {
        Stage crrStage = (Stage)myProduct
            .getScene()
            .getWindow();
        
        Scene newScene = MyProductSceneController.CreateScene();
        crrStage.setScene(newScene);
    }
}