package logic.Component;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameController;
import logic.Player.Player;

public class PlayerStatusPane extends HBox{
	private Player p;
	private Image PlayerImage;
	
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
		BackgroundFill bgFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = {bgFill};
		BackgroundSize bgSize = new BackgroundSize(160,160,false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(PlayerImage, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		//
		imagepane.setBackground(new Background(bgFillA,bgImgA));
		VBox status = new VBox();
		this.setPadding(new Insets(10));
		Text playerName = new Text(p.getName());
		Text playerHealth = new Text(String.valueOf(p.getHealth()));
		Text bombPower = new Text(String.valueOf(GameController.getWeaponCanvas().getBombpower()));
		status.getChildren().addAll(playerName, playerHealth, bombPower);
		this.getChildren().addAll(imagepane, status);
		
	}
}
