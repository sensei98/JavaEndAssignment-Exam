package GuitarShopFX.Scenes.StockMaintenance;

import GuitarShopFX.Model.Article;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddProduct {
    private Stage window;
    private boolean isAcoustic;

    public Stage getWindow() {
        return window;
    }

    public Article getArticles() {
        return articles;
    }

    private Article articles;

    public AddProduct(){
        window = new Stage();
        window.setHeight(500);
        window.setWidth(500);
        window.setTitle("Guitarshop FX - Add product");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(8);
        gridPane.setPadding(new Insets(10));

        Label addProductLabel = new Label("Add Product");
        GridPane.setConstraints(addProductLabel,0,0);
        Label brandLabel = new Label("Brand");
        GridPane.setConstraints(brandLabel,0,1);
        TextField brandTextfield = new TextField();
        GridPane.setConstraints(brandTextfield,1,1);

        Label modellabel = new Label("Model");
        GridPane.setConstraints(modellabel,0,2);
        TextField modelTextfieid = new TextField();
        GridPane.setConstraints(modelTextfieid,1,2);

        Label acousticLabel = new Label("Acoustic");
        GridPane.setConstraints(acousticLabel,0,3);

        RadioButton radioButton = new RadioButton();
        if(radioButton.isSelected()){
            isAcoustic = true;
        }
        else{
            isAcoustic = false;
        }
        GridPane.setConstraints(radioButton,1,3);

        Label guitartypeLabel = new Label("Guitar Type");
        GridPane.setConstraints(guitartypeLabel,0,4);
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("REGULAR","BASS");
        GridPane.setConstraints(comboBox,1,4);

        Label quantityLabel = new Label("Quantity");
        GridPane.setConstraints(quantityLabel,0,5);
        TextField quantityTextfield = new TextField();
        GridPane.setConstraints(quantityTextfield,1,5);

        Button addProduct = new Button("Add product");
        GridPane.setConstraints(addProduct,0,7);
        addProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                articles = new Article(Integer.parseInt(quantityTextfield.getText()),brandTextfield.getText(),modelTextfieid.getText(),
                        radioButton.isSelected(),0,comboBox.getValue().toString());

                window.close();
            }
        });
        Button cancel = new Button("Cancel");
        GridPane.setConstraints(cancel,1,7);
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.close();
            }
        });

        gridPane.getChildren().addAll(addProductLabel,brandLabel,brandTextfield,modellabel,modelTextfieid,acousticLabel
        ,radioButton,guitartypeLabel,comboBox,quantityLabel,quantityTextfield,addProduct,cancel);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);


    }
}
