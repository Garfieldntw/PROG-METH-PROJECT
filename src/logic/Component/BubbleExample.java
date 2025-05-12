package logic.Component;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import logic.GameController;
import logic.Component.GameScenes.BottomBar.PlayerStatusPane;

public class BubbleExample extends Application {

    @Override
    public void start(Stage primaryStage) {
    	GameController.setupScene();
        PlayerStatusPane playerStatus = new PlayerStatusPane(GameController.getPlayerCanvas().getP1());
      
        Scene scene = new Scene(playerStatus);
        primaryStage.setTitle("Bubble Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
