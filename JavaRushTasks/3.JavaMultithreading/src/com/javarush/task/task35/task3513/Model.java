package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

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

    // конструктор без параметров инициализирующий игровое поле и заполняющий его пустыми плитками
    public Model() {
        resetGameTiles();
    }

    // сдвиг влево
    public void left() {
        boolean isChanged = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
                isChanged = true;
        }

        if (isChanged) addTile();
    }

    // сдвиг вправо
    public void right() {
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    // сдвиг вверх
    public void up() {
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    // сдвиг вниз
    public void down() {
        rotate();
        rotate();
        rotate();
        left();
        rotate();
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
        // копируем двемерный массив во временную переменную
        Tile[][] CopyGameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++)
            for (int j = 0; j < gameTiles[i].length; j++)
                CopyGameTiles[i][j] = gameTiles[i][j];

        // поварачиваем массив
        for (int i = 0; i < gameTiles.length; i++)
            for (int j = 0; j < gameTiles[i].length; j++)
                gameTiles[i][j] = CopyGameTiles[CopyGameTiles.length - 1 - j][i];
    }
}
