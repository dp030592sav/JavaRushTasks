package com.javarush.task.task22.task2211;

import java.io.*;

/*
Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.


Требования:
1. Программа НЕ должна считывать данные с клавиатуры.
2. Программа НЕ должна выводить данные на экран.
3. Программа должна записывать данные в файл.
4. Содержимое второго файла должно соответствовать содержимому первого файла за исключением кодировки(UTF-8).
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];

        FileInputStream inputStream = new FileInputStream(new File(fileName1));
        byte[] array = new byte[inputStream.available()];
        inputStream.read(array, 0, inputStream.available());
        inputStream.close();

        String text = new String(array, "Windows-1251");
        array = text.getBytes("UTF-8");

        FileOutputStream outputStream = new FileOutputStream(new File(fileName2));
        outputStream.write(array);
        outputStream.close();
    }
}
