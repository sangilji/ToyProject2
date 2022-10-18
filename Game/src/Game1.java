import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;




public class Game1 {
	JFrame frame;
	JButton startButton, continueButton;
	JLabel l1;
	JLabel l2;
	int random;
	int clicker;
    boolean turn;
	int order=1;

	ArrayList<Player1> list1 = new ArrayList<>();
	ArrayList<Player2> list2 = new ArrayList<>();

	CardLayout card = new CardLayout(0,0);
	CoverPanel coverPanel;
	MainPanel mainPanel;
	JPanel CardPanel;
	Position p[][] = new Position[6][6];
	Player1 p1[] = new Player1[4];
	Player2 p2[] = new Player2[4];

	ImageIcon img1 = new ImageIcon("src/cover.jpg");
	ImageIcon img2 = new ImageIcon("src/blue 0 orange 0.jpg");
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		coverPanel = new CoverPanel();
		mainPanel = new MainPanel();
		CardPanel =new JPanel();
		startButton = new JButton("시작");
		continueButton = new JButton("시작버튼");


		CardPanel.setLayout(card);

		continueButton.setBounds(570, 150, 100, 50);


		coverPanel.add(startButton);
		mainPanel.add(continueButton);
		mainPanel.setLayout(null);

		Click c = new Click();
		mainPanel.addMouseListener(c);


		coverPanel.setBackground(Color.white);
		frame.setBackground(Color.white);
		coverPanel.setPreferredSize(new Dimension(700, 500));

		frame.getContentPane().add(BorderLayout.CENTER, coverPanel);
		frame.getContentPane().setLayout(card);

		ButtonListener b = new ButtonListener();
		startButton.addActionListener(b);
		continueButton.addActionListener(b);

		CardPanel.add("a1",coverPanel);
		CardPanel.add("a2", mainPanel);
		p1[0]= new Player1("src/skyblue.png",565,265,60,50);
		p1[1]= new Player1("src/skyblue.png",625,265,60,50);
		p1[2]= new Player1("src/skyblue.png",565,320,60,50);
		p1[3]= new Player1("src/skyblue.png",625,320,60,50);
		p2[0]= new Player2("src/orange.png",565,385,60,50);
		p2[1]= new Player2("src/orange.png",625,385,60,50);
		p2[2]= new Player2("src/orange.png",565,440,60,50);
		p2[3]= new Player2("src/orange.png",625,440,60,50);

		p[5][5]=new Position(465,420);
		p[5][4]=new Position(465,350);
		p[5][3]=new Position(465,275);
		p[5][2]=new Position(465,195);
		p[5][1]=new Position(465,120);
		p[5][0]=new Position(465,35);
		p[4][0]=new Position(390,35);
		p[3][0]=new Position(300,35);
		p[2][0]=new Position(210,35);
		p[1][0]=new Position(120,35);
		p[0][0]=new Position(30,35);
		p[0][1]=new Position(30,120);
		p[0][2]=new Position(30,195);
		p[0][3]=new Position(30,275);
		p[0][4]=new Position(30,350);
		p[0][5]=new Position(30,430);
		p[1][5]=new Position(120,430);
		p[2][5]=new Position(210,430);
		p[3][5]=new Position(300,430);
		p[4][5]=new Position(390,430);
		/*p[5][5].x=480;p[5][5].y=430;
		p[5][4].x=480;p[5][4].y=350;
		p[5][3].x=480;p[5][3].y=275;
		p[5][2].x=480;p[5][2].y=195;
		p[5][1].x=480;p[5][1].y=120;
		p[5][0].x=480;p[5][0].y=35;
		p[4][0].x=390;p[4][0].y=35;
		p[3][0].x=300;p[3][0].y=35;
		p[2][0].x=210;p[2][0].y=35;
		p[1][0].x=120;p[1][0].y=35;
		p[0][0].x=30;p[0][0].y=35;
		p[0][1].x=30;p[0][1].y=120;
		p[0][2].x=30;p[0][2].y=195;
		p[0][3].x=30;p[0][3].y=275;
		p[0][4].x=30;p[0][4].y=350;
		p[0][5].x=30;p[0][5].y=430;
		p[1][5].x=120;p[1][5].y=430;
		p[2][5].x=210;p[2][5].y=430;
		p[3][5].x=300;p[3][5].y=430;
		p[4][5].x=390;p[4][5].y=430;*/

