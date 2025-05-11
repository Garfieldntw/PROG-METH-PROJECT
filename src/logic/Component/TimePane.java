package logic.Component;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameController;
import logic.GameLogic;
import javafx.application.Platform;

public class TimePane extends VBox {

	private Text timer;
	private String strTimer;
	private Button button;
	private int seconds = 0;
	private int minutes = 5;
	public TimePane() {
		super();

		this.setPrefWidth(192);
		this.setPrefHeight(80);
		this.setAlignment(Pos.CENTER);

		timer = new Text("5:00");
		timer.setFont(new Font(20));

		button = new Button("End game");
		button.setFocusTraversable(false);

		this.getChildren().add(timer);
		this.getChildren().add(button);
		
	
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Platform.runLater(() -> GameController.setGameEnded(true));
			}
		});
		this.setFocusTraversable(false);
	
		Thread t = new Thread(() -> {
			while (!GameController.isGameEnded()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (minutes <= 0 && seconds <= 0)
					Platform.runLater(() -> GameController.setGameEnded(true));
				Platform.runLater(() -> timer.setText(String.format("%01d:%02d", minutes, seconds)));
				
				seconds -= 1;
				if (seconds < 0) {
					seconds = 59;
					minutes--;
					GameLogic.increaseBombPower();
				}
			}
		});
		t.start();
	}

}
