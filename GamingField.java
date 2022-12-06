/**
 * Класс содержит в себе всю внутреннюю логику ходов в игре.
 * Реализует игровое поле и расположение шаров внутри него.
 */
public class GamingField {
    private final int[][] field;
    private int numberOfRedCircles;
    private int numberOfBlackCircles;
    static final int FIELD_EMPTY = 0;
    static final int RED_CIRCLE = 1;
    static final int BLACK_CIRCLE = 2;
    /**
     * Очередность ходов.
     */
    boolean isRedMove;

    public GamingField() {
        isRedMove = true;
        field = new int[8][8];
        ResetField();
        numberOfRedCircles = 2;
        numberOfBlackCircles = 2;
    }

    int getNumberOfRedCircles() {
        return numberOfRedCircles;
    }

    int getNumberOfBlackCircles() {
        return numberOfBlackCircles;
    }

    int[][] getField() {
        return field;
    }

    /**
     * Считает актуальные значения для полей в которых содержится количество шаров каждого цвета.
     */
    void CountCircles() {
        numberOfBlackCircles = 0;
        numberOfRedCircles = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == BLACK_CIRCLE) {
                    numberOfBlackCircles++;
                }
                if (field[i][j] == RED_CIRCLE) {
                    numberOfRedCircles++;
                }
            }
        }
    }

    /**
     * Обновление поля(начальная ситуация на поле).
     */
    void ResetField() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                field[i][j] = FIELD_EMPTY;
            }
        }
        field[3][3] = BLACK_CIRCLE;
        field[4][4] = BLACK_CIRCLE;
        field[3][4] = RED_CIRCLE;
        field[4][3] = RED_CIRCLE;
    }

    /**
     * Проверяет по горизонтали, какое количество вражеских шаров закрывает постановка шара в ячейку [x][y].
     * @param x - координата нажатия
     * @param y - координата нажатия
     * @param isRedTurn
     * @param changeSomething - параметр отвечающий за необходимость менять данные на игровом поле
     *                        (при false лишь возвращает булевское значение).
     * @return
     */
    boolean CheckHorizontalClosedLine(int x, int y, boolean isRedTurn, boolean changeSomething) {
        boolean isSomethingChanged = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isRedTurn) {
                    if (field[x][j] == RED_CIRCLE) {
                        if (j < y) {
                            int num = j + 1;
                            int counter = 1;
                            while (field[x][num] == BLACK_CIRCLE) {
                                counter++;
                                num++;
                            }
                            if (counter != 1 && counter == y - j) {
                                num = j + 1;
                                if (changeSomething) {
                                    while (field[x][num] == BLACK_CIRCLE) {
                                        field[x][num] = RED_CIRCLE;
                                        num++;
                                    }
                                }
                                isSomethingChanged = true;
                            }
                        } else if (y < j) {
                            int num = y + 1;
                            int counter = 1;
                            while (field[x][num] == BLACK_CIRCLE) {
                                counter++;
                                num++;
                            }
                            if (counter != 1 && counter == j - y) {
                                num = y + 1;
                                if (changeSomething) {
                                    while (field[x][num] == BLACK_CIRCLE) {
                                        field[x][num] = RED_CIRCLE;
                                        num++;
                                    }
                                }
                                isSomethingChanged = true;
                            }
                        }
                    }
                } else {
                    if (field[x][j] == BLACK_CIRCLE) {
                        if (j < y) {
                            int num = j + 1;
                            int counter = 1;
                            while (field[x][num] == RED_CIRCLE) {
                                counter++;
                                num++;
                            }
                            if (counter != 1 && counter == y - j) {
                                num = j + 1;
                                if (changeSomething) {
                                    while (field[x][num] == RED_CIRCLE) {
                                        field[x][num] = BLACK_CIRCLE;
                                        num++;
                                    }
                                }
                                isSomethingChanged = true;
                            }
                        } else if (y < j) {
                            int num = y + 1;
                            int counter = 1;
                            while (field[x][num] == RED_CIRCLE) {
                                counter++;
                                num++;
                            }
                            if (counter != 1 && counter == j - y) {
                                num = y + 1;
                                if (changeSomething) {
                                    while (field[x][num] == RED_CIRCLE) {
                                        field[x][num] = BLACK_CIRCLE;
                                        num++;
                                    }
                                }
                                isSomethingChanged = true;
                            }
                        }
                    }
                }
            }
        }
        return isSomethingChanged;
    }

    /**
     * Аналогично предыдущему методу, только в данном случае работает с вертикалью.
     * @param x
     * @param y
     * @param isRedTurn
     * @param changeSomething
     * @return
     */
    boolean CheckVerticalClosedLine(int x, int y, boolean isRedTurn, boolean changeSomething) {
        boolean isSomethingChanged = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isRedTurn) {
                    if (field[i][y] == RED_CIRCLE) {
                        if (i < x) {
                            int num = i + 1;
                            int counter = 1;
                            while (field[num][y] == BLACK_CIRCLE) {
                                counter++;
                                num++;
                            }
                            if (counter != 1 && counter == x - i) {
                                num = i + 1;
                                if (changeSomething) {
                                    while (field[num][y] == BLACK_CIRCLE) {
                                        field[num][y] = RED_CIRCLE;
                                        num++;
                                    }
                                }
                                isSomethingChanged = true;
                            }
                        } else if (x < i) {
                            int num = x + 1;
                            int counter = 1;
                            while (field[num][y] == BLACK_CIRCLE) {
                                counter++;
                                num++;
                            }
                            if (counter != 1 && counter == i - x) {
                                num = x + 1;
                                if (changeSomething) {
                                    while (field[num][y] == BLACK_CIRCLE) {
                                        field[num][y] = RED_CIRCLE;
                                        num++;
                                    }
                                }
                                isSomethingChanged = true;
                            }
                        }
                    }
                } else {
                    if (field[i][y] == BLACK_CIRCLE) {
                        if (i < x) {
                            int num = i + 1;
                            int counter = 1;
                            while (field[num][y] == RED_CIRCLE) {
                                counter++;
                                num++;
                            }
                            if (counter != 1 && counter == x - i) {
                                num = i + 1;
                                if (changeSomething) {
                                    while (field[num][y] == RED_CIRCLE) {
                                        field[num][y] = BLACK_CIRCLE;
                                        num++;
                                    }
                                }
                                isSomethingChanged = true;
                            }
                        } else if (x < i) {
                            int num = x + 1;
                            int counter = 1;
                            while (field[num][y] == RED_CIRCLE) {
                                counter++;
                                num++;
                            }
                            if (counter != 1 && counter == i - x) {
                                num = x + 1;
                                if (changeSomething) {
                                    while (field[num][y] == RED_CIRCLE) {
                                        field[num][y] = BLACK_CIRCLE;
                                        num++;
                                    }
                                }
                                isSomethingChanged = true;
                            }
                        }
                    }
                }
            }
        }
        return isSomethingChanged;
    }

    /**
     * Аналогично двум предыдущим методам - только теперь диагональ.
     * @param x
     * @param y
     * @param isRedTurn
     * @param changeSomething
     * @return
     */
    boolean CheckDiagonalClosedLine(int x, int y, boolean isRedTurn, boolean changeSomething) {
        boolean isSomethingChanged = false;
        for (int i = 0; i < 8; i++) {
            if (isRedTurn) {
                if (x + i < 8 && y + i < 8 && field[(x + i)][(y + i)] == RED_CIRCLE) {
                    int num = 1;
                    while (field[x + num][y + num] == BLACK_CIRCLE) {
                        num++;
                    }
                    if (num != 1 && num == i) {
                        num = 1;
                        if (changeSomething) {
                            while (field[x + num][y + num] == BLACK_CIRCLE) {
                                field[x + num][y + num] = RED_CIRCLE;
                                num++;
                            }
                        }
                        isSomethingChanged = true;
                    }
                }
                if (x + i > 7 && y + i > 7 && field[(x + i) % 8][(y + i) % 8] == RED_CIRCLE) {
                    int num = 1;
                    while (field[(x + i + num) % 8][(y + i + num) % 8] == BLACK_CIRCLE) {
                        num++;
                    }
                    if (num != 1 && (x + i + num) % 8 == x) {
                        num = 1;
                        if (changeSomething) {
                            while (field[(x + i + num) % 8][(y + i + num) % 8] == BLACK_CIRCLE) {
                                field[(x + i + num) % 8][(y + i + num) % 8] = RED_CIRCLE;
                                num++;
                            }
                        }
                        isSomethingChanged = true;
                    }
                }
                if (x + i < 8 && y - i >= 0 && field[x + i][y - i] == RED_CIRCLE) {
                    int num = 1;
                    while (field[x + num][y - num] == BLACK_CIRCLE) {
                        num++;
                    }
                    if (num != 1 && num == i) {
                        num = 1;
                        if (changeSomething) {
                            while (field[x + num][y - num] == BLACK_CIRCLE) {
                                field[x + num][y - num] = RED_CIRCLE;
                                num++;
                            }
                        }
                        isSomethingChanged = true;
                    }
                }
                if (x - i >= 0 && y + i < 8 && field[x - i][y + i] == RED_CIRCLE) {
                    int num = 1;
                    while (field[x - num][y + num] == BLACK_CIRCLE) {
                        num++;
                    }
                    if (num != 1 && num == i) {
                        num = 1;
                        if (changeSomething) {
                            while (field[x - num][y + num] == BLACK_CIRCLE) {
                                field[x - num][y + num] = RED_CIRCLE;
                                num++;
                            }
                        }
                        isSomethingChanged = true;
                    }
                }
            } else {
                if (x + i < 8 && y + i < 8 && field[(x + i)][(y + i)] == BLACK_CIRCLE) {
                    int num = 1;
                    while (field[x + num][y + num] == RED_CIRCLE) {
                        num++;
                    }
                    if (num != 1 && num == i) {
                        num = 1;
                        if (changeSomething) {
                            while (field[x + num][y + num] == RED_CIRCLE) {
                                field[x + num][y + num] = BLACK_CIRCLE;
                                num++;
                            }
                        }
                        isSomethingChanged = true;
                    }
                }
                if (x + i > 7 && y + i > 7 && field[(x + i) % 8][(y + i) % 8] == BLACK_CIRCLE) {
                    int num = 1;
                    while (field[(x + i + num) % 8][(y + i + num) % 8] == RED_CIRCLE) {
                        num++;
                    }
                    if (num != 1 && (x + i + num) % 8 == x) {
                        num = 1;
                        if (changeSomething) {
                            while (field[(x + i + num) % 8][(y + i + num) % 8] == RED_CIRCLE) {
                                field[(x + i + num) % 8][(y + i + num) % 8] = BLACK_CIRCLE;
                                num++;
                            }
                        }
                        isSomethingChanged = true;
                    }
                }
                if (x + i < 8 && y - i >= 0 && field[x + i][y - i] == BLACK_CIRCLE) {
                    int num = 1;
                    while (field[x + num][y - num] == RED_CIRCLE) {
                        num++;
                    }
                    if (num != 1 && num == i) {
                        num = 1;
                        if (changeSomething) {
                            while (field[x + num][y - num] == RED_CIRCLE) {
                                field[x + num][y - num] = BLACK_CIRCLE;
                                num++;
                            }
                        }
                        isSomethingChanged = true;
                    }
                }
                if (x - i >= 0 && y + i < 8 && field[x - i][y + i] == BLACK_CIRCLE) {
                    int num = 1;
                    while (field[x - num][y + num] == RED_CIRCLE) {
                        num++;
                    }
                    if (num != 1 && num == i) {
                        num = 1;
                        if (changeSomething) {
                            while (field[x - num][y + num] == RED_CIRCLE) {
                                field[x - num][y + num] = BLACK_CIRCLE;
                                num++;
                            }
                        }
                        isSomethingChanged = true;
                    }
                }
            }
        }
        return isSomethingChanged;
    }

    /**
     * Проверка на возможность сделать ход в свою очередь.
     * @return
     */
    boolean isAvailableToMakeMove() {
        boolean isAvailableToMakeTurn = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.field[i][j] == GamingField.FIELD_EMPTY) {
                    if (this.CheckDiagonalClosedLine(i, j, this.isRedMove, false)
                            | this.CheckVerticalClosedLine(i, j, this.isRedMove, false)
                            | this.CheckHorizontalClosedLine(i, j, this.isRedMove, false)) {
                        isAvailableToMakeTurn = true;
                    }
                }
            }
        }
        return isAvailableToMakeTurn;
    }

    /**
     * Проверка присутствует ли в игре оба цвета шаров.(один из случаев окончания игры)
     * @return
     */
    boolean isOnlyOneColorLeft() {
        boolean isOnlyRedLeft = true;
        boolean isOnlyBlackLeft = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == GamingField.BLACK_CIRCLE) {
                    isOnlyRedLeft = false;
                }
                if (field[i][j] == GamingField.RED_CIRCLE) {
                    isOnlyBlackLeft = false;
                }
            }
        }
        return isOnlyBlackLeft && isOnlyRedLeft;
    }

    /**
     * Проверяет остались ли свободные ячейки.для ситуации окончания игры
     * @return
     */
    boolean isBoardFull() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == GamingField.FIELD_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Копирует состояние поля. Полезный метод для компьютерного интеллекта.
     * @return
     */
    GamingField Copy() {
        GamingField copy = new GamingField();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copy.getField()[i][j] = this.getField()[i][j];
            }
        }
        return copy;
    }
}

