package weapon;

import logic.Player.Player;

public abstract class Weapon {
	private int durability;
	private Player p;
	public Weapon(int durability, Player p) {
		this.durability = durability;
		this.p = p;
	}
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	public abstract void useWeapon(int Xpos, int yPos, int dirLR, int dirUD);
	
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
	
}
