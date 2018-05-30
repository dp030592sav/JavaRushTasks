package com.javarush.task.task35.task3513;

import java.awt.*;

// класс описывающий одну плитку
public class Tile {

    // вес плитки
    int value;

    // конструктор с параметром
    public Tile(int value) {
        this.value = value;
    }

    // конструктор без параметров
    public Tile() {
        this.value = 0;
    }

    // возвращающий true в случае, если значение поля value равно нулю
    public boolean isEmpty() {
        return value == 0;
    }

    // возвращающий новый цвет (объект типа Color)
    // (0x776e65) в случае, если вес плитки меньше 16, иначе - 0xf9f6f2
    public Color getFontColor() {
        return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
    }

    // возвращающий цвет плитки в зависимости от ее веса
    public Color getTileColor() {

        int x = 0xff0000;

        switch (value) {
            case 0 : x = 0xcdc1b4;break;
            case 2 : x = 0xeee4da;break;
            case 4 : x = 0xede0c8;break;
            case 8 : x = 0xf2b179;break;
            case 16 : x = 0xf59563;break;
            case 32 : x = 0xf67c5f;break;
            case 64 : x = 0xf65e3b;break;
            case 128 : x = 0xedcf72;break;
            case 256 : x = 0xedcc61;break;
            case 512 : x = 0xedc850;break;
            case 1024 : x = 0xedc53f;break;
            case 2048 : x = 0xedc22e;break;
        }

        return new Color(x);
    }
}