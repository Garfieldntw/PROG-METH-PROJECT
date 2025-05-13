package logic.Component;

import Util.ButtonTemplate;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import logic.GameController;

public class GameOverPane extends VBox {

	public GameOverPane(String winnerName, Image image,Color color) {
		super(20);
		
		Text winText = new Text(winnerName);
		winText.setFont(Font.font("Monospaced", FontWeight.EXTRA_BOLD, 24));
		ImageView winnerImageView = new ImageView(image);
		winnerImageView.setFitWidth(150);
		winnerImageView.setPreserveRatio(true);
		
		
		Button exitButton = new ButtonTemplate(new Text("Exit Game"));
		exitButton.setOnMouseClicked(e -> System.exit(0));

		setAlignment(Pos.CENTER);
		setSpacing(20);
		this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		getChildren().addAll(winText, winnerImageView, exitButton);
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.setHeight(630);
		this.prefWidthProperty().bind(GameController.getScene().widthProperty());
		this.prefHeightProperty().bind(GameController.getScene().heightProperty());
	}
	
	
}
