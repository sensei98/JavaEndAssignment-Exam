package GuitarShopFX;
import GuitarShopFX.Scenes.ExitConfirmation.ModalWindow;
import GuitarShopFX.Scenes.Login.LoginScene;
import GuitarShopFX.Scenes.StockMaintenance.StockScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
//        LoginScene scene = new LoginScene();
//        scene.getWindow().show();

//


        //QUESTION 1 - EXIT CONFIRMATION
        ModalWindow mw = new ModalWindow();
        mw.getWindow().showAndWait();





//        //QUESTION 3 - ADDING PRODUCTS
//        StockScene sc = new StockScene();
//        sc.getWindow().show();
    }
}

