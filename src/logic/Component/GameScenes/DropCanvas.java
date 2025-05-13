package logic.Component.GameScenes;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.*;
import item.weapon.*;

import java.io.InputStream;
import java.net.URL;

import item.Item;
import item.buff.*;

public class DropCanvas extends Canvas {
	// public DropCanvas() {
	// Drop(new SpeedBuff(1), 8, 4 , getGraphicsContext2D());
	// GameLogic.Break(GameController.getLayoutPane().GetEachPane(8, 4));
	// }
	private final AudioClip pop = new AudioClip(getClass().getClassLoader().getResource("pop.wav").toString());
	public void Drop(Item item, int xPos, int yPos, GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (GameController.getMenuPane().isSoundOn()) pop.setVolume(0.3);
		new Thread(() -> {

			boolean isPickedUp = false;
			double speed = 0.5;
			double curPos = yPos * 50;
			gc.setStroke(Color.rgb(200, 200, 210, 0.4));
			gc.setFill(Color.rgb(173, 216, 230, 0.5));
			gc.setLineWidth(2);
			boolean up = true;
			while (!isPickedUp) {
				if (up)
					curPos += speed;
				else
					curPos -= speed;
				final double finalcur = curPos;
				Platform.runLater(() -> {
					gc.clearRect(xPos * 50 - 5, finalcur - 5, 60, 60);
					gc.setFill(Color.rgb(173, 216, 230, 0.5));
					gc.strokeOval(xPos * 50, finalcur, 50, 50);
					gc.fillOval(xPos * 50, finalcur, 50, 50);
					gc.setFill(Color.rgb(255, 255, 255, 0.3));
					gc.fillOval(xPos * 50 + 5, finalcur + 5, 7.5, 7.5);
					gc.drawImage(item.getImage(), xPos * 50 + 7, finalcur + 7, 35.4, 35.4);
				});
				if (curPos > (yPos * 50) + 5)
					up = false;
				else if (curPos < (yPos * 50) - 5)
					up = true;
				if (GameLogic.collide(GameController.getPlayerCanvas().getP1(), xPos * 50, yPos * 50, 50, 50)) {
					item.getPickedUp(GameController.getPlayerCanvas().getP1());
					isPickedUp = true;
					if(GameController.getMenuPane().isSoundOn())pop.play();
				} else if (GameLogic.collide(GameController.getPlayerCanvas().getP2(), xPos * 50, yPos * 50, 50, 50)) {
					item.getPickedUp(GameController.getPlayerCanvas().getP2());
					isPickedUp = true;
					if(GameController.getMenuPane().isSoundOn())pop.play();
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Platform.runLater(() -> gc.clearRect(xPos * 50 - 5, finalcur - 5, 60, 60));
			}
		}).start();
	}
}
