package object;

import javafx.scene.image.Image;

public class Floor extends Obstacle{
	private final Image FloorImage = new Image(getClass().getResource("/green floor.png").toExternalForm());
	
	public Floor(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
	public Image getImage() {
		return FloorImage;
	}
}
