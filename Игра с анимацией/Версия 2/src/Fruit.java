import java.awt.image.BufferedImage;

public class Fruit {
    private final BufferedImage image;
    private int x;
    private int y;

    public Fruit(BufferedImage image, int initialX, int initialY) {
        this.image = image;
        this.x = initialX;
        this.y = initialY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
