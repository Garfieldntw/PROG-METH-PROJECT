package weapon;

import javafx.scene.image.Image;
import logic.Player.Player;

public abstract class Weapon {
	private int durability;
	private Player p;
	public Weapon(int durability, Player p) {
		this.durability = durability;
		this.p = p;
	}
	public Weapon(int durability) {
		this.durability = durability;
	}
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	public abstract void useWeapon(double Xpos, double yPos, int dirLR, int dirUD);
	
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		if(durability <= 0) {
			this.broke();
		}
		else this.durability = durability;
	}
	protected void broke() {
		this.p.setHoldingItem(false);
		
	}
	public abstract Image getImage();
}
