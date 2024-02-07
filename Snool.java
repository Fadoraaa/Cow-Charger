import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Snool extends Cow {
	private int height;
	private int width;
	private boolean left = false;
	private boolean right = false;
	private ImageIcon leftSnool = new ImageIcon("Cows\\leftSnool.png");
	private ImageIcon rightSnool = new ImageIcon("Cows\\rightSnool.png");
	private ImageIcon leftTilt = new ImageIcon("Cows\\leftTiltSnool.png");
	private ImageIcon rightTilt = new ImageIcon("Cows\\rightTiltSnool.png");
	
	//best cow/snail
	public Snool(int x, int y, int h, int w) {
		setySpeed(0);
		this.width = w;
		this.height = h;
	}
	
	public void draw(Graphics g) {
		

		//if the speed is negative, make cow face the left
		if (getxSpeed() <= 0 && getySpeed() == 0) {
			g.drawImage(leftSnool.getImage(), getX(), getY(), width, height, null);
			left = true;
			right = false;
		}
		//if the speed is positive, make cow face the right
		if (getxSpeed() > 0 && getySpeed() == 0) {
			g.drawImage(rightSnool.getImage(), getX(), getY(), width, height, null);
			left = false;
			right = true;
		}
		//if facing left and is being beamed, tilt left
		if (getySpeed() < 0 && left) {
			g.drawImage(leftTilt.getImage(), getX(), getY(), width-10, height+20, null);
		}
		//if facing right and is being beamed, tilt right
		if (getySpeed() < 0 && right) {
			g.drawImage(rightTilt.getImage(), getX(), getY(), width-10, height+20, null);
		}
	}
	
	//sets a very slow speed
	public void setRandomSpeed(double maxSpeed) {
		setxSpeed((int) (Math.random() * maxSpeed + 1));
	}
}
