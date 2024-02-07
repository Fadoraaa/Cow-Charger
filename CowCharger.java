
//import statements
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


@SuppressWarnings("serial")

public class CowCharger extends JPanel {

	// set the initial width and height
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	// required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private double cowCounter;
	private boolean beam = false;
	
	//integers for initiating a scene
	private int openingPages = 0;
	private int pages2 = 0;
	private int pages3 = 0;
	private int pages4 = 0;
	private int pages5 = 0;
	private int pages6 = 0;
	private int pages7 = 0;
	private int pages8 = 0;
	private int endText = 0;
	private int endText2 = 0;
	
	private UFO ufo;
	
	//cows
	private Cow[] cows1 = new Cow[10];
	private SpeedyCow[] speedy = new SpeedyCow[10];
	private JoJoCow[] jojo = new JoJoCow[10];
	private Snool[] snool = new Snool[10];
	private FatCow[] fatty = new FatCow[10];
	private Cow[] cows2 = new Cow[20];
	private SpeedyCow[] speedy2 = new SpeedyCow[20];
	private FatCow[] fatty2 = new FatCow[10];


	// scene images
	private ImageIcon opening = new ImageIcon("Cows\\openingScreen.jpg"); // opening/title screen
	private ImageIcon spaceScene = new ImageIcon("Cows\\spaceScene.jpg"); // scene with ship in space
	private ImageIcon crashScene = new ImageIcon("Cows\\crash.jpg"); // scene with ship hit by space thing
	private ImageIcon landingScene = new ImageIcon("Cows\\landing.jpg"); // scene with ship landing in field
	private ImageIcon background = new ImageIcon("Cows\\background.jpg"); // regular background
	private ImageIcon mission = new ImageIcon("Cows\\mission.jpg"); // end scene with mission reveal
	private ImageIcon boom = new ImageIcon("Cows\\boom.png"); //explosion
	private ImageIcon cowFace = new ImageIcon("Cows\\cowFace.png"); // yes.
	private ImageIcon takeOver = new ImageIcon("Cows\\takeOver.png"); //eheheheheh cow go brr
	private ImageIcon mooltiverse = new ImageIcon("Cows\\mooltiverse.png"); //explosion
	
	
	//all intros to different cows
	private ImageIcon cowAnalysis = new ImageIcon("Cows\\cowAnalysis.jpg"); // intro to cow
	private ImageIcon speedyAnalysis = new ImageIcon("Cows\\speedAnalysis.jpg"); // intro to speedy cow
	private ImageIcon snoolAnalysis = new ImageIcon("Cows\\snoolAnalysis.png"); // scene analyzing snool
	private ImageIcon jojoAnalysis = new ImageIcon("Cows\\JoJoAnalysis.png"); //intro to JoJo cow
	private ImageIcon fatAnalysis = new ImageIcon("Cows\\chunkyAnalysis.png"); //intro to chunky/fat cow
	private ImageIcon moreSpeed = new ImageIcon("Cows\\speedyHorde.png");
	private ImageIcon moreChunky = new ImageIcon("Cows\\chunkyHorde.png");
	private ImageIcon moreCows = new ImageIcon("Cows\\moreCows.png");
	
	// Constructor required by BufferedImage
	public CowCharger() {
		// set up Buffered Image and Graphics objects
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		timer = new Timer(10, new TimerListener());
		timer.start();
		// mouse/key input set up
		addMouseListener(new Mouse()); // new code
		addKeyListener(new Keyboard());
		setFocusable(true);
		// cows set up with speed and coordinates
		cowSetUp("Cow", cows1, 100, 120, 4);
		cowSetUp("SpeedyCow", speedy, 110, 130, 20);
		cowSetUp("Snool", snool, 70, 90, 1);
		cowSetUp("JoJoCow", jojo, 110, 130, 4);
		cowSetUp("FatCow", fatty, 110, 130, 0.3);
		cowSetUp("Cow", cows2, 100, 120, 4);
		cowSetUp("SpeedyCow", speedy2, 110, 130, 20);
		cowSetUp("FatCow", fatty2, 110, 130, 4);

		ufo = new UFO((WIDTH / 2) - 85, 100, 140, 170);

		// sets delta time
		ufo.setDeltaTime(0.0);
	}

