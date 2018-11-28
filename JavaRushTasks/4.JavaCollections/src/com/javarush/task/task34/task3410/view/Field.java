package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;


public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        for (GameObject gameObject : view.getGameObjects().getAll())
            gameObject.draw(g);


    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case VK_R:
                    eventListener.restart();
                    break;
            }
        }
    }
}
