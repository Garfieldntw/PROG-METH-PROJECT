package weapon;

import javafx.scene.image.Image;

public class NormalBomb extends Weapon{
	// ใส่รูป
	private final Image BombImage = new Image(getClass().getResource("/bombImage1.png").toExternalForm());

	@Override
	public void useWeapon() {
		// TODO Auto-generated method stub
		
	}
	public Image getBombImage() {
		return BombImage;
	}
	
}
