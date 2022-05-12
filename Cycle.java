import java.awt.*;

public class Cycle {
	private Vrect body;
	int playerNum;
	int direction;
	private boolean boost;
	private boolean peaceful;
	private boolean party;
	private int invincibility;
	
	public Cycle() {
		body = null;
		playerNum = -1;
		direction = -1;
		boost = false;
		peaceful = false;
		party = false;
		invincibility = 0;
	}

	public Cycle(int xPos, int yPos, int sideLength, int pNum, int dir) {
		body = new Vrect(xPos, yPos, sideLength, sideLength);
		playerNum = pNum;
		direction = dir;
	}

	public void move() {
		invincibility++;
		if (direction == 2) {
			if (boost == true) {
				body.translate(0, 20);
			} else {
				body.translate(0, 10);
			}
		}
		if (direction == 6) {
			if (boost == true) {
				body.translate(20, 0);
			} else {
				body.translate(10, 0);
			}
		}
		if (direction == 4) {
			if (boost == true) {
				body.translate(-20, 0);
			} else {
				body.translate(-10, 0);
			}
		}
		if (direction == 8) {
			if (boost == true) {
				body.translate(0, -20);
			} else {
				body.translate(0, -10);
			}
		}
	}

	public void draw(Graphics2D g) {
		body.draw(body, playerNum, g);
	}
	public void draw2(Graphics2D g) {
		body.draw2(body, playerNum, g);
	}
	public void draw3(Graphics2D g) {
		body.draw3(body, playerNum, g);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int d) {
		if (d + direction != 10 && direction!=0) {
			direction = d;
		}
	}

	public void setBoost(boolean b) {
		boost = b;
	}
	
	public void setPeacful(boolean p) {
		if(p && invincibility > 100) {
			invincibility = -50;
		}
	}
	
	public void setParty(boolean b) {
		party = b;
	}
	public boolean getParty() {
		return party;
	}
	
	public boolean getPeacful() {
		return invincibility < 0;
	}
	
	public void isDead() {
		direction = 0;
	}
	public boolean willCrash() {
		if (direction == 2 && TronGame.isClear(body.getX(), body.getY() + 20)) {
			return false;
		}
		if (direction == 4 && TronGame.isClear(body.getX() - 1, body.getY())) {
			return false;
		}
		if (direction == 6 && TronGame.isClear(body.getX() + 20, body.getY())) {
			return false;
		}
		if (direction == 8 && TronGame.isClear(body.getX(), body.getY() - 1)) {
			return false;
		}

		return true;
	}
}
