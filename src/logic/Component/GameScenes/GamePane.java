package logic.Component.GameScenes;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class GamePane extends StackPane {
	private LayoutPane layoutPane;
	private PlayerCanvas playerCanvas;
	private WeaponCanvas weaponCanvas;
	private DropCanvas dropCanvas = new DropCanvas();

	public GamePane(LayoutPane layoutPane, PlayerCanvas playerCanvas,WeaponCanvas weaponCanvas,DropCanvas dropCanvas) {
		super();
		this.layoutPane = layoutPane;
		this.playerCanvas = playerCanvas;
		playerCanvas.widthProperty().bind(layoutPane.widthProperty());
		playerCanvas.heightProperty().bind(layoutPane.heightProperty());
		//GamePane.setAlignment(layoutPane, Pos.CENTER);
		//GamePane.setAlignment(gameCanvas, Pos.CENTER);
		this.setPrefSize(850, 450);
		this.weaponCanvas = weaponCanvas;
		
		this.getChildren().addAll(layoutPane, playerCanvas,weaponCanvas, dropCanvas);
	}

	public LayoutPane getLayoutPane() {
		return layoutPane;
	}

	public void setLayoutPane(LayoutPane layoutPane) {
		this.layoutPane = layoutPane;
	}

	public PlayerCanvas getGameCanvas() {
		return playerCanvas;
	}

	public void setGameCanvas(PlayerCanvas playerCanvas) {
		this.playerCanvas = playerCanvas;
	}

	public WeaponCanvas getWeaponCanvas() {
		return weaponCanvas;
	}

	public void setWeaponCanvas(WeaponCanvas weaponCanvas) {
		this.weaponCanvas = weaponCanvas;
	}
	

}
