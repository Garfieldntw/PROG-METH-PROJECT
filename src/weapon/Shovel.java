package weapon;

import javax.swing.text.Position;

import javafx.scene.canvas.GraphicsContext;
import logic.GameController;
import logic.GameLogic;
import logic.Player.Player;

public class Shovel extends Weapon{
	
	public Shovel(int durability, Player p) {
		super(durability, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useWeapon(int Xpos, int yPos, int dirLR, int dirUD) {
		// TODO Auto-generated method stub
		GameController.getWeaponCanvas().drawShovel(this.getP(), Xpos, yPos, dirLR, dirUD, GameController.getWeaponCanvas().getGraphicsContext2D());
		this.setDurability(getDurability() - 1);
	}

	@Override
	protected void broke() {
		// TODO Auto-generated method stub
		
	}
	
}
