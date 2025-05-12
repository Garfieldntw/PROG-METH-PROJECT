package logic.Component;

import logic.GameController;
import logic.GameLogic;
import logic.Player.Player;
import logic.KeyboardController;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameCanvas extends Canvas {
	private Player p1;
	private Player p2;

	public GameCanvas(double width, double height) {
		super(width, height);
		// Add player health, spawnpoint
		this.p1 = new Player("Player 1", (50 * 1) + 1, (50 * 4) + 1, 10);
		this.p2 = new Player("Player 2", (50 * 15) + 1, (50 * 4) + 1, 1);
		setupMove();

		updateCanvas(this.getGraphicsContext2D());

	}

//// ต้องทำให้ canvas มองเห็นข้างหลัง
	private void updateCanvas(GraphicsContext gc) {
		// TODO Auto-generated method stub

		new Thread(() -> {
			while (!GameController.isGameEnded()) {

				int x = 0;
				try {
					gc.clearRect(0, 0, getWidth(), getHeight());
					// ?????????????????????????????????
					// RGBA: alpha = 0.5 for transparency
					p1.render(gc, (int) x / 10);
					p2.render(gc, (int) x / 10);
					x += 1;
					if (x >= 30)
						x = 0;

					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	private void debug() {
		new Thread(() -> {
			while (!GameController.isGameEnded()) {
				System.out.println(GameLogic.xPane(p1));
				System.out.println(GameLogic.yPane(p1));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
				if (keyboard.isP1UpPressed() && !GameLogic.HasTopObject(p1)) {
					dirUD1 = -1;
				}

				if (keyboard.isP1DownPressed() && !GameLogic.HasBottomObject(p1)) {
					dirUD1 = 1;
				}

				if (keyboard.isP1LeftPressed() && !GameLogic.HasLeftObject(p1)) {
					dirLR1 = -1;
				}

				if (keyboard.isP1RightPressed() && !GameLogic.HasRightObject(p1)) {
					dirLR1 = 1;
				}

				if (keyboard.isP2UpPressed() && !GameLogic.HasTopObject(p2)) {
					dirUD2 = -1;
				}
				if (keyboard.isP2DownPressed() && !GameLogic.HasBottomObject(p2))
					dirUD2 = 1;

				if (keyboard.isP2LeftPressed() && !GameLogic.HasLeftObject(p2))
					dirLR2 = -1;

				if (keyboard.isP2RightPressed() && !GameLogic.HasRightObject(p2))
					dirLR2 = 1;
				if (keyboard.isP1weaponPressed()) {
					p1.getHoldedWeapon().useWeapon(p1.getxPosition(), p1.getyPosition(), dirLR1, dirUD1);
				}
				if (keyboard.isP1weaponPressed()) {
					p2.getHoldedWeapon().useWeapon(p2.getxPosition(), p2.getyPosition(), dirLR2, dirUD2);
				}
				p1.move(dirLR1, dirUD1);
				p2.move(dirLR2, dirUD2);

				try {
					Thread.sleep(30); // adjust as needed (e.g., 60–150ms for smooth movement)
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
