package com.javarush.task.task36.task3607;


import java.util.Collections;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();
        return null;
    }
}
