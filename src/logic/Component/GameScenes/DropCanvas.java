package logic.Component.GameScenes;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.*;

import item.Item;

public class DropCanvas extends Canvas {

	private final AudioClip pop = new AudioClip(getClass().getClassLoader().getResource("pop.wav").toString()); // Item
																												// receiving
																												// sound
	public void drop(Item item, int xPos, int yPos, GraphicsContext gc) {
		pop.setVolume(0.3);
		new Thread(() -> {
			boolean isPickedUp = false;
			double speed = 0.5;
			double curPos = yPos * 50;
			Platform.runLater(() -> {
				gc.setStroke(Color.rgb(200, 200, 210, 0.4));
				gc.setFill(Color.rgb(173, 216, 230, 0.5));
				gc.setLineWidth(2);
			});
			boolean up = true;
			while (!isPickedUp) {
				if (up)
					curPos += speed;
				else
					curPos -= speed;
				final double finalcur = curPos;
				Platform.runLater(() -> drawBubbleAnimation(gc, xPos * 50, finalcur, item));
				if (curPos > (yPos * 50) + 5)
					up = false;
				else if (curPos < (yPos * 50) - 5)
					up = true;
				if (GameLogic.collide(GameController.getPlayerCanvas().getP1(), xPos * 50, yPos * 50, 50, 50)) {
					item.getPickedUp(GameController.getPlayerCanvas().getP1());
					isPickedUp = true;
					if (GameController.getMenuPane().isSoundOn())
						pop.play();
				} else if (GameLogic.collide(GameController.getPlayerCanvas().getP2(), xPos * 50, yPos * 50, 50, 50)) {
					item.getPickedUp(GameController.getPlayerCanvas().getP2());
					isPickedUp = true;
					if (GameController.getMenuPane().isSoundOn())
						pop.play();
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(() -> gc.clearRect(xPos * 50 - 5, finalcur - 5, 60, 60));
			}
		}).start();
	}

	private void drawBubbleAnimation(GraphicsContext gc, double xPos, double yPos, Item item) {
		gc.clearRect(xPos - 5, yPos - 5, 60, 60);
		gc.setFill(Color.rgb(173, 216, 230, 0.5));
		gc.strokeOval(xPos, yPos, 50, 50);
		gc.fillOval(xPos, yPos, 50, 50);
		gc.setFill(Color.rgb(255, 255, 255, 0.3));
		gc.fillOval(xPos + 5, yPos + 5, 7.5, 7.5);
		gc.drawImage(item.getImage(), xPos + 7, yPos + 7, 35.4, 35.4);
	}
}
