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




public class Game4 {
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
		continueButton = new JButton("윷 던지기");
		l1 = new JLabel();
		l2 = new JLabel();

		CardPanel.setLayout(card);

		continueButton.setBounds(570, 150, 100, 50);
		l1.setBounds(570,100,100,50);
		l2.setBounds(570,100,100,50);

		coverPanel.add(startButton);
		mainPanel.add(continueButton);
		mainPanel.add(l1);
		mainPanel.add(l2);
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



		p[5][5]=new Position(465,420,1);
		p[5][4]=new Position(465,335,1);
		p[5][3]=new Position(465,260,1);
		p[5][2]=new Position(465,180,1);
		p[5][1]=new Position(465,105,1);
		p[5][0]=new Position(465,25,50);
		p[4][0]=new Position(375,25,1);
		p[3][0]=new Position(285,25,1);
		p[2][0]=new Position(195,25,1);
		p[1][0]=new Position(105,25,1);
		p[0][0]=new Position(20,25,100);
		p[0][1]=new Position(20,105,1);
		p[0][2]=new Position(20,180,1);
		p[0][3]=new Position(20,260,1);
		p[0][4]=new Position(20,33,1);
		p[0][5]=new Position(20,42,1);
		p[1][5]=new Position(105,420,1);
		p[2][5]=new Position(195,420,1);
		p[3][5]=new Position(285,420,1);
		p[4][5]=new Position(375,430,1);
		p[4][1]=new Position(380,105,50);
		p[3][2]=new Position(285,180,50);
		p[2][3]=new Position(190,260,50);
		p[1][4]=new Position(100,335,50);
		p[1][1]=new Position(100, 105,100);
		p[2][2]=new Position(190,180,100);
		p[3][3]=new Position(285,260,100);
		p[4][4]=new Position(375,335,100);
		p[1][2]=new Position(285,260,100);//가운데

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
		Game4 g = new Game4();
		g.go();
	}
	public class Position{
		int x;
		int y;
		int change;
		Position(int x,int y,int change){
			this.x=x;
			this.y=y;
			this.change=change;
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

							continueButton.setEnabled(true);

							if(random==0)
								p1[i].backDo();
							else if(p1[i].crossChange==1)
								p1[i].move();
							else if(p1[i].crossChange==50)
								p1[i].cross();
							else if(p1[i].crossChange==100)
								p1[i].cross1();
							System.out.println(p1[i].pieceX1+"and"+p1[i].pieceY1);
							order=2;

							l1= new JLabel("2p차례");
							mainPanel.repaint();


						}

					}
					for(int i =0 ; i<p1.length;i++)
						for(int j=0;j<p2.length;j++) {
							if(p1[i].pX==p2[j].pX&&p1[i].pY==p2[j].pY) {
								p2[j].pieceX2=5;
								p2[j].pieceY2=5;
								p2[j].pX=p2[j].firstX;
								p2[j].pY=p2[j].firstY;
								order=1;		
							}	
						}
				}
			}
			else if(order==2) {
				if(!turn) {
					for (int i=0; i<p2.length; i++) {
						if (p2[i].distance(point) <= 24) {

							continueButton.setEnabled(true);

							if(random==0)
								p2[i].backDo();
							else if(p2[i].crossChange==1)
								p2[i].move();
							order=1;

							l1= new JLabel("1p차례");
							mainPanel.repaint();


						}
					}
					for(int i = 0;i<p2.length;i++)
						for(int j=0;j<p1.length;j++) {
							if(p2[i].pX==p1[j].pX&&p2[i].pY==p1[j].pY) {
								p1[j].pieceX1=5;
								p1[j].pieceY1=5;
								p1[j].pX=p1[j].firstX;
								p1[j].pY=p1[j].firstY;
								order=2;

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
				l1= new JLabel("1p차례");
			}
			if(button == continueButton) {
				random=(int) (Math.random()*6);
				continueButton.setEnabled(false);
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
		int firstX;
		int firstY;
		int finishX;
		int finishY;
		boolean finishScore=false;
		int width;			
		int height;
		int move=0;
		int pieceX1=5,pieceY1=5;
		boolean select;
		int moving=1;
		int crossChange=1;
		public Player1(String img, int x, int y, int width, int height) {
			super(img);
			pX=x;
			firstX=x;
			pY=y;
			firstY=y;
			this.width=width;
			this.height=height;
		}
		public void fisnsh() {
			if(finishScore&&pieceX1==5&&pieceY1<5) {
				pX=finishX;
				pY=finishY;
			}
		}
		public void draw(Graphics g) {
			g.drawImage(this.getImage(), pX, pY, width, height, null);
		}
		public double distance (Point p) {
			return Math.sqrt(Math.pow((pX+width/2)-p.x, 2)+ Math.pow((pY+height/2)-p.y, 2));
		}


		public void backDo() {
			if(!(pX==firstX&&pY==firstY)) {
				if(pieceY1==5&&pieceX1>0)
					pieceX1-=moving;
				else if(pieceX1==0&&pieceY1>0)
					pieceY1-=moving;
				else if(pieceY1==0&&pieceX1<5)
					pieceX1+=moving;
				else if(pieceX1==5&&pieceY1<5)
					pieceY1+=moving;
				pX=p[pieceX1][pieceY1].x;
				pY=p[pieceX1][pieceY1].y;
				mainPanel.repaint();
			}
			crossChange=p[pieceX1][pieceY1].change;
		}
		public void cross() {
			for(int i=0;i<random;i++) {
				if(pieceX1==5&&pieceY1==0) {
					pieceX1=4;
					pieceY1=1;
				}
				else if(pieceX1==4&&pieceY1==1) {
					pieceX1=3;
					pieceY1=2;
				}
				else if(pieceX1==3&&pieceY1==2) {
					pieceX1=1;
					pieceY1=2;
				}
				else if(pieceX1==1&&pieceY1==2) {
					pieceX1=2;
					pieceY1=3;
				}
				else if(pieceX1==2&&pieceY1==3) {
					pieceX1=1;
					pieceY1=4;
				}
				else if(pieceX1==1&&pieceY1==4) {
					pieceX1=0;
					pieceY1=5;
				}
				else if(pieceY1==5)
					pieceX1++;
				
				pX=p[pieceX1][pieceY1].x;
				pY=p[pieceX1][pieceY1].y;
				mainPanel.repaint();
			}
			crossChange=p[pieceX1][pieceY1].change;
		}
		public void cross1() {
			for(int i=0;i<random;i++) {
				if(pieceX1==0&&pieceY1==0) {
					pieceX1=1;
					pieceY1=1;
				}
				else if(pieceX1==1&&pieceY1==1) {
					pieceX1=2;
					pieceY1=2;
				}
				else if(pieceX1==2&&pieceY1==2) {
					pieceX1=1;
					pieceY1=2;
				}
				else if(pieceX1==1&&pieceY1==2) {
					pieceX1=3;
					pieceY1=3;
				}
				else if(pieceX1==3&&pieceY1==3) {
					pieceX1=4;
					pieceY1=4;
				}
				else if(pieceX1==4&&pieceY1==4) {
					pieceX1=5;
					pieceY1=5;
				}
				else if(pieceY1==5&&pieceX1==5) {
					finishScore=true;
					pieceY1--;
				}
				
				pX=p[pieceX1][pieceY1].x;
				pY=p[pieceX1][pieceY1].y;
				mainPanel.repaint();
			}
			crossChange=p[pieceX1][pieceY1].change;
		}
		public void move() {
			for(int i=0;i<random;i++) {
				if(pieceX1==5&&pieceY1>0) {
					pieceY1-=moving;
				}
				else if(pieceY1==0&&pieceX1>0){
					pieceX1-=moving;
				}
				else if(pieceX1==0&&pieceY1<5){
					pieceY1+=moving;
				}
				else if(pieceY1==5&&pieceX1<5){
					pieceX1+=moving;
				}

				pX=p[pieceX1][pieceY1].x;
				pY=p[pieceX1][pieceY1].y;
				if(pieceX1==5&&pieceY1==5)
					finishScore=true;

				mainPanel.repaint();	
			}
			crossChange=p[pieceX1][pieceY1].change;
		}
	}
	class Player2 extends ImageIcon {
		int pX;
		int pY;
		int firstX;
		int firstY;
		int finishX;
		int finishY;
		boolean finishScore=false;
		int width;			
		int height;
		int pieceX2=5,pieceY2=5;
		boolean select;
		int moving=1;
		int crossChange=1;
		public Player2(String img , int x,int y,int width, int height) {
			super(img);
			pX=x;
			firstX=x;
			pY=y;
			firstY=y;
			this.width=width;
			this.height=height;
		}
		public void finish() {
			if(finishScore&&pieceX2==5&&pieceY2<5) {
				pX=finishX;
				pY=finishY;
			}
		}
		public void draw(Graphics g) {
			g.drawImage(this.getImage(), pX, pY, width, height, null);
		}
		public double distance (Point p) {
			return Math.sqrt(Math.pow((pX+width/2)-p.x, 2)+ Math.pow((pY+height/2)-p.y, 2));
		}
		public void backDo() {
			if(!(pX==firstX&&pY==firstY)) {
				if(pieceY2==5&&pieceX2>0)
					pieceX2-=moving;
				else if(pieceX2==0&&pieceY2>0)
					pieceY2-=moving;
				else if(pieceY2==0&&pieceX2<5)
					pieceX2+=moving;
				else if(pieceX2==5&&pieceY2<5)
					pieceY2+=moving;
				pX=p[pieceX2][pieceY2].x;
				pY=p[pieceX2][pieceY2].y;
				mainPanel.repaint();}
		}
		public void cross() {
			for(int i=0;i<random;i++) {
				if(pieceX2==5&&pieceY2==0) {
					pieceX2=4;
					pieceY2=1;
				}
				else if(pieceX2==4&&pieceY2==1) {
					pieceX2=3;
					pieceY2=2;
				}
				else if(pieceX2==3&&pieceY2==2) {
					pieceX2=1;
					pieceY2=2;
				}
				else if(pieceX2==1&&pieceY2==2) {
					pieceX2=2;
					pieceY2=3;
				}
				else if(pieceX2==2&&pieceY2==3) {
					pieceX2=1;
					pieceY2=4;
				}
				else if(pieceX2==1&&pieceY2==4) {
					pieceX2=0;
					pieceY2=5;
				}
				else if(pieceY2==5)
					pieceX2++;
				
				pX=p[pieceX2][pieceY2].x;
				pY=p[pieceX2][pieceY2].y;
				mainPanel.repaint();
			}
			crossChange=p[pieceX2][pieceY2].change;
		}
		public void cross1() {
			for(int i=0;i<random;i++) {
				if(pieceX2==0&&pieceY2==0) {
					pieceX2=1;
					pieceY2=1;
				}
				else if(pieceX2==1&&pieceY2==1) {
					pieceX2=2;
					pieceY2=2;
				}
				else if(pieceX2==2&&pieceY2==2) {
					pieceX2=1;
					pieceY2=2;
				}
				else if(pieceX2==1&&pieceY2==2) {
					pieceX2=3;
					pieceY2=3;
				}
				else if(pieceX2==3&&pieceY2==3) {
					pieceX2=4;
					pieceY2=4;
				}
				else if(pieceX2==4&&pieceY2==4) {
					pieceX2=5;
					pieceY2=5;
				}
				else if(pieceY2==5&&pieceX2==5) {
					finishScore=true;
					pieceY2--;
				}
				
				pX=p[pieceX2][pieceY2].x;
				pY=p[pieceX2][pieceY2].y;
				mainPanel.repaint();
			}
			crossChange=p[pieceX2][pieceY2].change;
		}
		public void move() {
			for(int i=0;i<random;i++) {

				if(pieceX2==5&&pieceY2>0){
					pieceY2-=moving;
				}
				else if(pieceY2==0&&pieceX2>0) {
					pieceX2-=moving;
				}
				else if(pieceX2==0&&pieceY2<5){
					pieceY2+=moving;
				}
				else if(pieceY2==5&&pieceX2<5){
					pieceX2+=moving;
				}
				pX=p[pieceX2][pieceY2].x;
				pY=p[pieceX2][pieceY2].y;

				if(pieceX2==5&&pieceY2==5)
					finishScore=true;
				mainPanel.repaint();	
			}
		}

	}

}

