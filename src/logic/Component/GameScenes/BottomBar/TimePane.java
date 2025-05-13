package logic.Component.GameScenes.BottomBar;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameController;
import logic.GameLogic;
import logic.Player.Player;
import Util.ButtonTemplate;
import javafx.application.Platform;

public class TimePane extends VBox {

	private Text timer;
	private String strTimer;
	private int seconds = 0;
	private int minutes = 5;

	public TimePane() {
		super();

		this.setPrefWidth(192);
		this.setPrefHeight(80);
		this.setAlignment(Pos.CENTER);

		timer = new Text("5:00");
		timer.setFont(Font.font("Monospaced", FontWeight.EXTRA_BOLD, 30));
		timer.setFill(Color.ANTIQUEWHITE);
		Button endGame = new ButtonTemplate(new Text("End Game"));
		endGame.setPrefWidth(130);

		this.getChildren().add(timer);
		this.getChildren().addAll(endGame);
		endGame.setOnMouseClicked(event -> Platform.runLater(() -> GameController.setGameEnded(true)));
		endGame.setFocusTraversable(false);
		this.setFocusTraversable(false);
		this.setSpacing(10);
		Thread t = new Thread(() -> {
			while (!GameController.isGameEnded()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (minutes <= 0 && seconds <= 0) {
					int p1Hp = GameController.getPlayerCanvas().getP1().getHealth();
					int p2Hp = GameController.getPlayerCanvas().getP2().getHealth();
					Platform.runLater(() -> {
					if(p1Hp > p2Hp) GameController.setGameEndWithWinner(true,GameController.getPlayerCanvas().getP2());
					else if (p2Hp > p1Hp) GameController.setGameEndWithWinner(true,GameController.getPlayerCanvas().getP1());
					else GameController.setGameEnded(true);
					
						
					});
				}
				Platform.runLater(() -> timer.setText(String.format("%01d:%02d", minutes, seconds)));
				
				seconds -= 1;
				if (seconds < 0) {
					seconds = 59;
					minutes--;
					// increase Everyone bombpower every 1 minutes
					GameLogic.increaseBombPower(GameController.getPlayerCanvas().getP1());
					GameLogic.increaseBombPower(GameController.getPlayerCanvas().getP2());
					GameLogic.updateBombPower();
				}
			}
		});
		t.start();
	}

}
