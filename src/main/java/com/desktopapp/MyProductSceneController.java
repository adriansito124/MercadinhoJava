package com.desktopapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import com.desktopapp.model.Produto;
import com.desktopapp.model.User;

import jakarta.persistence.TypedQuery;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class MyProductSceneController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = MyProductSceneController.class
                .getResource("MyProductScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button goHome;

    @FXML
    protected Button lupa;

    @FXML
    protected TextField search;

    @FXML
    protected TableView<Produto> tabela;

    @FXML
    protected TableColumn<Produto, String> colPreco;

    @FXML
    protected TableColumn<Produto, Integer> colStatus;

    @FXML
    protected TableColumn<Produto, String> colProduto;

    @FXML
    protected TableColumn<Produto, String> colQtd;

    @FXML
    protected void inicio(MouseEvent e) throws Exception {
        Stage crrStage = (Stage) goHome
                .getScene()
                .getWindow();

        Scene newScene = MarketSceneController.CreateScene();
        crrStage.setScene(newScene);
    }

    @FXML
    protected void pesquisar(MouseEvent e) throws Exception {
        Context ctx = new Context();

        List<Produto> produtosEncontradosName = ctx.find(Produto.class,
                "SELECT u FROM Produto u WHERE u.name LIKE :arg0",
                "%" + search.getText() + "%");

        ObservableList<Produto> produtosList = FXCollections.observableArrayList(produtosEncontradosName);
        tabela.setItems(produtosList);
    }

    @FXML
    public void initialize() {
        colProduto.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("id"));

        Context ctx = new Context();

        colProduto.setCellFactory(TextFieldTableCell.forTableColumn());
        colPreco.setCellFactory(TextFieldTableCell.forTableColumn());
        colQtd.setCellFactory(TextFieldTableCell.forTableColumn());

        colProduto.setOnEditCommit(event -> {
            Produto produto = event.getRowValue();
            produto.setName(event.getNewValue());
            ctx.update(produto);
        });

        colPreco.setOnEditCommit(event -> {
            Produto produto = event.getRowValue();
            String newValue = event.getNewValue();

            try {
                Float a = Float.parseFloat(newValue);
                produto.setPrice(newValue);
                ctx.update(produto);
            } catch (NumberFormatException e) {

                System.out.println("Preço inválido: " + newValue);
            }
        });

        colQtd.setOnEditCommit(event -> {
            Produto produto = event.getRowValue();
            String newValue = event.getNewValue();

            try {
                int b = Integer.parseInt(newValue);
                produto.setQtd(newValue);
                ctx.update(produto);
            } catch (NumberFormatException e) {

                System.out.println("Quantidade inválida: " + newValue);
            }
        });

        tabela.setItems(ctx.listaproduto());
    }

}