package object;

import Interface.*;
import javafx.scene.image.Image;
import logic.GameLogic;

public class NormalObject extends BlockObject implements Breakable {
	// Source ภาพ

	private final Image[] NormalObjectImage = {
			new Image(getClass().getResource("/CabbageImage.png").toExternalForm(),true),
			new Image(getClass().getResource("/PurpleCabbageImage.png").toExternalForm(),true),
			new Image(getClass().getResource("/LettuceImage.png").toExternalForm(),true)
	};

	private Boolean isBreak;

	public NormalObject(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		this.setBreak(false);
		// TODO Auto-generated constructor stub
	}

	public void setBreak(Boolean Break) {
		this.isBreak = Break;
	}

	@Override
	public void Break() {
		// TODO Auto-generated method stub
		this.setBreak(true);
		
		//GameLogic.DropWeapon(this.getxPosition(), this.getyPosition());
	}

	@Override
	public Image getImage(int index) {
		// TODO Auto-generated method stub
		return NormalObjectImage[index];
	}
}
