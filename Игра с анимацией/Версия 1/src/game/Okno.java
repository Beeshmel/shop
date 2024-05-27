package game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;

public class Okno extends JFrame {
    int slogn;
    MyPanel panel;
    Image fon;
    int height;
    int width;

    class MyKey extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == 39) {
                panel.x += 30;
                if (panel.x > width * 0.9) {
                    panel.x = 0;
                }
            }
            if (key == 37) {
                panel.x -= 30;
                if (panel.x < -width * 0.2) {
                    panel.x = (int) (width * 0.85);
                }
            }
        }
    }

    public Okno(int s) throws IOException {
        fon = ImageIO.read(new File("images/fon.jpg"));
//        BufferedImage bufferedImage = ImageIO.read(new File("images/fon.jpg"));
        BufferedImage bufferedImage = (BufferedImage) fon;
        height = bufferedImage.getHeight();
        width = bufferedImage.getWidth();
        addKeyListener(new MyKey());
        setBounds(200, 200, width, height);
        setTitle("Моя игра");

        Container c = getContentPane();
        panel = new MyPanel(s, fon);
        c.add(panel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}

