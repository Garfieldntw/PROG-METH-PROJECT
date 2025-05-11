package weapon;

import javax.swing.text.Position;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameController;
import logic.GameLogic;
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
	public void useWeapon(double Xpos, double yPos, int dirLR, int dirUD) {
		// TODO Auto-generated method stub
		GameController.getWeaponCanvas().drawShovel(this.getP(), Xpos, yPos, dirLR, dirUD, GameController.getWeaponCanvas().getGraphicsContext2D());
		this.setDurability(getDurability() - 1);
	}

	@Override
	protected void broke() {
		// TODO Auto-generated method stub
		
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
