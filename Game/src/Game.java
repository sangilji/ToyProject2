import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;



public class Game {
	JFrame frame;
	JButton startButton;
	JLabel l1;
	JLabel l2;
	ArrayList<player1> list1 = new ArrayList<>();
	ArrayList<player2> list2 = new ArrayList<>();
	
	CardLayout card = new CardLayout(0,0);
	CoverPanel coverPanel;
	MainPanel mainPanel;
	JPanel CardPanel;

	ImageIcon img1 = new ImageIcon("src/cover.jpg");
	ImageIcon img2 = new ImageIcon("src/main.jpg");
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		coverPanel = new CoverPanel();
		mainPanel = new MainPanel();
		CardPanel =new JPanel();
		startButton = new JButton("시작");
		CardPanel.setLayout(card);

		coverPanel.add(startButton);



		coverPanel.setBackground(Color.white);
		frame.setBackground(Color.white);
		coverPanel.setPreferredSize(new Dimension(700, 500));

		frame.getContentPane().add(BorderLayout.CENTER, coverPanel);
		frame.getContentPane().setLayout(card);

		ButtonListener b = new ButtonListener();
		startButton.addActionListener(b);

		CardPanel.add("a1",coverPanel);
		CardPanel.add("a2", mainPanel);
		frame.add(CardPanel);
		frame.pack();
		frame.setVisible(true);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.go();
	}
	class CoverPanel extends JPanel{
		public void paintComponent(Graphics g) {	// 이 메소드는 화면이 리프레쉬될때마다 실행됨

			int w = this.getWidth();
			int h= this.getHeight();

			g.drawImage(img1.getImage(), 0,0, w, h,null);

		}
	}
	class MainPanel extends JPanel{
		public void paintComponent(Graphics g) {	// 이 메소드는 화면이 리프레쉬될때마다 실행됨

			int w = this.getWidth();
			int h= this.getHeight();

			g.drawImage(img2.getImage(), 0,0, w, h,null);

		}
	}
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if(button == startButton){
				card.show(CardPanel,"a2");
			
			}
		}
	}
	class player1 extends ImageIcon {
		int width;			
		int height;		
		public player1(int n) {
			
		}
	}
	class player2 extends ImageIcon {
		public player2(int n) {
			
		}
		
	}
}

