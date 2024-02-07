import java.awt.Graphics;

import javax.swing.ImageIcon;

public class SpeedyCow extends Cow {
	private int height;
	private int width;
	private boolean left = false;
	private boolean right = false;
	private ImageIcon speedyCowLeft = new ImageIcon("Cows\\speedyCowLeft.png");
	private ImageIcon speedyCowRight = new ImageIcon("Cows\\speedyCowRight.png");
	private ImageIcon leftTilt = new ImageIcon("Cows\\leftTiltSpeedCow.png");
	private ImageIcon rightTilt = new ImageIcon("Cows\\rightTiltSpeedCow.png");
	
	public SpeedyCow(int x, int y, int h, int w) {
		setySpeed(0);
		this.width = w;
		this.height = h;
	}
	
	public void draw(Graphics g) {
		

		//if the speed is negative, make cow face the left
		if (getxSpeed() <= 0 && getySpeed() == 0) {
			g.drawImage(speedyCowLeft.getImage(), getX(), getY(), width, height, null);
			left = true;
			right = false;
		}
		//if the speed is positive, make cow face the right
		if (getxSpeed() > 0 && getySpeed() == 0) {
			g.drawImage(speedyCowRight.getImage(), getX(), getY(), width, height, null);
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
	
	//sets a fast random speed
	public void setRandomSpeed(double maxSpeed) {
		setxSpeed((int) (Math.random() * maxSpeed + 20));
	}
}
