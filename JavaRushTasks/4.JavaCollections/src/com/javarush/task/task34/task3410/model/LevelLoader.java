package com.javarush.task.task34.task3410.model;

import java.io.*;
import java.nio.file.Path;

import java.util.HashSet;
import java.util.Set;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        int currentLevel = level % 60 == 0 ? 60 : level % 60;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()));
            while (reader.ready()){
                System.out.println(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        int x = FIELD_CELL_SIZE/2;
        int y = FIELD_CELL_SIZE/2;

        Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(x, y));
        walls.add(new Wall(x, y));
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(x, y));
        Set<Home> homes = new HashSet<>();
        homes.add(new Home(x, y));
        Player player = new Player(x, y);

        return new GameObjects(walls, boxes, homes, player);
    }
}
