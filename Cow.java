import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Cow {
//global variables
	private int x;
	private int y;
	private double xSpeed;
	private int ySpeed;
	private int height;
	private int width;
	private double radius;
	private boolean left = false;
	private boolean right = false;
	private ImageIcon cowLeft = new ImageIcon("Cows\\cowLeft.jpg");
	private ImageIcon cowRight = new ImageIcon("Cows\\cowRight.jpg");
	private ImageIcon leftTilt = new ImageIcon("Cows\\leftTiltCow.png");
	private ImageIcon rightTilt = new ImageIcon("Cows\\rightTiltCow.png");
	
	//default constructor
	public Cow() {
		x = 0;
		y = 0;
		setxSpeed(5);
		setySpeed(0);
		height=100;
		width=100;
	}
	
	//4-argument constructor
	public Cow(int x, int y, int h, int w) {
		this.x=x;
		this.y=y;
		height=h;
		width=w;
		radius = w/2;
		
		setxSpeed(5);
		setySpeed(0);
	}
	
	//getters and setters
	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}
	public void setX(int x) {
		this.x=x;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setY(double y) {
		this.y=(int)y;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	//draw method
	public void draw(Graphics g) {
		//if the speed is negative, make cow face the left
		if (getxSpeed() <= 0 && getySpeed() == 0) {
			g.drawImage(cowLeft.getImage(), x, y, width, height, null);
			left = true;
			right = false;
		}
		//if the speed is positive, make cow face the right
		if (getxSpeed() > 0 && getySpeed() == 0) {
			g.drawImage(cowRight.getImage(), x, y, width, height, null);
			left = false;
			right = true;
		}
		
		if (getySpeed() < 0 && left) {
			g.drawImage(leftTilt.getImage(), x, y, width-10, height+20, null);
		}
		
		if (getySpeed() < 0 && right) {
			g.drawImage(rightTilt.getImage(), x, y, width-10, height+20, null);
		}
	}
	public void setRandomSpeed(double maxSpeed) {
		xSpeed = (int) (Math.random() * maxSpeed+ 1);
	}
	public void setRandomX(int minX, int maxX) {
		x = (int) (Math.random()*maxX + minX);
	}
	public void setRandomY(int minY, int maxY) {
		y = (int) (Math.random()*maxY + minY);
	}
	
	//makes cow bounce off of edges
	public void move(int rightEdge, int bottomEdge) {
		radius /= 2;
		x+=xSpeed;
		y+=ySpeed;
		
		//when it hits the left side
		if (x-radius <= 0) {
			xSpeed *= -1;
			x=(int)(0+radius);
		}
		//when it hits above the ceiling
		if(getY() < -200) {
			setySpeed(0);
		}
		//when it hits right side
		if (x+radius >= rightEdge) {
			xSpeed *= -1;
			x=(int)(rightEdge-radius);
		}
		//white it hits bottom side
		if(y+radius >= bottomEdge) {
			ySpeed *= -1;
			y=(int) (bottomEdge-radius);
		}
	}
	//used for beaming
	public boolean intersectsWith(UFO ufo) {
		int ufoX = ufo.getX()+40;
		if (getX() > (ufoX - 20) && getX() < (ufoX+20)) {
			return true;
		}
		return false;
	}

	
}
