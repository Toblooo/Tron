import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TronGame extends JPanel implements KeyListener, ActionListener {

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~
	// UNCOMMENT after Question 1
	private Cycle flynn = new Cycle();
	private Cycle sark = new Cycle();
	private Cycle fernando = new Cycle();
	private Cycle eric = new Cycle();
	// UNCOMMENT after Question 1
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~

	private int flynnScore = 0;
	private int sarkScore = 0;
	private int fernandoScore = 0;
	private int ericScore = 0;
	private boolean er = false;
	private boolean s = false;
	private boolean fn = false;
	private boolean f = false;
	private String winMessage = "";
	public static int width = 1280, height = 986;

	/**
	 * initializes all instance variables needed for each round of play
	 */
	public void initRound() {

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// UNCOMMENT after Question 1
		flynn = new Cycle(width - 100, height / 2, 20, 1, 4);
		sark = new Cycle(100, height / 2, 20, 2, 6);
		fernando = new Cycle(width / 2, 800, 20, 3, 8);
		eric = new Cycle(width / 2, 100, 20, 4, 2);
		// UNCOMMENT after Question 1
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		winMessage = "";
		offScreenGraphics.clearRect(0, 0, width, height);
		repaint();
		er = false;
		s = false;
		fn = false;
		f = false;
		timer.restart();
	}

	/**
	 * renders all objects to Graphics g
	 */
	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(Color.WHITE);
		drawMessage(g);

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// UNCOMMENT after Question 2
		if (flynn.getParty()) {
			flynn.draw3(g);
		} else if (flynn.getPeacful() == true) {
			flynn.draw2(g);
		} else {
			flynn.draw(g);
		}

		if (sark.getParty()) {
			sark.draw3(g);
		} else if (sark.getPeacful() == true) {
			sark.draw2(g);
		} else {
			sark.draw(g);
		}

		if (fernando.getParty()) {
			fernando.draw3(g);
		} else if (fernando.getPeacful() == true) {
			fernando.draw2(g);
		} else {
			fernando.draw(g);
		}
		if (eric.getParty()) {
			eric.draw3(g);
		} else if (eric.getPeacful() == true) {
			eric.draw2(g);
		} else {
			eric.draw(g);
		}

		// UNCOMMENT after Question 2
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~

	}

	/**
	 * Called automatically when the timer fires<br>
	 * this is where all the game fields will be updated
	 */
	public void actionPerformed(ActionEvent e) {

		if (roundOver) {
			roundOver = false;
			timer.restart();
			initRound();
		}
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// UNCOMMENT after Question 2
		flynn.move();
		sark.move();
		fernando.move();
		eric.move();
		// UNCOMMENT after Question 2
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// UNCOMMENT after Question 3
		if (flynn.willCrash() && flynn.getPeacful() == false) {
			flynn.isDead();
			f = true;
		}
		if (sark.willCrash() && sark.getPeacful() == false) {
			sark.isDead();
			s = true;
		}
		if (fernando.willCrash() && fernando.getPeacful() == false) {
			fernando.isDead();
			fn = true;
		}
		if (eric.willCrash() && eric.getPeacful() == false) {
			eric.isDead();
			er = true;
		}

		if (er && fn && s) {
			waitAfterRoundOver();
			flynnScore++;
			winMessage = "Flynn Wins!";
		}
		if (f && s && er) {
			waitAfterRoundOver();
			fernandoScore++;
			winMessage = "Fernando Wins!";
		}
		if (er && f && fn) {
			waitAfterRoundOver();
			sarkScore++;
			winMessage = "Sark Wins!";
		}
		if (fn && s && f) {
			waitAfterRoundOver();
			ericScore++;
			winMessage = "Eric Wins!";
		}
		// UNCOMMENT after Question 3
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~

		if (flynnScore >= 7 || sarkScore >= 7 || fernandoScore >= 7 || ericScore >= 7) {
			winMessage = "GAME OVER";
			timer.stop();
		}
		repaint();// needed to refresh the animation
	}

	/**
	 * handles any key pressed events and updates the Cycle's direction by setting
	 * their direction to either 8,2,4 or 6 based on which key is pressed.
	 */
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// UNCOMMENT after Question 3
		if (keyCode == KeyEvent.VK_UP) {
			sark.setDirection(6);
		} else if (keyCode == KeyEvent.VK_DOWN) {
			sark.setDirection(4);
		} else if (keyCode == KeyEvent.VK_LEFT) {
			sark.setDirection(8);
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			sark.setDirection(2);
		}
		if (keyCode == KeyEvent.VK_NUMPAD2) {
			sark.setBoost(true);
		}
		if (keyCode == KeyEvent.VK_NUMPAD5) {
			sark.setPeacful(true);
		}
		if (keyCode == KeyEvent.VK_NUMPAD3) {
			flynn.setParty(true);
			sark.setParty(true);
			fernando.setParty(true);
			eric.setParty(true);
		}
		// UNCOMMENT after Question 3
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// UNCOMMENT after Question 4
		if (keyCode == KeyEvent.VK_W) {
			flynn.setDirection(4);
		} else if (keyCode == KeyEvent.VK_S) {
			flynn.setDirection(6);
		} else if (keyCode == KeyEvent.VK_A) {
			flynn.setDirection(2);
		} else if (keyCode == KeyEvent.VK_D) {
			flynn.setDirection(8);
		}
		if (keyCode == KeyEvent.VK_B) {
			flynn.setBoost(true);
		}
		if (keyCode == KeyEvent.VK_G) {
			flynn.setPeacful(true);
		}
		// UNCOMMENT after Question 4
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (keyCode == KeyEvent.VK_4) {
			eric.setDirection(2);
		} else if (keyCode == KeyEvent.VK_R) {
			eric.setDirection(8);
		} else if (keyCode == KeyEvent.VK_E) {
			eric.setDirection(6);
		} else if (keyCode == KeyEvent.VK_T) {
			eric.setDirection(4);
		}
		if (keyCode == KeyEvent.VK_M) {
			eric.setBoost(true);
		}
		if (keyCode == KeyEvent.VK_J) {
			eric.setPeacful(true);
		}

		if (keyCode == KeyEvent.VK_7) {
			fernando.setDirection(8);
		} else if (keyCode == KeyEvent.VK_U) {
			fernando.setDirection(2);
		} else if (keyCode == KeyEvent.VK_Y) {
			fernando.setDirection(4);
		} else if (keyCode == KeyEvent.VK_I) {
			fernando.setDirection(6);
		}
		if (keyCode == KeyEvent.VK_PERIOD) {
			fernando.setBoost(true);
		}
		if (keyCode == KeyEvent.VK_L) {
			fernando.setPeacful(true);
		}

	}

	/**
	 * handles any key released events ... <br>
	 * kills the game window by the 'Escape Key'
	 */
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_END) {
			System.exit(0);// kill game process.. close the window
		}

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// UNCOMMENT after Question 3
		if (keyCode == KeyEvent.VK_NUMPAD2) {
			sark.setBoost(false);
		}
		if (keyCode == KeyEvent.VK_NUMPAD5) {
			sark.setPeacful(false);
		}

		// UNCOMMENT after Question 3
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// UNCOMMENT after Question 4
		if (keyCode == KeyEvent.VK_B) {
			flynn.setBoost(false);
		}
		if (keyCode == KeyEvent.VK_G) {
			flynn.setPeacful(false);
		}

		if (keyCode == KeyEvent.VK_M) {
			fernando.setBoost(false);
		}
		if (keyCode == KeyEvent.VK_J) {
			fernando.setPeacful(false);
		}
		if (keyCode == KeyEvent.VK_PERIOD) {
			eric.setBoost(false);
		}
		if (keyCode == KeyEvent.VK_L) {
			eric.setPeacful(false);
		}

		// UNCOMMENT after Question 4
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~

	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ YOU DO NOT NEED TO MODIFY ANY OF THE CODE BELOW ~~~~~~~~~~
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * helper method for actionPerformed to wait 2 seconds after the round is over
	 * for the players
	 */
	public void waitAfterRoundOver() {
		repaint();
		roundOver = true;
		timer.restart();
	}

	/**
	 * helper method for draw to draw the 'who wins' message and the 'ready' message
	 * for the players
	 */

	public void drawMessage(Graphics2D g) {
		if (onCabinet) {
			g.rotate(Math.PI / 2, width / 2, height / 2);
			g.translate(0.0, 400.0);
			if (winMessage.length() > 0) {
				int c = 0;
				int steps = 7;
				for (int k = 0; k < steps; k++) {
					Color color = new Color(c, c, c);
					g.setColor(color);
					g.drawString(winMessage, width / 2 - 100 + k, height / 2 - 150 + k);
					g.drawString("flynn " + flynnScore + " ~ sark " + sarkScore, width / 2 - 130 + k,
							height / 2 - 100 + k);
					c += 256 / steps;
				}
			}
			g.translate(0.0, -400.0);
			g.rotate(-Math.PI / 2, width / 2, height / 2);

			g.rotate(-Math.PI / 2, width / 2, height / 2);
			g.translate(0.0, 400.0);
			if (winMessage.length() > 0) {
				int c = 0;
				int steps = 7;
				for (int k = 0; k < steps; k++) {
					Color color = new Color(c, c, c);
					g.setColor(color);
					g.drawString(winMessage, width / 2 - 100 + k, height / 2 - 150 + k);
					g.drawString("flynn " + flynnScore + " sark " + sarkScore + "fernando " + fernandoScore + "eric "
							+ ericScore, width / 2 - 130 + k, height / 2 - 100 + k);
					c += 256 / steps;
				}
			}
			g.translate(0.0, -400.0);
			g.rotate(Math.PI / 2, width / 2, height / 2);

		} else {
			if (winMessage.length() > 0) {
				int c = 0;
				int steps = 7;
				for (int k = 0; k < steps; k++) {
					Color color = new Color(c, c, c);
					g.setColor(color);
					g.drawString(winMessage, width / 2 - 100 + k, height / 2 - 150 + k);
					g.drawString("flynn " + flynnScore + " ~ sark " + sarkScore + " ~ fernando " + fernandoScore
							+ " ~ eric " + ericScore, width / 3 - 130 + k, height / 2 - 100 + k);
					c += 256 / steps;
				}
			}
		}
	}

	/**
	 * this method is needed for implementing interface KeyListener <br>
	 * THIS METHOD SHOULD NOT BE MODIFIED! ..
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * If the game is on the cabinet <br>
	 * THIS METHOD SHOULD NOT BE MODIFIED! ..
	 */
	public int swapKeyCode(int keyCode) {
		if (!onCabinet) {
			return keyCode;
		}
		if (keyCode == KeyEvent.VK_UP) {
			return KeyEvent.VK_D;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			return KeyEvent.VK_A;
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			return KeyEvent.VK_W;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			return KeyEvent.VK_S;
		}
		if (keyCode == KeyEvent.VK_W) {
			return KeyEvent.VK_LEFT;
		}
		if (keyCode == KeyEvent.VK_S) {
			return KeyEvent.VK_RIGHT;
		}
		if (keyCode == KeyEvent.VK_A) {
			return KeyEvent.VK_DOWN;
		}
		if (keyCode == KeyEvent.VK_D) {
			return KeyEvent.VK_UP;
		}
		if (keyCode == KeyEvent.VK_B) {
			return KeyEvent.VK_2;
		}
		if (keyCode == KeyEvent.VK_2) {
			return KeyEvent.VK_B;
		}

		return keyCode;
	}

	/**
	 * init method needed to initialize non-static fields<br>
	 * THIS METHOD SHOULD NOT BE MODIFIED! ..
	 */
	public void init() {

		try {
			String station = InetAddress.getLocalHost().getHostName();
			if (station.indexOf("2070") != -1) {
				onCabinet = true;
			}
			// System.out.println(station);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		offScreenBuffer = createImage(getWidth(), getHeight());// should be 1280x986 ish
		offScreenGraphics = offScreenBuffer.getGraphics();
		timer = new Timer(30, this);// timer fires every 30 milliseconds.. invokes method actionPerformed()
		timer.setInitialDelay(3000);
		timer.start();
		initRound();
	}

	/**
	 * returns true if the color value of the pixel<br>
	 * with coordinates (x,y) is WHITE, false otherwise<br>
	 * NOTE: THIS METHOD SHOULD NOT BE MODIFIED
	 */
	public static boolean isClear(int x, int y) {
		BufferedImage bi = (BufferedImage) offScreenBuffer;
		if (bi == null)
			return true;
		boolean result = true;
		try {
			int colorVal = bi.getRGB(x, y);
			// result = (colorVal == -1); //for -1 is white
			result = (colorVal == -16777216); // -16777216 is black
		} catch (Exception ex) {
			result = false;
		}
		return result;
	}

	/**
	 * Called automatically after a repaint request<br>
	 * THIS METHOD SHOULD NOT BE MODIFIED! ..
	 */
	public void paint(Graphics g) {
		try {
			draw((Graphics2D) offScreenGraphics);
			g.drawImage(offScreenBuffer, 0, 0, this);
		} catch (NullPointerException ex) {
			// ex.printStackTrace();
		}
	}

	private boolean roundOver = false;
	private boolean onCabinet = false;
	private Font font = new Font("Arial", Font.BOLD, 40);
	private static String title = "Sheesh";
	private Timer timer;// handles animation
	private static Image offScreenBuffer;// needed for double buffering graphics
	private Graphics offScreenGraphics;// needed for double buffering graphics

	/**
	 * main() is needed to initialize the window.<br>
	 * THIS METHOD SHOULD NOT BE MODIFIED! .. <br>
	 * you should write all necessary initialization code in initRound()
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame(title);
		window.setBounds(0, 0, width, height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		TronGame game = new TronGame();
		window.getContentPane().add(game);
		window.setBackground(Color.BLACK);
		window.setVisible(true);
		game.init();
		window.addKeyListener(game);
	}

}// end class TronGame

/**
 * Helper class Vrect .. simple wrapper class for a Rectangle object in Java
 */
class Vrect {

	private Rectangle rect;
	Random rand = new Random();

	/**
	 * Creates a new instance with the given a position and dimensions
	 * 
	 * @param x,y,w,h represent the (x, y) coordinates of the upper-left corner and
	 *                the w idth and h eight
	 */
	public Vrect(int x, int y, int w, int h) {
		rect = new Rectangle(x, y, w, h);
	}

	/**
	 * translates this object by adding values dx, dy ...to the upper-left corner
	 * coordinates
	 * 
	 * @param dx,dy the amount of translation of the (x, y) coordinates of the
	 *              upper-left corner
	 */
	public void translate(int dx, int dy) {
		rect.x += dx;
		rect.y += dy;
	}

	/**
	 * @return the x-coordinate of the upper-left corner of the rectangle shape
	 */
	public int getX() {
		return rect.x;
	}

	/**
	 * @return the y-coordinate of the upper-left corner of the rectangle shape
	 */
	public int getY() {
		return rect.y;
	}

	/**
	 * 
	 * @return the Rectangle of this Vrect
	 */
	public Rectangle getRect() {
		return rect;
	}

	/**
	 * renders the Vrect to Graphics2D g
	 * 
	 * @param r
	 * @param pNum
	 * @param g
	 */

	public void draw(Vrect r, int pNum, Graphics2D g) {
		if (pNum == 1) {
			g.setColor(new Color(36, 146, 255)); // flynn blue
		} else if (pNum == 2) {
			g.setColor(new Color(255, 219, 0));// sark yellow
		} else if (pNum == 3) {
			g.setColor(new Color(102, 255, 102)); // fernando
		} else if (pNum == 4) {
			g.setColor(new Color(255, 204, 203)); // eric red
		}
		g.fill(rect);
	}

	public void draw2(Vrect r, int pNum, Graphics2D g) {
		if (pNum == 1) {
			g.setColor(new Color(0, 0, 204)); // flynn blue
		} else if (pNum == 2) {
			g.setColor(new Color(255, 1, 0));// sark yellow
		} else if (pNum == 3) {
			g.setColor(new Color(0, 153, 0)); // fernando
		} else if (pNum == 4) {
			g.setColor(new Color(204, 0, 0)); // eric red
		}
		g.fill(rect);
	}

	public void draw3(Vrect r, int pNum, Graphics2D g) {

		if (pNum == 1) {
			g.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())); // flynn blue
		} else if (pNum == 2) {
			g.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));// sark yellow
		} else if (pNum == 3) {
			g.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())); // fernando
		} else if (pNum == 4) {
			g.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())); // eric red
		}
		g.fill(rect);
	}

}
