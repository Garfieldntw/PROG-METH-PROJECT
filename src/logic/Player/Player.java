package logic.Player;


import java.util.ArrayList;
import logic.GameController;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.GameLogic;
import weapon.Shovel;
import weapon.Weapon;

public class Player {
	private String name;
	private double xPosition;
	private double yPosition;
	private int health = 1;
	private boolean isDead = false;;
	private boolean isBombPlaced = false;
	private int speed = 3;
	private boolean isHoldingItem;
	private Weapon holdedWeapon;
	private final double width = 45;
	private final double height = 45;
	private boolean isInvincible = false;
	// add final ArrayList<E> when finished
	private ArrayList<Image> PlayerImage;

	public Player(String name, double SpawnXPos, double SpawnYPos, int health) {
		this.name = name;
		this.xPosition = SpawnXPos;
		this.yPosition = SpawnYPos;
		this.health = health;
		this.isHoldingItem = false;
		
		// walk animation (to be fixed)
		PlayerImage = new ArrayList<>();
		PlayerImage.add(new Image(getClass().getResource("/PlayerSpriteFront.png").toExternalForm()));
		PlayerImage.add(new Image(getClass().getResource("/PlayerSpriteBack.png").toExternalForm()));
		PlayerImage.add(new Image(getClass().getResource("/PlayerSpriteLeft.png").toExternalForm()));
		PlayerImage.add(new Image(getClass().getResource("/PlayerSpriteRight.png").toExternalForm()));
		placeBomb();
		this.setHoldedWeapon(new Shovel(2, this));
	}

	
	public void move(int dirLR, int dirUD){

		if(GameController.isGameEnded())return;
		this.xPosition += dirLR*speed;
        this.yPosition += dirUD*speed;
        if(this.xPosition<50)this.xPosition=50;
        if(this.xPosition>850-50-30)this.xPosition=850-50-30;
        if(this.yPosition<0)this.yPosition=0;
        if(this.yPosition>450-50-30)this.yPosition=450-50-30;
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
		if (health <= 0){
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
		if (isDead){
			Platform.runLater(() -> GameController.setGameEnded(true,this));
		}
	}

	public boolean isHoldingItem() {
		return isHoldingItem;
	}

	public void setHoldingItem(boolean isHoldingItem) {
		this.isHoldingItem = isHoldingItem;
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

	public void render(GraphicsContext gc, int index) {
		// TODO Auto-generated method stub
		gc.drawImage(PlayerImage.get(index), getxPosition(), getyPosition(), width, height);

	}
	public void getHurt() {
		new Thread(() -> {
			if(!isInvincible) {
			this.setHealth(health - 1);
			this.setInvincible(true);
			if(name == "Player 1") GameController.getStatusPane().getP1Status().updateHealth();
			else GameController.getStatusPane().getP2Status().updateHealth();
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
	
}
