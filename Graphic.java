import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Данный класс отвечает за графическую реализацию игры в окне JFrame.
 * Используются базовые графические библиотеки языка Java.
 */
public class Graphic extends JComponent {
    private final GamingField gamingField;
    private final Game game;

    /**
     * Конструктор...
     * @param gamingField - наше игровое поле
     * @param game - используется для завершения игры
     */
    public Graphic(GamingField gamingField, Game game) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        this.gamingField = gamingField;
        this.game = game;
    }

    /**
     * При нажатии мышью в соответствующую ячейку срабатывает проверка на легитимность шара в этой ячейке.
     *
     * @param mouseEvent the mouse event
     */
    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            int i = (int) ((float) x / getWidth() * 8);
            int j = (int) ((float) y / getHeight() * 8);
            if (gamingField.getField()[i][j] == GamingField.FIELD_EMPTY
                    && (gamingField.CheckHorizontalClosedLine(i, j, gamingField.isRedMove, true)
                    | gamingField.CheckVerticalClosedLine(i, j, gamingField.isRedMove, true)
                    | gamingField.CheckDiagonalClosedLine(i, j, gamingField.isRedMove, true))) {
                gamingField.getField()[i][j] = gamingField.isRedMove ? GamingField.RED_CIRCLE : GamingField.BLACK_CIRCLE;
                gamingField.isRedMove = !gamingField.isRedMove;
                repaint();
                if (game.gameMode == Game.COMPUTER) {
                    game.getComputer().DoBestMove();
                }
                if (gamingField.isBoardFull() || gamingField.isOnlyOneColorLeft() || !gamingField.isAvailableToMakeMove()) {
                    game.GameIsOver();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        DrawField(graphics);
        DrawCurrentField(graphics);
    }

    /**
     * Расчерчивает поле игры.
     * @param graphics
     */
    void DrawField(Graphics graphics) {
        int width = getWidth();
        int height = getHeight();
        int lineWidth = width / 8;
        int lineHeight = height / 8;
        graphics.setColor(Color.black);
        for (int i = 1; i < 8; i++) {
            graphics.drawLine(0, lineHeight * i, width, lineHeight * i);
            graphics.drawLine(lineWidth * i, 0, lineWidth * i, height);
        }
    }

    /**
     * Рисует красные и черные шары в свободных слотах.
     * @param xCord - координата по оси X
     * @param yCord
     * @param graphics
     * @param isRed - указание на цвет шара
     */
    void DrawRedOrBlackCircle(int xCord, int yCord, Graphics graphics, boolean isRed) {
        int widthDividedBy8 = getWidth() / 8;
        int heightDividedBy8 = getHeight() / 8;
        int x = xCord * widthDividedBy8;
        int y = yCord * heightDividedBy8;
        gamingField.CountCircles();
        if (isRed) {
            graphics.setColor(Color.RED);
            graphics.drawString(String.valueOf(gamingField.getNumberOfRedCircles()), x + 20, y + 25);
        } else {
            graphics.setColor(Color.BLACK);
            graphics.drawString(String.valueOf(gamingField.getNumberOfBlackCircles()), x + 20, y + 25);
        }
        graphics.drawOval(x, y, widthDividedBy8, heightDividedBy8);
    }

    /**
     * Отрисовывает поле исходя из текущей ситуации.
     * @param graphics
     */
    void DrawCurrentField(Graphics graphics) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (gamingField.getField()[i][j] == GamingField.RED_CIRCLE) {
                    DrawRedOrBlackCircle(i, j, graphics, true);
                } else if (gamingField.getField()[i][j] == GamingField.BLACK_CIRCLE) {
                    DrawRedOrBlackCircle(i, j, graphics, false);
                }
            }
        }
    }
}
