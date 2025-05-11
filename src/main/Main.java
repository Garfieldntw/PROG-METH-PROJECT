package main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameController;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		//MainMenuPane menuPane = new MainMenuPane();
		//Scene scene = new Scene(menuPane);
		
		GameController.setupScene();
		primaryStage.setTitle("game demo");
		primaryStage.setScene(GameController.getScene());
		primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setResizable(false);
	}
	
	
	public static void main(String[] args) {
        launch(args);
    }
}
