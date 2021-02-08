package GuitarShopFX.Scenes.Orders;

import GuitarShopFX.Model.Article;
import GuitarShopFX.Model.Customer;
import GuitarShopFX.Model.Order;
import GuitarShopFX.Scenes.ArticleW.ArticleListScene;
import GuitarShopFX.Scenes.Customers.CustomerListScene;
import GuitarShopFX.Scenes.Orders.OrderConfirmation.ConfirmScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class OrderScene {
    private Stage window;
    public TableView<Article> articlesTableView;
    private Article selectedItem;
    int orderNo = 10000;

    public Article getSelectedItem() {
        return selectedItem;
    }
    public Stage getWindow() {
        return window;
    }

    public OrderScene() {
        window = new Stage();
        window.setHeight(900);
        window.setWidth(1000);
        window.setTitle("Guitarshop FX - Create an Order");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        VBox menuBarLayout = new VBox();
        menuBarLayout.setPadding(new Insets(10));

        MenuBar menuBar = new MenuBar();
        Menu homeMenu = new Menu("Home");
        Menu salesMenu = new Menu("Sales");
        MenuItem orderItem = new Menu("Order");
        MenuItem listOrdersItem = new Menu("Order List");

        salesMenu.getItems().addAll(orderItem,listOrdersItem);
        menuBar.getMenus().addAll(homeMenu,salesMenu);

        menuBarLayout.getChildren().add(menuBar);

        GridPane topLeftLayout = new GridPane();
        topLeftLayout.setPadding(new Insets(10));
        topLeftLayout.setHgap(10);
        topLeftLayout.setVgap(8);
        topLeftLayout.setStyle("-fx-background-color: #C0C0C0;");

        Label firstName = new Label("First Name: ");
        Label firstNameAns = new Label();
        GridPane.setConstraints(firstName,0,0);
        GridPane.setConstraints(firstNameAns,1,0);

        Label lastName = new Label("Last Name: ");
        Label lastNameAns = new Label();
        GridPane.setConstraints(lastName,0,1);
        GridPane.setConstraints(lastNameAns,1,1);

        Label streetAddress = new Label("Street address: ");
        Label streetAddressAns = new Label();
        GridPane.setConstraints(streetAddress,0,2);
        GridPane.setConstraints(streetAddressAns,1,2);

        Label city = new Label("City: ");
        Label cityAns = new Label();
        GridPane.setConstraints(city,0,3);
        GridPane.setConstraints(cityAns,1,3);

        Label phoneNumber = new Label("Phone Number: ");
        Label phoneNumberAns = new Label();
        GridPane.setConstraints(phoneNumber,0,4);
        GridPane.setConstraints(phoneNumberAns,1,4);

        Label emailAddress = new Label("Email address: ");
        Label emailAddressAns = new Label();
        GridPane.setConstraints(emailAddress,0,5);
        GridPane.setConstraints(emailAddressAns,1,5);

        Label createOrderLabel = new Label("Create order #" + orderNo++);
        GridPane.setConstraints(createOrderLabel,1,0);
        Label customerLabel = new Label("Customer");
        GridPane.setConstraints(customerLabel,1,2);
        TextField searchField = new TextField();
        GridPane.setConstraints(searchField,1,3);
        Button searchBtn = new Button("Search");

        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CustomerListScene customerWindow = new CustomerListScene();
                customerWindow.getWindow().showAndWait();

                Customer customer = customerWindow.getSelectedCustomer();
                firstNameAns.setText(customer.getFirstName());
                lastNameAns.setText(customer.getLastName());
                streetAddressAns.setText(customer.getStreetAddress());
                cityAns.setText(customer.getCity());
                phoneNumberAns.setText(customer.getPhoneNo());
                emailAddressAns.setText(customer.getEmail());
            }
        });
        GridPane.setConstraints(searchBtn,2,3);

        HBox rightLayout = new HBox();
        rightLayout.setPadding(new Insets(10,10,10,10));
        rightLayout.setSpacing(40);
        rightLayout.setMinHeight(100);
        rightLayout.setMinWidth(400);
        rightLayout.setStyle("-fx-background-color: #C0C0C0;");

        GridPane topRightLayout = new GridPane();
        topRightLayout.setHgap(10);
        topRightLayout.setVgap(8);
        topRightLayout.setPadding(new Insets(10));

        articlesTableView = new TableView<>();
        articlesTableView.setEditable(true);
        articlesTableView.getSelectionModel().setCellSelectionEnabled(false);
        articlesTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn quantityColumn = new TableColumn("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Article, Integer>("quantity"));

        TableColumn brandColumn = new TableColumn("Brand");
        brandColumn.setMinWidth(100);
        brandColumn.setCellValueFactory(new PropertyValueFactory<Article,String>("brand"));

        TableColumn modelColumn = new TableColumn("Model");
        modelColumn.setMinWidth(100);
        modelColumn.setCellValueFactory(new PropertyValueFactory<Article,String>("model"));

        TableColumn acousticColumn = new TableColumn("Acoustic");
        acousticColumn.setMinWidth(100);
        acousticColumn.setCellValueFactory(new PropertyValueFactory<Article,Boolean>("acoustic"));

        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("guitarType"));

        TableColumn priceColumn = new TableColumn("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<Article,Double>("price"));

        VBox buttonsBox = new VBox();
        buttonsBox.setSpacing(60);
        buttonsBox.setPadding(new Insets(35,0,0,140));

        Button addBtn = new Button("Add");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArticleListScene articleWindow = new ArticleListScene();
                articleWindow.getWindow().showAndWait();

                selectedItem = articleWindow.getSelectedItem();
                articlesTableView.getItems().add(selectedItem);
            }
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Article selectedItem = articlesTableView.getSelectionModel().getSelectedItem();
                articlesTableView.getItems().remove(selectedItem);
            }
        });

        Button confirmBtn = new Button("Confirm");
        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConfirmScene confirmWindow = new ConfirmScene();
                confirmWindow.getWindow().show();

                confirmWindow.getCustomerName().setText(firstNameAns.getText()+ " " + lastNameAns.getText());
                confirmWindow.getAddress().setText(streetAddressAns.getText());
                confirmWindow.getCity().setText(cityAns.getText());
                confirmWindow.getPhoneNo().setText(phoneNumberAns.getText());
                confirmWindow.getEmailAddress().setText(emailAddressAns.getText());

                confirmWindow.getCustomerQty().setText(String.valueOf(selectedItem.getQuantity()));
                confirmWindow.getCustomerBrand().setText(selectedItem.getBrand());
                confirmWindow.getCustomerModel().setText(selectedItem.getModel());
                confirmWindow.getCustomerType().setText(selectedItem.getGuitarType());
                confirmWindow.getCustomerPrice().setText(String.valueOf(selectedItem.getPrice()));
                confirmWindow.getTotalPrice().setText(String.valueOf(selectedItem.getTotalPrice()));
            }
        });

        Button resetBtn = new Button("Reset");
        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                articlesTableView.getItems().clear();
                firstNameAns.setText("");
                lastNameAns.setText("");
                cityAns.setText("");
                phoneNumberAns.setText("");
                emailAddressAns.setText("");
                streetAddressAns.setText("");
            }
        });

        Label articleLabel = new Label("ARTICLES");
        buttonsBox.getChildren().addAll(addBtn,deleteBtn,confirmBtn,resetBtn,articleLabel);
        articlesTableView.getColumns().addAll(quantityColumn,brandColumn,modelColumn,acousticColumn,typeColumn,priceColumn);

        topRightLayout.getChildren().addAll(firstName,lastName,streetAddress,city,phoneNumber,emailAddress
        , firstNameAns,lastNameAns,streetAddressAns,cityAns,phoneNumberAns,emailAddressAns);

        topLeftLayout.getChildren().addAll(createOrderLabel,customerLabel,searchField,searchBtn);

        rightLayout.getChildren().addAll(topRightLayout);

        borderPane.setRight(rightLayout);
        borderPane.setLeft(topLeftLayout);
        borderPane.setTop(menuBarLayout);
        borderPane.setBottom(articlesTableView);
        borderPane.setCenter(buttonsBox);

        Scene scene = new Scene(borderPane);
        window.setScene(scene);
    }
}
