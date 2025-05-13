package logic;

import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.Component.*;
import logic.Component.GameScenes.*;
import logic.Component.GameScenes.BottomBar.StatusPane;
import logic.Component.MainMenu.*;
import logic.Player.Player;
import java.util.ArrayList;

import Util.MapReader;

public class GameController {

	private static MainMenuPane menuPane;
	private static HowToPlay howToPlayPane;
	private static MapSelectorPane mapSelectorPane;
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
	private static final Image[] TextureImage = {new Image(GameController.class.getResource("/green floor.png").toExternalForm(),true),
			new Image(GameController.class.getResource("/woodTexture.png").toExternalForm(),true),
			new Image(GameController.class.getResource("/dirtFloor.png").toExternalForm(),true)
	};
	// initialize MainMenuPane
	public static void setupScene() {	
		// set Vbox
		root = new VBox();
		root.setFocusTraversable(true);
		root.setAlignment(Pos.CENTER);
		root.setPadding(Insets.EMPTY);
		scene = new Scene(root, 850, 630); // scene size
		
		// initiating
		isGameEnded = false;
		menuPane = new MainMenuPane();
		root.getChildren().addAll(menuPane);

	}

	public static void toMainmenuScene() {
		root.getChildren().clear();
		root.getChildren().add(menuPane);
	}
	// SelectMap
	public static void toMapSelectorScene() {
		mapSelectorPane = new MapSelectorPane();
		root.getChildren().clear();
		root.getChildren().add(mapSelectorPane);
	}
	// Generate Map choosed
	public static void toGameScene() {
		createMap();
		dropCanvas = new DropCanvas();
		keyboardController = new KeyboardController();
		layoutPane = new LayoutPane(epList, mapNum);
		playerCanvas = new PlayerCanvas(17 * 50, 9 * 50);
		weaponCanvas = new WeaponCanvas(850, 450);
		statusPane = new StatusPane();
		gamePane = new GamePane(layoutPane, playerCanvas, weaponCanvas, dropCanvas);
		root.getChildren().clear();
		root.getChildren().addAll(gamePane, statusPane);
	}
	
	public static void setGameEndWithWinner(boolean isGameEnded, Player player) {
		GameController.isGameEnded = isGameEnded;
		Player winner = playerCanvas.getP1();
		if (player.getName().equals("Player 1")) // if loser is 1 -> winner is 2
			winner = playerCanvas.getP2();
		GameOverPane gameOverPane = new GameOverPane(winner.getName() + " Wins!", winner.getPlayerImage().get(0), player.getColor());
		toGameOverScene(gameOverPane);
	}

	public static void setGameEnded(boolean isGameEnded) {
		GameController.isGameEnded = isGameEnded;
		Image drawImage = new Image(GameController.class.getResource("/drawImage.png").toExternalForm());
		GameOverPane gameOverPane = new GameOverPane("Draw!", drawImage, Color.GRAY); // if game ends in draw, background is gray
		toGameOverScene(gameOverPane);
	}


	public static void toGameOverScene(GameOverPane gameOverPane) {
		root.getChildren().clear();
		root.getChildren().add(gameOverPane);

	}
	
	public static void toHowToPlayScene() {
		howToPlayPane = new HowToPlay();
		root.getChildren().clear();
		root.getChildren().add(howToPlayPane);
	}
	
	public static void toMainMenuScene() {
		root.getChildren().clear();
		root.getChildren().add(menuPane);
	}
	
	public static void createMap() {
		// MapReader mapreader = new MapReader("/Map1.txt");
		String Path = "/Map" + (mapNum + 1) + ".txt";
		MapReader mapreader = new MapReader(Path);
		epList = mapreader.getMapint();
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

	public static int getMapnum() {
		return mapNum;
	}

	public static void setmapNum(int mapnum) {
		GameController.mapNum = mapnum;
	}
	
	public static Image[] getTextureImage() {
		return TextureImage;
	}
}
