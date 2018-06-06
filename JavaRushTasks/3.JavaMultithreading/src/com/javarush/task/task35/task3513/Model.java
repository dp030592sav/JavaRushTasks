package com.javarush.task.task35.task3513;

import java.util.*;

// будет содержать игровую логику и хранить игровое поле
public class Model {

    // константа определяющая ширину игрового поля
    private static final int FIELD_WIDTH = 4;
    // игровое поле
    private Tile[][] gameTiles;
    // текущий счет игры
    int score;
    // максимальный вес плитки на игровом поле
    int maxTile;
    // состояния предыдущие игрового поля
    private Stack<Tile[][]> previousStates;
    // состояния предыдущие счета
    private Stack<Integer> previousScores;
    // флаг используемый в механизме отмены последнего хода
    private boolean isSaveNeeded = true;

    // конструктор без параметров инициализирующий игровое поле и заполняющий его пустыми плитками
    public Model() {
        resetGameTiles();
    }

    // getter для игрового поля
    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    // позволяет начать/перезапустить игру
    void resetGameTiles() {
        score = 0;
        maxTile = 0;

        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < gameTiles.length; i++)
            for (int j = 0; j < gameTiles[i].length; j++)
                gameTiles[i][j] = new Tile();

        // добавление двух случайных плиток в начале игры
        addTile();
        addTile();

        // обновления стеков, которые служат для отмены последнего хода
        previousStates = new Stack<>();
        previousScores = new Stack<>();
    }

    // сдвиг влево
    public void left() {
        // сохранения состояния игры, для подальшей возможности отмены хода
        if (isSaveNeeded)
            saveState(gameTiles);

        boolean isChanged = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
                isChanged = true;
        }

        if (isChanged) {
            addTile();
            isSaveNeeded = true;
        }
    }

    // сдвиг вправо
    public void right() {
        // сохранения состояния игры, для подальшей возможности отмены хода
        saveState(gameTiles);

        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    // сдвиг вверх
    public void up() {
        // сохранения состояния игры, для подальшей возможности отмены хода
        saveState(gameTiles);

        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    // сдвиг вниз
    public void down() {
        // сохранения состояния игры, для подальшей возможности отмены хода
        saveState(gameTiles);

        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    // определяет возможен ли ход в текущей позиции, или нет
    public boolean canMove() {
        if (getEmptyTiles().size() > 0)
            return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value)
                    return true;
            }
        }

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length - 1; j++) {
                if (gameTiles[j][i].value == gameTiles[j + 1][i].value)
                    return true;
            }
        }

        return false;
    }

    // будет смотреть какие плитки пустуют и менять вес одной из них
    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles != null && emptyTiles.size() != 0)
            emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = (Math.random() < 0.9 ? 2 : 4);
    }

    // получение свободных плиток
    private List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();

        for (int i = 0; i < gameTiles.length; i++)
            for (int j = 0; j < gameTiles[i].length; j++)
                if (gameTiles[i][j].isEmpty())
                    result.add(gameTiles[i][j]);

        return result;
    }

    // сжатие плиток, таким образом, чтобы все пустые плитки были справа
    private boolean compressTiles(Tile[] tiles) {
        boolean result = false;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length - 1 - i; j++) {
                if (tiles[j].isEmpty() && !tiles[j + 1].isEmpty()) {
                    Tile tile = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = tile;
                    result = true;
                }
            }
        }

        return result;
    }

    // слияние плиток одного номинала
    private boolean mergeTiles(Tile[] tiles) {
        boolean result = false;

        for (int i = 0; i < tiles.length - 1; i++) {
            int currentValue = tiles[i].value;
            int doubleCurrentValue = currentValue * 2;
            if (currentValue == tiles[i + 1].value && currentValue != 0) {
                tiles[i].value = doubleCurrentValue;
                tiles[i + 1].value = 0;
                i++;
                result = true;

                // изменение значений score и maxTile
                score += doubleCurrentValue;
                if (doubleCurrentValue > maxTile)
                    maxTile = doubleCurrentValue;
            }
        }

        compressTiles(tiles);

        return result;
    }

    // поаорот двухмерного массива на 90 градусов
    // используеться в методах сдвигах
    private void rotate() {
        // копируем двухмерный массив во временную переменную
        Tile[][] CopyGameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++)
            for (int j = 0; j < gameTiles[i].length; j++)
                CopyGameTiles[i][j] = gameTiles[i][j];

        // поварачиваем массив
        for (int i = 0; i < gameTiles.length; i++)
            for (int j = 0; j < gameTiles[i].length; j++)
                gameTiles[i][j] = CopyGameTiles[CopyGameTiles.length - 1 - j][i];
    }

    // будет сохранять текущее игровое состояние и счет в стеки
    private void saveState(Tile[][] tiles) {
        Tile[][] copyTiles = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < gameTiles.length; i++)
            for (int j = 0; j < gameTiles[i].length; j++)
                copyTiles[i][j] = new Tile(tiles[i][j].value);

        previousStates.push(copyTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    // будет устанавливать текущее игровое состояние равным последнему находящемуся в стеках
    public void rollback() {
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    // случайный ход
    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;

        switch (n) {
            case 0:
                left();
                break;
            case 1:
                up();
                break;
            case 2:
                right();
                break;
            case 3:
                down();
                break;
        }
    }

    // будет возвращать true, в случае, если вес плиток в массиве gameTiles отличается от
    // веса плиток в верхнем массиве стека previousStates
    public boolean hasBoardChanged() {

        if (!previousStates.isEmpty()) {
            Tile[][] previousState = previousStates.peek();
            for (int i = 0; i < gameTiles.length; i++)
                for (int j = 0; j < gameTiles[i].length; j++)
                    if (gameTiles[i][j].value != previousState[i][j].value)
                        return true;
        }

        return false;
    }

    // возвращает объект типа MoveEfficiency описывающий эффективность переданного хода
    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();

        if (hasBoardChanged())
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else
            moveEfficiency = new MoveEfficiency(-1, 0, move);

        rollback();

        return moveEfficiency;
    }

    // реализация выбора эффективного хода из возможных
    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue(4, Collections.reverseOrder());
        queue.add(getMoveEfficiency(this::left));
        queue.add(getMoveEfficiency(this::right));
        queue.add(getMoveEfficiency(this::up));
        queue.add(getMoveEfficiency(this::down));
        Move move = queue.peek().getMove();
        move.move();
    }
}
