package logic.Component;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class GamePane extends StackPane {
	private LayoutPane layoutPane;
	private GameCanvas gameCanvas;
	private WeaponCanvas weaponCanvas;

	public GamePane(LayoutPane layoutPane, GameCanvas gameCanvas,WeaponCanvas weaponCanvas) {
		super();
		this.layoutPane = layoutPane;
		this.gameCanvas = gameCanvas;
		gameCanvas.widthProperty().bind(layoutPane.widthProperty());
		gameCanvas.heightProperty().bind(layoutPane.heightProperty());
		//GamePane.setAlignment(layoutPane, Pos.CENTER);
		//GamePane.setAlignment(gameCanvas, Pos.CENTER);
		this.setPrefSize(850, 450);
		this.weaponCanvas = weaponCanvas;
		this.getChildren().addAll(layoutPane, gameCanvas,weaponCanvas);
	}

	public LayoutPane getLayoutPane() {
		return layoutPane;
	}

	public void setLayoutPane(LayoutPane layoutPane) {
		this.layoutPane = layoutPane;
	}

	public GameCanvas getGameCanvas() {
		return gameCanvas;
	}

	public void setGameCanvas(GameCanvas gameCanvas) {
		this.gameCanvas = gameCanvas;
	}

	public WeaponCanvas getWeaponCanvas() {
		return weaponCanvas;
	}

	public void setWeaponCanvas(WeaponCanvas weaponCanvas) {
		this.weaponCanvas = weaponCanvas;
	}

}
