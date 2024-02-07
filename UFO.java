import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class UFO {
//global variables
	private int x;
	private int y;
	private int xSpeed;
	private int ySpeed;
	private int height;
	private double radius;
	private int diameter;
	private ImageIcon UFOLeft = new ImageIcon("Cows\\UFOLeft.jpg");
	private ImageIcon UFORight = new ImageIcon("Cows\\UFORight.jpg");
	private ImageIcon CowUFOLeft = new ImageIcon("Cows\\cowUFOLeft.png");;
	private ImageIcon CowUFORight = new ImageIcon("Cows\\cowUFORight.png");;
	private double deltaTime;
	boolean cow = false;
	boolean top = true;
	

	// default constructor
	public UFO() {
		x = 0;
		y = 0;
		setxSpeed(0);
		setySpeed(0);
		height = 100;
		radius = 100;
	}

	// 4-argument constructor
	public UFO(int x, int y, int h, int d) {
		this.x = x;
		this.y = y;
		;
		height = h;
		diameter = d;
		radius = d / 2;
		setxSpeed(0);
		setySpeed(0);
		setDeltaTime(0.5);
	}

	// getters and setters
	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	// draw method
	public void draw(Graphics g) {
		if (xSpeed >= 0) {
			g.drawImage(UFOLeft.getImage(), x, y, diameter, height, null);
		}
		if (xSpeed >= 0 && cow) {
			g.drawImage(CowUFOLeft.getImage(), x, y, diameter, height, null);
		}
		if (xSpeed < 0) {
			g.drawImage(UFORight.getImage(), x, y, diameter, height, null);
		}
		if (xSpeed < 0 && cow) {
			g.drawImage(CowUFORight.getImage(), x, y, diameter, height, null);
		}
	}

	//draw beam method
	public void drawBeam(Graphics g) {
		setDeltaTime(getDeltaTime() + 0.1);
		int x = (getX() + 85);
		int y = (getY() + 125);
		//if 'cowified', make beam white
		if (cow) {
			g.setColor(new Color(255, 255, 255, 100));
		} else { //if normal, beam is blue
			g.setColor(new Color(50, 255, 255, 100));
		}
		int[] beamX = { x - 30, x - 90, x + 90, x + 30 };
		int[] beamY = { y + 10, y + 500, y + 500, y + 10 };
		g.fillPolygon(beamX, beamY, beamX.length);
	}

	// makes ufo bounce off of edges
	public void move(int rightEdge, int bottomEdge) {
		radius /= 2;
		x += xSpeed;
		y += ySpeed;

		// when it hits the left side
		if (x - radius <= 0) {
			x = (int) (0 - radius);
		}

		// when it hits top side
		if (top) {
			if (y - radius <= 0) {
				y = (int) (0 + radius);
			}
		}

		// when it hits right side
		if (x + radius >= rightEdge) {
			x = (int) (rightEdge - radius);
		}
		// white it hits bottom side
		if (y + radius >= bottomEdge) {
			y = (int) (bottomEdge - radius);
		}
	}

	public double getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(double deltaTime) {
		this.deltaTime = deltaTime;
	}

	public boolean getCow() {
		return cow;
	}

	public void isCow(boolean cow) {
		this.cow = cow;
	}

	public boolean getTop() {
		return top;
	}

	public void isTop(boolean top) {
		this.top = top;
	}
}
