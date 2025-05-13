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
import logic.*;
import object.Floor;
import object.BlockObject;

import java.util.ArrayList;

import item.weapon.*;
public class EachPane extends Pane{
	private BlockObject Object;
	private int xPosition;
	private int yPosition;
	private Color basecolor = Color.web("#488f3d");
	private Weapon weapondrop;
	private final int PANE_WIDTH = 50;
	private final int PANE_HEIGHT = 50;
	
	
	public EachPane(BlockObject Object, int xPosition, int yPosition, int index) {
		this.setObject(Object);
		this.setxPosition(xPosition);
		this.setyPosition(yPosition);
		this.setMinSize(PANE_WIDTH, PANE_HEIGHT);
		this.drawcell(this.getObject().getImage(index));
	}
	// draw only Pane that has Object thats not a Floor inside of it
	// so the backgroundimage is actually from the layoutpane
	public void drawcell(Image image) {
		if(!(this.getObject() instanceof Floor)) {
			BackgroundSize bgSize = new BackgroundSize(50,50,false,false,false,false);
			BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
			this.setBackground(new Background(bgImg));
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
	
	//public void Break() {
	//	this.setObject(new Floor(this.getxPosition(),this.getyPosition()));
	//}
}
