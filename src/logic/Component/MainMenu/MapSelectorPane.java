package logic.Component.MainMenu;

import Util.ButtonTemplate;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
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

public class MapSelectorPane extends VBox {
	private ButtonTemplate start;
	public MapSelectorPane() {

		Image bgImage = new Image(getClass().getResource("/background.png").toExternalForm());
		BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, 1.0, true, true, false, false));
		this.setBackground(new Background(backgroundImage));

		Text text = new Text("Select Map");
		text.setFont(Font.font("Monospaced", FontWeight.EXTRA_BOLD, 30));
		text.setFill(Color.WHITE);
		
		ToggleButton Map1 = new ToggleButton("Just a Healthy guy");
		ToggleButton Map2 = new ToggleButton("I'm cutting bro");
		ToggleButton Map3 = new ToggleButton("I'm a Health Freak");
		start = new ButtonTemplate(new Text("Start game"));
		ButtonTemplate backToMenu = new ButtonTemplate(new Text("Back to Menu"));
		backToMenu.setOnMouseClicked(e -> GameController.toMainmenuScene());
		HBox hbox = new HBox();
		hbox.getChildren().addAll(backToMenu,start);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);
		
		styleToggleButton(Map1, GameController.getTextureImage()[0]);
		styleToggleButton(Map2, GameController.getTextureImage()[1]);
		styleToggleButton(Map3, GameController.getTextureImage()[2]);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.setPadding(new Insets(50));
		this.getChildren().addAll(text, Map1, Map2, Map3, hbox);
		ToggleGroup group = new ToggleGroup();
		Map1.setToggleGroup(group);
		Map2.setToggleGroup(group);
		Map3.setToggleGroup(group);
		
		Map1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Map1 Clicked");
				GameController.setmapNum(0);

			}
		});

		Map2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Map2 Clicked");
				GameController.setmapNum(1);
				
			}
		});

		Map3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Map3 Clicked");
				GameController.setmapNum(2);
			}
		});

		start.setOnMouseClicked(e -> GameController.toGameScene());
	}

	private void styleToggleButton(ToggleButton tbtn, Image image) {
		tbtn.setFont(Font.font("Monospaced", FontWeight.BOLD, 24));
		BackgroundFill bgFill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		BackgroundSize bgSize = new BackgroundSize(50, 50, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		BackgroundImage[] bgImgA = { bgImg };
		tbtn.setBackground(new Background(bgFillA, bgImgA));
		// Default style
		tbtn.setStyle("-fx-text-fill: #fcecd1;");

		tbtn.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
			if (isNowSelected) {
				tbtn.setStyle("-fx-text-fill: #d8725e;");
			} else {
				tbtn.setStyle("-fx-text-fill: #fcecd1;");
			}
		});

		ColorAdjust lighten = new ColorAdjust();
		lighten.setBrightness(0.3); // Positive value makes it lighter
		tbtn.setOnMouseEntered(e -> {
			tbtn.setCursor(Cursor.HAND);
			start.getHoverSound().play();
			tbtn.setEffect(lighten);

		});
		tbtn.setOnMouseExited(e -> {
			tbtn.setCursor(Cursor.DEFAULT);
			tbtn.setEffect(null);
		});
		tbtn.setPrefWidth(300);
		tbtn.setPrefHeight(150);
	}
}
