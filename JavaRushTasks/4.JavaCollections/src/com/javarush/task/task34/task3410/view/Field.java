package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Player;

import javax.swing.*;
import java.awt.*;


public class Field extends JPanel {
    private View view;

    public Field(View view) {
        this.view = view;
    }

    @Override
    public void paint(Graphics g) {
        new Box(20, 25).draw(g);
        new Player(75, 85).draw(g);
//        super.paint(g);
    }
}
