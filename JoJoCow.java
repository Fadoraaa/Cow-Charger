import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class JoJoCow extends Cow {
	private int height;
	private int width;
	private boolean left = false;
	private boolean right = false;
	int hits;
	private ImageIcon leftCow = new ImageIcon("Cows\\leftJoJo.png");
	private ImageIcon rightCow = new ImageIcon("Cows\\rightJoJo.png");
	private ImageIcon leftTilt = new ImageIcon("Cows\\leftTiltJoJo.png");
	private ImageIcon rightTilt = new ImageIcon("Cows\\rightTiltJoJo.png");
	
	public JoJoCow(int x, int y, int h, int w) {
		setySpeed(0);
		this.width = w;
		this.height = h;
	}
	
	public void draw(Graphics g) {
		//if the speed is negative, make cow face the left
		if (getxSpeed() <= 0 && getySpeed() == 0) {
			g.drawImage(leftCow.getImage(), getX(), getY(), width, height, null);
			left = true;
			right = false;
		}
		//if the speed is positive, make cow face the right
		if (getxSpeed() > 0 && getySpeed() == 0) {
			g.drawImage(rightCow.getImage(), getX(), getY(), width, height, null);
			left = false;
			right = true;
		}
		//if facing left and being beamed, tilt left
		if (getySpeed() < 0 && left) {
			g.drawImage(leftTilt.getImage(), getX(), getY(), width-10, height+20, null);
		}
		//if facing right and being beamed, tilt right
		if (getySpeed() < 0 && right) {
			g.drawImage(rightTilt.getImage(), getX(), getY(), width-10, height+20, null);
		}
	}
	
	//special jojo method
	public void increaseHits() {
		hits++;
	}
	public int getHits() {
		return hits;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
