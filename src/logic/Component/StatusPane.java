package logic.Component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import logic.GameController;

public class StatusPane extends HBox{
	public StatusPane() {
		PlayerStatusPane p1Status = new PlayerStatusPane(GameController.getGameCanvas().getP1());
		TimePane timepane = new TimePane();
		PlayerStatusPane p2Status = new PlayerStatusPane(GameController.getGameCanvas().getP2());
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(850, 180);
		this.setPadding(new Insets(20,0,0,0));
		this.getChildren().addAll(p1Status, timepane, p2Status);
	}
}
