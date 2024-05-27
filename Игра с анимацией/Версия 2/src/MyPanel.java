import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel {
	private static final int FRUITS_COUNT = 6;
	private static final int MAX_LIFE_COUNT = 7;
	private static final int REFRESH_RATE = 50;
	private static final int FRUIT_X_MIN_BOUND = 0;
	private static final int FRUIT_X_MAX_BOUND = 570;
	private static final int FRUIT_Y_MIN_BOUND = 0;
	private static final int FRUIT_Y_MAX_BOUND = 270;
	private static final int FRUIT_Y_STEP = 5;
	private static final int LIFE_IMAGE_STEP = 40;
	private final Fruit[] fruits = new Fruit[FRUITS_COUNT];
	private final Random r = new Random();
	private final BufferedImage smile;
	private final Image fon;
	private final Image smile1;
	private final Timer timerFruit;
	private int lifeCount;
	private int score = 0;
	public int catcherX = 100;
	public int catcherY = 220;

	public MyPanel(int difficulty) throws IOException {
		smile = ImageIO.read(new File("images/smile.png"));
		fon = ImageIO.read(new File("images/fon.jpg"));
		smile1 = ImageIO.read(new File("images/smile1.png"));

		lifeCount = MAX_LIFE_COUNT - difficulty;
		for (int i = 0; i < FRUITS_COUNT; i++) {
			fruits[i] = FruitFactory.createFruit(r.nextInt(Window.WIDTH), -r.nextInt(Window.HEIGHT));
		}
		timerFruit = new Timer(REFRESH_RATE, e -> {
			for (int i = 0; i < FRUITS_COUNT; i++) {
				fruits[i].setY(fruits[i].getY() + FRUIT_Y_STEP);
				if (fruits[i].getY() > FRUIT_Y_MAX_BOUND) {
					lifeCount--;
					fruits[i].setY(FRUIT_Y_MIN_BOUND);
					fruits[i].setX(r.nextInt(FRUIT_X_MAX_BOUND));
				}
			}
			repaint();
		});

		timerFruit.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(fon, 0, 0, null);
		g.drawImage(smile, catcherX, catcherY, null);
		for (int i = 0; i < FRUITS_COUNT; i++) {
			if (fruits[i].getX() > catcherX - fruits[i].getImage().getWidth() / 2 &&
					fruits[i].getX() < catcherX + smile.getWidth() &&
					fruits[i].getY() > catcherY - fruits[i].getImage().getHeight() / 2 &&
					fruits[i].getY() < FRUIT_Y_MAX_BOUND) {
				fruits[i].setY(-r.nextInt(FRUIT_Y_MAX_BOUND + fruits[i].getImage().getWidth() / 2));
				fruits[i].setX(r.nextInt(FRUIT_X_MAX_BOUND));
				score++;
			}
		    
			g.drawImage(fruits[i].getImage(), fruits[i].getX() ,fruits[i].getY(),null);
		}

		for (int i = 0; i < lifeCount; i++) {
			g.drawImage(smile1, i * LIFE_IMAGE_STEP,0, null);
		}

		if (lifeCount == 0) {
			try {
				timerFruit.stop();
				stop();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void stop() throws IOException {
		 JOptionPane.showMessageDialog(null,"Вы набрали " + score + " очков.");
		 System.exit(0);
	}
}

