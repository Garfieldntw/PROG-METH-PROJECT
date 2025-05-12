package logic.Component.GameScenes;
import weapon.*;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.*;

public class DropCanvas extends Canvas{
	//public DropCanvas() {
		// Drop(new SpeedBuff(1), 8, 4 , getGraphicsContext2D());
		// GameLogic.Break(GameController.getLayoutPane().GetEachPane(8, 4));
	//}
	public void Drop(Weapon weapon, int xPos, int yPos, GraphicsContext gc) {
		// TODO Auto-generated method stub
		new Thread(() -> {
			boolean isPickedUp = false;
			double speed = 0.5;
			double curPos = yPos * 50;
			gc.setStroke(Color.rgb(200, 200, 210, 0.4));
			gc.setFill(Color.rgb(173, 216, 230, 0.5));
			gc.setLineWidth(2);
			boolean up = true;
			while (isPickedUp == false) {
				if (up)
			        curPos += speed;
			    else
			        curPos -= speed;
			
			    final double finalcur = curPos; // ✅ This is effectively final now
			
			    Platform.runLater(() -> {
			        gc.clearRect(xPos * 50 - 5, finalcur - 5, 60, 60); // optional
			        gc.setFill(Color.rgb(173, 216, 230, 0.5));
			        gc.strokeOval(xPos * 50, finalcur, 50, 50);
			        gc.fillOval(xPos * 50, finalcur, 50, 50);
			        gc.setFill(Color.rgb(255, 255, 255, 0.3));
			        gc.fillOval(xPos * 50 + 5, finalcur + 5, 7.5, 7.5);
			        gc.drawImage(weapon.getImage(), xPos * 50 + 7, finalcur + 7, 35.4, 35.4);
			    });
				if (curPos > (yPos * 50) + 5)
					up = false;
				else if (curPos < (yPos * 50) - 5)
					up = true;
				if (GameLogic.collide(GameController.getPlayerCanvas().getP1(), xPos * 50, yPos * 50, 50, 50)) {
					GameController.getPlayerCanvas().getP1().setHoldedWeapon(weapon);
					Platform.runLater(() -> {
						weapon.setP(GameController.getPlayerCanvas().getP1());
						
					});
					isPickedUp = true;
				} else if (GameLogic.collide(GameController.getPlayerCanvas().getP2(), xPos * 50, yPos * 50, 50, 50)) {
					GameController.getPlayerCanvas().getP2().setHoldedWeapon(weapon);
					Platform.runLater(() -> {
						weapon.setP(GameController.getPlayerCanvas().getP2());
						
					});
					isPickedUp = true;
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Platform.runLater(() -> gc.clearRect(xPos*50 -5 ,finalcur-5 ,60,60));
			}
			}).start();
	}
}
