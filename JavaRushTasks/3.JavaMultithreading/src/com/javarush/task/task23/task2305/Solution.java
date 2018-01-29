package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution solution1 = new Solution();
        Solution solution2 = new Solution();
        Solution[] res = new Solution[]{solution1, solution2};
        res[0].innerClasses = new InnerClass[]{solution1.new InnerClass(), solution1.new InnerClass()};
        res[1].innerClasses = new InnerClass[]{solution2.new InnerClass(), solution2.new InnerClass()};
        return res;
    }

    public static void main(String[] args) {

    }
}
