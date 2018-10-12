package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object obj = "";

        int x = (int) obj;
    }

    public void methodThrowsNullPointerException() {
        String str = null;

        str.toLowerCase();
    }

    public static void main(String[] args) {
        new VeryComplexClass().methodThrowsClassCastException();
    }
}
