package logic.Component.GameScenes.BottomBar;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameController;
import logic.Player.Player;

public class PlayerStatusPane extends HBox{
	private Player p;
	private ImageView playerImageV;
	private final ImageView healthImageV = new ImageView(new Image(getClass().getResource("/heartImage.png").toExternalForm()));
	private final ImageView bombImageV = new ImageView (new Image(getClass().getResource("/bombImage1.png").toExternalForm()));
	private ImageView weaponImageV = new ImageView(new Image(getClass().getResource("/bulletImage.png").toExternalForm()));
	private ImageView speedImageV = new ImageView(new Image (getClass().getResource("/speedBoots.png").toExternalForm()));
	private Text playerHealth;
	private Text bombPower;
	private Text weaponHolding;
	private Text speed;
	
	public PlayerStatusPane(Player p) {
		this.p = p;
		// Create a border stroke
		BorderStroke borderStroke = new BorderStroke(
		    Color.web("#76310c"),                // Border color
		    BorderStrokeStyle.SOLID,   // Border style
		    new CornerRadii(10),        // Rounded corners (use CornerRadii.EMPTY for square)
		    new BorderWidths(2)        // Thickness of the border
		);

		// Apply the border
		this.setBorder(new Border(borderStroke));
		
		VBox playerVbox = new VBox();
		playerImageV = new ImageView(p.getPlayerImage().get(0));
		playerImageV.setFitHeight(90);
		playerImageV.setPreserveRatio(true);
		playerHealth = new Text(p.getHealth() + "");
		Text playerName = new Text(p.getName());
		playerVbox.getChildren().addAll(playerImageV,playerName);
		playerVbox.setAlignment(Pos.CENTER);
		playerVbox.setSpacing(10);
		playerVbox.setPrefWidth(120);

		
		VBox status = new VBox();
		playerHealth = new Text("" + p.getHealth());
		HBox healthBox = makeMiniHbox(playerHealth,healthImageV);
		
		bombPower = new Text("" + p.getBombPower());
		HBox bombPowerBox = makeMiniHbox(bombPower,bombImageV);
		
		weaponHolding = new Text(p.getHoldedWeapon().toString());
		weaponImageV = new ImageView(p.getHoldedWeapon().getImage());
		HBox weaponBox = makeMiniHbox(weaponHolding,weaponImageV);
		
		speed = new Text(p.getSpeed() + "");
		HBox speedBox = makeMiniHbox(speed, speedImageV);
		status.setAlignment(Pos.CENTER);
		status.getChildren().addAll( healthBox, bombPowerBox, weaponBox,speedBox);
		status.setSpacing(5);
		
		this.setMaxHeight(150);
		this.setSpacing(10);
		this.getChildren().addAll(playerVbox,status);
		this.setPrefWidth(270);
		
		this.setBackground(new Background(new BackgroundFill(p.getColor(), new CornerRadii(10), Insets.EMPTY)));

	}

	public HBox makeMiniHbox(Text text, ImageView imageView){
		HBox hBox = new HBox();
		imageView.setFitWidth(30);
		imageView.setPreserveRatio(true);
		hBox.getChildren().addAll(imageView, text);
		text.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
		hBox.setAlignment(Pos.CENTER_LEFT);
		hBox.setSpacing(15);
		hBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7), new CornerRadii(10), Insets.EMPTY)));
		hBox.setPrefWidth(120);
		return hBox;
	}
	
	public void updateHealth() {
		Platform.runLater(() -> playerHealth.setText(String.valueOf(p.getHealth())));
	}
	
	public void updateBombPower(){
		Platform.runLater(() -> bombPower.setText("" + p.getBombPower()));
	}
	
	public void updateWeapon(){
		Platform.runLater(() -> {
			weaponHolding.setText(p.getHoldedWeapon().toString());
			weaponImageV.setImage(p.getHoldedWeapon().getImage());
		});
	}
	
	public void updateSpeed(){
		Platform.runLater(() -> speed.setText("" + p.getSpeed()));
	}
}

