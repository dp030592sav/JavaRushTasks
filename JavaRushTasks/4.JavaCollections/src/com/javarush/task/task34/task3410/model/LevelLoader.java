package com.javarush.task.task34.task3410.model;

import java.io.*;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        ArrayList<String> listLvlLines = new ArrayList<>();
        int currentLevel = level % 60 == 0 ? 60 : level % 60;

        // read needed lvl lines to List<String>
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            String readLine;
            boolean lvlIsFind = false;
            int counterEmptyLine = 0;

            while (reader.ready()) {
                readLine = reader.readLine();

                if (readLine.contains("Maze") && Integer.parseInt(readLine.split(" ")[1]) == currentLevel) {
                    lvlIsFind = true;
                    continue;
                }

                if (lvlIsFind && readLine.equals("")) {
                    counterEmptyLine++;
                    continue;
                }

                if (counterEmptyLine == 2)
                    break;

                if (counterEmptyLine == 1) {
                    listLvlLines.add(readLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // pars List<String> to objects
        int x;
        int y = Model.FIELD_CELL_SIZE / 2;

        for (String line : listLvlLines) {
            x = Model.FIELD_CELL_SIZE / 2;
            char[] chars = line.toCharArray();

            for (char c : chars) {
                switch (c) {
                    case 'X':
                        walls.add(new Wall(x, y));
                        break;
                    case '*':
                        boxes.add(new Box(x, y));
                        break;
                    case '.':
                        homes.add(new Home(x, y));
                        break;
                    case '&':
                        boxes.add(new Box(x, y));
                        homes.add(new Home(x, y));
                        break;
                    case '@':
                        player = new Player(x, y);
                        break;
                }
                x += Model.FIELD_CELL_SIZE;
            }
            y += Model.FIELD_CELL_SIZE;
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
