package logic;

import java.util.ArrayList;



import java.util.Random;

import Interface.Breakable;
import javafx.scene.image.Image;
import logic.Component.*;
import logic.Player.Player;
import object.*;
import weapon.Rock;
import weapon.Shovel;
import weapon.Weapon;

public class GameLogic {
	private static ArrayList<Weapon> WeaponList = new ArrayList<Weapon>();
	// add PaneHeight&&number of Row&Column
	private static double PaneWidth = 50;
	private static double PaneHeight = 50;
	private static GamePane gamepane = GameController.getGamePane();
	private ArrayList<ArrayList<EachPane>> map = gamepane.getLayoutPane().getmap();


	public static void DropWeapon(int xPosition, int yPosition) {
		// 3 = Shovel, 4 = Rock.
		//Random rand = new Random();
		//int randomIndex = rand.nextInt(3);
		//if(randomIndex == 3) GameController.getWeaponCanvas().Drop(new Shovel(2), xPosition, yPosition, GameController.getWeaponCanvas().getGraphicsContext2D());
		//else if(randomIndex == 4) GameController.getWeaponCanvas().Drop(new Rock(2), randomIndex, randomIndex, GameController.getWeaponCanvas().getGraphicsContext2D());
		GameController.getWeaponCanvas().Drop(new Shovel(2), xPosition, yPosition, GameController.getWeaponCanvas().getGraphicsContext2D());

	}
	// Return PANE from x and y pixel
	public static EachPane getPane(double xPos, double yPos) {
		return gamepane.getLayoutPane().GetEachPane((int) (xPos/PaneWidth), (int) (yPos/PaneWidth));
	}
	
	// Return topright PIXEL of the given pane
	public static double getPaneLeftPos(EachPane pane) {
		return (PaneWidth)*pane.getxPosition();
	}
	
	public static double getPaneRightPos(EachPane pane) {
		return (PaneWidth)*(pane.getxPosition()+1);
	}
	
	public static double getPaneTopPos(EachPane pane) {
		return (PaneHeight)*pane.getyPosition();
	}
	
	public static double getPaneBottomTopPos(EachPane pane) {
		return (PaneHeight)*(pane.getyPosition()+1);
	}
	
	// Check the center of the player and return what pane the center is in
	public static int getxPane(Player p) {
		if(p.getxPosition()%PaneWidth < (PaneWidth - p.getWidth()/2)) {
			return (int) (p.getxPosition()/PaneWidth);
		}
		else {
			return	(int) (p.getxPosition()/PaneWidth) + 1;
		}
	}
	
	public static int getyPane(Player p) {
		if(p.getyPosition()%PaneWidth < (PaneWidth - p.getHeight()/2)) {
			return (int) (p.getyPosition()/PaneWidth);
		}
		else {
			return	(int) (p.getyPosition()/PaneWidth) + 1;
		}
	}
	
	//return the pane that topright pixel of the player is in
	public static int xPane(Player p) {
		return (int) (p.getxPosition()/PaneWidth);
	}
	
	public static int yPane(Player p) {
		return (int) (p.getyPosition()/PaneWidth);
	}
	
	// check if player overlaps 2 Pane more than 5 pixels -> True: return pane that it overlaps by more than 5 pixel
	//                               					  -> False: return pane that the topright pixel of player is in
	public static int getyPanefortop(Player p) {
		if(p.getyPosition() % PaneWidth > 5) {
			return (int) (p.getyPosition()/PaneWidth)+1;
		}
		return (int) (p.getyPosition()/PaneWidth);
	}
	
	public static int getxPaneforleft(Player p) {
		if(p.getxPosition() % PaneWidth > 5) {
			return (int) (p.getxPosition()/PaneWidth)+1;
		}
		return (int) (p.getxPosition()/PaneWidth);
	}

	// --------------------------PLAYER AND OBSTACLE COLLISION LOGIC----------------------------------------------
	public static boolean HasTopObject(Player p) {
		//check if player overlaps 2 Pane -> True: check floor on top and topright
		//                                -> False: check just floor on top
		boolean xOverlapped = p.getxPosition() % PaneWidth > 50-p.getWidth();
		boolean floorTopRight = (gamepane.getLayoutPane().GetEachPane(GameLogic.xPane(p)+1, GameLogic.getyPanefortop(p)-1).getObject() instanceof Floor);
		boolean floorTop = (gamepane.getLayoutPane().GetEachPane(GameLogic.xPane(p), GameLogic.getyPanefortop(p)-1).getObject() instanceof Floor);
		
		if(xOverlapped) {
			return !(floorTop) || !(floorTopRight);	
		}
		return !(floorTop);
	}
	
