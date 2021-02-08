package GuitarShopFX.Scenes.DashBoard;

import GuitarShopFX.Model.Person;
import GuitarShopFX.Model.Sales;
import GuitarShopFX.Scenes.Orders.OrderListScene;
import GuitarShopFX.Scenes.Orders.OrderScene;
import GuitarShopFX.Scenes.StockMaintenance.StockScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

public class Dashboard {
    private Stage window;
    private String role;

    public Stage getWindow() {
        return window;
    }

    public Dashboard(Person person){
        window = new Stage();
        window.setHeight(600);
        window.setWidth(700);
        window.setTitle("Guitarshop FX - Dashboard");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));

        MenuBar menuBar = new MenuBar();
        Menu home = new Menu("Home");
        Menu sales = new Menu("Sales");
        MenuItem listOfOrders = new MenuItem("List Orders");
        listOfOrders.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OrderListScene scene = new OrderListScene();
                scene.getWindow().show();
            }
        });

        if(person instanceof Sales){
            MenuItem order = new MenuItem("Order");
            order.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    OrderScene orderWindow = new OrderScene();
                    orderWindow.getWindow().show();
                }
            });
            sales.getItems().addAll(listOfOrders,order);
            menuBar.getMenus().addAll(home,sales);

            role = "  SALES";
        }
        else{
            Menu stock = new Menu("Stock");
            MenuItem maintain = new MenuItem("Maintain");
            maintain.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    StockScene stockWindow = new StockScene();
                    stockWindow.getWindow().show();
                }
            });
            sales.getItems().add(listOfOrders);
            stock.getItems().add(maintain);
            menuBar.getMenus().addAll(home,sales,stock);

            role = "  MANAGER";
        }


        vbox.getChildren().add(menuBar);

        Label welcomeLabel = new Label();
        welcomeLabel.setText("Welcome: " + person.getFullName());
        Label roleLabel = new Label();
        roleLabel.setText("Your role is: " + role);
        Label dateAndTimeLabel  = new Label();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateAndTimeLabel.setText(" Today is: " + java.time.LocalDateTime.now().format(format));

        vbox.getChildren().addAll(welcomeLabel,roleLabel,dateAndTimeLabel);

        Scene scene = new Scene(vbox);
        window.setScene(scene);

    }
}
