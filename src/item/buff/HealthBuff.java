package item.buff;

import javafx.scene.image.Image;
import logic.GameController;
import logic.GameLogic;
import logic.Player.Player;

public class HealthBuff extends Buff{
	private final Image HealthImage = new Image(getClass().getResource("/heartImage.png").toExternalForm());;
	@Override
	protected void applyBuff(Player p) {
		// TODO Auto-generated method stub
		p.setHealth(p.getHealth() + 1);
		GameLogic.updateHealth();
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return HealthImage;
	}

}
