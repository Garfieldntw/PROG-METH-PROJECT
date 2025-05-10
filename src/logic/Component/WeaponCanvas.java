package logic.Component;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.GameLogic;
import logic.Player.Player;
import object.Floor;
import object.Lettuce;

public class WeaponCanvas extends Canvas {
	private int bombpower = 1;
	private final ArrayList<Image> bombImage;

	public WeaponCanvas(double width, double height) {
		super(width, height);
		this.bombImage = new ArrayList<>();
		bombImage.add(new Image(getClass().getResource("/bombImage1.png").toExternalForm()));
		bombImage.add(new Image(getClass().getResource("/bombImage2.png").toExternalForm()));
		bombImage.add(new Image(getClass().getResource("/bombImage3.png").toExternalForm()));
		bombImage.add(new Image(getClass().getResource("/bombImage4.png").toExternalForm()));
		// Add player health, spawnpoint
	}

	public void FirstExplode(double xPos, double yPos, GraphicsContext gc) {

		new Thread(() -> {
			double x = 1;
			// while (x < 25) {
			// double finalX = x;
			Platform.runLater(() -> {
				gc.clearRect(xPos, yPos, 50, 50);
				gc.setFill(Color.YELLOW);
				gc.setStroke(Color.GHOSTWHITE);
				gc.setLineWidth(5);
				// gc.fillRect(xPos+12.5-finalX/2, yPos+12.5-finalX/2, 25 + finalX, 25 +finalX);
				gc.fillRect(xPos+1, yPos+1, 50, 50);
			});
			// x += 6;
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// }
			SecondExplode(xPos, yPos, gc);
		}).start();
	}

	public int getBombpower1() {
		return bombpower;
	}

	public void setBombpower(int bombpower) {
		this.bombpower = bombpower;
	}

