package object;

import Interface.*;
import javafx.scene.image.Image;
import logic.GameLogic;

public class Lettuce extends BlockObject implements Breakable {
	// Source ภาพ

	private final Image LettuceImage = new Image(getClass().getResource("/CabbageImage.png").toExternalForm());

	private Boolean isBreak;

	public Lettuce(int xPosition, int yPosition) {
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
	public Image getImage() {
		// TODO Auto-generated method stub
		return LettuceImage;
	}
}
