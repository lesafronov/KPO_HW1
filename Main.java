import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GamingField field = new GamingField();
        Game game;
        int input;
        do {
            System.out.println("Введите 1, если хотите сыграть с компьютером. Введите 0, если хотите игрок против игрока:");
            input = scanner.nextInt();
            if (input != 0 && input != 1) {
                System.out.println("Неверный ввод!");
            }
        } while (input != 0 && input != 1);
        if (input == 0) {
            game = new Game(Game.PERSON, field);
        } else {
            game = new Game(Game.COMPUTER, field);
        }
        System.out.println("Сейчас у вас открылось новое окно, в открывшемся окне происходит сам процесс игры");
        JFrame window = new JFrame("Реверси");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        Graphic graphic = new Graphic(field, game);
        window.add(graphic);
    }
}