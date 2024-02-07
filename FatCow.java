import java.awt.Graphics;

import javax.swing.ImageIcon;

public class FatCow extends Cow {
	private int height;
	private int width;
	private ImageIcon cowLeft = new ImageIcon("Cows\\leftFatCow.png");
	private ImageIcon cowRight = new ImageIcon("Cows\\rightFatCow.png");
	int side;
	
	public FatCow(int x, int y, int h, int w) {
		setySpeed(0);
		this.width = w;
		this.height = h;
		side = (int) (Math.random()*2 + 1);
	}
	
	public void draw(Graphics g) {
		//if the speed is negative, make cow face the left
		if (side == 2) {
			g.drawImage(cowLeft.getImage(), getX(), getY(), width, height, null);
		}
		//if the speed is positive, make cow face the right
		else {
			g.drawImage(cowRight.getImage(), getX(), getY(), width, height, null);
		}
	}
	
	//override setRandomSpeed and make it not move
	public void setRandomSpeed(double maxSpeed) {
		setxSpeed(0);
	}
	
	//because they can't move, they shouldn't spawn somewhere unbeamable
	public void setRandomX(int minX, int maxX) {
		setX((int)(Math.random()*(maxX-40) + (minX + 20)));
	}
	
	//increase intersecting range to make it easier
	public boolean intersectsWith(UFO ufo) {
		int ufoX = ufo.getX()+40;
		if (getX() > (ufoX - 40) && getX() < (ufoX+40)) {
			return true;
		}
		return false;
	}
	
}