	public static boolean HasRightObject(Player p) {
		boolean yOverlapped = p.getyPosition() % PaneWidth > 50-p.getWidth();
		boolean floorRight = (gamepane.getLayoutPane().GetEachPane(GameLogic.xPane(p)+1, GameLogic.yPane(p)).getObject() instanceof Floor);
		boolean floorRightBottom = (gamepane.getLayoutPane().GetEachPane(GameLogic.xPane(p)+1, GameLogic.yPane(p)+1).getObject() instanceof Floor);
		if(yOverlapped) {
			return !(floorRight) || !(floorRightBottom); 
		}
		return !(floorRight);
		//return false;
	}
	
	public static boolean HasBottomObject(Player p) {
		boolean xOverlapped = p.getxPosition() % PaneWidth > 50-p.getWidth();
		boolean floorBottom = (gamepane.getLayoutPane().GetEachPane(GameLogic.xPane(p), GameLogic.yPane(p)+1).getObject() instanceof Floor);
		boolean floorBottomRight = (gamepane.getLayoutPane().GetEachPane(GameLogic.xPane(p)+1, GameLogic.yPane(p)+1).getObject() instanceof Floor);
		if(xOverlapped) {
			return !(floorBottom) || !(floorBottomRight);
		}
		return !(floorBottom);
	}
	
	public static boolean HasLeftObject(Player p) {
		boolean yOverlapped = p.getyPosition() % PaneWidth > 50-p.getWidth();
		boolean floorLeft = (gamepane.getLayoutPane().GetEachPane(GameLogic.getxPaneforleft(p)-1, GameLogic.yPane(p)).getObject() instanceof Floor);
		boolean floorLeftBottom = (gamepane.getLayoutPane().GetEachPane(GameLogic.getxPaneforleft(p)-1, GameLogic.yPane(p)+1).getObject() instanceof Floor);
		if(yOverlapped) {
			return !(floorLeft)||!(floorLeftBottom);
		}
		return !(floorLeft);
		//return false;
	}
	

	//------------------------------------------------BOMB LOGIC-----------------------------------------
	public static void Explode(EachPane pane) {
		// TODO Auto-generated method stub
		//GameController.getWeaponCanvas().Explode(pane, GameController.getWeaponCanvas().getGraphicsContext2D());
	}

	public static void DrawBomb(Player p) {
		// TODO Auto-generated method stub
		
		GameController.getWeaponCanvas().Drawbomb(p, GameController.getWeaponCanvas().getGraphicsContext2D());

	}
	
	// -----------------------------------------BOMB, EXPLOSION AND PLAYER COLLISION LOGIC-----------------------------
	public static void Break(EachPane pane) {
		int xPos = pane.getxPosition();
		int yPos = pane.getyPosition();
		if( pane.getObject() instanceof Breakable) {
			GameController.getLayoutPane().getChildren().removeIf(node -> {
		        Integer nodeCol = GameController.getLayoutPane().getColumnIndex(node);
		        Integer nodeRow = GameController.getLayoutPane().getRowIndex(node);
		        return (nodeCol == null ? 0 : nodeCol) == xPos &&
		               (nodeRow == null ? 0 : nodeRow) == yPos;
		    });
		    EachPane newFloor = new EachPane(new Floor(xPos, yPos), xPos, yPos);
		    GameController.getLayoutPane().getmap().get(yPos).set(xPos, newFloor);
			GameController.getLayoutPane().add(newFloor, xPos, yPos);
			System.out.println(GameController.getLayoutPane().GetEachPane(xPos, yPos).getObject());
			((Breakable) pane.getObject()).Break();
			}
	}
	
	public static boolean collide(double xPos, double yPos, double xStart, double yStart, double xSize, double ySize) {
		boolean inX = xPos < xStart + xSize && xPos > xStart;
		boolean inY = yPos < yStart + ySize && yPos > yStart;
		return inX && inY;
	}
	public static void increaseBombPower() {
		// TODO Auto-generated method stub
		GameController.getWeaponCanvas().setBombpower(GameController.getWeaponCanvas().getBombpower() + 2);
	}
}
