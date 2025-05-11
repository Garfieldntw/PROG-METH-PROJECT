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
import weapon.Shovel;
import weapon.Weapon;

public class WeaponCanvas extends Canvas {
	private int bombpower = 1;
	private final ArrayList<Image> bombImage;
	private final Image RockImage = new Image(getClass().getResource("").toExternalForm());

	public WeaponCanvas(double width, double height) {
		super(width, height);
		this.bombImage = new ArrayList<>();
		bombImage.add(new Image(getClass().getResource("/bombImage1.png").toExternalForm()));
		bombImage.add(new Image(getClass().getResource("/bombImage2.png").toExternalForm()));
		bombImage.add(new Image(getClass().getResource("/bombImage3.png").toExternalForm()));
		// Add player health, spawnpoint
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

	public void FirstExplode(double xPos, double yPos, GraphicsContext gc) {

		new Thread(() -> {

			Platform.runLater(() -> {
				gc.clearRect(xPos, yPos, 50, 50);
				gc.setFill(Color.YELLOW);
				gc.setStroke(Color.GHOSTWHITE);
				gc.setLineWidth(5);
				gc.fillRect(xPos, yPos, 50.5, 50.5);
			});
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SecondExplode(xPos, yPos, gc);
		}).start();
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
							.getHurt();

				if (GameLogic.collide(GameController.getGameCanvas().getP2().getxPosition() + 22.5,
						GameController.getGameCanvas().getP2().getyPosition() + 22.5, xPos - size.get(0), yPos,
						size.get(0) + size.get(1) + 50, 50)
						|| GameLogic.collide(GameController.getGameCanvas().getP2().getxPosition() + 22.5,
								GameController.getGameCanvas().getP2().getyPosition() + 22.5, xPos, yPos - size.get(2),
								50, size.get(2) + size.get(3) + 50))
					GameController.getGameCanvas().getP2()
							.getHurt();

				realsize += 50;
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			Platform.runLater(() -> endExplosion(gc, size, xPos, yPos, next));
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gc.clearRect(xPos - bombpower*50, yPos , bombpower*50 + 50 + bombpower*50, 50);
			gc.clearRect(xPos , yPos - bombpower*50, 50, bombpower*50 + 50 + bombpower*50);
		}).start();
	}

	private void endExplosion(GraphicsContext gc, ArrayList<Double> size, double xPos, double yPos,
			ArrayList<Boolean> next) {
		// TODO Auto-generated method stub
		gc.setFill(Color.YELLOW);
		// to left
		if (next.get(0))
			gc.fillOval(xPos - size.get(0), yPos, 50, 50);
		// to right
		if (next.get(1))
			gc.fillOval(xPos + size.get(1), yPos, 50, 50);
		// to top
		if (next.get(2))
			gc.fillOval(xPos, yPos - size.get(2), 50, 50);
		// to bottom
		if (next.get(3))
			gc.fillOval(xPos, yPos + size.get(3), 50, 50);
	}

	private void Clearrect(GraphicsContext gc, ArrayList<Double> size, double xPos, double yPos) {
		// TODO Auto-generated method stub
		gc.clearRect(xPos - size.get(0), yPos, size.get(0), 50);
		// to right
		gc.clearRect(xPos + 50, yPos, size.get(1), 50);
		// to top
		gc.clearRect(xPos, yPos - size.get(2), 50, size.get(2));
		// to bottom
		gc.clearRect(xPos, yPos + 50, 50, size.get(3));
	}

	public void drawExplosion(GraphicsContext gc, ArrayList<Double> size, double xPos, double yPos) {
		gc.setFill(Color.YELLOW);
		// to left
		gc.fillRect(xPos - size.get(0) + 25, yPos, size.get(0) - 25, 50);
		// to right
		gc.fillRect(xPos + 50, yPos, size.get(1) - 25, 50);
		// to top
		gc.fillRect(xPos, yPos - size.get(2) + 25, 50, size.get(2) - 25) ;
		// to bottom
		gc.fillRect(xPos, yPos + 50, 50, size.get(3) - 25);
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
		}).start();
	}

	public int getBombpower() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void drawShovel(Player p, double xpos, double yPos, int dirLR, int dirUD, GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.WHEAT);

		new Thread(() -> {
			int startAngle = -45;
			if (p.equals(GameController.getGameCanvas().getP2()))
				startAngle = 135;
			if (dirLR == -1)
				startAngle = 135;
			else if (dirLR == 1)
				startAngle = -45;
			else if (dirUD == -1)
				startAngle = -135;
			else if (dirUD == 1)
				startAngle = 45;
			double Angle = 0;
			p.setSpeed(0);
			while (Angle < 90) {
				Angle += 15;
				gc.clearRect(xpos, yPos, 120, 120);
				gc.fillArc(xpos - 30, yPos - 30, 120, 120, startAngle, Angle, javafx.scene.shape.ArcType.ROUND);
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dirLR != 0 && GameController.getLayoutPane()
					.GetEachPane(GameLogic.getxPane(p) + dirLR, GameLogic.getyPane(p)).getObject() instanceof Floor) {
				GameLogic.Break(GameController.getLayoutPane().GetEachPane(GameLogic.getxPane(p) + dirLR,
						GameLogic.getyPane(p)));
			} else if (dirUD != 0 && GameController.getLayoutPane()
					.GetEachPane(GameLogic.getxPane(p), GameLogic.getyPane(p) + dirUD).getObject() instanceof Floor) {
				GameLogic.Break(GameController.getLayoutPane().GetEachPane(GameLogic.getxPane(p),
						GameLogic.getyPane(p) + dirUD));
			}
			while (Angle > 0) {
				Angle -= 15;
				gc.clearRect(xpos, yPos, 120, 120);
				gc.fillArc(xpos - 30, yPos - 30, 120, 120, startAngle + 90 - Angle, Angle,
						javafx.scene.shape.ArcType.ROUND);
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			p.setSpeed(3);

		}).start();

	}

	public void ThrowRock(double xPos, double yPos, int dirLR, int dirUD, GraphicsContext gc) {
		// TODO Auto-generated method stub
		new Thread(() -> {
			double speed = 10.0;
			double RockX = xPos;
			double RockY = yPos;
			while (!(GameLogic.getPane(RockX, RockY).getObject() instanceof Floor)) {
				gc.clearRect(RockX, RockY, 50, 50);
				RockX += speed * dirLR;
				RockY += speed * dirUD;
				gc.drawImage(RockImage, RockX, RockY, 50, 50);
				if (GameLogic.collide(GameController.getGameCanvas().getP1().getxPosition() + 22.5,
						GameController.getGameCanvas().getP1().getyPosition() + 22.5, RockX, RockY, 50, 50)) {
					GameController.getGameCanvas().getP1()
							.setHealth(GameController.getGameCanvas().getP1().getHealth() - 1);
					break;
				} else if (GameLogic.collide(GameController.getGameCanvas().getP2().getxPosition() + 22.5,
						GameController.getGameCanvas().getP2().getyPosition() + 22.5, RockX, RockY, 50, 50)) {
					GameController.getGameCanvas().getP2()
							.setHealth(GameController.getGameCanvas().getP2().getHealth() - 1);
					break;
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			gc.clearRect(RockX, RockY, 50, 50);
		}).start();
	}

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
				gc.clearRect(xPos, curPos, 50, 50);
				if (up == true)
					curPos += speed;
				else
					curPos -= speed;
				gc.setFill(Color.rgb(173, 216, 230, 0.5));
				gc.strokeOval(xPos * 50, curPos + speed, 50, 50);
				gc.fillOval(xPos * 50, curPos + speed, 50, 50);
				gc.setFill(Color.rgb(255, 255, 255, 0.3));
				gc.fillOval(xPos + 5, curPos + 5, 7.5, 7.5);
				gc.drawImage(weapon.getImage(), xPos * 50 + 7, curPos * 50 + 7, 35.4, 35.4);
				if (curPos > (yPos * 50) + 5)
					up = false;
				else if (curPos < (yPos * 50) - 5)
					up = true;
				if (GameLogic.collide(GameController.getGameCanvas().getP1().getxPosition() + 22.5,
						GameController.getGameCanvas().getP1().getyPosition() + 22.5, xPos * 50, yPos * 50, 50, 50)) {
					GameController.getGameCanvas().getP1().setHoldedWeapon(weapon);
					weapon.setP(GameController.getGameCanvas().getP1());
					isPickedUp = true;
				} else if (GameLogic.collide(GameController.getGameCanvas().getP2().getxPosition() + 22.5,
						GameController.getGameCanvas().getP2().getyPosition() + 22.5, xPos * 50, yPos * 50, 50, 50)) {
					GameController.getGameCanvas().getP2().setHoldedWeapon(weapon);
					weapon.setP(GameController.getGameCanvas().getP2());
					isPickedUp = true;
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gc.clearRect(xPos*50-1,yPos *50 - 5,52,60);
			}
		}).start();
	}

	public int getBombpower1() {
		return bombpower;
	}

	public void setBombpower(int bombpower) {
		this.bombpower = bombpower;
	}
}
