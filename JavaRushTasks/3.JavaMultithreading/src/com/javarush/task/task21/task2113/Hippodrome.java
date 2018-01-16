package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }

    }

    public void move() {
        for (Horse i : horses) {
            i.move();
        }
    }

    public void print() {
        for (Horse i : horses) {
            i.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner(){
        if(horses == null || horses.size() ==0)
            return null;
        Horse winner = horses.get(0);
        for (Horse i : horses) {
            if(winner.getDistance() < i.getDistance())
                winner = i;
        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) throws InterruptedException {
        List horses = new ArrayList();
        horses.add(new Horse("1", 3, 0));
        horses.add(new Horse("2", 3, 0));
        horses.add(new Horse("3", 3, 0));
        Hippodrome.game = new Hippodrome(horses);
        Hippodrome.game.run();
        Hippodrome.game.printWinner();
    }
}
