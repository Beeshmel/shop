import javax.swing.*;
import java.io.IOException;

public class Game {
	public static void start() throws IOException {
		String input = JOptionPane.showInputDialog("Выберите сложность игры");
		int difficult = Integer.parseInt(input);
		if(difficult >= 1 && difficult <= 6) {
			new Window(difficult);
		} else {
			 JOptionPane.showMessageDialog(null,"Некорректный ввод сложности");
		}
	}

	public static void main(String[] args) throws IOException {
		 start();
	}
}
