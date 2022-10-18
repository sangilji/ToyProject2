import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Final {
	JFrame frame;
	JButton startButton, endButton, autoRectButton, autoCircleButton;
	JLabel timeLabel;				// 경과시간을 출력함
	int timePassed=0;				// 경과한 시간 값
	Timer timer = new Timer(1000, new TimeListener());
	DrawPanel drawPanel;			// 게임이 이루어지는 패널
	boolean started = false;		// 시작버튼이 눌렸는지를 나타냄. 눌렸으면 더이상 사각형 생성못함.
	ArrayList<Rectangle> list = new ArrayList<>();	// 화면에 나타날 모든사각형을 가지고 있는 리스트
	ArrayList<Circle> list2 = new ArrayList<>();	// 화면에 나타날 모든 원을 가지고 있는 리스트
	Point p1; 						// 마우스가 눌린 패널에서의 위치
	Point p2; 						// 마우스가 드레그될 때 패널에서의 위치

	public static void main(String[] args) {
		Final gui = new Final();
		gui.go();
	}

	public void go() {
		//GUI 생성
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();

		startButton = new JButton("시작");
		endButton = new JButton("종료");
		JPanel buttonsPanel1 = new JPanel();
		buttonsPanel1.add(startButton);
		buttonsPanel1.add(endButton);

		autoRectButton = new JButton("자동 사각형 생성");
		autoCircleButton = new JButton("자동 원 생성");
		timeLabel = new JLabel ("경과시간 : " + timePassed);
		JPanel buttonsPanel2 = new JPanel();
		buttonsPanel2.add(autoRectButton);
		buttonsPanel2.add(autoCircleButton);
		buttonsPanel2.add(timeLabel);
		
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.getContentPane().add(BorderLayout.NORTH, buttonsPanel1);
		frame.getContentPane().add(BorderLayout.SOUTH, buttonsPanel2);

		drawPanel.setBackground(Color.white);
		frame.setBackground(Color.white);
		drawPanel.setPreferredSize(new Dimension(500, 500));

		frame.pack();
		frame.setVisible(true);
		
		// 버튼에 리스너를 장착함
		ButtonListener bl = new ButtonListener();
		startButton.addActionListener(bl);
		endButton.addActionListener(bl);
		autoRectButton.addActionListener(bl);
		autoCircleButton.addActionListener(bl);
	}
	
	class TimeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			timePassed++;
			timeLabel.setText("경과시간 : " + timePassed);
			drawPanel.repaint();			// 전체 화면을 다시그림. drawPanel의 paintComponent 메소드가 자동 호출됨
		}
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button == startButton)	{	// start 버튼이 눌렸으면 이를 등록하고 시계 시작
				started = true;
				timer.start();
			}
			else if (button == endButton){	// end 버튼이 눌리면 프로그램 종료
				frame.dispose();
				System.exit(0);
			}
			else if (button == autoRectButton){
				doAutoRectGeneration(3);		// 자동 사각형 생성인 경우 사각형 3개 만들어 냄
			}
			else if (button == autoCircleButton){
				doAutoCircleGeneration(3);		// 자동 원 생성인 경우 원을 3개 만들어 냄
			}
		}
		// 자동으로 사각형 생성
		void doAutoRectGeneration(int n) {
			if (started) return;			// 만약 start 버튼이 이미 눌려진 경우라면 아무 작동 안 함
			int width = drawPanel.getWidth();
			int height = drawPanel.getHeight();
			for (int i=0; i<n; i++) {
				int w = (int)(Math.random()*40 + 10);		// 크기는 10~50
				int h = (int)(Math.random()*40 + 10);		// 크기는 10~50
				list.add(new Rectangle((int) (Math.random()*(width-2*w)+w),(int) (Math.random()*(height-2*h)+h),
						w, h, Color.BLUE));
			}
			drawPanel.repaint();			// 전체 화면을 다시그림. drawPanel의 paintComponent 메소드가 자동 호출됨
		}
		// 자동으로 원 생성
		void doAutoCircleGeneration(int n) {
			if (started) return;			// 만약 start 버튼이 이미 눌려진 경우라면 아무 작동 안 함
			int width = drawPanel.getWidth();
			int height = drawPanel.getHeight();
			for (int i=0; i<n; i++) {
				int r = (int)(Math.random()*20 + 5);		// 반지름 크기는 5~25
				list2.add(new Circle((int) (Math.random()*(width-r)),(int) (Math.random()*(height-r)),
						r, Color.BLUE));
			}
			drawPanel.repaint();			// 전체 화면을 다시그림. drawPanel의 paintComponent 메소드가 자동 호출됨
		}
	}

	// 패널의 정의 및 마우스 관련 이벤트를 처리할 수 있는 핸들러로 작용
	class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
		public DrawPanel() {
			this.addMouseListener(this);			// 마우스 클릭 핸들러 등록
			this.addMouseMotionListener(this);		// 마우스의 각종 동작에 대한 핸들러 등록
		}
		public void paintComponent(Graphics g) {	// 이 메소드는 화면이 리프레쉬될때마다 실행됨
			super.paintComponent (g);
			int w = this.getWidth();
			int h= this.getHeight();
			g.setColor(Color.white);
			g.fillRect(0,0,w,h);

			// 사각형들을 화면에 뿌려 줌
			for (Rectangle r : list) {
				r.draw(g);
			}
			// 원들을 화면에 뿌려 줌
			for (Circle c : list2) {
				c.draw(g);
			}

			if (!started) {							// start 버튼이 눌리지 않았고, p1, p2가 존재한다면 이는 드래그 하고 있는 상황임. 임시 사각형 그림
				g.setColor (Color.BLUE);
				if (p1 != null && p2 != null)
					g.fillRect(p1.x, p1.y, p2.x-p1.x, p2.y-p1.y);
			}
			else {						// 모든 사각형이 흰색이면 게임 종료
				int count = 0;
				for (Rectangle r : list) {
					if (r.c == Color.WHITE)
						count++;
				}
				for (Circle c : list2) {
					if (c.c == Color.WHITE)
						count++;
				}				
				if (count==list.size()+list2.size()) {
					frame.dispose();
					System.exit(0);
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			p1 = arg0.getPoint();					// 마우스가 눌린 곳의 좌표를 확보

			if (started) {							// 만약 start버튼이 눌려진 상황이라면 이에 해당하는 사각형 또는 원이 있을 경우 이의 색을 바꿈
				for (Rectangle r : list) {
					if (r.inside(p1)) {
						if (r.c==Color.BLUE)
							r.c=Color.YELLOW;
						else if (r.c==Color.YELLOW)
							r.c=Color.RED;
						else if (r.c==Color.RED)
							r.c=Color.WHITE;
					}
				}

				for (Circle c : list2) {
					if (c.inside(p1)) {
						if (c.c==Color.BLUE)
							c.c=Color.YELLOW;
						else if (c.c==Color.YELLOW)
							c.c=Color.RED;
						else if (c.c==Color.RED)
							c.c=Color.WHITE;
					}
				}
			}
			this.repaint();						// 화면 전체를 다시그림
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if (!started)							// 만약 start버튼이 눌리지 않은 상태라면 이는 p1과 p2로 형성된 사각형을 만들라는 것 
				if (p1 != null && p2 != null)
					list.add(new Rectangle(p1.x, p1.y, p2.x-p1.x, p2.y-p1.y, Color.BLUE));
		}
		@Override
		public void mouseDragged(MouseEvent arg0) {
			if (!started) {							// 만약 start버튼이 눌리지 않은 상태라면 이는 p1을 기준으로 사각형을 만들고 있는 것	
				p2 = arg0.getPoint();	
				this.repaint();						// p1과 p2 사이에서의 그림 그리기		
			}
		}
		@Override
		public void mouseMoved(MouseEvent arg0) {}
		@Override
		public void mouseClicked(MouseEvent arg0) {}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
	}
}

