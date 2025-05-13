package item.buff;
import logic.GameController;
import logic.Player.*;
import javafx.scene.image.Image;
import logic.GameLogic;
public class SpeedBuff extends Buff{
	private final Image speedBuffImage = new Image(getClass().getResource("/bulletImage.png").toExternalForm());;
	
	
	public SpeedBuff() {
		super();
	}
	
	public Image getImage() {
		// TODO Auto-generated method stub
		return speedBuffImage;
	}

	@Override
	public void applyBuff(Player p) {
		// TODO Auto-generated method stub
		p.setSpeed(p.getSpeed() + 1);
		GameLogic.updateSpeed();
	}
	
}
