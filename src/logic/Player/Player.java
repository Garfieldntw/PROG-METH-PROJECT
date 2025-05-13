package logic.Player;

import java.util.ArrayList;

import item.weapon.*;
import item.*;
import logic.GameController;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class Player {
	//player attributes
	private String name;
	private double xPosition;
	private double yPosition;
	private int health = 1;
	private int speed = 3;
	private int bombPower;
	private Color color;
	//player logic
	private boolean isInvincible = false;
	private boolean isDead = false;
	private boolean isBombPlaced = false;
	private Direction direction;
	//weapon
	private Weapon holdedWeapon;
	
	//set Sprite width and height
	private final double width = 45;
	private final double height = 45;

	// add final ArrayList<E> when finished
	private ArrayList<Image> PlayerImage;

	public Player(String name, double SpawnXPos, double SpawnYPos, int health, Direction direction) {
		this.name = name;
		this.xPosition = SpawnXPos;
		this.yPosition = SpawnYPos;
		this.health = health;
		this.direction = direction;
		setColor();
		// this.setHoldedWeapon(new Rock(20, this));
		this.setHoldedWeapon(new Shovel(100, this));
		// this.setHoldedWeapon(new NoWeapon(1, this));
		// walk animation (to be fixed)
		PlayerImage = new ArrayList<>();
		PlayerImage.add(new Image(getClass().getResource("/PlayerSpriteFront.png").toExternalForm()));
		PlayerImage.add(new Image(getClass().getResource("/PlayerSpriteBack.png").toExternalForm()));
		PlayerImage.add(new Image(getClass().getResource("/PlayerSpriteLeft.png").toExternalForm()));
		PlayerImage.add(new Image(getClass().getResource("/PlayerSpriteRight.png").toExternalForm()));
		placeBomb();
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void move(int dirLR, int dirUD) {
		if (GameController.isGameEnded())
			return;
		this.xPosition += dirLR * speed;
		this.yPosition += dirUD * speed;
		if (this.xPosition < 50)
			this.xPosition = 50;
		if (this.xPosition > 850 - 50 - this.getWidth())
			this.xPosition = 850 - 50 - this.getWidth();
		if (this.yPosition < 0)
			this.yPosition = 0;
		if (this.yPosition > 450 - 50 - this.getHealth())
			this.yPosition = 450 - 50 - this.getHealth();
	}

	public void placeBomb() {
		Thread thread = new Thread(() -> {
			while (!GameController.isGameEnded()) {
				try {
					if ((GameController.getKeyboardController().isP1BombPressed() && getName() == "Player 1")
							|| (GameController.getKeyboardController().isP2BombPressed() && getName() == "Player 2")) {
						isBombPlaced = true;
						// draw bomb
						GameLogic.DrawBomb(this);
						isBombPlaced = false;
						Thread.sleep(5000);
					}

					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	public boolean isBombPlaced() {
		return isBombPlaced;
	}

	public double getxPosition() {
		return xPosition;
	}

	public double getyPosition() {
		return yPosition;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health <= 0) {
			setDead(true);
			this.health = 0;
		} else {
			this.health = health;
		}
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
		if (isDead) {
			Platform.runLater(() -> GameController.setGameEndWithWinner(true, this));
		}
	}
	
	public void setColor(){
		this.color = name.equals("Player 1") ? Color.rgb(255, 102, 102) : Color.LIGHTBLUE;
	}	
	
	public Color getColor(){
		return this.color;
	}
	public Weapon getHoldedWeapon() {
		return holdedWeapon;
	}

	public void setHoldedWeapon(Weapon holdedWeapon) {
		this.holdedWeapon = holdedWeapon;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public ArrayList<Image> getPlayerImage() {
		return PlayerImage;
	}

	public void setPlayerImage(ArrayList<Image> playerImage) {
		PlayerImage = playerImage;
	}

	public void setBombPlaced(boolean isBombPlaced) {
		this.isBombPlaced = isBombPlaced;
	}

	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub

		double triangleWidth = 14;
		double triangleHeight = 10;
		double centerX = xPosition+22.5;
		double centerY = yPosition-20; // position above the head

		// Coordinates for an upside-down triangle
		double[] xPoints = { centerX - triangleWidth / 2, centerX + triangleWidth / 2, centerX };
		double[] yPoints = { centerY, centerY, centerY + triangleHeight };
		if(name == "Player 1")gc.setFill(Color.RED);
		else gc.setFill(Color.BLUE);
		
		gc.fillPolygon(xPoints, yPoints, 3);
		
		double aspectRatio = PlayerImage.get(0).getWidth() * height;
		double targetWidth = aspectRatio / PlayerImage.get(0).getHeight();
		
		switch (direction) {
			case Direction.UP -> gc.drawImage(PlayerImage.get(1), getxPosition()+5, getyPosition(), targetWidth, height);
			case Direction.DOWN -> gc.drawImage(PlayerImage.get(0), getxPosition()+5, getyPosition(), targetWidth, height);
			case Direction.RIGHT -> gc.drawImage(PlayerImage.get(3), getxPosition()+5, getyPosition(), targetWidth, height);
			case Direction.LEFT -> gc.drawImage(PlayerImage.get(2), getxPosition()+5, getyPosition(), targetWidth, height);
		}
		

	}

	public void getHurt() {
		new Thread(() -> {
			if (!isInvincible) {
				this.setHealth(health - 1);
				this.setInvincible(true);
				if (name == "Player 1")
					GameController.getStatusPane().getP1Status().updateHealth();
				else
					GameController.getStatusPane().getP2Status().updateHealth();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.setInvincible(false);
			}
		}).start();

	}

	public boolean isInvincible() {
		return isInvincible;
	}

	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}

	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}

	public int getBombPower() {
		return bombPower;
	}

	public void setBombPower(int bombPower) {
		this.bombPower = bombPower;
	}

}
