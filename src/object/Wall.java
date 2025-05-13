package object;



import javafx.scene.image.Image;

public class Wall extends BlockObject{
	// Source ภาพ
	private final Image[] WallImage	= {
			new Image(getClass().getResource("/CarrotImage.png").toExternalForm()),
			new Image(getClass().getResource("/EggplantImage.png").toExternalForm()),
			//new Image(getClass().getResource("/BroccoliImage.png").toExternalForm())
	};
	
	public Wall(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage(int index) {
		// TODO Auto-generated method stub
		return WallImage[index];
	}


	
	
}
