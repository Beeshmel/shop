import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;

public class Window extends JFrame {
	public static final int WIDTH = 596;
	public static final int HEIGHT = 380;
	private static final int X = 200;
	private static final int Y = 200;
	private static final int SHIFT_STEP = 30;
	private final MyPanel panel;
	
	class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_RIGHT) {
				panel.catcherX += SHIFT_STEP;
				if (panel.catcherX > WIDTH * 0.9) {
					panel.catcherX = 0;
				}
			}
			if(key == KeyEvent.VK_LEFT) {
				panel.catcherX -= SHIFT_STEP;
				if (panel.catcherX < -WIDTH * 0.2) {
					panel.catcherX = (int)(WIDTH * 0.85);
				}
			}
		}
	}
	
	public Window(int s) throws IOException {
		addKeyListener(new MyKeyAdapter());
		setBounds(X, Y, WIDTH, HEIGHT);
		setTitle("Моя игра");
		
		Container c = getContentPane();
		panel = new MyPanel(s);
		c.add(panel);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

