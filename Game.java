import javax.swing.*;
import java.awt.*;

/**
 * Класс отвечает за окончание игры и за выбор режима.
 */
public class Game {
    boolean gameMode;
    private Computer computer;
    private final GamingField gamingField;
    static final boolean COMPUTER = true;
    static final boolean PERSON = false;

    /**
     * Конструктор...
     * @param gameMode - режим игры (игрок или компьютер)
     * @param gamingField - наше игровое поле
     */
    public Game(boolean gameMode, GamingField gamingField) {
        if (gameMode) {
            this.gameMode = COMPUTER;
            this.computer = new Computer(gamingField);
        } else {
            this.gameMode = PERSON;
        }
        this.gamingField = gamingField;
    }

    Computer getComputer(){
        return computer;
    }

    /**
     * При окончании игры появляется еще одно окно с сообщением. В консоль выводятся результаты игры.
     */
    void GameIsOver() {
        JFrame gameOverWindow = new JFrame();
        gameOverWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameOverWindow.setSize(400, 400);
        gameOverWindow.setLayout(new BorderLayout());
        gameOverWindow.setVisible(true);
        JLabel lookInConsole = new JLabel("Результаты игры в консоли!" +
                "\n(Можете закрыть окно игры)");
        gameOverWindow.add(lookInConsole);
        gamingField.CountCircles();
        if (gamingField.getNumberOfBlackCircles() < gamingField.getNumberOfRedCircles()) {
            System.out.println("Красные победили! Количество очков красных: " + gamingField.getNumberOfRedCircles());
        } else if (gamingField.getNumberOfBlackCircles() > gamingField.getNumberOfRedCircles()) {
            System.out.println("Черные победили! Количество очков черных: " + gamingField.getNumberOfBlackCircles());
        } else {
            System.out.println("Ничья!");
        }
    }
}
