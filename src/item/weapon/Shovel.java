package item.weapon;

import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.image.Image;
import logic.GameController;
import logic.Player.Player;

public class Shovel extends Weapon{
	private final Image ShovelImage = new Image(getClass().getResource("/shovelImage.png").toExternalForm());;
	public Shovel(int durability, Player p) {
		super(durability, p);
		// TODO Auto-generated constructor stub
	}
	public Shovel(int durability) {
		super(durability);
	}
	@Override
	public void useWeapon(double xPos, double yPos, Direction direction) {
		// TODO Auto-generated method stub
		//int dirLR = 0; int dirUD = 0;
		//switch (direction) {
			//case Direction.UP -> dirUD = -1;
			//case Direction.DOWN -> dirUD = 1;
			//case Direction.RIGHT -> dirLR = 1;
			//case Direction.LEFT -> dirLR = -1;
		//}
		//GameController.getWeaponCanvas().drawShovel(this.getP(), Xpos, yPos, dirLR, dirUD, GameController.getWeaponCanvas().getGraphicsContext2D());
		GameController.getWeaponCanvas().playShovelSlashThread(GameController.getWeaponCanvas().getGraphicsContext2D(), ShovelImage, xPos + 22.5, yPos +22.5, direction);
		this.setDurability(getDurability() - 1);
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return ShovelImage;
	}

	@Override
	public java.lang.String toString() {
		// TODO Auto-generated method stub
		return "Shovel";
	}
	
}
