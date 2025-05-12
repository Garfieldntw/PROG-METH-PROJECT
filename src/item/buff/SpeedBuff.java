package item.buff;
import logic.Player.*;
import javafx.scene.image.Image;
public class SpeedBuff extends Weapon{
	private final Image speedBuffImage = new Image(getClass().getResource("/bulletImage.png").toExternalForm());;
	public SpeedBuff(int durability) {
		super(durability);
	}
	
	public SpeedBuff(int durability, Player p) {
		super(durability);
	}
	
	public Image getImage() {
		// TODO Auto-generated method stub
		return speedBuffImage;
	}
	
	public void useWeapon(double Xpos, double yPos, int dirLR, int dirUD) {
		getP().setSpeed(getP().getSpeed() + 2);
		this.setDurability(0);
	}
	
	
}
