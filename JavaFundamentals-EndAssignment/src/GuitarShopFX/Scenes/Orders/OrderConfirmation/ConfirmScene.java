package GuitarShopFX.Scenes.Orders.OrderConfirmation;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConfirmScene {
    private Stage window;
    private Label customerName;
    private Label address;
    private Label city;
    private Label phoneNo;
    private Label emailAddress;
    private Label customerQty;
    private Label customerBrand;
    private Label customerModel;
    private Label customerType;
    private Label customerPrice;
    private Label totalPrice;

    public Label getCustomerName() { return customerName;}
    public Label getAddress() { return address;}
    public Label getCity() { return city;}
    public Label getPhoneNo() { return phoneNo;}
    public Label getEmailAddress() { return emailAddress;}
    public Label getCustomerQty() { return customerQty;}
    public Label getCustomerBrand() { return customerBrand;}
    public Label getCustomerModel() { return customerModel;}
    public Label getCustomerType() { return customerType;}
    public Label getCustomerPrice() { return customerPrice;}
    public Label getTotalPrice() { return totalPrice;}
    public Stage getWindow() { return window;}

    public ConfirmScene(){
        window = new Stage();
        window.setHeight(400);
        window.setWidth(600);
        window.setTitle("Guitarshop FX - Confirm order");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(8);

        Label customerLabel = new Label("Customer: ");
        GridPane.setConstraints(customerLabel,0,0);
        customerName = new Label();
        GridPane.setConstraints(customerName,1,0);
        address = new Label();
        GridPane.setConstraints(address,1,1);
        city = new Label();
        GridPane.setConstraints(city,1,2);
        phoneNo = new Label();
        GridPane.setConstraints(phoneNo,1,3);
        emailAddress = new Label();
        GridPane.setConstraints(emailAddress,1,4 );

        Label qty = new Label("Qty");
        GridPane.setConstraints(qty,0,6);
        Label brand = new Label("Brand");
        GridPane.setConstraints(brand,1,6);
        Label model = new Label("Model");
        GridPane.setConstraints(model,5,6);
        Label type = new Label("Type");
        GridPane.setConstraints(type,7,6);
        Label price = new Label("Price");
        GridPane.setConstraints(price,9,6);

        customerQty = new Label();
        GridPane.setConstraints(customerQty,0,7);
        customerBrand = new Label();
        GridPane.setConstraints(customerBrand,1,7);
        customerModel = new Label();
        GridPane.setConstraints(customerModel,5,7);
        customerType = new Label();
        GridPane.setConstraints(customerType,7,7);
        customerPrice = new Label();
        GridPane.setConstraints(customerPrice,9,7);

        Label totalPriceLabel = new Label("Total price: ");
        GridPane.setConstraints(totalPriceLabel,0,9);
        totalPrice = new Label();
        GridPane.setConstraints(totalPrice,1,9);
        Button confirmBtn = new Button("Confirm");
        GridPane.setConstraints(confirmBtn,0,11);

        gridPane.getChildren().addAll(customerLabel,customerName,address,city,phoneNo,emailAddress,
                qty,brand,model,type,price,customerQty,customerBrand,customerModel,customerType,customerPrice,
                totalPriceLabel,totalPrice,confirmBtn);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
    }

}
