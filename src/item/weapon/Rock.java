package weapon;

import javafx.scene.image.Image;
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
	public void useWeapon(double xPos, double yPos, int dirLR, int dirUD) {
		System.out.println("use rock");
		GameController.getWeaponCanvas().ThrowRock(xPos + 50*dirLR, yPos + 50*dirUD, dirLR, dirUD, GameController.getWeaponCanvas().getGraphicsContext2D());
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
