package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel {
    int x = 400;
    int y = 220;
    Image smile;
    Image fon;
    Image smile1;
    int slogn;
    Timer timerFruit;
    List<Fruit> fruits;


    Random r = new Random();
    int time;

    int count;//life col
    int ball = 0;//ochki
    int[] xf = new int[6];
    int[] yf = new int[6];
    int col1;


    public MyPanel(int slogn, Image fon) throws IOException {

        this.fon = fon;
        smile = ImageIO.read(new File("images/smile.png"));
        smile1 = ImageIO.read(new File("images/smile1.png"));

        int col = slogn;
        count = 7 - slogn;
        for (int i = 0; i < col; i++) {
            xf[i] = r.nextInt(590);
            yf[i] = -r.nextInt(300);

        }
        col1 = col;
        fruits = Stream.of(
                        new Fruit(1L, ImageIO.read(new File("images/ananas.png"))),
                        new Fruit(2L, ImageIO.read(new File("images/apple.png"))),
                        new Fruit(3L, ImageIO.read(new File("images/banan.png"))),
                        new Fruit(4L, ImageIO.read(new File("images/grusha.png"))),
                        new Fruit(5L, ImageIO.read(new File("images/orange.png"))),
                        new Fruit(6L, ImageIO.read(new File("images/persik.png"))))
                .collect(Collectors.toList());

        timerFruit = new Timer(50, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < col; i++) {
                    yf[i] += 5;
                    if (yf[i] > 270) {
                        count--;
                        yf[i] = 0;
                        xf[i] = r.nextInt(570);
                    }
                }

                repaint();

            }
        });

        timerFruit.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(fon, 0, 0, null);
        g.drawImage(smile, x, y, null);
        for (int i = 0; i < col1; i++) {
            if (xf[i] > x - 30 && xf[i] < x + 128 && yf[i] > y - 40 && yf[i] < 270) {
                yf[i] = -r.nextInt(300);
                xf[i] = r.nextInt(570);
                ball++;

            }

            g.drawImage(fruits.get(i).getImage(), xf[i], yf[i], null);


        }

        for (int i = 0; i <= count; i++) {
            g.drawImage(smile1, i * 40, 0, null);

        }
        if (count == 0)
            try {
                timerFruit.stop();
                stop();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


    }

    private void stop() throws IOException {

        JOptionPane.showMessageDialog(null, "Вы набрали " + ball + "очков.");

        System.exit(0);


    }
}

