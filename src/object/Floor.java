package object;

import javafx.scene.image.Image;

public class Floor extends BlockObject{
	private final Image[] FloorImage = {new Image(getClass().getResource("/green floor.png").toExternalForm(),true),
			new Image(getClass().getResource("/woodTexture.png").toExternalForm(),true),
			new Image(getClass().getResource("/dirtFloor.png").toExternalForm(),true)
		};
	
	public Floor(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}

	public Image getImage(int index) {
		// TODO Auto-generated method stub
		return FloorImage[index];
	}
}
