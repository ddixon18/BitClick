

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Coin extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int speed;
	
	public Coin()
	{
		x = 0;
		y = -50;
		speed = 1;
	}
	
	public Coin(int cx, int cy, int sp)
	{
		x = cx;
		y = cy;
		speed = sp;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void drawCoin(int x, Graphics g)
	{
		int color = (int)(Math.random() * 51 + 204);
		g.setColor(new Color(color, color, 0));
		g.fillOval(x, y, 50, 50);
		g.setColor(Color.black);
		g.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		g.drawString("$", x + 17, y + 38);
	}

}