		frame.add(CardPanel);
		frame.pack();
		frame.setVisible(true);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game1 g = new Game1();
		g.go();
	}
	public class Position{
		int x;
		int y;
		Position(int x,int y){
			this.x=x;
			this.y=y;
		}
	}

	class CoverPanel extends JPanel{
		public void paintComponent(Graphics g) {	// 이 메소드는 화면이 리프레쉬될때마다 실행됨

			int w = this.getWidth();
			int h= this.getHeight();

			g.drawImage(img1.getImage(), 0,0, w, h,null);

		}
	}
	class Click implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {

			Point point = e.getPoint();
			System.out.println(e.getPoint());
			if(order==1) {
				if(turn) {
				for (int i=0; i<p1.length; i++) {
					if (p1[i].distance(point) <= 24) {
						p1[i].pX=p[p1[i].pieceX1][p1[i].pieceY1].x;
						p1[i].pY=p[p1[i].pieceX1][p1[i].pieceY1].y;
						
							clicker=i;
							p1[i].move();
							order++;
						mainPanel.repaint();
						
					}
					
				}
				
				}
			}
			else if(order==2) {
				if(!turn) {
				for (int i=0; i<p2.length; i++) {
					if (p2[i].distance(point) <= 24) {
						p2[i].pX=p[p2[i].pieceX2][p2[i].pieceY2].x;
						p2[i].pY=p[p2[i].pieceX2][p2[i].pieceY2].y;
						
						
							clicker=i;
							p2[i].move();
							order--;
						mainPanel.repaint();

					}
				}
				
			}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	class MainPanel extends JPanel{
		public void paintComponent(Graphics g) {	// 이 메소드는 화면이 리프레쉬될때마다 실행됨

			int w = this.getWidth();
			int h= this.getHeight();

			g.drawImage(img2.getImage(), 0,0, w, h,null);


			for(int i =0 ; i<4 ;i++) {
				p1[i].draw(g);	
			}
			for(int i =0 ; i<4 ;i++) {
				p2[i].draw(g);	
			}

		}
	}
	class ButtonListener implements ActionListener {
		int t;
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if(button == startButton){
				card.show(CardPanel,"a2");
			}
			if(button == continueButton) {
				random=(int) (Math.random()*6);
				if(order==1) {
					System.out.println(random);
					turn=true;
				}
				else if(order==2) {
					System.out.println(random);
					turn=false;
				}
			}
		}
	}

	class Player1 extends ImageIcon {
		int pX;
		int pY;
		int width;			
		int height;
		int move=0;
		int pieceX1=5,pieceY1=5;
		boolean select;

		public Player1(String img, int x, int y, int width, int height) {
			super(img);
			pX=x;
			pY=y;
			this.width=width;
			this.height=height;
		}
		public void draw(Graphics g) {
			g.drawImage(this.getImage(), pX, pY, width, height, null);
		}
		public double distance (Point p) {
			return Math.sqrt(Math.pow((pX+width/2)-p.x, 2)+ Math.pow((pY+height/2)-p.y, 2));
		}
		public void move() {
			for(int i=0;i<random;i++) {
				if(pieceX1==5)
					if(pieceY1>0) {
						pieceY1-=1;
						p1[clicker].pX=p[pieceX1][pieceY1].x;
						p1[clicker].pY=p[pieceX1][pieceY1].y;
						mainPanel.repaint();
						continue;
					}
				if(pieceY1==0)
					if(pieceX1>0){
						pieceX1-=1;
						p1[clicker].pX=p[pieceX1][pieceY1].x;
						p1[clicker].pY=p[pieceX1][pieceY1].y;
						mainPanel.repaint();
						continue;
					}
				if(pieceX1==0)
					if(pieceY1<5){
						pieceY1+=1;
						p1[clicker].pX=p[pieceX1][pieceY1].x;
						p1[clicker].pY=p[pieceX1][pieceY1].y;
						mainPanel.repaint();
						continue;
					}
				if(pieceY1==5)
					if(pieceX1<5){
						pieceX1+=1;
						p1[clicker].pX=p[pieceX1][pieceY1].x;
						p1[clicker].pY=p[pieceX1][pieceY1].y;
						mainPanel.repaint();
						continue;
					}
			}
		}
	}
	class Player2 extends ImageIcon {
		int pX;
		int pY;
		int width;			
		int height;
		int pieceX2=5,pieceY2=5;
		boolean select;
		public Player2(String img , int x,int y,int width, int height) {
			super(img);
			pX=x;
			pY=y;
			this.width=width;
			this.height=height;
		}

		public void draw(Graphics g) {
			g.drawImage(this.getImage(), pX, pY, width, height, null);
		}
		public double distance (Point p) {
			return Math.sqrt(Math.pow((pX+width/2)-p.x, 2)+ Math.pow((pY+height/2)-p.y, 2));
		}
		public void move() {
			for(int i=0;i<random;i++) {
				if(pieceX2==5)
					if(pieceY2>0) {
						pieceY2-=1;
						p2[clicker].pX=p[pieceX2][pieceY2].x;
						p2[clicker].pY=p[pieceX2][pieceY2].y;
						mainPanel.repaint();
						continue;
					}
				if(pieceY2==0)
					if(pieceX2>0) {
						pieceX2-=1;
						p2[clicker].pX=p[pieceX2][pieceY2].x;
						p2[clicker].pY=p[pieceX2][pieceY2].y;
						mainPanel.repaint();
						continue;
					}
				if(pieceX2==0)
					if(pieceY2<5) {
						pieceY2+=1;
						p2[clicker].pX=p[pieceX2][pieceY2].x;
						p2[clicker].pY=p[pieceX2][pieceY2].y;
						mainPanel.repaint();
						continue;
					}
				if(pieceY2==5)
					if(pieceX2<5) {
						pieceX2+=1;
						p2[clicker].pX=p[pieceX2][pieceY2].x;
						p2[clicker].pY=p[pieceX2][pieceY2].y;
						mainPanel.repaint();
						continue;
					}
			}
		}

	}

}

