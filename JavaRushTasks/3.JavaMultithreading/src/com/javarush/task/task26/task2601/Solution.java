package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        Integer[] res = array.clone();
        double median = getMedian(array);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int res;
                if((Math.abs(median - o1) == Math.abs(median - o2)))
                    res = o1.compareTo(o2);
                else
                    res = (int) (Math.abs(median - o1) - Math.abs(median - o2));

                return res;
            }
        };

        Arrays.sort(res, comparator);
        return res;
    }

    private static double getMedian(Integer[] array) {
        Integer[] clone = array.clone();
        Arrays.sort(clone);
        double median;
        if (clone.length % 2 == 0)
            median = ((double) clone[clone.length / 2] + (double) clone[clone.length / 2 - 1]) / 2;
        else
            median = (double) clone[clone.length / 2];
        return median;
    }
}
