package item.buff;
import logic.Player.*;
import javafx.scene.image.Image;
import logic.GameLogic;
public class SpeedBuff extends Buff{
	private final Image speedBuffImage = new Image(getClass().getResource("/speedBoots.png").toExternalForm());;
	
	
	public SpeedBuff() {
		super();
	}
	
	public Image getImage() {
		return speedBuffImage;
	}

	@Override
	public void applyBuff(Player p) {
		p.setSpeed(p.getSpeed() + 1);
		GameLogic.updateSpeed();
	}
	
}
