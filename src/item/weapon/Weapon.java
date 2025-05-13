package item.weapon;

import item.Item;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import logic.GameController;
import logic.Player.*;
import logic.GameLogic;
public abstract class Weapon extends Item{
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
	public abstract void useWeapon(double Xpos, double yPos, Direction direction);
	
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
		p.setHoldedWeapon(new NoWeapon(1, p));;
	}
	
	@Override
	public void getPickedUp(Player p) {
		this.setP(p);
		p.setHoldedWeapon(this);
		GameLogic.updateWeapon();
	}
}
