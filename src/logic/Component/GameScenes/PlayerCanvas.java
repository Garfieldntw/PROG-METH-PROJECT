package logic.Component.GameScenes;

import logic.GameController;
import logic.GameLogic;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import logic.Player.Player;
import logic.KeyboardController;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class PlayerCanvas extends Canvas {
	private Player p1;
	private Player p2;

	public PlayerCanvas(double width, double height) {
		super(width, height);
		// Add player health, spawnpoint
		this.p1 = new Player("Player 1", (50 * 1) + 1, (50 * 4) + 1, 10, Direction.RIGHT);
		this.p2 = new Player("Player 2", (50 * 15) + 1, (50 * 4) + 1, 1, Direction.LEFT);
		setupMove();

		updateCanvas(this.getGraphicsContext2D());

	}

//// ต้องทำให้ canvas มองเห็นข้างหลัง
	private void updateCanvas(GraphicsContext gc) {
		// TODO Auto-generated method stub

		new Thread(() -> {
			while (!GameController.isGameEnded()) {
				try {
					gc.clearRect(0, 0, getWidth(), getHeight());
					p1.render(gc);
					p2.render(gc);
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	private void setupMove() {
		// TODO Auto-generated method stub

		new Thread(() -> {
			KeyboardController keyboard = GameController.getKeyboardController();
			while (!GameController.isGameEnded()) {
				int dirUD1 = 0, dirLR1 = 0;
				int dirUD2 = 0, dirLR2 = 0;
				if (keyboard.isP1UpPressed()) {
					p1.setDirection(Direction.UP);
					if(!GameLogic.HasTopObject(p1))dirUD1 = -1;
				}

				if (keyboard.isP1DownPressed() ) {
					p1.setDirection(Direction.DOWN);
					if(!GameLogic.HasBottomObject(p1)) dirUD1 = 1; 
				}

				if (keyboard.isP1LeftPressed()) {
					p1.setDirection(Direction.LEFT);
					if(!GameLogic.HasLeftObject(p1)) dirLR1 = -1;
				}

				if (keyboard.isP1RightPressed()) {
					p1.setDirection(Direction.RIGHT);
					if(!GameLogic.HasRightObject(p1))dirLR1 = 1;
				}

				if (keyboard.isP2UpPressed()) {
					p2.setDirection(Direction.UP);
					if(!GameLogic.HasTopObject(p2)) dirUD2 = -1;
				}
				if (keyboard.isP2DownPressed()) {
					p2.setDirection(Direction.DOWN);
					if(!GameLogic.HasBottomObject(p2)) dirUD2 = 1;
				}
				if (keyboard.isP2LeftPressed()) {
					p2.setDirection(Direction.LEFT);
					if(!GameLogic.HasLeftObject(p2)) dirLR2 = -1;
				}
				if (keyboard.isP2RightPressed()) {
					p2.setDirection(Direction.RIGHT);
					if(!GameLogic.HasRightObject(p2)) dirLR2 = 1;
				}
				
				if (keyboard.isP1WeaponPressed()) {
					p1.getHoldedWeapon().useWeapon(p1.getxPosition(), p1.getyPosition(), p1.getDirection());
					keyboard.setP1WeaponPressed(false);
				}
				if (keyboard.isP2WeaponPressed()) {
					p2.getHoldedWeapon().useWeapon(p2.getxPosition(), p2.getyPosition(), p2.getDirection());
					keyboard.setP2WeaponPressed(false);
				}
				p1.move(dirLR1, dirUD1);
				p2.move(dirLR2, dirUD2);

				try {
					Thread.sleep(30); // ??????????????
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public Player getP1() {
		return this.p1;
	}

	public Player getP2() {
		return this.p2;
	}
}
