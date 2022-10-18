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
import javax.swing.Timer;
import javax.swing.plaf.synth.SynthSeparatorUI;




public class Game6 {
	JFrame frame;
	JFrame yutFrame;
	JButton startButton, continueButton;
	JLabel l1;
	JLabel l2;
	int random;
	int clicker;
	boolean turn;
	int order=1;
	int timePassed=0;
	int yutTimePassed=0;
	int p1Finish=0;
	int p2Finish=0;
	
	ArrayList<Player1> list1 = new ArrayList<>();
	ArrayList<Player2> list2 = new ArrayList<>();

	CardLayout card = new CardLayout(0,0);
	CoverPanel coverPanel;
	MainPanel mainPanel;
	EndingPanel endingPanel;
	Ending1Panel ending1Panel;
	YutPanel yutPanel;
	JPanel CardPanel;
	Timer t = new Timer(100,new Time());
	Timer yutT = new Timer(1000,new YutTimer());
	
	Position p[][] = new Position[6][6];
	Player1 p1[] = new Player1[4];
	Player2 p2[] = new Player2[4];

	ImageIcon img1 = new ImageIcon("src/커버사진.jpg");
	ImageIcon img2 = new ImageIcon("src/blue 0 orange 0.jpg");
	ImageIcon img3 = new ImageIcon("src/ending1.png");
	ImageIcon img4 = new ImageIcon("src/ending2.png");
	ImageIcon yutImage ;
	String yut[] = {"src/김준수새끼.gif","src/do.gif","src/Gae.gif","src/gar.gif","src/yooc.gif","src/mo.gif","src/firstYut.jpg"};
	String yutStop[] = {"src/backdo.jpg","src/do.jpg","src/Gae.jpg","src/gar.jpg","src/yooc.jpg","src/mo.jpg",};
	public void go() {
		
		frame = new JFrame();
		yutFrame = new JFrame();
		
		
		yutFrame.setSize(600,500);
		yutImage= new ImageIcon(yut[6]);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		coverPanel = new CoverPanel();
		mainPanel = new MainPanel();
		endingPanel = new EndingPanel();
		ending1Panel = new Ending1Panel();
		yutPanel = new YutPanel();
		CardPanel =new JPanel();
		startButton = new JButton(new ImageIcon("src/버튼1.png"));
		continueButton = new JButton(new ImageIcon("src/basicThrow.png"));
		l1 = new JLabel("1p 차례");
	
		
		yutFrame.add(yutPanel);
		CardPanel.setLayout(card);

		
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		continueButton.setContentAreaFilled(false);
		continueButton.setBorderPainted(false);
		continueButton.setFocusPainted(false);
		startButton.setBounds(270, 20, 150, 100);
		continueButton.setBounds(550, 150, 150, 50);
		l1.setBounds(570,50,100,50);
		

		coverPanel.add(startButton);
		mainPanel.add(continueButton);
		mainPanel.add(l1);
		
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
		CardPanel.add("a3",endingPanel);
		CardPanel.add("a4",ending1Panel);
		p1[0]= new Player1("src/말1.png",565,265,60,50);
		p1[1]= new Player1("src/말1.png",625,265,60,50);
		p1[2]= new Player1("src/말1.png",565,320,60,50);
		p1[3]= new Player1("src/말1.png",625,320,60,50);
		p2[0]= new Player2("src/말2.png",565,385,60,50);
		p2[1]= new Player2("src/말2.png",625,385,60,50);
		p2[2]= new Player2("src/말2.png",565,440,60,50);
		p2[3]= new Player2("src/말2.png",625,440,60,50);



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
		p[0][4]=new Position(20,330,1);
		p[0][5]=new Position(20,420,1);
		p[1][5]=new Position(105,420,1);
		p[2][5]=new Position(195,420,1);
		p[3][5]=new Position(285,420,1);
		p[4][5]=new Position(375,420,1);
		p[4][1]=new Position(375,100,50);
		p[3][2]=new Position(305,170,50);
		p[2][3]=new Position(170,285,50);
		p[1][4]=new Position(100,350,50);
		p[1][1]=new Position(100, 100,100);
		p[2][2]=new Position(170,170,100);
		p[3][3]=new Position(305,285,100);
		p[4][4]=new Position(375,350,100);
		p[1][2]=new Position(240,220,100);//가운데

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
		Game6 g = new Game6();
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
	class YutPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(yutImage.getImage(),0,0,600,500,this);
			repaint();
		}
	}
	class CoverPanel extends JPanel{
		public void paintComponent(Graphics g) {	// 이 메소드는 화면이 리프레쉬될때마다 실행됨

			int w = this.getWidth();
			int h= this.getHeight();

			g.drawImage(img1.getImage(), 0,0, w, h,null);
			

		}
	}
	class EndingPanel extends JPanel{
		public void paintComponent(Graphics g) {	// 이 메소드는 화면이 리프레쉬될때마다 실행됨

			int w = this.getWidth();
			int h= this.getHeight();

			g.drawImage(img3.getImage(), 0,0, w, h,null);

		}
	}
	
	class Ending1Panel extends JPanel{
		public void paintComponent(Graphics g) {	// 이 메소드는 화면이 리프레쉬될때마다 실행됨

			int w = this.getWidth();
			int h= this.getHeight();

			g.drawImage(img4.getImage(), 0,0, w, h,null);

		}
	}
	class Time implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timePassed++;
			if(p1Finish==4)
				card.show(CardPanel,"a3");
			mainPanel.repaint();
			if(p2Finish==4)
				card.show(CardPanel, "a4");
			
		}
		
	}
	class YutTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			yutTimePassed++;
			System.out.println(yutTimePassed);
			if(yutTimePassed==2) {
				yutImage= new ImageIcon(yutStop[random]);
				yutT.stop();
				yutTimePassed=0;
				
			}
		
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
						if (p1[i].distance(point) <= 24&&p1[i].select) {
							
							continueButton.setEnabled(true);

							if(random==0)
								p1[i].backDo();
							else if(p1[i].crossChange==1)
								p1[i].move();
							else if(p1[i].crossChange==50)
								p1[i].cross();
							else if(p1[i].crossChange==100)
								p1[i].cross1();
							System.out.println(p1[i].pieceX+"and"+p1[i].pieceY);

							p1[i].finish();
							if(random==4||random==5)
								order=1;
							else {
								order=2;
								l1.setText("2p 차례");
							}
							mainPanel.repaint();


						}

					}
					for(int i =0 ; i<p1.length;i++)
						for(int j=0;j<p2.length;j++) {
							if(p1[i].pX==p2[j].pX&&p1[i].pY==p2[j].pY) {
								p2[j].pieceX=5;
								p2[j].pieceY=5;
								p2[j].pX=p2[j].firstX;
								p2[j].pY=p2[j].firstY;
								p2[j].crossChange=1;
								order=1;
								l1.setText("1p 차례");
							}	
						}
				}
			}
			else if(order==2) {
				if(!turn) {
					for (int i=0; i<p2.length; i++) {
						if (p2[i].distance(point) <= 24&&p2[i].select) {
							
							
							continueButton.setEnabled(true);
							
							
							if(random==0)
								p2[i].backDo();
							else if(p2[i].crossChange==1)
								p2[i].move();
							else if(p2[i].crossChange==50)
								p2[i].cross();
							else if(p2[i].crossChange==100)
								p2[i].cross1();
							
							
							p2[i].finish();
							if(random==4||random==5) 
								order=2;
							else {
								order=1;
								l1.setText("1p 차례");
							}
							mainPanel.repaint();
							

						}
					}
					for(int i = 0;i<p2.length;i++)
						for(int j=0;j<p1.length;j++) {
							if(p2[i].pX==p1[j].pX&&p2[i].pY==p1[j].pY) {
								p1[j].pieceX=5;
								p1[j].pieceY=5;
								p1[j].pX=p1[j].firstX;
								p1[j].pY=p1[j].firstY;
								p1[j].crossChange=1;
								order=2;
								l1.setText("2p 차례");
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
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if(button == startButton){
				card.show(CardPanel,"a2");
				yutFrame.setVisible(true);
				t.start();
				
			}
			if(button == continueButton) {
				random=(int) (Math.random()*6);
				yutImage= new ImageIcon(yut[random]);
				yutT.start();
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
		int tmpX1,tmpX2;
		int tmpY1,tmpY2;;
		int stackCount =1;
		//ArrayList<Integer> tmpX = new ArrayList<>();
		//ArrayList<Integer> tmpY = new ArrayList<>();
		boolean finishScore=false;
		int width;			
		int height;
		int move=0;
		int pieceX=5,pieceY=5;
		boolean select=true;
		int moving=1;
		int crossChange=1;
		public Player1(String img, int x, int y, int width, int height) {
			super(img);
			pX=x;
			firstX=x;
			pY=y;
			firstY=y;
			finishX=380;
			finishY=250;
			this.width=width;
			this.height=height;
		}
		public void finish() {
			if(finishScore&&pieceX==5&&pieceY<5) {
				pX=finishX;
				pY=finishY;
				select=false;
				p1Finish++;
			}
		}
		public void draw(Graphics g) {
			g.drawImage(this.getImage(), pX, pY, width, height, null);
		}
		public double distance (Point p) {
			return Math.sqrt(Math.pow((pX+width/2)-p.x, 2)+ Math.pow((pY+height/2)-p.y, 2));
		}
		/*public void storage() {
			for(int j=tmpX.size()-1;j>=0;j--) {
				tmpX.add(j+1, tmpX.get(j));
			}
			for(int j=tmpY.size()-1;j>=0;j--) {
				tmpY.add(j+1, tmpY.get(j));
				
			}
			tmpX.add(0, pieceX);tmpY.add(0,pieceY);
		}
		 */
		public void backDo() {
			if(!(pX==firstX&&pY==firstY)) {
				pieceX=tmpX1;pieceY=tmpY1;
				tmpX1=tmpX2;tmpY1=tmpY2;
				/*pieceX=tmpX.get(0);
				pieceY=tmpY.get(0);
				tmpX.remove(0);
				tmpY.remove(0);
*/
				pX=p[pieceX][pieceY].x;
				pY=p[pieceX][pieceY].y;
				if(pieceX==5&&pieceY==5)
					finishScore=true;
				
				mainPanel.repaint();
			}
			crossChange=p[pieceX][pieceY].change;
		}
		public void cross() {
			for(int i=0;i<random;i++) {
				if(pieceX==5&&pieceY==0) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=4;pieceY=1;
				}
				else if(pieceX==4&&pieceY==1) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=3;pieceY=2;
				}
				else if(pieceX==3&&pieceY==2) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=1;pieceY=2;
				}
				else if(pieceX==1&&pieceY==2) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=2;pieceY=3;
				}
				else if(pieceX==2&&pieceY==3) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=1;pieceY=4;
				}
				else if(pieceX==1&&pieceY==4) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=0;pieceY=5;
				}
				else if(pieceY==5) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX++;
				}
				pX=p[pieceX][pieceY].x;
				pY=p[pieceX][pieceY].y;
				mainPanel.repaint();
			}
			crossChange=p[pieceX][pieceY].change;
		}
		public void cross1() {
			for(int i=0;i<random;i++) {
				if(pieceX==0&&pieceY==0) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=1;pieceY=1;
				}
				else if(pieceX==1&&pieceY==1) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=2;pieceY=2;
				}
				else if(pieceX==2&&pieceY==2) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=1;pieceY=2;
				}
				else if(pieceX==1&&pieceY==2) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=3;pieceY=3;
				}
				else if(pieceX==3&&pieceY==3) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=4;pieceY=4;
				}
				else if(pieceX==4&&pieceY==4) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=5;pieceY=5;
				}
				else if(pieceY==5&&pieceX==5) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					finishScore=true;
					pieceY--;
				}

				pX=p[pieceX][pieceY].x;
				pY=p[pieceX][pieceY].y;
				mainPanel.repaint();
			}
			crossChange=p[pieceX][pieceY].change;
		}
		public void move() {
			for(int i=0;i<random;i++) {
				if(pieceX==5&&pieceY>0) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceY-=moving;
				}
				else if(pieceY==0&&pieceX>0){
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX-=moving;
				}
				else if(pieceX==0&&pieceY<5){
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceY+=moving;
				}
				else if(pieceY==5&&pieceX<5){
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX+=moving;
				}

				pX=p[pieceX][pieceY].x;
				pY=p[pieceX][pieceY].y;
				if(pieceX==5&&pieceY==5)
					finishScore=true;

				mainPanel.repaint();	
			}
			crossChange=p[pieceX][pieceY].change;
		}
	}
	class Player2 extends ImageIcon {
		int pX;
		int pY;
		int firstX;
		int firstY;
		int finishX;
		int finishY;
		int tmpX1,tmpX2;
		int tmpY1,tmpY2;
		int stackCount =1;
		//ArrayList<Integer> tmpX = new ArrayList<>();
		//ArrayList<Integer> tmpY = new ArrayList<>();
		boolean finishScore=false;
		int width;			
		int height;
		int pieceX=5,pieceY=5;
		boolean select=true;
		int moving=1;
		int crossChange=1;

		public Player2(String img , int x,int y,int width, int height) {
			super(img);
			pX=x;
			firstX=x;
			pY=y;
			firstY=y;
			finishX=130;
			finishY=250;
			
			this.width=width;
			this.height=height;
		}
		public void stack() {
			if(p2[0].pieceX==p2[1].pieceX&&p2[0].pieceY==p2[1].pieceY) {
				p2[0].stackCount=2;p2[1].stackCount=2;
				}
				else if(p2[0].pieceX==p2[2].pieceX&&p2[0].pieceY==p2[2].pieceY) {
					p2[0].stackCount=2;p2[2].stackCount=2;
				}
				else if(p2[0].pieceX==p2[3].pieceX&&p2[0].pieceY==p2[3].pieceY) {
					p2[0].stackCount=2;p2[3].stackCount=2;
				}
				else if(p2[1].pieceX==p2[2].pieceX&&p2[1].pieceY==p2[2].pieceY) {
					p2[1].stackCount=2;p2[2].stackCount=2;
				}
				else if(p2[1].pieceX==p2[3].pieceX&&p2[1].pieceY==p2[3].pieceY) {
					p2[1].stackCount=2;p2[3].stackCount=2;
				}
				else if(p2[2].pieceX==p2[3].pieceX&&p2[2].pieceY==p2[3].pieceY) {
					p2[2].stackCount=2;p2[3].stackCount=2;
				}
				else if((p2[0].pieceX==p2[1].pieceX&&p2[1].pieceX==p2[2].pieceX)&&(p2[0].pieceY==p2[1].pieceY&&p2[1].pieceY==p2[2].pieceY)) {
					p2[0].stackCount=2;p2[1].stackCount=2;p2[2].stackCount=3;
				}
		}
				
		
		public void finish() {
			if(finishScore&&pieceX==5&&pieceY<5) {
				pX=finishX;
				pY=finishY;
				select=false;
				p2Finish++;
			}
		}
		public void draw(Graphics g) {
			g.drawImage(this.getImage(), pX, pY, width, height, null);
		}
		public double distance (Point p) {
			return Math.sqrt(Math.pow((pX+width/2)-p.x, 2)+ Math.pow((pY+height/2)-p.y, 2));
		}
		/*public void storage() {
			for(int j=tmpX.size()-1;j>=0;j--) {
				tmpX.add(j+1, tmpX.get(j));
			}
			for(int j=tmpY.size()-1;j>=0;j--) {
				tmpY.add(j+1, tmpY.get(j));
				
			}
			tmpX.add(0, pieceX);tmpY.add(0,pieceY);
		}*/
		public void backDo() {
			if(!(pX==firstX&&pY==firstY)) {
				pieceX=tmpX1;pieceY=tmpY1;
				tmpX1=tmpX2;tmpY1=tmpY2;
			/*	pieceX=tmpX.get(0);
				pieceY=tmpY.get(0);
				tmpX.remove(0);
				tmpY.remove(0);
*/
				pX=p[pieceX][pieceY].x;
				pY=p[pieceX][pieceY].y;
				if(pieceX==5&&pieceY==5)
					finishScore=true;
				
				mainPanel.repaint();
			}
			crossChange=p[pieceX][pieceY].change;
		}
		public void cross() {
			for(int i=0;i<random;i++) {
				if(pieceX==5&&pieceY==0) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=4;pieceY=1;
				}
				else if(pieceX==4&&pieceY==1) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=3;pieceY=2;
				}
				else if(pieceX==3&&pieceY==2) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=1;pieceY=2;
				}
				else if(pieceX==1&&pieceY==2) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=2;pieceY=3;
				}
				else if(pieceX==2&&pieceY==3) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=1;pieceY=4;
				}
				else if(pieceX==1&&pieceY==4) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=0;pieceY=5;
				}
				else if(pieceY==5) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX++;
				}
				pX=p[pieceX][pieceY].x;
				pY=p[pieceX][pieceY].y;
				mainPanel.repaint();
			}
			crossChange=p[pieceX][pieceY].change;
		}
		public void cross1() {
			for(int i=0;i<random;i++) {
				if(pieceX==0&&pieceY==0) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=1;pieceY=1;
				}
				else if(pieceX==1&&pieceY==1) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=2;pieceY=2;
				}
				else if(pieceX==2&&pieceY==2) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=1;pieceY=2;
				}
				else if(pieceX==1&&pieceY==2) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=3;pieceY=3;
				}
				else if(pieceX==3&&pieceY==3) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=4;pieceY=4;
				}
				else if(pieceX==4&&pieceY==4) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX=5;pieceY=5;
				}
				else if(pieceY==5&&pieceX==5) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					finishScore=true;
					pieceY--;
				}

				pX=p[pieceX][pieceY].x;
				pY=p[pieceX][pieceY].y;
				mainPanel.repaint();
			}
			crossChange=p[pieceX][pieceY].change;
		}
		public void move() {
			for(int i=0;i<random;i++) {
				if(pieceX==5&&pieceY>0) {
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceY-=moving;
				}
				else if(pieceY==0&&pieceX>0){
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX-=moving;
				}
				else if(pieceX==0&&pieceY<5){
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceY+=moving;
				}
				else if(pieceY==5&&pieceX<5){
					tmpX2=tmpX1;tmpY2=tmpY1;
					tmpX1=pieceX;tmpY1=pieceY;
					pieceX+=moving;
				}

				pX=p[pieceX][pieceY].x;
				pY=p[pieceX][pieceY].y;
				if(pieceX==5&&pieceY==5)
					finishScore=true;

				mainPanel.repaint();	
			}
			crossChange=p[pieceX][pieceY].change;
		}

	}

}

