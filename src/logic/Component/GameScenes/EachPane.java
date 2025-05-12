package logic.Component.GameScenes;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.GameLogic;
import object.Floor;
import object.BlockObject;
import weapon.Weapon;

public class EachPane extends Pane{
	private BlockObject Object;
	private int xPosition;
	private int yPosition;
	private Color basecolor = Color.web("#488f3d");
	private Weapon weapondrop;
	private final Image bombImage1 = new Image(ClassLoader.getSystemResource("bombImage1.png").toString());
	private final Image bombImage2 = new Image(ClassLoader.getSystemResource("bombImage2.png").toString());
	private final Image bombImage3 = new Image(ClassLoader.getSystemResource("bombImage3.png").toString());
	private final int PANE_WIDTH = 50;
	private final int PANE_HEIGHT = 50;
	
	
	public EachPane(BlockObject Object, int xPosition, int yPosition) {
		this.setObject(Object);
		this.setxPosition(xPosition);
		this.setyPosition(yPosition);
		this.setMinSize(PANE_WIDTH, PANE_HEIGHT);
		this.drawcell(this.getObject().getImage());
		//this.setStyle("-fx-background-color: lightblue; -fx-background-radius: 20;");
	}
	
	public void drawcell(Image image) {
		if(this.getObject() != null) {
			BackgroundFill bgFill = new BackgroundFill(basecolor, CornerRadii.EMPTY, Insets.EMPTY);
			BackgroundFill[] bgFillA = {bgFill};

			
			BackgroundSize bgSize = new BackgroundSize(50,50,false,false,false,false);
			BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);

			BackgroundImage[] bgImgA = {bgImg};
			
			this.setBackground(new Background(bgFillA,bgImgA));
		}
		else {
			BackgroundFill bgFill = new BackgroundFill(basecolor, CornerRadii.EMPTY, Insets.EMPTY);
			this.setBackground(new Background(bgFill));
		}
	}
	
	public void DeleteObject() {
		BackgroundFill bgFill = new BackgroundFill(basecolor, CornerRadii.EMPTY, Insets.EMPTY);
		this.setBackground(new Background(bgFill));
	}

	public BlockObject getObject() {
		return Object;
	}
	public void setObject(BlockObject object) {
		Object = object;
	}
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public Weapon getWeapondrop() {
		return weapondrop;
	}

	public void setWeapondrop(Weapon weapondrop) {
		this.weapondrop = weapondrop;
	}
	
	public void Break() {
		this.setObject(new Floor(this.getxPosition(),this.getyPosition()));
	}
}
