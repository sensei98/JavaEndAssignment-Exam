package GuitarShopFX.Scenes.StockMaintenance;

import GuitarShopFX.Data.Database;
import GuitarShopFX.Model.Article;
import GuitarShopFX.Scenes.ArticleW.ArticleListScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StockScene {
    private Stage window;
    private ObservableList<Article> articlesList;
    private Database db;
    private Article selectedItem;

    public Stage getWindow() {
        return window;
    }

    public StockScene() {
        window = new Stage();
        window.setHeight(700);
        window.setWidth(900);
        window.setTitle("Guitarshop FX - Stock maintenance");

        db = new Database();
        articlesList = FXCollections.observableArrayList(db.getArticleList());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));

        VBox stockLayout = new VBox();
        Label stockLabel = new Label("STOCK MAINTENANCE");
        stockLayout.setPadding(new Insets(30));

        TableView<Article> stockTableView = new TableView<>();
        stockTableView.setRowFactory(new Callback<TableView<Article>, TableRow<Article>>() {
            @Override
            public TableRow<Article> call(TableView<Article> stockTableView) {
                TableRow<Article> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(!row.isEmpty()){
                              selectedItem = row.getItem();
                        }
                    }
                });
             return row;
            }
        });
        TableColumn quantity = new TableColumn("Quantity");
        quantity.setMinWidth(150);
        quantity.setCellValueFactory(new PropertyValueFactory<Article,Integer>("quantity"));
        TableColumn brand = new TableColumn("Brand");
        brand.setMinWidth(150);
        brand.setCellValueFactory(new PropertyValueFactory<Article,String>("brand"));
        TableColumn model = new TableColumn("Model");
        model.setMinWidth(150);
        model.setCellValueFactory(new PropertyValueFactory<Article,String>("model"));
        TableColumn acoustic = new TableColumn("Acoustic");
        acoustic.setMinWidth(150);
        acoustic.setCellValueFactory(new PropertyValueFactory<Article,Boolean>("acoustic"));
        TableColumn type = new TableColumn("Guitar Type");
        type.setMinWidth(150);
        type.setCellValueFactory(new PropertyValueFactory<Article, String>("guitarType"));

        stockTableView.getColumns().addAll(quantity,brand,model,acoustic,type);
        stockLayout.getChildren().addAll(stockLabel,stockTableView);

        stockTableView.setItems(articlesList);

        VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setMaxHeight(100);
        box.setMaxWidth(100);
        GridPane.setConstraints(box,0,1);
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        CheckBox negate = new CheckBox("Negate");
        Button addBtn = new Button("Add");

        Button addProductBtn = new Button("Add Product");
        addProductBtn.setMinWidth(200);
        addProductBtn.setPadding(new Insets(10));

        addProductBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddProduct addProduct = new AddProduct();
                addProduct.getWindow().showAndWait();

                if(addProduct.getArticles()!= null){
                    articlesList.add(addProduct.getArticles());
                }
            }
        });
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               if(negate.isSelected()){
                   articlesList.remove(selectedItem);
                   selectedItem.setQuantity(selectedItem.getQuantity() - Integer.parseInt(quantityField.getText()));
                   articlesList.add(selectedItem);
               }
               else{
                   articlesList.remove(selectedItem);
                   selectedItem.setQuantity(selectedItem.getQuantity() + Integer.parseInt(quantityField.getText()));
                   articlesList.add(selectedItem);
               }
            }
        });

        box.getChildren().addAll(quantityField,negate,addBtn,addProductBtn);

        gridPane.getChildren().addAll(stockLayout,box);
        Scene scene = new Scene(gridPane);
        window.setScene(scene);
    }
}
