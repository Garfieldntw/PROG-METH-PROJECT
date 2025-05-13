package item.buff;

import javafx.scene.image.Image;
import logic.GameController;
import logic.Player.Player;
import logic.GameLogic;
public class BombBuff extends Buff{
	private final Image bombBuffImage = new Image(getClass().getResource("/bombImage1.png").toExternalForm());;
	@Override
	protected void applyBuff(Player p) {
		// TODO Auto-generated method stub
		p.setBombPower(p.getBombPower() + 1);
		GameLogic.updateBombPower();
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return bombBuffImage;
	}

}
