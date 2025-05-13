package logic;

import java.util.HashSet;
import java.util.Set;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardController {
	private boolean p1UpPressed = false;
	private boolean p1DownPressed = false;
	private boolean p1LeftPressed = false;
	private boolean p1RightPressed = false;
	private boolean p1BombPressed = false;
	private boolean p1WeaponPressed = false;
	private boolean p2UpPressed = false;
	private boolean p2DownPressed = false;
	private boolean p2LeftPressed = false;
	private boolean p2RightPressed = false;
	private boolean p2BombPressed = false;
	private boolean p2WeaponPressed = false;
	private Set<KeyCode> pressedKeys = new HashSet<>();

	public KeyboardController() {
		keyboardSetup();
	}

	public void keyboardSetup() {
		GameController.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				if (!pressedKeys.contains(code)) {
					pressedKeys.add(code);
					if (e.getCode() == KeyCode.SHIFT)
						setP1WeaponPressed(true);
					if (e.getCode() == KeyCode.DIGIT1)
						setP2WeaponPressed(true);
				}
				if (e.getCode() == KeyCode.W)
					setP1UpPressed(true);
				if (e.getCode() == KeyCode.S)
					setP1DownPressed(true);
				if (e.getCode() == KeyCode.A)
					setP1LeftPressed(true);
				if (e.getCode() == KeyCode.D)
					setP1RightPressed(true);
				if (e.getCode() == KeyCode.SPACE)
					setP1BombPressed(true);

				if (e.getCode() == KeyCode.UP)
					setP2UpPressed(true);
				if (e.getCode() == KeyCode.DOWN)
					setP2DownPressed(true);
				if (e.getCode() == KeyCode.LEFT)
					setP2LeftPressed(true);
				if (e.getCode() == KeyCode.RIGHT)
					setP2RightPressed(true);
				if (e.getCode() == KeyCode.ENTER)
					setP2BombPressed(true);

			}
		});
		GameController.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				
				pressedKeys.remove(e.getCode());
				
				if (e.getCode() == KeyCode.W)
					setP1UpPressed(false);
				if (e.getCode() == KeyCode.S)
					setP1DownPressed(false);
				if (e.getCode() == KeyCode.A)
					setP1LeftPressed(false);
				if (e.getCode() == KeyCode.D)
					setP1RightPressed(false);
				if (e.getCode() == KeyCode.SPACE)
					setP1BombPressed(false);
				if (e.getCode() == KeyCode.SHIFT)
					setP1WeaponPressed(false);

				if (e.getCode() == KeyCode.UP)
					setP2UpPressed(false);
				if (e.getCode() == KeyCode.DOWN)
					setP2DownPressed(false);
				if (e.getCode() == KeyCode.LEFT)
					setP2LeftPressed(false);
				if (e.getCode() == KeyCode.RIGHT)
					setP2RightPressed(false);
				if (e.getCode() == KeyCode.ENTER)
					setP2BombPressed(false);
				if (e.getCode() == KeyCode.DIGIT1)
					setP2WeaponPressed(false);

			}
		});
	}

	public boolean isP1UpPressed() {
		return p1UpPressed;
	}

	public void setP1UpPressed(boolean p1UpPressed) {
		this.p1UpPressed = p1UpPressed;
	}

	public boolean isP1DownPressed() {
		return p1DownPressed;
	}

	public void setP1DownPressed(boolean p1DownPressed) {
		this.p1DownPressed = p1DownPressed;
	}

	public boolean isP1LeftPressed() {
		return p1LeftPressed;
	}

	public void setP1LeftPressed(boolean p1LeftPressed) {
		this.p1LeftPressed = p1LeftPressed;
	}

	public boolean isP1RightPressed() {
		return p1RightPressed;
	}

	public void setP1RightPressed(boolean p1RightPressed) {
		this.p1RightPressed = p1RightPressed;
	}

	public boolean isP1BombPressed() {
		return p1BombPressed;
	}

	public void setP1BombPressed(boolean p1BombPressed) {
		this.p1BombPressed = p1BombPressed;
	}

	public void setP1WeaponPressed(boolean p1weaponPressed) {
		this.p1WeaponPressed = p1weaponPressed;
	}

	public boolean isP1WeaponPressed() {
		return p1WeaponPressed;
	}

	public boolean isP2UpPressed() {
		return p2UpPressed;
	}

	public void setP2UpPressed(boolean p2UpPressed) {
		this.p2UpPressed = p2UpPressed;
	}

	public boolean isP2DownPressed() {
		return p2DownPressed;
	}

	public void setP2DownPressed(boolean p2DownPressed) {
		this.p2DownPressed = p2DownPressed;
	}

	public boolean isP2LeftPressed() {
		return p2LeftPressed;
	}

	public void setP2LeftPressed(boolean p2LeftPressed) {
		this.p2LeftPressed = p2LeftPressed;
	}

	public boolean isP2RightPressed() {
		return p2RightPressed;
	}

	public void setP2RightPressed(boolean p2RightPressed) {
		this.p2RightPressed = p2RightPressed;
	}

	public boolean isP2BombPressed() {
		return p2BombPressed;
	}

	public void setP2BombPressed(boolean p2BombPressed) {
		this.p2BombPressed = p2BombPressed;
	}

	public void setP2WeaponPressed(boolean p2weaponPressed) {
		this.p2WeaponPressed = p2weaponPressed;
	}

	public boolean isP2WeaponPressed() {
		return p2WeaponPressed;
	}

}