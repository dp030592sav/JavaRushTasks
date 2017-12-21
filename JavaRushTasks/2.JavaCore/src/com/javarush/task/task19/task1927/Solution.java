package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream printStreamOld = System.out;

        ByteArrayOutputStream byteArrOutStr = new ByteArrayOutputStream();
        PrintStream printStreamNew = new PrintStream(byteArrOutStr);
        System.setOut(printStreamNew);

        testString.printSomething();

        String result = byteArrOutStr.toString();
        String[] mas = result.split(System.getProperty("line.separator"));

        System.setOut(printStreamOld);

        for (int i = 0; i < mas.length; i++) {
            System.out.println(mas[i]);
            if(i % 2 != 0)
                System.out.println("JavaRush - курсы Java онлайн");
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
