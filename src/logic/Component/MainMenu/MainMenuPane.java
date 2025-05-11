package logic.Component.MainMenu;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.GameController;

public class MainMenuPane extends VBox{

	public MainMenuPane() {
		super();
		// TODO Auto-generated constructor stub
		Image gameLogo = new Image(getClass().getResource("/icons/music.png").toExternalForm());
		ImageView gameLogoView = new ImageView(gameLogo);
		Button start = new Button("Start game");
		start.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Platform.runLater(() -> GameController.mainToGameScene());
			}
		});
		
		Button howToPlay = new Button("How to play");
		
		HBox hBox = new HBox();
		Image soundIcon = new Image(getClass().getResource("/icons/music.png").toExternalForm());
        ImageView soundimageView = new ImageView(soundIcon);
        soundimageView.setFitWidth(30);  
        soundimageView.setFitHeight(30);
        Button sound = new Button();
        sound.setGraphic(soundimageView);  // Set icon
        sound.setStyle("-fx-background-color: transparent;");
        
		Image musicIcon = new Image(getClass().getResource("/icons/music.png").toExternalForm());
        ImageView imageView = new ImageView(musicIcon);
        imageView.setFitWidth(30);  
        imageView.setFitHeight(30);
        Button music = new Button();
        music.setGraphic(imageView);  // Set icon
        music.setStyle("-fx-background-color: transparent;");
        hBox.getChildren().addAll(sound,music);
        
		this.getChildren().addAll(gameLogoView,start,howToPlay,hBox);
	}


}
