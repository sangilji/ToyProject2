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
	JLabel timeLabel;				// ����ð��� �����
	int timePassed=0;				// ����� �ð� ��
	Timer timer = new Timer(1000, new TimeListener());
	DrawPanel drawPanel;			// ������ �̷������ �г�
	boolean started = false;		// ���۹�ư�� ���ȴ����� ��Ÿ��. �������� ���̻� �簢�� ��������.
	ArrayList<Rectangle> list = new ArrayList<>();	// ȭ�鿡 ��Ÿ�� ���簢���� ������ �ִ� ����Ʈ
	ArrayList<Circle> list2 = new ArrayList<>();	// ȭ�鿡 ��Ÿ�� ��� ���� ������ �ִ� ����Ʈ
	Point p1; 						// ���콺�� ���� �гο����� ��ġ
	Point p2; 						// ���콺�� �巹�׵� �� �гο����� ��ġ

	public static void main(String[] args) {
		Final gui = new Final();
		gui.go();
	}

	public void go() {
		//GUI ����
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();

		startButton = new JButton("����");
		endButton = new JButton("����");
		JPanel buttonsPanel1 = new JPanel();
		buttonsPanel1.add(startButton);
		buttonsPanel1.add(endButton);

		autoRectButton = new JButton("�ڵ� �簢�� ����");
		autoCircleButton = new JButton("�ڵ� �� ����");
		timeLabel = new JLabel ("����ð� : " + timePassed);
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
		
		// ��ư�� �����ʸ� ������
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
			timeLabel.setText("����ð� : " + timePassed);
			drawPanel.repaint();			// ��ü ȭ���� �ٽñ׸�. drawPanel�� paintComponent �޼ҵ尡 �ڵ� ȣ���
		}
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button == startButton)	{	// start ��ư�� �������� �̸� ����ϰ� �ð� ����
				started = true;
				timer.start();
			}
			else if (button == endButton){	// end ��ư�� ������ ���α׷� ����
				frame.dispose();
				System.exit(0);
			}
			else if (button == autoRectButton){
				doAutoRectGeneration(3);		// �ڵ� �簢�� ������ ��� �簢�� 3�� ����� ��
			}
			else if (button == autoCircleButton){
				doAutoCircleGeneration(3);		// �ڵ� �� ������ ��� ���� 3�� ����� ��
			}
		}
		// �ڵ����� �簢�� ����
		void doAutoRectGeneration(int n) {
			if (started) return;			// ���� start ��ư�� �̹� ������ ����� �ƹ� �۵� �� ��
			int width = drawPanel.getWidth();
			int height = drawPanel.getHeight();
			for (int i=0; i<n; i++) {
				int w = (int)(Math.random()*40 + 10);		// ũ��� 10~50
				int h = (int)(Math.random()*40 + 10);		// ũ��� 10~50
				list.add(new Rectangle((int) (Math.random()*(width-2*w)+w),(int) (Math.random()*(height-2*h)+h),
						w, h, Color.BLUE));
			}
			drawPanel.repaint();			// ��ü ȭ���� �ٽñ׸�. drawPanel�� paintComponent �޼ҵ尡 �ڵ� ȣ���
		}
		// �ڵ����� �� ����
		void doAutoCircleGeneration(int n) {
			if (started) return;			// ���� start ��ư�� �̹� ������ ����� �ƹ� �۵� �� ��
			int width = drawPanel.getWidth();
			int height = drawPanel.getHeight();
			for (int i=0; i<n; i++) {
				int r = (int)(Math.random()*20 + 5);		// ������ ũ��� 5~25
				list2.add(new Circle((int) (Math.random()*(width-r)),(int) (Math.random()*(height-r)),
						r, Color.BLUE));
			}
			drawPanel.repaint();			// ��ü ȭ���� �ٽñ׸�. drawPanel�� paintComponent �޼ҵ尡 �ڵ� ȣ���
		}
	}

	// �г��� ���� �� ���콺 ���� �̺�Ʈ�� ó���� �� �ִ� �ڵ鷯�� �ۿ�
	class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
		public DrawPanel() {
			this.addMouseListener(this);			// ���콺 Ŭ�� �ڵ鷯 ���
			this.addMouseMotionListener(this);		// ���콺�� ���� ���ۿ� ���� �ڵ鷯 ���
		}
		public void paintComponent(Graphics g) {	// �� �޼ҵ�� ȭ���� ���������ɶ����� �����
			super.paintComponent (g);
			int w = this.getWidth();
			int h= this.getHeight();
			g.setColor(Color.white);
			g.fillRect(0,0,w,h);

			// �簢������ ȭ�鿡 �ѷ� ��
			for (Rectangle r : list) {
				r.draw(g);
			}
			// ������ ȭ�鿡 �ѷ� ��
			for (Circle c : list2) {
				c.draw(g);
			}

			if (!started) {							// start ��ư�� ������ �ʾҰ�, p1, p2�� �����Ѵٸ� �̴� �巡�� �ϰ� �ִ� ��Ȳ��. �ӽ� �簢�� �׸�
				g.setColor (Color.BLUE);
				if (p1 != null && p2 != null)
					g.fillRect(p1.x, p1.y, p2.x-p1.x, p2.y-p1.y);
			}
			else {						// ��� �簢���� ����̸� ���� ����
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
			p1 = arg0.getPoint();					// ���콺�� ���� ���� ��ǥ�� Ȯ��

			if (started) {							// ���� start��ư�� ������ ��Ȳ�̶�� �̿� �ش��ϴ� �簢�� �Ǵ� ���� ���� ��� ���� ���� �ٲ�
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
			this.repaint();						// ȭ�� ��ü�� �ٽñ׸�
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if (!started)							// ���� start��ư�� ������ ���� ���¶�� �̴� p1�� p2�� ������ �簢���� ������ �� 
				if (p1 != null && p2 != null)
					list.add(new Rectangle(p1.x, p1.y, p2.x-p1.x, p2.y-p1.y, Color.BLUE));
		}
		@Override
		public void mouseDragged(MouseEvent arg0) {
			if (!started) {							// ���� start��ư�� ������ ���� ���¶�� �̴� p1�� �������� �簢���� ����� �ִ� ��	
				p2 = arg0.getPoint();	
				this.repaint();						// p1�� p2 ���̿����� �׸� �׸���		
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

// �簢���� �𵨸��� Ŭ����. ��ǥ, ���� ����, �������� ����
class Rectangle {
	int pX;				// �簢���� X��ǥ
	int pY;				// �簢���� y��ǥ
	int width;			// �簢���� ����
	int height;			// �簢���� ����
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

	// Point p�� �簢�� ������ ��ǥ������ �Ǵ�
	public boolean inside (Point p) {
		return (p.x>= this.pX && p.x <= this.pX+this.width) && (p.y>= this.pY && p.y <= this.pY+this.height);
	}
}

//���� �𵨸��� Ŭ����. ���� ��ǥ, ������, �������� ����
class Circle {
	int pX;				// ���� ������ X��ǥ
	int pY;				// ���� ������ y��ǥ
	int radius;			// ���� ������
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

	// Point p�� �簢�� ������ ��ǥ������ �Ǵ�
	public boolean inside (Point p) {
		return (p.distance(new Point(pX, pY)) <= radius);
	}
}

