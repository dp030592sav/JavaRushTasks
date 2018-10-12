package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        Thread.currentThread().interrupt();
        Thread.sleep(5);
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();
    }
}
