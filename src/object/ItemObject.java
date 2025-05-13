package object;

import Interface.Breakable;
import javafx.scene.image.Image;
import logic.GameLogic;

public class ItemObject extends BlockObject implements Breakable{

	private final Image[] ItemObjectImage = {
			new Image(getClass().getResource("/CucumberImage.png").toExternalForm(),true),
			new Image(getClass().getResource("/AvocadoImage.png").toExternalForm(),true),
			new Image(getClass().getResource("/EggImage.png").toExternalForm(),true)
	};

	private Boolean isBreak;

	public ItemObject(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		this.setBreak(false);
		// TODO Auto-generated constructor stub
	}

	public void setBreak(Boolean Break) {
		this.isBreak = Break;
	}

	@Override
	public void breakObject() {
		// TODO Auto-generated method stub
		this.setBreak(true);
		
		GameLogic.dropWeapon(this.getxPosition(), this.getyPosition());
	}

	@Override
	public Image getImage(int index) {
		// TODO Auto-generated method stub
		return ItemObjectImage[index];
	}
}
