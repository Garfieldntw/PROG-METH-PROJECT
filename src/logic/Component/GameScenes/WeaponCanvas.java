package logic.Component.GameScenes;

import java.util.ArrayList;


import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import logic.GameController;
import logic.GameLogic;
import logic.Player.Player;
import object.Floor;
import object.NormalObject;
import item.weapon.*;

public class WeaponCanvas extends Canvas {
	private final ArrayList<Image> bombImage;
	private final Image RockImage = new Image(getClass().getResource("/rockImage.png").toExternalForm());
	private final AudioClip throwingSound = new AudioClip(ClassLoader.getSystemResource("throwing.wav").toString());
	private final AudioClip bombSound = new AudioClip(ClassLoader.getSystemResource("bombticking.wav").toString());
	private final AudioClip explodeSound = new AudioClip(ClassLoader.getSystemResource("explosion.wav").toString()); 
	private final AudioClip shovelSound = new AudioClip(ClassLoader.getSystemResource("shovelDig.wav").toString()); 
	
	public WeaponCanvas(double width, double height) {
		super(width, height);
		this.bombImage = new ArrayList<>();
		bombImage.add(new Image(getClass().getResource("/bombImage1.png").toExternalForm()));
		bombImage.add(new Image(getClass().getResource("/bombImage2.png").toExternalForm()));
		bombImage.add(new Image(getClass().getResource("/bombImage3.png").toExternalForm()));
		throwingSound.setVolume(0.5);
		bombSound.setVolume(0.5);
		explodeSound.setVolume(0.5);
		shovelSound.setVolume(0.5);
		// Add player health, spawnpoint
	}

