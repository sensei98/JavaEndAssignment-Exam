package GuitarShopFX.Scenes.Orders;

import GuitarShopFX.Data.Database;
import GuitarShopFX.Model.Article;
import GuitarShopFX.Model.Order;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import java.time.LocalDate;

public class OrderListScene {
    private Stage window;
    private Database db;
    private ObservableList <Order> orders;
    private ObservableList<Article> articles;

    public Stage getWindow() {
        return window;
    }

    public OrderListScene() {
        window = new Stage();
        window.setTitle("Guitarshop FX - View Order List");

        db = new Database();
        orders = FXCollections.observableArrayList(db.getOrders());

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));

        VBox orderList = new VBox();
        Label orderListLabel = new Label("ORDER LIST");

        TableView<Order> customersTableView = new TableView<>();

        TableColumn orderNo = new TableColumn("Order #");
        orderNo.setMinWidth(150);
        orderNo.setCellValueFactory(new PropertyValueFactory<Order,Integer>("orderID"));

        TableColumn date = new TableColumn("Date");
        date.setMinWidth(150);
        date.setCellValueFactory(new PropertyValueFactory<Order,LocalDate>("date"));

        TableColumn customerName = new TableColumn("Customer name");
        customerName.setMinWidth(150);
        customerName.setCellValueFactory(new PropertyValueFactory<Order,String>("customerName"));

        TableColumn city = new TableColumn("City");
        city.setMinWidth(150);
        city.setCellValueFactory(new PropertyValueFactory<Order,String>("customerCity"));

        TableColumn phoneNo = new TableColumn("Phone #");
        phoneNo.setMinWidth(150);
        phoneNo.setCellValueFactory(new PropertyValueFactory<Order,String>("phoneNo"));


        TableColumn emailAddress = new TableColumn("Email address");
        emailAddress.setMinWidth(150);
        emailAddress.setCellValueFactory(new PropertyValueFactory<Order,String>("email"));

        TableColumn count = new TableColumn("Count");
        count.setMinWidth(150);
        count.setCellValueFactory(new PropertyValueFactory<Order,Integer>("count"));

        TableColumn total = new TableColumn("Total");
        total.setMinWidth(150);
        total.setCellValueFactory(new PropertyValueFactory<Order,Double>("totalPrice"));

        customersTableView.getColumns().addAll(orderNo,date,customerName,city,phoneNo,emailAddress,count,total);
        orderList.getChildren().addAll(orderListLabel,customersTableView);

        customersTableView.setItems(orders);

        VBox forTableView = new VBox();
        Label details = new Label("DETAILS");

        GridPane.setConstraints(forTableView,0,2);
        GridPane.setConstraints(details,0,1);

        TableView<Article> detailsTableView= new TableView<>();

        TableColumn uuid = new TableColumn("uuid");
        uuid.setMinWidth(150);
        uuid.setCellValueFactory(new PropertyValueFactory<Order,String>("uuid"));

        TableColumn brand = new TableColumn("Brand");
        brand.setMinWidth(150);
        brand.setCellValueFactory(new PropertyValueFactory<Order,String>("brand"));


        TableColumn model = new TableColumn("Model");
        model.setMinWidth(150);
        model.setCellValueFactory(new PropertyValueFactory<Order,String>("model"));

        TableColumn acoustic = new TableColumn("Acoustic");
        acoustic.setMinWidth(150);
        acoustic.setCellValueFactory(new PropertyValueFactory<Order,Boolean>("acoustic"));

        TableColumn type = new TableColumn("Type");
        type.setMinWidth(150);
        type.setCellValueFactory(new PropertyValueFactory<Order,String>("guitarType"));

        TableColumn price = new TableColumn("Price");
        price.setMinWidth(150);
        price.setCellValueFactory(new PropertyValueFactory<Order,Double>("price"));

        TableColumn quantity = new TableColumn("Quantity");
        quantity.setMinWidth(150);
        quantity.setCellValueFactory(new PropertyValueFactory<Order,Integer>("quantity"));

        detailsTableView.getColumns().addAll(uuid,brand,model,acoustic,type,price,quantity);
        forTableView.getChildren().addAll(details,detailsTableView);

        customersTableView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Order>() {
                    @Override
                    public void changed(ObservableValue<? extends Order> observableValue, Order order, Order newOrder) {
                        articles = FXCollections.observableArrayList( newOrder.getArticles());
                        detailsTableView.setItems(articles);
                    }
                }
        );
        layout.getChildren().addAll(orderList,forTableView);

        Scene scene = new Scene(layout);
        window.setScene(scene);
    }
}
