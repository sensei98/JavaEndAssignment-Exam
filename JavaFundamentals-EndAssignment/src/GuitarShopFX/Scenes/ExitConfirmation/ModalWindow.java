package GuitarShopFX.Scenes.ExitConfirmation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ModalWindow {
    private Stage window;

    public Stage getWindow() {
        return window;
    }
    public ModalWindow(){
        window = new Stage();
        window.setHeight(200);
        window.setWidth(200);
        window.setTitle("INFO");

        window.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                StageCloseTrigger sct = new StageCloseTrigger();
                sct.getWindow().showAndWait();
            }
        });

    }

}
