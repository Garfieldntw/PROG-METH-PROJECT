package object;

import Interface.*;
import javafx.scene.image.Image;
import logic.*;

public class BuffObject extends BlockObject implements Breakable{
	private final Image[] BuffObjectImage = {
			new Image(getClass().getResource("/ChiliImage.png").toExternalForm(),true),
			new Image(getClass().getResource("/OnionImage.png").toExternalForm(),true),
			new Image(getClass().getResource("/BellPepperImage.png").toExternalForm(),true)
	};
	public BuffObject(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}

	@Override
	public void breakObject() {
		// TODO Auto-generated method stub
		GameLogic.dropBuff(getxPosition(), getyPosition());
	}

	@Override
	public Image getImage(int index) {
		// TODO Auto-generated method stub
		return BuffObjectImage[index];
	}

}
