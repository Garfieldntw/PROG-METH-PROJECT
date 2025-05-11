package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import logic.Component.*;
import logic.Component.MainMenu.MainMenuPane;
import logic.Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import object.*;
import weapon.Weapon;

public class GameController {
    private static VBox root;
    private static Scene scene;
    private static KeyboardController keyboardController;
    private static boolean isGameEnded;
    private static LayoutPane layoutPane;
    private static GamePane gamePane;
    private static GameCanvas gameCanvas;
    private static WeaponCanvas weaponCanvas;
    private static ArrayList<ArrayList<EachPane>> epList = new ArrayList<>();
	private static StatusPane StatusPane;
	private static MainMenuPane menuPane;
	
    public static void setupScene() {
        // set scene
        root = new VBox();
        root.setFocusTraversable(true);
        root.setAlignment(Pos.CENTER);
        root.setPadding(Insets.EMPTY);
        // ตั้งขนาดพื้นที่จอ
        scene = new Scene(root, 850, 630);
        keyboardController = new KeyboardController();
        isGameEnded = false;
        // map creation
        ArrayList<Integer> xSpawnPoints = new ArrayList<>(Arrays.asList(1, 15));
        ArrayList<Integer> ySpawnPoints = new ArrayList<>(Arrays.asList(3, 4, 5));
        
		for (int y = 0; y < 9; y++) {
		    ArrayList<EachPane> row = new ArrayList<>();
		    for (int x = 0; x < 17; x++) {
                if(y == 0 || y == 8 || x == 0 || x == 16 || (y % 2 ==0 && x % 2 ==0 && !(y== 4 && x == 8))){
                    row.add(new EachPane(new Carrot(x,y),x,y));
                } else if (xSpawnPoints.contains(x) && ySpawnPoints.contains(y)) {
                	row.add(new EachPane(new Floor(x,y),x,y));
                	} else {
                		Random random = new Random();
                    	if (random.nextInt(100) < 80) {
                            if (random.nextInt(100) < 85) row.add(new EachPane(new Lettuce(x,y),x,y));
                            else row.add(new EachPane(new Purple_Cabbage(x,y),x,y));
                    	} else {
                    		row.add(new EachPane(new Floor(x,y),x,y));
                    	}
                }
		    }
		    epList.add(row);
		}
		
		
		// game status in the future
        layoutPane = new LayoutPane(epList);
		gameCanvas = new GameCanvas(17*50,9*50);
		weaponCanvas = new WeaponCanvas(850,450);
        gamePane = new GamePane(layoutPane,gameCanvas,weaponCanvas);
        StatusPane = new StatusPane();
        menuPane = new MainMenuPane();
        
        root.getChildren().addAll(menuPane);
        
    }

    
    public static Scene getScene(){
        return scene;
    }
    public static boolean isGameEnded(){
        return isGameEnded;
    }
    public static KeyboardController getKeyboardController() {
        return keyboardController;
    }

	public static VBox getRoot() {
		return root;
	}

	public static void setRoot(VBox root) {
		GameController.root = root;
	}

	public static GamePane getGamePane() {
		return gamePane;
	}

	public static void setGamePane(GamePane gamePane) {
		GameController.gamePane = gamePane;
	}

	public static LayoutPane getLayoutPane() {
		return layoutPane;
	}


	public static void setLayoutPane(LayoutPane layoutPane) {
		GameController.layoutPane = layoutPane;
	}


	public static GameCanvas getGameCanvas() {
		return gameCanvas;
	}


	public static void setGameCanvas(GameCanvas gameCanvas) {
		GameController.gameCanvas = gameCanvas;
	}


	public static WeaponCanvas getWeaponCanvas() {
		return weaponCanvas;
	}


	public static void setWeaponCanvas(WeaponCanvas weaponCanvas) {
		GameController.weaponCanvas = weaponCanvas;
	}


	public static void setScene(Scene scene) {
		GameController.scene = scene;
	}
	
	
	public static ArrayList<ArrayList<EachPane>> getEpList() {
		return epList;
	}


	public static void setEpList(ArrayList<ArrayList<EachPane>> epList) {
		GameController.epList = epList;
	}


	public static StatusPane getStatusPane() {
		return StatusPane;
	}


	public static void setStatusPane(StatusPane statusPane) {
		StatusPane = statusPane;
	}


	public static MainMenuPane getMenuPane() {
		return menuPane;
	}


	public static void setMenuPane(MainMenuPane menuPane) {
		GameController.menuPane = menuPane;
	}


	public static void setKeyboardController(KeyboardController keyboardController) {
		GameController.keyboardController = keyboardController;
	}

	public static void setGameEnded(boolean isGameEnded, Player player) {
		GameController.isGameEnded = isGameEnded;
		Player winner = gameCanvas.getP1();
		if(player.getName() == "Player 1") winner = gameCanvas.getP2();
		GameOverPane gameOverPane = new GameOverPane(winner.getName() + " Wins!",winner.getPlayerImage().get(0));
		root.getChildren().clear();
		root.getChildren().add(gameOverPane);
	}
	
    public static void setGameEnded(boolean isGameEnded) {
		GameController.isGameEnded = isGameEnded;
		GameOverPane gameOverPane = new GameOverPane("Draw!", new Image(GameController.class.getResource("/drawImage.png").toExternalForm()));
		root.getChildren().clear();
		root.getChildren().add(gameOverPane);

	}
	public static void mainToGameScene(){
        root.getChildren().clear();
        root.getChildren().addAll(gamePane,StatusPane);
    }
}
