package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader readerForFileName = new BufferedReader(new InputStreamReader(System.in));
        String fileName = readerForFileName.readLine();
        readerForFileName.close();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            Collections.addAll(list, reader.readLine().split(" "));
        }
        reader.close();

        StringBuilder result = getLine(list.toArray(new String[list.size()]));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder res = new StringBuilder();

        if (words == null || words.length == 0)
            return res;

        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));

        res.append(list.get(0));
        list.remove(0);

        while (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                String a = list.get(i).toUpperCase().toLowerCase();
                String b = res.toString().toUpperCase().toLowerCase();
                if (a.charAt(0) == b.charAt(res.length() - 1)) {
                    res.append("" + list.get(i));
                    list.remove(i);
                    break;
                }
                else if (b.charAt(0) == a.charAt(a.length() - 1)) {
                    res.insert(0, list.get(i) + " ");
                    list.remove(i);
                }
            }
            res.append(" " + list.get(0));
            list.remove(list.get(0));
        }
        return res;
    }
}
