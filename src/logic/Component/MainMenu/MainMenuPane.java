package logic.Component.MainMenu;

import javafx.application.Platform;

import Util.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameController;

public class MainMenuPane extends VBox {
	private final ImageView SOUNDON = new ImageView(new Image(getClass().getResource("/bombImage1.png").toExternalForm()));
	private final ImageView SOUNDOFF = new ImageView(new Image(getClass().getResource("/bombImage4.png").toExternalForm()));
	private final ImageView MUSICON = new ImageView(new Image(getClass().getResource("/bombImage3.png").toExternalForm()));
	private final ImageView MUSICOFF = new ImageView(new Image(getClass().getResource("/bombImage4.png").toExternalForm()));
	private boolean soundIsOn = true;
	private boolean musicIsOn = true;

	public MainMenuPane() {
		super();
		// Background Image
		Image bgImage = new Image(getClass().getResource("/background.png").toExternalForm());
		BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, 1.0, true, true, false, false));
		this.setBackground(new Background(backgroundImage));

		
		Image gameLogo = new Image(getClass().getResource("/GameLogo.png").toExternalForm());
		ImageView gameLogoView = new ImageView(gameLogo);
		gameLogoView.setPreserveRatio(true);
		gameLogoView.setFitHeight(400);
		
		// Start game button
		Button start = new ButtonTemplate(new Text("Start game"));

		start.setOnMouseClicked(event -> {
			((ButtonTemplate) start).getClickSound().play();
			GameController.mainToGameScene();
		});
		
		// how to play button
		Button howToPlay = new ButtonTemplate(new Text("How to play"));
		this.setMargin(howToPlay, new Insets(10,10,10,10));
		
		// hbox for 2 button2 -> soundon/off and music on/off
		HBox hBox = new HBox();
		hBox.setSpacing(20);
		Button sound = StyledTogglingButton(SOUNDON);
		
		sound.setOnMouseClicked(e -> {
			soundIsOn = !soundIsOn;
			if (soundIsOn) {
				sound.setGraphic(SOUNDON);
			} else {
				sound.setGraphic(SOUNDOFF);
			}
		});
		setImageViewSize(MUSICON);
		setImageViewSize(MUSICOFF);
		setImageViewSize(SOUNDON);
		setImageViewSize(SOUNDOFF);
		Button music = StyledTogglingButton(MUSICON);
		String musicPath = getClass().getResource("/bgSong.mp3").toExternalForm();
		Media media = new Media(musicPath);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
		mediaPlayer.setVolume(0.05);
		mediaPlayer.play();
		music.setOnMouseClicked(e -> {
			musicIsOn = !musicIsOn;
			if (musicIsOn) {
				mediaPlayer.play();
				music.setGraphic(MUSICON);
			} else {
				mediaPlayer.stop();
				music.setGraphic(MUSICOFF);
			}
		});
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(sound, music);

		// vbox styling
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(gameLogoView, start, howToPlay, hBox);
		this.prefWidthProperty().bind(GameController.getScene().widthProperty());
		this.prefHeightProperty().bind(GameController.getScene().heightProperty());
	}
	private void setImageViewSize(ImageView iv){
		iv.setFitWidth(50);
		iv.setFitHeight(50);
	}
	
	private Button StyledTogglingButton(ImageView imageView) {
		Button button = new Button();
		button.setGraphic(imageView);
		button.setStyle("-fx-background-color: transparent;");
		button.setPrefWidth(50);
		button.setPrefHeight(50);
		
		return button;
	}
	
}
