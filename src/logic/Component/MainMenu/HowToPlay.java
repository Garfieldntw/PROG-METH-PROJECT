package logic.Component.MainMenu;

import Util.ButtonTemplate;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import logic.GameController;
import Util.*;

public class HowToPlay extends VBox{
	private final Image tutorialImage = new Image(getClass().getResource("/howToPlay.png").toExternalForm(),true);
	private final Image backgroundImage = new Image(getClass().getResource("/background.png").toExternalForm(),true);
	public HowToPlay() {
		ImageView tutorialImageView = new ImageView(tutorialImage);
		ButtonTemplate toMenubt = new ButtonTemplate(new Text("Go Back"));
		drawBackground();
		tutorialImageView.setPreserveRatio(true);
		tutorialImageView.setFitHeight(550);
		this.setAlignment(Pos.CENTER);
		toMenubt.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				GameController.toMainmenuScene();
			}
		});
		this.prefWidthProperty().bind(GameController.getScene().widthProperty());
		this.prefHeightProperty().bind(GameController.getScene().heightProperty());
		this.getChildren().addAll( tutorialImageView, toMenubt);
	}
	
	private void drawBackground() {
		BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, 1.0, true, true, false, false);
		BackgroundImage bgImage = new BackgroundImage(backgroundImage, null, null, null, bgSize);
		this.setBackground(new Background(bgImage));
	}
}
