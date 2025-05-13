package item.weapon;
import logic.Player.*;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.image.Image;
public class NoWeapon extends Weapon{
	private final Image fistImage = new Image(getClass().getResource("/bulletImage.png").toExternalForm());;
	public NoWeapon(int durability, Player p) {
		super(durability, p);
	}
	
	public void Broke() {
		;
	}
	
	public Image getImage() {
		return fistImage;
	}
	
	public void useWeapon(double xPos, double yPos, Direction direction) {
		System.out.println(this.getP().getName() + " don't have any weapon");
	}
	
	@Override
	public void setDurability(int durability) {
		super.setDurability(1);
	}
	
	@Override
	public java.lang.String toString() {
		// TODO Auto-generated method stub
		return "NoWeapon";
	}
}
