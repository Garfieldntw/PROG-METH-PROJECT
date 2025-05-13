package item.buff;

import item.Item;
import logic.Player.Player;

public abstract class Buff extends Item{
	public Buff() {
	}
	
	protected abstract void applyBuff(Player p);
	
	@Override
	public void getPickedUp(Player p) {
		applyBuff(p);
	}
}
