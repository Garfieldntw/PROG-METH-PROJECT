package logic.Component.GameScenes;

import logic.GameController;
import logic.GameLogic;
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
				System.out.println(GameLogic.getxPaneNumOfPlayerTR(p1));
				System.out.println(GameLogic.getyPaneNumOfPlayerTR(p1));
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
			int realdirUD1 = 0; int realdirLR1 = 1;
			int realdirUD2 = 0; int realdirLR2 = -1;
			while (!GameController.isGameEnded()) {
				int dirUD1 = 0, dirLR1 = 0;
				int dirUD2 = 0, dirLR2 = 0;
				if (keyboard.isP1UpPressed()) {
					realdirUD1 = -1; realdirLR1 =0;
					if(!GameLogic.HasTopObject(p1))dirUD1 = -1;
				}

				if (keyboard.isP1DownPressed() ) {
					realdirUD1 = 1; realdirLR1 =0;
					if(!GameLogic.HasBottomObject(p1)) dirUD1 = 1; 
				}

				if (keyboard.isP1LeftPressed()) {
					realdirUD1 = 0; realdirLR1 = -1;
					if(!GameLogic.HasLeftObject(p1)) dirLR1 = -1;
				}

				if (keyboard.isP1RightPressed()) {
					realdirUD1 = 0; realdirLR1 = 1;
					if(!GameLogic.HasRightObject(p1))dirLR1 = 1;
				}

				if (keyboard.isP2UpPressed()) {
					realdirUD2 = -1; realdirLR2 = 0;
					if(!GameLogic.HasTopObject(p2)) dirUD2 = -1;
				}
				if (keyboard.isP2DownPressed()) {
					realdirUD2 = 1; realdirLR2 =0;
					if(!GameLogic.HasBottomObject(p2)) dirUD2 = 1;
				}
				if (keyboard.isP2LeftPressed()) {
					realdirUD2 = 0; realdirLR2 =-1;
					if(!GameLogic.HasLeftObject(p2)) dirLR2 = -1;
				}
				if (keyboard.isP2RightPressed()) {
					realdirUD2 = 0; realdirLR2 =1;
					if(!GameLogic.HasRightObject(p2)) dirLR2 = 1;
				}
				final int Hdir1 = realdirLR1;
				final int Vdir1 = realdirUD1;
				final int Hdir2 = realdirLR2;
				final int Vdir2 = realdirUD2;
				
				if (keyboard.isP1weaponPressed()) {
					p1.getHoldedWeapon().useWeapon(p1.getxPosition(), p1.getyPosition(),Hdir1, Vdir1);
				}
				if (keyboard.isP2weaponPressed()) {
					p2.getHoldedWeapon().useWeapon(p2.getxPosition(), p2.getyPosition(),Hdir2, Vdir2);
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
