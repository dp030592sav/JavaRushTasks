package com.javarush.task.task35.task3513;

// описывает эффективность хода
public class MoveEfficiency implements Comparable<MoveEfficiency> {
    // количество пустых плиток, которое будет на поле после этого хода.
    private int numberOfEmptyTiles;
    // счет, который будет после этого хода
    private int score;
    // сам ход
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        int result = Integer.compare(numberOfEmptyTiles, o.numberOfEmptyTiles);
        if(result == 0)
            result = Integer.compare(score, o.score);

        return result;
    }
}
