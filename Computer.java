/**
 * Компьютерный игрок.
 */
public class Computer {
    private final GamingField field;
    static final boolean COMPUTER_IS_ALWAYS_BLACK = false;

    public Computer(GamingField field) {
        this.field = field;
    }

    /**
     * Считает какое количество очков прибавится в наилучшем случае при всех возможных ходах.
     * @return
     */
    int CountBestMove() {
        int maxValueClosedBlackCircles = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                GamingField copyGamingField = field.Copy();
                int ValueOfBlackBefore = CountBlackCirclesValue(copyGamingField);
                if (copyGamingField.getField()[i][j] == GamingField.FIELD_EMPTY) {
                    if (copyGamingField.CheckDiagonalClosedLine(i, j, COMPUTER_IS_ALWAYS_BLACK, true)
                            | copyGamingField.CheckHorizontalClosedLine(i, j, COMPUTER_IS_ALWAYS_BLACK, true)
                            | copyGamingField.CheckVerticalClosedLine(i, j, COMPUTER_IS_ALWAYS_BLACK, true)) {
                        int differenceValueOfBlack = CountBlackCirclesValue(copyGamingField) - ValueOfBlackBefore;
                        if (maxValueClosedBlackCircles < differenceValueOfBlack) {
                            maxValueClosedBlackCircles = differenceValueOfBlack;
                        }
                    }
                }
            }
        }
        return maxValueClosedBlackCircles;
    }

    /**
     * Делает ход который соответствует получению наибольшего количества выгоды.(пользуясь прошлым методом)
     */
    void DoBestMove() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                GamingField copyGamingField = field.Copy();
                int ValueOfBlackBefore = CountBlackCirclesValue(copyGamingField);
                if (copyGamingField.getField()[i][j] == GamingField.FIELD_EMPTY) {
                    if (copyGamingField.CheckDiagonalClosedLine(i, j, COMPUTER_IS_ALWAYS_BLACK, true)
                            | copyGamingField.CheckHorizontalClosedLine(i, j, COMPUTER_IS_ALWAYS_BLACK, true)
                            | copyGamingField.CheckVerticalClosedLine(i, j, COMPUTER_IS_ALWAYS_BLACK, true)) {
                        int differenceValueOfBlack = CountBlackCirclesValue(copyGamingField) - ValueOfBlackBefore;
                        if (differenceValueOfBlack == CountBestMove()) {
                            field.CheckVerticalClosedLine(i, j, COMPUTER_IS_ALWAYS_BLACK, true);
                            field.CheckHorizontalClosedLine(i, j, COMPUTER_IS_ALWAYS_BLACK, true);
                            field.CheckDiagonalClosedLine(i, j, COMPUTER_IS_ALWAYS_BLACK, true);
                            field.getField()[i][j] = GamingField.BLACK_CIRCLE;
                            field.isRedMove = !field.isRedMove;
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Метод подсчитывает ценность всего поля компьютера.(За кромочные клетки - 2 очка,за обычные - 1 очко)
     * @param field - игровое поле на котором идет подсчет
     * @return
     */
    static int CountBlackCirclesValue(GamingField field) {
        int blackValueSum = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field.getField()[i][j] == GamingField.BLACK_CIRCLE) {
                    if (i == 0 || i == 7 || j == 0 || j == 7) {
                        blackValueSum += 2;
                    } else {
                        blackValueSum++;
                    }
                }

            }
        }
        return blackValueSum;
    }
}

