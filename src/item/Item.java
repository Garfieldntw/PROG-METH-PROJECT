package item;

import javafx.scene.image.Image;
import logic.Player.Player;

public abstract class Item {

	public abstract Image getImage();
	
	public abstract void getPickedUp(Player p);
}
