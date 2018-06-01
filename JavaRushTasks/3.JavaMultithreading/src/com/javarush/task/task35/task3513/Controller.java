package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//  будет следить за нажатием клавиш во время игры
public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static final int WINNING_TILE = 2048;

    // конструктор
    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    // возвращает игровое поле
    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    // возвращает текущий счет игры
    public int getScore() {
        return model.score;
    }

    // getter для view
    public View getView() {
        return view;
    }

    // позволяет вернуть игровое поле в начальное состояние
    public void resetGame() {
        view.isGameWon = false;
        view.isGameLost = false;
        model.score = 0;
        model.resetGameTiles();
    }

    // оброботчик нажатий клавишь
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            resetGame();
        if (!model.canMove())
            view.isGameLost = true;

        if (view.isGameWon == false && view.isGameLost == false) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    model.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    model.right();
                    break;
                case KeyEvent.VK_UP:
                    model.up();
                    break;
                case KeyEvent.VK_DOWN:
                    model.down();
                    break;
            }
        }
        if (model.maxTile == WINNING_TILE)
            view.isGameWon = true;

        view.repaint();
    }
}
