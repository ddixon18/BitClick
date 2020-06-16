import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {

	public JFrame frame;
	public static String[] thing;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		thing = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 840, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton playBitClickButton = new JButton("Play BitClick");
		playBitClickButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(playBitClickButton);
				BitClick.main(thing);
			}
		});
		playBitClickButton.setForeground(Color.BLACK);
		playBitClickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		playBitClickButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		playBitClickButton.setBounds(343, 449, 172, 64);
		frame.getContentPane().add(playBitClickButton);
		
		JLabel welcomeText = new JLabel("Welcome To BitClick!");
		welcomeText.setForeground(Color.BLUE);
		welcomeText.setBackground(Color.BLACK);
		welcomeText.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		welcomeText.setBounds(248, 11, 346, 82);
		frame.getContentPane().add(welcomeText);
		
		JLabel lblTheGameWhere = new JLabel("The game that makes mining fun...");
		lblTheGameWhere.setForeground(Color.BLUE);
		lblTheGameWhere.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblTheGameWhere.setBounds(294, 81, 253, 36);
		frame.getContentPane().add(lblTheGameWhere);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setIcon(new ImageIcon("E:\\Computer Science\\AP CS\\giphy.gif"));
		lblNewLabel.setBounds(311, 167, 241, 200);
		frame.getContentPane().add(lblNewLabel);
	}
}
