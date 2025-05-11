package logic.Component;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameController;
import logic.Player.Player;

public class PlayerStatusPane extends HBox{
	private Player p;
	private Image PlayerImage;
	private final Image HealthImage = new Image(getClass().getResource("/heartImage.png").toExternalForm());
	private final Image bombImage = new Image(getClass().getResource("/bombImage1.png").toExternalForm());
	

	private Image weaponImage = new Image(getClass().getResource("/bulletImage.png").toExternalForm());
	
	public PlayerStatusPane(Player p) {
		this.p = p;
		this.setPrefWidth(270);
		this.setPrefHeight(150);
		// Create a border stroke
		BorderStroke borderStroke = new BorderStroke(
		    Color.BLACK,                // Border color
		    BorderStrokeStyle.SOLID,   // Border style
		    new CornerRadii(5),        // Rounded corners (use CornerRadii.EMPTY for square)
		    new BorderWidths(2)        // Thickness of the border
		);

		// Apply the border
		this.setBorder(new Border(borderStroke));
		PlayerImage = p.getPlayerImage().get(0);
		Pane imagepane = new Pane();
		//
		BackgroundFill bgFill = new BackgroundFill(Color.web("#e6aa49"), CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = {bgFill};
		BackgroundSize bgSize = new BackgroundSize(160,160,false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(PlayerImage, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		
		//
		imagepane.setBackground(new Background(bgFillA,bgImgA));
		VBox status = new VBox();
		this.setPadding(new Insets(10));
		
		Text playerName = new Text(p.getName());
		// set playername size or highlight
		Text playerHealth = new Text(String.valueOf(p.getHealth()));
		HBox HealthBox = new HBox();
		// add size
		ImageView healthimageview = new ImageView(HealthImage);
		healthimageview.setFitHeight(10);
		healthimageview.setFitWidth(10);
		HealthBox.getChildren().addAll(healthimageview, playerHealth);
		ImageView bombimageview = new ImageView(bombImage);
		bombimageview.setFitHeight(10);
		bombimageview.setFitWidth(10);
		Text bombPower = new Text(String.valueOf(GameController.getWeaponCanvas().getBombpower()));
		HBox bombpowerbox = new HBox();
		// add size
		bombpowerbox.getChildren().addAll(bombimageview, bombPower);
		Text WeaponHolded = new Text("");
		ImageView weaponImageview = new ImageView(weaponImage);
		weaponImageview.setFitHeight(10);
		weaponImageview.setFitWidth(10);
		if(p.isHoldingItem()) {
			weaponImageview.setImage(p.getHoldedWeapon().getImage());
			WeaponHolded.setText(p.getHoldedWeapon().toString());
		}
		HBox weaponinfobox = new HBox();
		weaponinfobox.getChildren().addAll(weaponImageview);
		
		status.getChildren().addAll(playerName, HealthBox, bombpowerbox, weaponinfobox);
		this.getChildren().addAll(imagepane, status);
		
	}
	
	public Image getWeaponImage() {
		return weaponImage;
	}

	public void setWeaponImage(Image weaponImage) {
		this.weaponImage = weaponImage;
	}
}
