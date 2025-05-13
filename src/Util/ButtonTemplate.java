package Util;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
public class ButtonTemplate extends Button{
	private final AudioClip hoverSound = new AudioClip(getClass().getClassLoader().getResource("hover.wav").toString());
	private final AudioClip clickSound = new AudioClip(getClass().getClassLoader().getResource("click.wav").toString());
	
	public ButtonTemplate(Text text){
		hoverSound.setVolume(0.5);
		clickSound.setVolume(0.5);
		this.setGraphic(text);
		this.setFont(Font.font("Monospaced", FontWeight.BOLD, 24));
		this.setTextFill(Color.web("#fcecd1"));
		BackgroundFill backgroundFill = new BackgroundFill(Color.web("#d8725e"), new CornerRadii(10), Insets.EMPTY);
		this.setBackground(new Background(backgroundFill));
		BorderStroke borderStroke = new BorderStroke(Color.web("#fcecd1"), BorderStrokeStyle.SOLID, new CornerRadii(10),
				new BorderWidths(2));
		this.setBorder(new Border(borderStroke));
		this.setPadding(new Insets(10, 20, 10, 20));
		
		ColorAdjust lighten = new ColorAdjust();
		lighten.setBrightness(0.3); // Positive value makes it lighter

		this.setOnMouseEntered(e -> {
			this.setCursor(Cursor.HAND);
			hoverSound.play();
			this.setEffect(lighten);
			
		});
		this.setOnMouseExited(e -> {
			this.setCursor(Cursor.DEFAULT);
			this.setEffect(null);
		});
	}

	public AudioClip getHoverSound() {
		return hoverSound;
	}
	public AudioClip getClickSound(){
		return clickSound;
	}
	
}
