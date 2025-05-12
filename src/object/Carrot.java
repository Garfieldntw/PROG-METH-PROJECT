package object;



import javafx.scene.image.Image;

public class Carrot extends BlockObject{
	// Source ภาพ
	private final Image CarrotImage	= new Image(getClass().getResource("/carrot.png").toExternalForm());;
	
	public Carrot(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return CarrotImage;
	}


	
	
}
