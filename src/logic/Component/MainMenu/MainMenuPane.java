package logic.Component.MainMenu;

import javafx.application.Platform;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;

public class MainMenuPane extends VBox {
	private final Image SOUNDON = new Image(getClass().getResource("/bombImage1.png").toExternalForm());
	private final Image SOUNDOFF = new Image(getClass().getResource("/bombImage2.png").toExternalForm());
	private final Image MUSICON = new Image(getClass().getResource("/bombImage3.png").toExternalForm());
	private final Image MUSICOFF = new Image(getClass().getResource("/bombImage4.png").toExternalForm());
	private boolean soundIsOn = true;
	private boolean musicIsOn = true;

	public MainMenuPane() {
		super();
		// TODO Auto-generated constructor stub
		// Background Image
		Image bgImage = new Image(getClass().getResource("/carrot.png").toExternalForm());
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
		BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		this.setBackground(new Background(backgroundImage));
		Image gameLogo = new Image(getClass().getResource("/carrot.png").toExternalForm());
		ImageView gameLogoView = new ImageView(gameLogo);

		// Start game button
		Button start = StyledButton("Start game");
		start.setOnMouseClicked(event -> GameController.mainToGameScene());

		// how to play button
		Button howToPlay = StyledButton("How to play");

		// hbox for 2 button2 -> soundon/off and music on/off
		HBox hBox = new HBox();

		//Button sound = StyledTogglingButton();
		//sound.setOnMouseClicked(e -> soundIsOn = !soundIsOn);

		//Button music = StyledTogglingButton();
		//String musicPath = MusicPlayer.class.getResource(path).toExternalForm();
		//Media media = new Media(MusicPath);
		//MediaPlayer mediaPlayer = new MediaPlayer(media);
		//mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
		//mediaPlayer.setVolume(0.5);

		//music.setOnMouseClicked(e -> {
			musicIsOn = !musicIsOn;
			if (musicIsOn) {
				//mediaPlayer.play();
			} else {
				//mediaPlayer.stop();
			}
		//});
		
		//hBox.getChildren().addAll(sound, music);

		// vbox styling
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().addAll(gameLogoView, start, howToPlay, hBox);

	}

	private Button StyledButton(String text) {
		Button button = new Button(text);
		button.setFont(Font.font("Monospaced", 14));
		button.setTextFill(Color.WHITE);
		BackgroundFill backgroundFill = new BackgroundFill(Color.web("#88cc88"), new CornerRadii(10), Insets.EMPTY);
		button.setBackground(new Background(backgroundFill));
		BorderStroke borderStroke = new BorderStroke(Color.web("#2e7d32"), BorderStrokeStyle.SOLID, new CornerRadii(10),
				new BorderWidths(2));
		button.setBorder(new Border(borderStroke));
		button.setPadding(new Insets(10, 20, 10, 20));
		return button;
	}

	private Button StyledTogglingButton(Image image) {
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(30);
		imageView.setFitHeight(30);
		Button button = new Button();
		button.setGraphic(imageView);
		button.setStyle("-fx-background-color: transparent;");
		return button;
	}
}
