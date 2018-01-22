package com.javarush.task.task22.task2203;

/*
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        try {
            int index1 = string.indexOf("\t");
            int index2 = string.indexOf("\t",++index1);
            if(index2 == -1)
                throw new TooShortStringException();
            return string.substring(index1, index2);
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
