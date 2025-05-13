package logic;

import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.Component.*;
import logic.Component.GameScenes.DropCanvas;
import logic.Component.GameScenes.EachPane;
import logic.Component.GameScenes.PlayerCanvas;
import logic.Component.GameScenes.GamePane;
import logic.Component.GameScenes.LayoutPane;
import logic.Component.GameScenes.WeaponCanvas;
import logic.Component.GameScenes.BottomBar.StatusPane;
import logic.Component.MainMenu.MainMenuPane;
import logic.Player.Player;
import item.*;
import item.buff.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Util.MapReader;
import object.*;

public class GameController {

	private static MainMenuPane menuPane;
	private static PlayerCanvas playerCanvas;
	private static WeaponCanvas weaponCanvas;
	private static ArrayList<ArrayList<Integer>> epList = new ArrayList<>();
	private static LayoutPane layoutPane;
	private static DropCanvas dropCanvas;
	private static GamePane gamePane;
	private static KeyboardController keyboardController;
	private static StatusPane statusPane;
	private static VBox root;
	private static Scene scene;
	private static boolean isGameEnded;
	private static int mapNum;


	public static void setupScene() {
		setmapNum(0);
		// set Vbox
		root = new VBox();
		root.setFocusTraversable(true);
		root.setAlignment(Pos.CENTER);
		root.setPadding(Insets.EMPTY);
		scene = new Scene(root, 850, 630); // scene size
		createMap();

		// initiating
		dropCanvas = new DropCanvas();
		isGameEnded = false;
		keyboardController = new KeyboardController();
		layoutPane = new LayoutPane(epList, mapNum);

		playerCanvas = new PlayerCanvas(17 * 50, 9 * 50);
		weaponCanvas = new WeaponCanvas(850, 450);
		statusPane = new StatusPane();
		gamePane = new GamePane(layoutPane, playerCanvas, weaponCanvas, dropCanvas);

		menuPane = new MainMenuPane();

		root.getChildren().addAll(menuPane);

	}

	public static void setGameEndWithWinner(boolean isGameEnded, Player player) {
		GameController.isGameEnded = isGameEnded;
		Player winner = playerCanvas.getP1();
		if (player.getName().equals("Player 1"))
			winner = playerCanvas.getP2();
		GameOverPane gameOverPane = new GameOverPane(winner.getName() + " Wins!", winner.getPlayerImage().get(0), player.getColor());

		GametoGameOverScene(gameOverPane);
	}

	public static void setGameEnded(boolean isGameEnded) {
		GameController.isGameEnded = isGameEnded;
		Image drawImage = new Image(GameController.class.getResource("/drawImage.png").toExternalForm());
		GameOverPane gameOverPane = new GameOverPane("Draw!", drawImage, Color.GRAY);

		GametoGameOverScene(gameOverPane);
	}

	public static void mainToGameScene() {
		root.getChildren().clear();
		root.getChildren().addAll(gamePane, statusPane);
	}

	public static void GametoGameOverScene(GameOverPane gameOverPane) {
		root.getChildren().clear();
		root.getChildren().add(gameOverPane);

	}

	public static void GameOvertoMainmenuScene() {
		root.getChildren().clear();
		root.getChildren().add(menuPane);
	}

	public static MainMenuPane getMenuPane() {
		return menuPane;
	}

	public static void setMenuPane(MainMenuPane menuPane) {
		GameController.menuPane = menuPane;
	}

	public static PlayerCanvas getPlayerCanvas() {
		return playerCanvas;
	}

	public static void setPlayerCanvas(PlayerCanvas playerCanvas) {
		GameController.playerCanvas = playerCanvas;
	}

	public static WeaponCanvas getWeaponCanvas() {
		return weaponCanvas;
	}

	public static void setWeaponCanvas(WeaponCanvas weaponCanvas) {
		GameController.weaponCanvas = weaponCanvas;
	}

	public static ArrayList<ArrayList<Integer>> getEpList() {
		return epList;
	}

	public static void setEpList(ArrayList<ArrayList<Integer>> epList) {
		GameController.epList = epList;
	}

	public static LayoutPane getLayoutPane() {
		return layoutPane;
	}

	public static void setLayoutPane(LayoutPane layoutPane) {
		GameController.layoutPane = layoutPane;
	}

	public static GamePane getGamePane() {
		return gamePane;
	}

	public static void setGamePane(GamePane gamePane) {
		GameController.gamePane = gamePane;
	}

	public static KeyboardController getKeyboardController() {
		return keyboardController;
	}

	public static void setKeyboardController(KeyboardController keyboardController) {
		GameController.keyboardController = keyboardController;
	}

	public static StatusPane getStatusPane() {
		return statusPane;
	}

	public static void setStatusPane(StatusPane statusPane) {
		GameController.statusPane = statusPane;
	}

	public static VBox getRoot() {
		return root;
	}

	public static void setRoot(VBox root) {
		GameController.root = root;
	}

	public static Scene getScene() {
		return scene;
	}

	public static void setScene(Scene scene) {
		GameController.scene = scene;
	}

	public static boolean isGameEnded() {
		return isGameEnded;
	}

	public static DropCanvas getDropCanvas() {
		return dropCanvas;
	}

	public static void setDropCanvas(DropCanvas dropCanvas) {
		GameController.dropCanvas = dropCanvas;
	}
	//

	public static void createMap() {
		// MapReader mapreader = new MapReader("/Map1.txt");
		MapReader mapreader = new MapReader("/Map4.txt");
		epList = mapreader.getMapint();

		// map creation
		// ArrayList<Integer> xSpawnPoints = new ArrayList<>(Arrays.asList(1, 15));
		// ArrayList<Integer> ySpawnPoints = new ArrayList<>(Arrays.asList(3, 4, 5));

		// for (int y = 0; y < 9; y++) {
		// ArrayList<EachPane> row = new ArrayList<>();
		// for (int x = 0; x < 17; x++) {
		// if (y == 0 || y == 8 || x == 0 || x == 16 || (y % 2 == 0 && x % 2 == 0 && !(y
		// == 4 && x == 8))) {
		// row.add(new EachPane(new Carrot(x, y), x, y));
		// } else if (xSpawnPoints.contains(x) && ySpawnPoints.contains(y)) {
		// row.add(new EachPane(new Floor(x, y), x, y));
		// } else {
		// Random random = new Random();
		// if (random.nextInt(100) < 80) {
		// if (random.nextInt(100) < 85)
		// row.add(new EachPane(new Lettuce(x, y), x, y));
		// else
		// row.add(new EachPane(new Purple_Cabbage(x, y), x, y));
		// } else {
		// row.add(new EachPane(new Floor(x, y), x, y));
		// }
		// }
		// }
		// epList.add(row);
		// }
	}

	public static int getMapnum() {
		return mapNum;
	}

	public static void setmapNum(int mapnum) {
		mapNum = mapnum;
	}
}