	public void Drawbomb(Player p, GraphicsContext gc) {
		double xPos = GameLogic.getxPaneNumOfPlayer(p) * 50;
		double yPos = GameLogic.getyPaneNumOfPlayer(p) * 50;
		// if (soundIsOn)
		bombSound.play();
		new Thread(() -> {
			try {
				Platform.runLater(() -> gc.drawImage(bombImage.get(0), xPos, yPos, 50, 50));
				Thread.sleep(1000);
				Platform.runLater(() -> gc.drawImage(bombImage.get(1), xPos, yPos, 50, 50));
				Thread.sleep(1000);
				Platform.runLater(() -> gc.drawImage(bombImage.get(2), xPos, yPos, 50, 50));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(() -> {
			gc.clearRect(xPos, yPos, 50, 50);
			FirstExplode(p, xPos, yPos, gc);
			});
			
		}).start();
	}

	public void FirstExplode(Player p, double xPos, double yPos, GraphicsContext gc) {
		//
		explodeSound.play();
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
			Platform.runLater(() -> SecondExplode(p, xPos, yPos, gc));
		}).start();
	}

	public void SecondExplode(Player p, double xPos, double yPos, GraphicsContext gc) {

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

			while (realsize < p.getBombPower() * 50) {
				if (GameLogic.getPaneFromXY(xPos - (size.get(0) + 12.5), yPos).getObject() instanceof Floor
						&& next.get(0)) {
					size.set(0, size.get(0) + 25);
				} else {
					Platform.runLater(
							() -> GameLogic.Break(GameLogic.getPaneFromXY(xPos - (size.get(0) + 12.5), yPos)));
					next.set(0, false);
				}
				if (GameLogic.getPaneFromXY(xPos + 50 + (size.get(1) + 12.5), yPos).getObject() instanceof Floor
						&& next.get(1)) {
					size.set(1, size.get(1) + 25);
				} else {
					Platform.runLater(
							() -> GameLogic.Break(GameLogic.getPaneFromXY(xPos + 50 + (size.get(1) + 12.5), yPos)));
					next.set(1, false);
				}
				if (GameLogic.getPaneFromXY(xPos, yPos - (size.get(2) + 12.5)).getObject() instanceof Floor
						&& next.get(2)) {
					size.set(2, size.get(2) + 25);
				} else {
					Platform.runLater(
							() -> GameLogic.Break(GameLogic.getPaneFromXY(xPos, yPos - (size.get(2) + 12.5))));
					next.set(2, false);
				}
				if (GameLogic.getPaneFromXY(xPos, yPos + 50 + (size.get(3) + 12.5)).getObject() instanceof Floor
						&& next.get(3)) {
					size.set(3, size.get(3) + 25);
				} else {
					Platform.runLater(
							() -> GameLogic.Break(GameLogic.getPaneFromXY(xPos, yPos + 50 + (size.get(3) + 12.5))));
					next.set(3, false);
				}
				Platform.runLater(() -> {
					Clearrect(gc, size, xPos, yPos);
					drawExplosion(gc, size, xPos, yPos);
				});

				if (GameLogic.collide(GameController.getPlayerCanvas().getP1(), xPos - size.get(0), yPos,
						size.get(0) + size.get(1) + 50, 50)
						|| GameLogic.collide(GameController.getPlayerCanvas().getP1(), xPos, yPos - size.get(2), 50,
								size.get(2) + size.get(3) + 50))
					GameController.getPlayerCanvas().getP1().getHurt();

				if (GameLogic.collide(GameController.getPlayerCanvas().getP2(), xPos - size.get(0), yPos,
						size.get(0) + size.get(1) + 50, 50)
						|| GameLogic.collide(GameController.getPlayerCanvas().getP2(), xPos, yPos - size.get(2), 50,
								size.get(2) + size.get(3) + 50))
					GameController.getPlayerCanvas().getP2().getHurt();

				realsize += 25;
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
			Platform.runLater(() -> {
			gc.clearRect(xPos - p.getBombPower() * 50, yPos, p.getBombPower() * 50 + 50 + p.getBombPower() * 50, 50);
			gc.clearRect(xPos, yPos - p.getBombPower() * 50, 50, p.getBombPower() * 50 + 50 + p.getBombPower() * 50);
			});
		}).start();
	}

	private void endExplosion(GraphicsContext gc, ArrayList<Double> size, double xPos, double yPos,
			ArrayList<Boolean> next) {
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
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
		});
	}

	private void Clearrect(GraphicsContext gc, ArrayList<Double> size, double xPos, double yPos) {
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			gc.clearRect(xPos - size.get(0), yPos, size.get(0), 50);
			// to right
			gc.clearRect(xPos + 50, yPos, size.get(1), 50);
			// to top
			gc.clearRect(xPos, yPos - size.get(2), 50, size.get(2));
			// to bottom
			gc.clearRect(xPos, yPos + 50, 50, size.get(3));
		});
	}

	public void drawExplosion(GraphicsContext gc, ArrayList<Double> size, double xPos, double yPos) {
		Platform.runLater(() -> {
			gc.setFill(Color.YELLOW);
			// to left
			gc.fillRect(xPos - size.get(0) + 25, yPos, size.get(0) - 25, 50);
			// to right
			gc.fillRect(xPos + 50, yPos, size.get(1) - 25, 50);
			// to top
			gc.fillRect(xPos, yPos - size.get(2) + 25, 50, size.get(2) - 25);
			// to bottom
			gc.fillRect(xPos, yPos + 50, 50, size.get(3) - 25);
		});
	}

	public void Drawbombbutbetter(Player p, GraphicsContext gc) {
		double xPos = GameLogic.getxPaneNumOfPlayer(p) * 50;
		double yPos = GameLogic.getyPaneNumOfPlayer(p) * 50;

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
			this.FirstExplode(p, xPos, yPos, gc);
		}).start();
	}

	public void drawShovel(Player p, double xpos, double yPos, int dirLR, int dirUD, GraphicsContext gc) {
		// if (soundIsOn)
		//d
		shovelSound.play();
		new Thread(() -> {
			int angleInit = -45;
			if (p.equals(GameController.getPlayerCanvas().getP2()))
				angleInit = 135;
			if (dirLR == -1)
				angleInit = 135;
			else if (dirLR == 1)
				angleInit = -45;
			else if (dirUD == 1)
				angleInit = -135;
			else if (dirUD == -1)
				angleInit = 45;

			final int startAngle = angleInit;

			p.setSpeed(0);

			double angle = 0;
			int steps = 6;
			int sleepTime = 25;

			// First step
			for (int i = 0; i < steps; i++) {
				angle = (i + 1) * 15;
				double finalAngle = angle;
				Platform.runLater(() -> {
					gc.clearRect(xpos - 50, yPos - 50, 150, 150);
					gc.setFill(Color.rgb(255, 255, 255, 0.9));
					gc.fillArc(xpos - 30, yPos - 30, 120, 120, startAngle, finalAngle, ArcType.ROUND);
				});
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// break object
			if (dirLR != 0) {
				EachPane pane = GameController.getLayoutPane().GetEachPane(GameLogic.getxPaneNumOfPlayer(p) + dirLR,
						GameLogic.getyPaneNumOfPlayer(p));
				if (!(pane.getObject() instanceof Floor)) {
					Platform.runLater(() -> GameLogic.Break(pane));
				}
			} else if (dirUD != 0) {
				EachPane pane = GameController.getLayoutPane().GetEachPane(GameLogic.getxPaneNumOfPlayer(p),
						GameLogic.getyPaneNumOfPlayer(p) + dirUD);
				if (!(pane.getObject() instanceof Floor)) {
					Platform.runLater(() -> GameLogic.Break(pane));
				}
			}

			// Second step
			for (int i = steps - 1; i >= 0; i--) {
				angle = i * 15;
				double finalAngle = angle;
				Platform.runLater(() -> {
					gc.clearRect(xpos - 50, yPos - 50, 150, 150);
					gc.setFill(Color.rgb(255, 255, 255, 0.7));
					gc.fillArc(xpos - 30, yPos - 30, 120, 120, startAngle + 90 - finalAngle, finalAngle, ArcType.ROUND);
				});
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			Platform.runLater(() -> {
				gc.clearRect(xpos, yPos, 120, 120);
				p.setSpeed(3);
			});

		}).start();
	}
	
	public void playShovelSlashThread(GraphicsContext gc, Image shovelImage, double startX, double startY, Direction direction) {
	    // Initialize animation parameters
	    double maxSteps = 10;
	    double startAngle;
	    switch (direction) {
	        case Direction.UP : startAngle = -90; GameLogic.breakbyPosition(startX, startY - 50);
	        case Direction.RIGHT : startAngle = 0; GameLogic.breakbyPosition(startX + 50, startY);
	        case Direction.DOWN : startAngle = 90; GameLogic.breakbyPosition(startX, startY + 50);
	        case Direction.LEFT : startAngle = 180; GameLogic.breakbyPosition(startX - 50, startY);
	        default : startAngle = 0;
	    }
	    Platform.runLater(() -> GameLogic.breakbyPosition(startX, startY));
	    final double finalStartAngle = startAngle;
	    final double[] currentStep = {0}; // Store current step (frame of animation)

	    // AnimationTimer to handle frame-by-frame updates
	    AnimationTimer timer = new AnimationTimer() {
	        @Override
	        public void handle(long now) {
	            if (currentStep[0] < maxSteps) {
	                // Calculate the angle based on the current step
	                double totalAngle = finalStartAngle + currentStep[0] * 9;

	                // Clear previous frame and draw new one
	                gc.clearRect(startX - 75, startY - 75, 150, 150);
	                gc.save();
	                gc.translate(startX, startY);
	                gc.rotate(totalAngle);
	                gc.drawImage(shovelImage, 0, -25, 50, 50); // Adjust if necessary
	                gc.restore();

	                // Increment the step
	                currentStep[0]++;
	            } else {
	                // Stop the animation when max steps are reached
	                stop();
	                // Final clear after animation
	                gc.clearRect(startX - 75, startY - 75, 150, 150);
	            }
	        }
	    };

	    // Start the animation
	    timer.start();
	}

	
	private void drawRotateShovel(double startAngle, GraphicsContext gc, Image shovelImage, int state, double startX, double startY) {
		gc.clearRect(startX - 50, startY - 50, 150, 150);	
		gc.save();
        gc.translate(startX, startY);
        gc.rotate(startAngle + 9*state);   
        gc.drawImage(shovelImage, 0, 0, 50, 50); 
        gc.restore();
	}
	
	public void ThrowRock(double xPos, double yPos, int dirLR, int dirUD, GraphicsContext gc) {
    final double speed = 4;
    final double[] rockX = {xPos};
    final double[] rockY = {yPos};
    // if (soundIsOn)
	throwingSound.play();
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
			System.out.println("start throw");
            // Clear previous frame
            

            // Update position
            gc.clearRect(rockX[0]- 5, rockY[0]-5, 60, 60);
            rockX[0] += speed * dirLR;
            rockY[0] += speed * dirUD;
            
			gc.drawImage(RockImage, rockX[0], rockY[0], 50, 50);
			if(RockImage != null) System.out.println("there is a rock image");
			else System.out.println("mai mee rock image");
			System.out.println("rock should thrown");
            // Stop if hitting wall/floor
            if (!(GameLogic.getPaneFromXY(rockX[0], rockY[0]).getObject() instanceof Floor)) {
                gc.clearRect(rockX[0] -5, rockY[0]-5, 60, 60);
                System.out.println("hitting wall");
                stop(); // stop animation                
                return;
            }

            // Draw the rock
            

            // Check collision with players
            if (GameLogic.collide(GameController.getPlayerCanvas().getP1(), rockX[0], rockY[0], 50, 50)) {
                GameController.getPlayerCanvas().getP1()
                    .getHurt();
                System.out.println("hit player 1");
                stop();
            } else if (GameLogic.collide(GameController.getPlayerCanvas().getP2(), rockX[0], rockY[0], 50, 50)) {
                GameController.getPlayerCanvas().getP2()
                    .getHurt();
                System.out.println("hit player 2");
                stop();
            }
        }
    };

    Platform.runLater(timer::start);
	}
}