	public void SecondExplode(double xPos, double yPos, GraphicsContext gc) {

		new Thread(() -> {

			ArrayList<Double> size = new ArrayList<>();
			size.add(0.0);
			size.add(0.0);
			size.add(0.0);
			size.add(0.0);
			ArrayList<Boolean> next = new ArrayList<>();
			next.add(true);
			next.add(true);
			next.add(true);
			next.add(true);
			int realsize = 0;

			while (realsize < bombpower * 50) {
				if (GameLogic.getPane(xPos - (size.get(0) + 12.5), yPos).getObject() instanceof Floor && next.get(0)) {
					size.set(0, size.get(0) + 25);
				} else {
					Platform.runLater(() -> GameLogic.Break(GameLogic.getPane(xPos - (size.get(0) + 12.5), yPos)));
					next.set(0, false);
				}
				if (GameLogic.getPane(xPos + 50 + (size.get(1) + 12.5), yPos).getObject() instanceof Floor
						&& next.get(1)) {
					size.set(1, size.get(1) + 25);
				} else {
					Platform.runLater(() -> GameLogic.Break(GameLogic.getPane(xPos + 50 + (size.get(1) + 12.5), yPos)));
					next.set(1, false);
				}
				if (GameLogic.getPane(xPos, yPos - (size.get(2) + 12.5)).getObject() instanceof Floor && next.get(2)) {
					size.set(2, size.get(2) + 25);
				} else {
					Platform.runLater(() -> GameLogic.Break(GameLogic.getPane(xPos, yPos - (size.get(2) + 12.5))));
					next.set(2, false);
				}
				if (GameLogic.getPane(xPos, yPos + 50 + (size.get(3) + 12.5)).getObject() instanceof Floor
						&& next.get(3)) {
					size.set(3, size.get(3) + 25);
				} else {
					Platform.runLater(() -> GameLogic.Break(GameLogic.getPane(xPos, yPos + 50 + (size.get(3) + 12.5))));
					next.set(3, false);
				}
				Platform.runLater(() -> {
					Clearrect(gc, size, xPos, yPos);
					drawExplosion(gc, size, xPos, yPos);
				});

				if (GameLogic.collide(GameController.getGameCanvas().getP1().getxPosition() + 22.5,
						GameController.getGameCanvas().getP1().getyPosition() + 22.5, xPos - size.get(0), yPos,
						size.get(0) + size.get(1) + 50, 50)
						|| GameLogic.collide(GameController.getGameCanvas().getP1().getxPosition() + 22.5,
								GameController.getGameCanvas().getP1().getyPosition() + 22.5, xPos, yPos - size.get(2),
								50, size.get(2) + size.get(3) + 50))
					GameController.getGameCanvas().getP1()
							.setHealth(GameController.getGameCanvas().getP1().getHealth() - 1);

				if (GameLogic.collide(GameController.getGameCanvas().getP2().getxPosition() + 22.5,
						GameController.getGameCanvas().getP2().getyPosition() + 22.5, xPos - size.get(0), yPos,
						size.get(0) + size.get(1) + 50, 50)
						|| GameLogic.collide(GameController.getGameCanvas().getP2().getxPosition() + 22.5,
								GameController.getGameCanvas().getP2().getyPosition() + 22.5, xPos, yPos - size.get(2),
								50, size.get(2) + size.get(3) + 50))
					GameController.getGameCanvas().getP2()
							.setHealth(GameController.getGameCanvas().getP2().getHealth() - 1);

				realsize += 12.5;
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			Platform.runLater(() -> endExplosion(gc, size, xPos, yPos));
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gc.clearRect(xPos - 200, yPos - 200, 450, 450);
		}).start();
	}

	private void endExplosion(GraphicsContext gc, ArrayList<Double> size, double xPos, double yPos) {
		// TODO Auto-generated method stub
		gc.setFill(Color.YELLOW);
		// to left
		gc.fillOval(xPos - size.get(0) - 25, yPos, 50, 50);
		// to right
		gc.fillOval(xPos + 25 + size.get(1), yPos, 50, 50);
		// to top
		gc.fillOval(xPos, yPos - size.get(2) - 25, 50, 50);
		gc.fillOval(xPos, yPos + 25 + size.get(3), 50, 50);
	}

	private void Clearrect(GraphicsContext gc, ArrayList<Double> size, double xPos, double yPos) {
		// TODO Auto-generated method stub
		gc.clearRect(xPos - size.get(0), yPos, size.get(0), 50);
		// to right
		gc.clearRect(xPos + 50, yPos, size.get(1), 50);
		// to top
		gc.clearRect(xPos, yPos - size.get(2), 50, size.get(2));
		gc.clearRect(xPos, yPos + 50, 50, size.get(3));
	}

	public void drawExplosion(GraphicsContext gc, ArrayList<Double> size, double xPos, double yPos) {
		gc.setFill(Color.YELLOW);
		// to left
		gc.fillRect(xPos - size.get(0), yPos, size.get(0), 50);
		// to right
		gc.fillRect(xPos + 50, yPos, size.get(1), 50);
		// to top
		gc.fillRect(xPos, yPos - size.get(2), 50, size.get(2));
		gc.fillRect(xPos, yPos + 50, 50, size.get(3));
	}

	public void Drawbomb(Player p, GraphicsContext gc) {
		double xPos = GameLogic.getxPane(p) * 50;
		double yPos = GameLogic.getyPane(p) * 50;

		new Thread(() -> {
			try {
				gc.drawImage(bombImage.get(0), xPos, yPos, 50, 50);
				Thread.sleep(1000);
				gc.drawImage(bombImage.get(1), xPos, yPos, 50, 50);
				Thread.sleep(1000);
				gc.drawImage(bombImage.get(2), xPos, yPos, 50, 50);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gc.clearRect(xPos, yPos, 50, 50);
			FirstExplode(xPos, yPos, gc);
		}).start();
	}

	public void Drawbombbutbetter(Player p, GraphicsContext gc) {
		double xPos = GameLogic.getxPane(p) * 50;
		double yPos = GameLogic.getyPane(p) * 50;

		new Thread(() -> {
			int x = 0;
			while (x < 100) {
				gc.clearRect(xPos, yPos, 50, 50);
				if (x < 33) {
					gc.drawImage(bombImage.get(x % 3), xPos, yPos, 50, 50);
				} else if (x < 66) {
					gc.drawImage(bombImage.get(x % 3), xPos, yPos, 50, 50);
				} else {
					gc.drawImage(bombImage.get(x % 3), xPos, yPos, 50, 50);

				}
				try {
					Thread.sleep(400 / (1 + (int) (x / 33)) ^ 2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				x += 1;
			}
			gc.clearRect(xPos, yPos, 50, 50);
			this.FirstExplode(xPos, yPos, gc);
			this.SecondExplode(xPos, yPos, gc);
		}).start();
	}

	public int getBombpower() {
		// TODO Auto-generated method stub
		return 0;
	}
}