// 사각형을 모델링한 클래스. 좌표, 변의 길이, 색상으로 구성
class Rectangle {
	int pX;				// 사각형의 X좌표
	int pY;				// 사각형의 y좌표
	int width;			// 사각형의 넓이
	int height;			// 사각형의 높이
	Color c;

	public Rectangle(int x, int y, int width, int height, Color c) {
		pX=x;
		pY=y;
		this.width = width;
		this.height = height;
		this.c = c;
	}
	public void move(int x, int y) {
		pX += x;
		pY += y;
	}
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(pX, pY, width, height);
	}

	// Point p가 사각형 안쪽의 좌표인지를 판단
	public boolean inside (Point p) {
		return (p.x>= this.pX && p.x <= this.pX+this.width) && (p.y>= this.pY && p.y <= this.pY+this.height);
	}
}

//원을 모델링한 클래스. 중점 좌표, 반지름, 색상으로 구성
class Circle {
	int pX;				// 원의 중점의 X좌표
	int pY;				// 원의 중점의 y좌표
	int radius;			// 원의 반지름
	Color c;

	public Circle(int x, int y, int radius, Color c) {
		pX=x;
		pY=y;
		this.radius = radius;
		this.c = c;
	}
	public void move(int x, int y) {
		pX += x;
		pY += y;
	}
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(pX-radius, pY-radius, 2*radius, 2*radius);
	}

	// Point p가 사각형 안쪽의 좌표인지를 판단
	public boolean inside (Point p) {
		return (p.distance(new Point(pX, pY)) <= radius);
	}
}

