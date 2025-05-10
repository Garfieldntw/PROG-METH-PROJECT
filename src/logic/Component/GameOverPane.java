package logic.Component;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverPane extends VBox {

	public GameOverPane(String winnerName, Image image) {
		super(20);

		Text winText = new Text(winnerName);

		ImageView winnerImageView = new ImageView(image);
		winnerImageView.setFitWidth(150);
		winnerImageView.setPreserveRatio(true);

		Button exitButton = new Button("Exit Game");
		exitButton.setOnAction(e -> System.exit(0));

		setAlignment(Pos.CENTER);

		getChildren().addAll(winText, winnerImageView, exitButton);
	}
}
