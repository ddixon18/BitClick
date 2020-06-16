import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;

public class BitClick extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static long total = 0;
	public static int xPlacer = 0;
	
	//tracks gamble button
	public static int gambleCost = (int) total / 2;

	//button / cost / level trackers
	public static int autoClickCount = 0;
	public static int autoClickLevel = 0;
	public static int autoClickCost = 20;
	public static int enhanceAlgorithmCost = 50;
	public static int clickAmount = 1;

	static ArrayList<Coin> coins = new ArrayList<Coin>();

	int coinX = (int)(Math.random() * 500) + 100;
	int coinY = 0;

	//pattern / upgrade conditionals
	static boolean draw = false;
	static boolean striped = false;
	static boolean zigzag = false;
	static boolean autoClick = false;
	static boolean compoundClick = false;
	
	//condition for zig zag pattern
	static boolean add = true;

	static Graphics g;

	static JFrame gameFrame;

	static JButton clickMe = new JButton();
	static JButton togglePattern = new JButton();
	static JButton upgrade1 = new JButton();
	static JButton upgrade2 = new JButton();
	static JButton gamble = new JButton();

	static JLabel score = new JLabel();
	static JLabel upgradeOneLabel = new JLabel();
	static JLabel upgradeTwoLabel = new JLabel();

	public static void main(String[] args) {

		gameFrame = new JFrame ("Bit Click");
		gameFrame.setSize(800, 625);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameFrame.setResizable(false);
		gameFrame.setLayout(null);
		gameFrame.setContentPane( new BitClick());
		gameFrame.getContentPane().setBackground(Color.black);

		//initializes all j-elements
		clickMe = new JButton("mine.exe");
		togglePattern = new JButton("Set coin pattern to stripes");
		upgrade1 = new JButton("Computer:" + autoClickCost + "BTC");
		upgrade2 = new JButton("Enhance algorithm:" + enhanceAlgorithmCost + "BTC");
		score = new JLabel("" + total);
		gamble = new JButton("Gamble: " + gambleCost + " BTC");
		
		//sets text of level trackers
		upgradeOneLabel = new JLabel("Computers: " + autoClickLevel);
		upgradeTwoLabel = new JLabel("Level of Algorithm: " + clickAmount);

		//adds all j-elements to game
		gameFrame.add(clickMe);
		gameFrame.add(togglePattern);
		gameFrame.add(upgrade1);
		gameFrame.add(upgrade2);
		gameFrame.add(score);
		gameFrame.add(upgradeOneLabel);
		gameFrame.add(upgradeTwoLabel);
		gameFrame.add(gamble);

		//mouse listener for clicker
		clickMe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!compoundClick)
				{
					total ++;
					
					if(zigzag && add)
						xPlacer += 50;
					if(zigzag && !add)
						xPlacer -= 50;
					if(striped)
						xPlacer += 50;

					if(striped || zigzag)
						coins.add(new Coin(xPlacer, -50, 2));
					else 
						coins.add(new Coin((int) (Math.random() * 560) + 20, -50, 2));
				}
				else
				{
					for(int i = 0; i < clickAmount; i++)
						total ++;
					
					xPlacer += 50;

					if(striped || zigzag)
						coins.add(new Coin(xPlacer, -50, 2));
					else
						coins.add(new Coin((int) (Math.random() * 560) + 20, -50, 2));
				}
			}
		});

		//mouse listener for pattern toggle
		togglePattern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(togglePattern.getText().equals("Set coin pattern to stripes"))
				{
					striped = true;
					zigzag = false;
					//System.out.println("Pattern changed to stripes");
					togglePattern.setText("Set coin pattern to zig zag");
				}
				else if(togglePattern.getText().equals("Set coin pattern to zig zag"))
				{
					striped = false;
					zigzag = true;
					//System.out.println("Pattern changed to zig zag");
					togglePattern.setText("Set coin pattern to random");
				}
				else if(togglePattern.getText().equals("Set coin pattern to random"))
				{
					striped = false;
					zigzag = false;
					//System.out.println("Pattern set to random");
					togglePattern.setText("Set coin pattern to stripes");
				}

			}
		});

		//mouse listener for upgrades
		upgrade1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(total >= autoClickCost)
				{
					total -= autoClickCost;
					autoClickCost += 20;
					autoClick = true;
					autoClickLevel ++;
				}
			}
		});

		upgrade2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(total >= enhanceAlgorithmCost)
				{
					total -= enhanceAlgorithmCost;
					enhanceAlgorithmCost *= 2;
					compoundClick = true;
					clickAmount++;
				}
			}
		});
		
		gamble.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int guess = (int) (Math.random() * 3 + 1);
				int temp = (int) total;
				if(total >= gambleCost)
				{
					total -= gambleCost;
				}
				 
				if(guess == 3)
					total = temp * 2;
			}
		});
	}

	public BitClick () {

		setLocation (0,0);
		this.setVisible(true);

	}

	@Override
	public void paintComponent ( Graphics g ) {

		super.paintComponent(g);
		BitClick.g = g;
		
		//updates gamble price
		if(total >= 1000)
			gambleCost = (int) total / 2;
		else
			gambleCost = 500;

		//implements auto click feature
		if(autoClick)
		{
			autoClickCount += autoClickLevel;
			if(autoClickCount >= 500)
			{
				total ++;
				if(zigzag && add)
					xPlacer += 50;
				if(zigzag && !add)
					xPlacer -= 50;
				else
					xPlacer += 50;
				//System.out.println("Coin drawn");

				//checks for pattern
				if(striped || zigzag)
					coins.add(new Coin(xPlacer, -50, 2));
				else
					coins.add(new Coin((int) (Math.random() * 560) + 20, -50, 2));

				//resets auto click counter
				autoClickCount = 0;
			}
		}


		//condition to make stripe pattern
		if(striped)
		{
			if(xPlacer >= 500)
			{
				xPlacer = 0;
			}
		}
		
		//condition for zigzag pattern
		if(zigzag)
		{
			if(xPlacer >= 580)
				add = false;
			if(xPlacer <= 0)
				add = true;
		}
		

		//draws all recent coins
		for(int i = 0; i < coins.size(); i++)
		{
			coins.get(i).drawCoin(coins.get(i).getX(), g);
		}

		//makes coins go down
		for(int i = 0; i < coins.size(); i++)
		{
			coins.get(i).setY(coins.get(i).getY() + coins.get(i).getSpeed());
		}

		//makes coins disappear
		for(int i = 0; i < coins.size(); i++)
		{
			if(coins.get(i).getY() >= 600)
			{
				gameFrame.remove(coins.get(i));
				coins.remove(i);
			}
		}

		//updates location and etc of buttons
		clickMe.setBounds(400, 400, 100, 40);
		togglePattern.setBounds(20, 10, 200, 40);
		upgrade1.setBounds(250, 540, 140, 40);
		upgrade1.setText("Computer:" + autoClickCost + "BTC");
		upgrade2.setBounds(400, 540, 200, 40);
		upgrade2.setText("Enhance algorithm:" + enhanceAlgorithmCost + "BTC");
		gamble.setText("Gamble: " + gambleCost + " BTC");
		
		gamble.setBounds(0, 540, 240, 40);
		gamble.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
		gamble.setForeground(Color.black);

		//updates computer level
		upgradeOneLabel.setBounds(620, 200, 120, 40);
		upgradeOneLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		upgradeOneLabel.setForeground(Color.blue);
		upgradeOneLabel.setText("Computers: " + autoClickLevel);

		//updates computer level
		upgradeTwoLabel.setBounds(620, 240, 200, 40);
		upgradeTwoLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		upgradeTwoLabel.setForeground(Color.blue);
		upgradeTwoLabel.setText("Level of Algorithm: " + clickAmount);


		//updates score
		score.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		score.setForeground(Color.white);
		
		//adjusts position of score label
		if(total >= 1000000000)
			score.setBounds(310, 25, 300, 40);
		
		if(total >= 100000000)
			score.setBounds(320, 25, 300, 40);
		
		else if(total >= 10000000)
			score.setBounds(330, 25, 300, 40);
		
		else if(total >= 1000000)
			score.setBounds(340, 25, 300, 40);
		
		else if(total >= 100000)
			score.setBounds(350, 25, 300, 40);
		
		else if(total >= 10000)
			score.setBounds(360, 25, 300, 40);
		
		else if(total >= 1000)
			score.setBounds(370, 25, 300, 40);
		
		else if(total >= 100)
			score.setBounds(380, 25, 300, 40);
		
		else if(total >= 10)
			score.setBounds(390, 25, 300, 40);

		if(total < 10)
			score.setBounds(400, 25, 300, 40);

		score.setText("" + total);

		repaint();

	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}