	// mouse input
	private class Mouse implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			// if right click, go to next page (opening)
			if (e.getButton() == 3) {
				if (cowCounter == 0) { //opening scene
					openingPages++;
				}
				if (cowCounter == 10) { //intro to speedy cow
					pages2++;
				}
				if (cowCounter == 20) { //intro to snool
					pages3++;
				}
				if (cowCounter == 30) { //intro to jojo cow
					pages4++;
				}
				if (cowCounter == 40) { //intro to fat cow
					pages5++;
				}
				if (cowCounter == 50) { //intro to fat cow
					pages6++;
				}
				if (cowCounter == 70) { //intro to fat cow
					pages7++;
				}
				if (cowCounter == 90) { //intro to fat cow
					pages8++;
				}
				if (cowCounter == 100 && endText2 == 0) { //end scenario one
					endText++;
				}
			}
			// if left click, go to previous page (opening)
			if (e.getButton() == 1) {
				if (openingPages > 0 && cowCounter == 0) {
					openingPages--;
				}
				if (cowCounter == 100 && endText == 0) { //end scenario two
					endText2++;
				}
			}
		}
		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
		// if mouse enters frame, game starts
		public void mouseEntered(MouseEvent e) {
			timer.start();
		}
		// if mouse leaves frame, game pauses
		public void mouseExited(MouseEvent e) {
			timer.stop();
		}
	}

	// keyboard inputs
	private class Keyboard implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}
		public void keyPressed(KeyEvent e) {
			// if W or the up arrow is clicked, UFO goes up
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				ufo.setxSpeed(0);
				ufo.setySpeed(-4);
			}
			// if A or the left arrow is clicked, UFO goes left
			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
				ufo.setxSpeed(-4);
				ufo.setySpeed(0);
			}
			// if S or the down arrow is clicked, UFO goes down
			if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
				ufo.setxSpeed(0);
				ufo.setySpeed(4);
			}
			// if D or the right arrow is clicked, UFO goes right
			if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
				ufo.setxSpeed(4);
				ufo.setySpeed(0);
			}
			// if spacebar is clicked, UFO shoots a beam
			if (e.getKeyCode() == KeyEvent.VK_SPACE && ufo.getDeltaTime() < 0.1) {
				beam = true;
			}
			// make beam not last forever
			if (ufo.getDeltaTime() >= 0.1) {
				beam = false;
			}
		}
		public void keyReleased(KeyEvent e) {
			// stops beam after releasing keyboard
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				beam = false;
				ufo.setDeltaTime(0.0);
			}
		}
	}

	// TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// draw opening images with nothing complicated using a method
			drawScene(openingPages, 0, opening);
			drawScene(openingPages, 1, spaceScene);
			drawScene(openingPages, 2, crashScene);
			drawScene(openingPages, 3, landingScene);
			drawScene(openingPages, 4, cowAnalysis);
			// set up for 'level 1' / mini tutorial
			if (openingPages > 4) {
				g.drawImage(background.getImage(), 0, 0, WIDTH, HEIGHT, null);
				drawCowCounter();
				basicGame(cows1, "Cow");
				ufo.move(WIDTH - 170, HEIGHT - 300);
				ufo.draw(g);
				if (cowCounter < 1) {
					textBox("Press space to beam up cows!", 60, "", "");
				}
				if (cowCounter >= 2 && cowCounter < 5) {
					textBox("The bar on the top left", 100, "will tell you your progress", "");
				}
				if (cowCounter >=5 && cowCounter < 8) {
					textBox("You can move using WASD", 100, "or the arrow keys :)", "");
				}
			}
			// levels setup
			levelSetUp(pages2, 10, speedyAnalysis, speedy, "Speedy", openingPages);
			levelSetUp(pages3, 20, snoolAnalysis, snool, "Snool", pages2);
			levelSetUp(pages4, 30, jojoAnalysis, jojo, "JoJo", pages3);
			levelSetUp(pages5, 40, fatAnalysis, fatty, "FatCow", pages4);
			levelSetUp(pages6, 50, moreCows, cows2, "Cow", pages5);
			levelSetUp(pages7, 70, moreSpeed, speedy2, "Speedy", pages6);
			levelSetUp(pages8, 90, moreChunky, fatty2, "FatCow", pages7);
			
			// setup for end
			if (cowCounter == 100) {
				ufo.isCow(true);
				ufo.setySpeed(-1);
				ufo.isTop(false);
				textBox("I've achieved immootality.", 140, "Time to complete my", "mission... (Click)");
				drawScene(endText, 1, mission);
				if (endText >= 2) {
					g.drawImage(boom.getImage(), 0, 0, WIDTH, HEIGHT, null);
				}
				drawScene(endText2, 1, takeOver);
				if (endText2 >= 2) {
					g.drawImage(mooltiverse.getImage(), 0, 0, WIDTH, HEIGHT, null);
				}
			}
			repaint(); // leave this alone, it MUST be the last thing in this method
		}
	}
	// do not modify this
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	// draw background onto screen
	public void drawScene(int pagevar, int pagenum, ImageIcon image) {
		if (pagevar == pagenum) {
			g.drawImage(image.getImage(), 0, 0, WIDTH, HEIGHT, null);
		}
	}
	// draw cow counter
	public void drawCowCounter() {
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		if (cowCounter == 69) {
			g.drawString("Cows Collected: nice ;)", 20, 55);
		} else {
			g.drawString("Cows Collected: " + (int) cowCounter, 20, 55);
		}
		g.setColor(Color.black);
		g.fillRect(15, 75, 210, 25);
		g.setColor(Color.gray);
		g.fillRect(20, 80, 200, 15);
		// color changes from red to green based on the number of cows caught
		if (cowCounter < 50) {
			for (int i = 0; i < (cowCounter * 5); i++) {
				g.setColor(new Color(250, i, 0));
			}
		} else if (cowCounter >= 50) {
			for (int i = 0; i < ((cowCounter - 49.9) * 5); i++) {
				g.setColor(new Color(250 - i, 250, 0));
			}
		}
		for (int i = 0; i < (cowCounter * 2); i++) {
			g.fillRect(20, 80, i, 15);
			g.drawImage(cowFace.getImage(), i + 15, 78, 20, 20, null);
		}
	}
	// how every game would be layed out
	public void basicGame(Cow[] cows, String cowType) {
		for (int i = 0; i < cows.length; i++) {
			cows[i].move(WIDTH - 110, HEIGHT);
			if (cows[i].getY() > (ufo.getY() + 30)) {
				cows[i].draw(g);
				//do different method if it's a JoJo cow array (use 'hits')
				if (cowType == "JoJo") {
					jojo[i].draw(g);
					if (jojo[i].intersectsWith(ufo) && beam && jojo[i].getySpeed() != -15) {
						jojo[i].increaseHits();
					}
					if (jojo[i].getHits() > 20 && jojo[i].intersectsWith(ufo) && beam && jojo[i].getySpeed() != -15) {
						jojo[i].setxSpeed(0);
						jojo[i].setySpeed(-15);
						cowCounter++;
					}
				}
				//regular method if not a JoJo cow
				else {
					if (cows[i].intersectsWith(ufo) && beam && cows[i].getySpeed() != -15) {
						cows[i].setxSpeed(0);
						if (cowType == "FatCow") {
							cows[i].setySpeed(-15);
						} else {
							cows[i].setySpeed(-15);
						}
						cowCounter++;
					}
				}
			}
			if (beam) {
				ufo.drawBeam(g);
				ufo.setxSpeed(0);
			}
		}
	}
	// draw text box
	public void textBox(String string, int height, String string2, String string3) {
		g.setColor(Color.black);
		g.fillRect(65, 415, 660, height + 10);
		g.setColor(Color.gray);
		g.fillRect(70, 420, 650, height);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		g.setColor(Color.white);
		g.drawString((string), 140, 465);
		g.drawString((string2), 140, 505);
		g.drawString((string3), 140, 545);
	}
	
	//basic level set up
	public void levelSetUp(int pages, int num, ImageIcon image, Cow[] cow, String cowType, int reset) {
		reset = 0;
		if (cowCounter == num && pages == 0) {
			textBox("Right Click to Continue!", 60, "", "");
		}
		drawScene(pages, 1, image);
		if (pages >= 2) {
			basicGame(cow, cowType);
			ufo.move(WIDTH - 170, HEIGHT - 300);
			ufo.draw(g);
		}
	}

	//sets up every cow for me so I don't have to copy & paste 7 lines of code for every cow array
	public void cowSetUp(String type, Cow[] cow, int h, int w, double speed) {
		for (int i = 0; i < cow.length; i++) {
			if (type == "Cow") {
				cow[i] = new Cow(500, 500, h, w);
			} else if (type == "SpeedyCow") {
				cow[i] = new SpeedyCow(500, 500, h, w);
			} else if (type == "Snool") {
				cow[i] = new Snool(500, 500, h, w);
			} else if (type == "JoJoCow") {
				cow[i] = new JoJoCow(500, 500, h, w);
			} else if (type == "FatCow") {
				cow[i] = new FatCow(500, 500, h, w);
			}
			cow[i].setRandomSpeed(speed);
			cow[i].setRandomX(0, 700);
			cow[i].setRandomY(400, 100);
		}
	}
	
	// main method with standard graphics code
	public static void main(String[] args) {
		JFrame frame = new JFrame("Cow Charger Of Justice");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		CowCharger cc = new CowCharger();
		frame.setContentPane(cc); // TODO: Change this to the name of your class!
		frame.setVisible(true);
	}
}