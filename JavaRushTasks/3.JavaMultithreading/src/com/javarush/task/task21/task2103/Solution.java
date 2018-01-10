package com.javarush.task.task21.task2103;

/* 
Все гениальное - просто!
Упростить. Переменные не переименовывать, комментарии не оставлять.


Требования:
1. Метод calculate должен быть статическим.
2. Метод calculate должен возвращать значение типа boolean.
3. Метод calculate должен принимать четыре параметра типа boolean.
4. Метод calculate должен быть максимально упрощен(поведение должно остаться прежним).
*/
public class Solution {
    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
//        return (a && b && c && !d) || (!a && c) || (!b && c) || (c && d);
        return c;
    }

    public static void main(String[] args) {
        System.out.println(Solution.calculate(true, true, true, false));
        System.out.println(Solution.calculate(true, false, true, false));
        System.out.println(Solution.calculate(false, true, true, false));
        System.out.println(Solution.calculate(false, false, true, true));
    }
}
