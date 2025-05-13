package item.weapon;

import javafx.scene.image.Image;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import logic.GameController;
import logic.Player.Player;

public class Rock extends Weapon{
	private final Image RockImage = new Image(getClass().getResource("/rockImage.png").toExternalForm());;
	public Rock(int durability, Player p) {
		super(durability, p);
	}
	public Rock(int durability) {
		super(durability);
	}
	@Override
	public void useWeapon(double xPos, double yPos, Direction direction) {
		int dirLR = 0; int dirUD = 0;
		switch (direction) {
			case Direction.UP -> dirUD = -1;
			case Direction.DOWN -> dirUD = 1;
			case Direction.RIGHT -> dirLR = 1;
			case Direction.LEFT -> dirLR = -1;
		}
		GameController.getWeaponCanvas().throwRock(xPos + 50*dirLR, yPos + 50*dirUD, dirLR, dirUD, GameController.getWeaponCanvas().getGraphicsContext2D());
		this.setDurability(getDurability() - 1);
	}

	@Override
	public Image getImage() {
		return RockImage;
	}
	
	@Override
	public java.lang.String toString() {
		return "Rock";
	}

}
