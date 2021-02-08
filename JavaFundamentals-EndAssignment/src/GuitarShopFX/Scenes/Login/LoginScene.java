package GuitarShopFX.Scenes.Login;

import GuitarShopFX.Scenes.DashBoard.Dashboard;
import GuitarShopFX.Data.Database;
import GuitarShopFX.Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class LoginScene {
    private Stage window;
    private ObservableList<Person> personsList ;
    private Boolean canLogin = false;
    private Database db;

    public Stage getWindow() {
        return window;
    }

    public LoginScene() {
        window = new Stage();
        window.setHeight(300);
        window.setWidth(400);
        window.setTitle("Guitarshop FX - Login");

        db = new Database();
        personsList = FXCollections.observableArrayList(db.getPersonList());

        Label loginLabel = new Label("Login");
        Label usernameLabel = new Label("username");
        TextField usernameTxtField = new TextField();
        usernameTxtField.setPromptText("username");
        Label passwordLabel = new Label("password");
        PasswordField passwordTxtField = new PasswordField();
        passwordTxtField.setPromptText("password");
        Button loginButton = new Button("Login");
        Label errorLabel = new Label();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                for(Person person : personsList){
                    if(usernameTxtField.getText().equals(person.getUsername()) &&
                            passwordTxtField.getText().equals(person.getPassword())){
                        canLogin = true;
                        Dashboard dashBoard = new Dashboard(person);
                        dashBoard.getWindow().show();

                        errorLabel.setText("");
                        usernameTxtField.clear();
                        passwordTxtField.clear();

                    }
                }
                if(canLogin.equals(false)){
                    errorLabel.setText("Incorrect login!");
                    errorLabel.setTextFill(Color.RED);


                }

            }
        });

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(10));
        gridpane.setHgap(10);
        gridpane.setVgap(8);

        GridPane.setConstraints(loginLabel,0,0);
        GridPane.setConstraints(usernameLabel,0,1);
        GridPane.setConstraints(usernameTxtField,1,1);
        GridPane.setConstraints(passwordLabel,0,2);
        GridPane.setConstraints(passwordTxtField,1,2);
        GridPane.setConstraints(loginButton,0,3);
        GridPane.setConstraints(errorLabel,0,5);

        gridpane.getChildren().addAll(loginLabel,usernameLabel,usernameTxtField,passwordLabel
                ,passwordTxtField,loginButton,errorLabel);

        Scene scene = new Scene(gridpane);
        window.setScene(scene);
    }

}
