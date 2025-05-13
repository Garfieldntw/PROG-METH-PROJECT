package logic.Component.GameScenes.BottomBar;

import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import logic.GameController;

public class StatusPane extends HBox{
	private PlayerStatusPane p1Status;
	private PlayerStatusPane p2Status;
	public StatusPane() {
		p1Status = new PlayerStatusPane(GameController.getPlayerCanvas().getP1());
		TimePane timepane = new TimePane();
		p2Status = new PlayerStatusPane(GameController.getPlayerCanvas().getP2());
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(850, 180);
		this.setPadding(new Insets(0,0,0,0));
		this.getChildren().addAll(p1Status, timepane, p2Status);
		
		Image bgImage = new Image(getClass().getResource("/dirtBackground.png").toExternalForm());
		BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, 1.0, true, true, false, false));
		this.setBackground(new Background(backgroundImage));
	}
	public PlayerStatusPane getP1Status() {
		return p1Status;
	}
	public void setP1Status(PlayerStatusPane p1Status) {
		this.p1Status = p1Status;
	}
	public PlayerStatusPane getP2Status() {
		return p2Status;
	}
	public void setP2Status(PlayerStatusPane p2Status) {
		this.p2Status = p2Status;
	}
	
}
