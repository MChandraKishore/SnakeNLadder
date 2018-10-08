import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Game extends JFrame {

	private JPanel contentPane;
	int Player1x=770;
	int Player1y=450;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1000,640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPlayer1 = new JLabel();
		ImageIcon player1 = new ImageIcon(new ImageIcon(Game.class.getResource("/img/player-1.png")).getImage().getScaledInstance(40, 80, Image.SCALE_DEFAULT));
		lblPlayer1.setIcon(player1);
		lblPlayer1.setBounds(Player1x, Player1y, 40, 80);
		contentPane.add(lblPlayer1);
		
		JLabel lblCk = new JLabel();
		ImageIcon board = new ImageIcon(new ImageIcon(Game.class.getResource("/img/board.jpg")).getImage().getScaledInstance(700, 600, Image.SCALE_DEFAULT));
		lblCk.setIcon(board);
		lblCk.setBounds(0,0, 700, 600);
		contentPane.add(lblCk);
		
		JLabel lblDice = new JLabel();
		ImageIcon dice = new ImageIcon(new ImageIcon(Game.class.getResource("/img/dice-1.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		lblDice.setIcon(dice);
		lblDice.setBounds(797, 136, 100, 100);
		contentPane.add(lblDice);
		
		JLabel lblPlayerTurn = new JLabel("Player 1 Turn");
		lblPlayerTurn.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerTurn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPlayerTurn.setBounds(755, 28, 204, 34);
		contentPane.add(lblPlayerTurn);
		
		
		JLabel lblPlayer2 = new JLabel();
		ImageIcon player2 = new ImageIcon(new ImageIcon(Game.class.getResource("/img/player-2.png")).getImage().getScaledInstance(40, 80, Image.SCALE_DEFAULT));
		lblPlayer2.setIcon(player2);
		lblPlayer2.setBounds(830, 450, 40, 80);
		contentPane.add(lblPlayer2);
		
		JButton btnRollDice = new JButton("Roll Dice");
		btnRollDice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int diceroll=RollDice();
				ImageIcon dice = new ImageIcon(new ImageIcon(Game.class.getResource("/img/dice-"+diceroll+".png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				lblDice.setIcon(dice);
				MovePlayer1(lblPlayer1,diceroll);
				
			}
		});
		btnRollDice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRollDice.setBounds(780, 296, 142, 50);
		contentPane.add(btnRollDice);
		
		
	}
	public int RollDice() {
		Random num=new Random();
		int rnum=num.nextInt(6);
		while(rnum==0) {
			rnum=num.nextInt(6);
		}
		return rnum;
	}
	public void MovePlayer1(JLabel obj,int position) {
		if(Player1x==770&&Player1y==450) {
			Player1x=20+70*(position-1);
			Player1y=480;
		}
		else{
			Player1x+=70*position;
			if(Player1x>700) {
				Player1x=Player1x%700;
				Player1y-=60;
			}
		}
		obj.setBounds(Player1x, Player1y, 40, 80);
	}
}
