import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class FruitFactory {
    private static final String[] IMAGES = {"ananas.png", "apple.png", "banan.png", "grusha.png", "orange.png", "persik.png"};

    public static Fruit createFruit(int x, int y) {
        try {
            return new Fruit(ImageIO.read(new File("images" + File.separator + IMAGES[new Random().nextInt(IMAGES.length)])), x, y);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
