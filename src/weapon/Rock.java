package weapon;

import javafx.scene.image.Image;
import logic.GameController;
import logic.Player.Player;

public class Rock extends Weapon{
	private final Image RockImage = new Image(getClass().getResource("/rockImage.png").toExternalForm());;
	public Rock(int durability, Player p) {
		super(durability, p);
		// TODO Auto-generated constructor stub
	}
	public Rock(int durability) {
		super(durability);
	}
	@Override
	public void useWeapon(double Xpos, double yPos, int dirLR, int dirUD) {
		// TODO Auto-generated method stub
		GameController.getWeaponCanvas().ThrowRock(dirUD, dirUD, dirUD, dirUD, GameController.getWeaponCanvas().getGraphicsContext2D());
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return RockImage;
	}
	
	@Override
	public java.lang.String toString() {
		// TODO Auto-generated method stub
		return "Rock";
	}

}
