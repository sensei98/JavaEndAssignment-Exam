package GuitarShopFX.Scenes.Customers;

import GuitarShopFX.Data.Database;
import GuitarShopFX.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CustomerListScene {
    private Stage window;
    private ObservableList<Customer> customers;;
    private Database db;
    private Customer selectedCustomer;

    public Customer getSelectedCustomer(){
        return selectedCustomer;
    }
    public Stage getWindow() {
        return window;
    }

    public CustomerListScene() {
        window = new Stage();
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Guitarshop FX - Search customer");

        db = new Database();
        //customers = ReadCustomers();
        customers = FXCollections.observableArrayList(db.getCustomerList());

        VBox layout = new VBox();

        Label customerListLabel = new Label("Customer List");

        VBox tableViewBox = new VBox();
        tableViewBox.setPadding(new Insets(60));

        TableView<Customer> customersTableView = new TableView<>();
        customersTableView.setRowFactory(new Callback<TableView<Customer>, TableRow<Customer>>() {
            @Override
            public TableRow<Customer> call(TableView<Customer> customersTableView) {
                TableRow <Customer> row = new TableRow<>();

                row.setOnMouseClicked(
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                if(mouseEvent.getClickCount() == 2 && !row.isEmpty()){
                                    selectedCustomer = row.getItem();
                                    window.close();
                                }
                            }
                        }
                );
                return row;
            }
        });

        customersTableView.getSelectionModel().setCellSelectionEnabled(false);
        customersTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));

        TableColumn streetAddresscCol = new TableColumn("Street Address");
        streetAddresscCol.setMinWidth(100);
        streetAddresscCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("streetAddress"));

        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));

        TableColumn phoneNoCol = new TableColumn("Phone #");
        phoneNoCol.setMinWidth(100);
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNo"));

        TableColumn emailCol = new TableColumn("Email ");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));

        customersTableView.getColumns().addAll(firstNameCol,lastNameCol,streetAddresscCol,cityCol,phoneNoCol,emailCol);
        customersTableView.setItems(customers);

        tableViewBox.getChildren().add(customersTableView);
        layout.getChildren().addAll(customerListLabel,tableViewBox);

        Scene scene = new Scene(layout);
        window.setScene(scene);

    }
    public ObservableList<Customer> ReadCustomers(){

        try{
            Path path = Paths.get("");
            for(String line: Files.readAllLines(path)){
                Customer customer = new Customer(
                        line.split(",")[0],line.split(",")[1],
                        line.split(",")[2],line.split(",")[3],
                        line.split(",")[5],line.split(",")[6]);

            }
        }
        catch (IOException e){
            e.printStackTrace();
        };

//        Path path = Paths.get("CUSTOMERS.csv");
//        Stream<String> streamOfStrings = Files.lines(path);
//        Stream<String> streamWithCharset =
//        Files.lines(path, Charset.forName("UTF-8"));
        return customers;
    }
}
