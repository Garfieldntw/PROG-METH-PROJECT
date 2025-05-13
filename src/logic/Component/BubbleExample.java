package logic.Component;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.GameController;
import logic.Component.GameScenes.EachPane;
import logic.Component.GameScenes.BottomBar.PlayerStatusPane;
import object.Wall;

public class BubbleExample extends Application {

    @Override
    public void start(Stage primaryStage) {
    	ImageView iv = new ImageView(new Image(getClass().getResource("/CarrotImage.png").toExternalForm()));
    	GameController.setupScene();
        EachPane ep = new EachPane(new Wall(0,0),0,0,0);
        ep.getChildren().add(iv);
      
        Scene scene = new Scene(ep);
        primaryStage.setTitle("Bubble Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
