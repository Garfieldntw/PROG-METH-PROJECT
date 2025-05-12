package object;

import Interface.Breakable;
import javafx.scene.image.Image;
import logic.GameLogic;

public class Purple_Cabbage extends BlockObject implements Breakable{

	private final Image purpleCabbageImage = new Image(getClass().getResource("/purplecabbage.png").toExternalForm());

	private Boolean isBreak;

	public Purple_Cabbage(int xPosition, int yPosition) {
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
		
		GameLogic.DropWeapon(this.getxPosition(), this.getyPosition());
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return purpleCabbageImage;
	}
}
