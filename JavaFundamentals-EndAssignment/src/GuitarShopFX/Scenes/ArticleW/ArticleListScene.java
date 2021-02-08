package GuitarShopFX.Scenes.ArticleW;

import GuitarShopFX.Data.Database;
import GuitarShopFX.Model.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ArticleListScene {
    private Stage window;
    private ObservableList<Article> articleList;
    private Database db;
    private Article selectedItem;
    public Article getSelectedItem(){
        return selectedItem;
    }
    public Stage getWindow() {
        return window;
    }

    public ArticleListScene(){

        window = new Stage();
        window.setTitle("Guitarshop FX - Add article");

        db = new Database();
        articleList = FXCollections.observableArrayList(db.getArticleList());

        VBox overallLayout = new VBox();
        overallLayout.setPadding(new Insets(10,10,10,10));

        VBox boxForTableView = new VBox();
        TableView<Article> tableview = new TableView<>();
        TextField quantityField = new TextField();

        tableview.setRowFactory(new Callback<TableView<Article>, TableRow<Article>>() {
            @Override
            public TableRow<Article> call(TableView<Article> articleTableView) {
                TableRow<Article> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!row.isEmpty()){
                            selectedItem = row.getItem();
                      }
                    }
                });
                return row;
            }
        });

        TableColumn brandColumn = new TableColumn("Brand");
        brandColumn.setMinWidth(200);
        brandColumn.setCellValueFactory(new PropertyValueFactory<Article,String>("brand"));

        TableColumn modelCol = new TableColumn("Model");
        modelCol.setMinWidth(200);
        modelCol.setCellValueFactory(new PropertyValueFactory<Article,String>("model"));

        TableColumn acousticCol = new TableColumn("Acoustic");
        acousticCol.setMinWidth(200);
        acousticCol.setCellValueFactory(new PropertyValueFactory<Article,String>("acoustic"));

        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setMinWidth(200);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("guitarType"));

        TableColumn priceCol = new TableColumn("Price");
        priceCol.setMinWidth(200);
        priceCol.setCellValueFactory(new PropertyValueFactory<Article,Double>("price"));

        tableview.getColumns().addAll(brandColumn,modelCol,acousticCol,typeColumn,priceCol);
        boxForTableView.getChildren().add(tableview);

        Label errorMessage = new Label();
        Button addBtn = new Button("Add");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Integer.parseInt(quantityField.getText()) > selectedItem.getQuantity()){
                    errorMessage.setText("Not enough in stock for " + selectedItem.getBrand() + ". Only "+
                            selectedItem.getQuantity()+ " remaining");
                    errorMessage.setTextFill(Color.RED);
                }
                else{
                    selectedItem.setQuantity(selectedItem.getQuantity() - Integer.parseInt(quantityField.getText()));
                    selectedItem.setQuantity(Integer.parseInt(quantityField.getText()));
                    window.close();
                }
            }
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.close();
            }
        });
        tableview.setItems(articleList);
        overallLayout.getChildren().addAll(boxForTableView,quantityField,addBtn,cancelBtn,errorMessage);

        Scene scene = new Scene(overallLayout);
        window.setScene(scene);
    }
}
