
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
	int Player1Pos=-1;//770,450;
	int Player2Pos=-1;//830,450;
	int turn=1;
	int[] boardOrder[]={{15,510},{85,510},{155,510},{225,510},{295,510},{365,510},{435,510},{505,510},{575,510},{645,510},{645,450},{575,450},{505,450},{435,450},{365,450},{295,450},{225,450},{155,450},{85,450},{15,450},{15,390},{85,390},{155,390},{225,390},{295,390},{365,390},{435,390},{505,390},{575,390},{645,390},{645,330},{575,330},{505,330},{435,330},{365,330},{295,330},{225,330},{155,330},{85,330},{15,330},{15,270},{85,270},{155,270},{225,270},{295,270},{365,270},{435,270},{505,270},{575,270},{645,270},{645,210},{575,210},{505,210},{435,210},{365,210},{295,210},{225,210},{155,210},{85,210},{15,210},{15,150},{85,150},{155,150},{225,150},{295,150},{365,150},{435,150},{505,150},{575,150},{645,150},{645,90},{575,90},{505,90},{435,90},{365,90},{295,90},{225,90},{155,90},{85,90},{15,90},{15,30},{85,30},{155,30},{225,30},{295,30},{365,30},{435,30},{505,30},{575,30},{645,30},{645,0},{575,0},{505,0},{435,0},{365,0},{295,0},{225,0},{155,0},{85,0},{15,0}};
	int[] snakes[]={{17,7},{54,34},{62,19},{64,60},{87,24},{93,73},{95,75}};
	int[] ladders[]={{1,38},{4,14},{9,31},{21,42},{28,84},{51,67},{71,91},{80,100}};
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
		lblPlayer1.setBounds(770, 450, 40, 80);
		contentPane.add(lblPlayer1);
		
		
		JLabel lblPlayer2 = new JLabel();
		ImageIcon player2 = new ImageIcon(new ImageIcon(Game.class.getResource("/img/player-2.png")).getImage().getScaledInstance(40, 80, Image.SCALE_DEFAULT));
		lblPlayer2.setIcon(player2);
		lblPlayer2.setBounds(830, 450, 40, 80);
		contentPane.add(lblPlayer2);
		
		JLabel lblCk = new JLabel();
		ImageIcon board = new ImageIcon(new ImageIcon(Game.class.getResource("/img/board.gif")).getImage().getScaledInstance(700, 600, Image.SCALE_DEFAULT));
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
		
		JButton btnRollDice = new JButton("Roll Dice");
		btnRollDice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int diceroll=RollDice();
				ImageIcon dice = new ImageIcon(new ImageIcon(Game.class.getResource("/img/dice-"+diceroll+".png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				lblDice.setIcon(dice);
				if(turn==1) {
					MovePlayer1(lblPlayer1,diceroll);
					turn=2;
				}
				else {
					MovePlayer2(lblPlayer2,diceroll);
					turn=1;
				}
				if(Player1Pos==99) {
					setVisible(false);
					new Winner(1).setVisible(true);
				}
				else if(Player2Pos==99) {
					setVisible(false);
					new Winner(2).setVisible(true);
				}
				lblPlayerTurn.setText("Player "+turn+" Turn");
				
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
		if(Player1Pos+position<100) {
			Player1Pos+=position;
		}
		for(int x=0;x<snakes.length;x++){
			if(snakes[x][0]==(Player1Pos+1)){
				Player1Pos=snakes[x][1]-1;
			}
		}
		for(int y=0;y<ladders.length;y++){
			if(ladders[y][0]==(Player1Pos+1)){
				Player1Pos=ladders[y][1]-1;
			}
		}
		obj.setBounds(boardOrder[Player1Pos][0], boardOrder[Player1Pos][1], 40, 80);
	}
	public void MovePlayer2(JLabel obj,int position) {
		if(Player2Pos+position<100) {
			Player2Pos+=position;
		}
		for(int x=0;x<snakes.length;x++){
			if(snakes[x][0]==(Player2Pos+1)){
				Player2Pos=snakes[x][1]-1;
			}
		}
		for(int y=0;y<ladders.length;y++){
			if(ladders[y][0]==(Player2Pos+1)){
				Player2Pos=ladders[y][1]-1;
			}
		}
		obj.setBounds(boardOrder[Player2Pos][0], boardOrder[Player2Pos][1], 40, 80);
	}
}
