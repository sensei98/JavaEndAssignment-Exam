package GuitarShopFX.Scenes.ExitConfirmation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;

public class StageCloseTrigger {
    private Stage window;

    public Stage getWindow() {
        return window;
    }

    public StageCloseTrigger(){
        window = new Stage();
        window.setTitle("INFO");
        window.setHeight(200);
        window.setWidth(200);

        //HBox hbox = new HBox();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(8);
        gridPane.setPadding(new Insets(10));

        Label heading = new Label("Close the window?");
        GridPane.setConstraints(heading,0,0);

        Button OkBtn = new Button("OK");
        GridPane.setConstraints(OkBtn,0,1);

        OkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.close();
            }
        });

        Button cancelbtn = new Button("Cancel");
        GridPane.setConstraints(cancelbtn,1,1);

        cancelbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ModalWindow mw = new ModalWindow();
                mw.getWindow().show();
            }
        });

        gridPane.getChildren().addAll(heading,OkBtn,cancelbtn);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
    }


}
