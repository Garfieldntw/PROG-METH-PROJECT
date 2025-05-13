package logic;

import java.util.ArrayList;

import java.util.Random;

import Interface.Breakable;
import javafx.application.Platform;
import javafx.scene.image.Image;
import logic.Component.*;
import logic.Component.GameScenes.EachPane;
import logic.Component.GameScenes.GamePane;
import logic.Player.Player;
import object.*;
import item.buff.*;
import item.weapon.*;

public class GameLogic {

	private static ArrayList<Weapon> WeaponList = new ArrayList<Weapon>();
	
	// add PaneHeight&&number of Row&Column
	private static double PaneWidth = 50;
	private static double PaneHeight = 50;
	private static GamePane gamepane = GameController.getGamePane();
	private ArrayList<ArrayList<EachPane>> map = gamepane.getLayoutPane().getmap();

	public static void dropWeapon(int xPosition, int yPosition) {
		Random rand = new Random();
		
		// shovel and rock have drop rates of 25percent each
		int randomIndex = rand.nextInt(4);
		if (randomIndex == 2)
			GameController.getDropCanvas().drop(new Shovel(2), xPosition, yPosition,
					GameController.getWeaponCanvas().getGraphicsContext2D());
		else if (randomIndex == 3)
			GameController.getDropCanvas().drop(new Rock(2), xPosition, yPosition,
					GameController.getWeaponCanvas().getGraphicsContext2D());
	}

	public static void dropBuff(int xPos, int yPos) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int randomIndex = rand.nextInt(5);
		if (randomIndex == 2)
			GameController.getDropCanvas().drop(new SpeedBuff(), xPos, yPos,
					GameController.getWeaponCanvas().getGraphicsContext2D());
		else if (randomIndex == 3)
			GameController.getDropCanvas().drop(new HealthBuff(), xPos, yPos,
					GameController.getWeaponCanvas().getGraphicsContext2D());
		else if (randomIndex == 4)
			GameController.getDropCanvas().drop(new BombBuff(), xPos, yPos,
					GameController.getWeaponCanvas().getGraphicsContext2D());
	}

	// Return PANE from x and y pixel
	public static EachPane getPaneFromXY(double xPos, double yPos) {
		return gamepane.getLayoutPane().getEachPane((int) (xPos / PaneWidth), (int) (yPos / PaneWidth));
	}

	// Return topright PIXEL of the given pane
	public static double getLeftPixelOfPane(EachPane pane) {
		return (PaneWidth) * pane.getxPosition();
	}

	public static double getRightPixelOfPane(EachPane pane) {
		return (PaneWidth) * (pane.getxPosition() + 1);
	}

	public static double getTopPixelOfPane(EachPane pane) {
		return (PaneHeight) * pane.getyPosition();
	}

	public static double getBottomPixelOfPane(EachPane pane) {
		return (PaneHeight) * (pane.getyPosition() + 1);
	}

	// Check the center of the player and return what pane the center is in (Used
	// for Bombdrawing)
	public static int getxPaneNumOfPlayer(Player p) {
		if (p.getxPosition() % PaneWidth < (PaneWidth - p.getWidth() / 2)) {
			return (int) (p.getxPosition() / PaneWidth);
		} else {
			return (int) (p.getxPosition() / PaneWidth) + 1;
		}
	}

	public static int getyPaneNumOfPlayer(Player p) {
		if (p.getyPosition() % PaneWidth < (PaneHeight - p.getHeight() / 2)) {
			return (int) (p.getyPosition() / PaneHeight);
		} else {
			return (int) (p.getyPosition() / PaneHeight) + 1;
		}
	}

	// return the pane that topright(TR) pixel of the player is in (Used for
	// collision logic)
	public static int getxPaneNumOfPlayerTR(Player p) {
		return (int) (p.getxPosition() / PaneWidth);
	}

	public static int getyPaneNumOfPlayerTR(Player p) {
		return (int) (p.getyPosition() / PaneHeight);
	}

	// check if player overlaps 2 Pane more than 5 pixels -> True: return pane that
	// it overlaps by more than 5 pixel
	// -> False: return pane that the topright pixel of player is in
	public static int getxPaneforleft(Player p) {
		if (p.getxPosition() % PaneWidth > PaneWidth - p.getWidth()) {
			return getxPaneNumOfPlayerTR(p) + 1;
		}
		return getxPaneNumOfPlayerTR(p);
	}

	public static int getyPanefortop(Player p) {
		if (p.getyPosition() % PaneHeight > PaneHeight - p.getHeight()) {
			return getyPaneNumOfPlayerTR(p) + 1;
		}
		return getyPaneNumOfPlayerTR(p);
	}

	// --------------------------PLAYER AND OBSTACLE COLLISION LOGIC----------------------------------------------
	public static boolean hasTopObject(Player p) {
		// check if player overlaps 2 Pane	    -> True: check floor on top and topright
		//									   	-> False: check just floor on top
		boolean xOverlapped = p.getxPosition() % PaneWidth > 50 - p.getWidth();
		
		boolean floorTopRight = (gamepane.getLayoutPane()
				.getEachPane(GameLogic.getxPaneNumOfPlayerTR(p) + 1, GameLogic.getyPanefortop(p) - 1)
				.getObject() instanceof Floor);
		boolean floorTop = (gamepane.getLayoutPane()
				.getEachPane(GameLogic.getxPaneNumOfPlayerTR(p), GameLogic.getyPanefortop(p) - 1)
				.getObject() instanceof Floor);

		if (xOverlapped) {
			return !(floorTop) || !(floorTopRight);
		}
		return !(floorTop);
	}

	public static boolean hasRightObject(Player p) {
		boolean yOverlapped = p.getyPosition() % PaneWidth > 50 - p.getWidth();
		boolean floorRight = (gamepane.getLayoutPane()
				.getEachPane(GameLogic.getxPaneNumOfPlayerTR(p) + 1, GameLogic.getyPaneNumOfPlayerTR(p))
				.getObject() instanceof Floor);
		boolean floorRightBottom = (gamepane.getLayoutPane()
				.getEachPane(GameLogic.getxPaneNumOfPlayerTR(p) + 1, GameLogic.getyPaneNumOfPlayerTR(p) + 1)
				.getObject() instanceof Floor);
		if (yOverlapped) {
			return !(floorRight) || !(floorRightBottom);
		}
		return !(floorRight);
	}

	public static boolean hasBottomObject(Player p) {
		boolean xOverlapped = p.getxPosition() % PaneWidth > 50 - p.getWidth();
		boolean floorBottom = (gamepane.getLayoutPane()
				.getEachPane(GameLogic.getxPaneNumOfPlayerTR(p), GameLogic.getyPaneNumOfPlayerTR(p) + 1)
				.getObject() instanceof Floor);
		boolean floorBottomRight = (gamepane.getLayoutPane()
				.getEachPane(GameLogic.getxPaneNumOfPlayerTR(p) + 1, GameLogic.getyPaneNumOfPlayerTR(p) + 1)
				.getObject() instanceof Floor);
		if (xOverlapped) {
			return !(floorBottom) || !(floorBottomRight);
		}
		return !(floorBottom);
	}

	public static boolean hasLeftObject(Player p) {
		boolean yOverlapped = p.getyPosition() % PaneWidth > 50 - p.getWidth();
		boolean floorLeft = (gamepane.getLayoutPane()
				.getEachPane(GameLogic.getxPaneforleft(p) - 1, GameLogic.getyPaneNumOfPlayerTR(p))
				.getObject() instanceof Floor);
		boolean floorLeftBottom = (gamepane.getLayoutPane()
				.getEachPane(GameLogic.getxPaneforleft(p) - 1, GameLogic.getyPaneNumOfPlayerTR(p) + 1)
				.getObject() instanceof Floor);
		if (yOverlapped) {
			return !(floorLeft) || !(floorLeftBottom);
		}
		return !(floorLeft);
	}

	// ------------------------------------------------BOMB LOGIC-----------------------------------------

	public static void drawBomb(Player p) {
		// TODO Auto-generated method stub
		GameController.getWeaponCanvas().drawbomb(p, GameController.getWeaponCanvas().getGraphicsContext2D());
	}

	// -----------------------------------------WEAPON, EXPLOSION AND PLAYER COLLISIONLOGIC-----------------------------

	// Break all tiles that is breakable in that pane
	public static void Break(EachPane pane) {
		int xPos = pane.getxPosition();
		int yPos = pane.getyPosition();

		if (pane.getObject() instanceof Breakable) {
			GameController.getLayoutPane().getChildren().removeIf(node -> {
				Integer nodeCol = GameController.getLayoutPane().getColumnIndex(node);
				Integer nodeRow = GameController.getLayoutPane().getRowIndex(node);
				return (nodeCol == null ? 0 : nodeCol) == xPos && (nodeRow == null ? 0 : nodeRow) == yPos;
			});
			
			EachPane newFloor = new EachPane(new Floor(xPos, yPos), xPos, yPos, GameController.getMapnum());
			GameController.getLayoutPane().getmap().get(yPos).set(xPos, newFloor);
			GameController.getLayoutPane().add(newFloor, xPos, yPos);
			System.out.println(GameController.getLayoutPane().getEachPane(xPos, yPos).getObject());
			((Breakable) pane.getObject()).breakObject();
		}
	}
	
	//Break all tiles that the given position is in (if breakable))
	public static void breakbyPosition(double xPos, double yPos) {
		Platform.runLater(() -> Break(GameController.getLayoutPane().getEachPane((int) (xPos / 50), (int) (yPos / 50))));
	}

	// check if player is in a rectangle with topleft pixel of xstart and ystart and length of xSize and height of ySize
	public static boolean collide(Player p, double xStart, double yStart, double xSize, double ySize) {
		double playerCenterx = p.getxPosition() + p.getWidth() / 2;
		double playerCentery = p.getyPosition() + p.getHeight() / 2;
		boolean inX = playerCenterx < xStart + xSize && playerCenterx > xStart;
		boolean inY = playerCentery < yStart + ySize && playerCentery > yStart;
		return inX && inY;
	}

	// increase given player's bombpower by 1
	public static void increaseBombPower(Player p) {
		// TODO Auto-generated method stub
		if (p.getName().equals("Player 1"))
			GameController.getPlayerCanvas().getP1()
					.setBombPower(GameController.getPlayerCanvas().getP1().getBombPower() + 1);
		else if (p.getName().equals("Player 2"))
			GameController.getPlayerCanvas().getP2()
					.setBombPower(GameController.getPlayerCanvas().getP2().getBombPower() + 1);
	}

	//--------------------------------------------------Call playerstatuspane to update player's health/bombpower/weapon/speed-------------------
	public static void updateHealth() {
		GameController.getStatusPane().getP1Status().updateHealth();
		GameController.getStatusPane().getP2Status().updateHealth();
	}

	public static void updateBombPower() {
		GameController.getStatusPane().getP1Status().updateBombPower();
		GameController.getStatusPane().getP2Status().updateBombPower();
	}

	public static void updateWeapon() {
		GameController.getStatusPane().getP1Status().updateWeapon();
		GameController.getStatusPane().getP2Status().updateWeapon();
	}

	public static void updateSpeed() {
		GameController.getStatusPane().getP1Status().updateSpeed();
		GameController.getStatusPane().getP2Status().updateSpeed();
	}

}
