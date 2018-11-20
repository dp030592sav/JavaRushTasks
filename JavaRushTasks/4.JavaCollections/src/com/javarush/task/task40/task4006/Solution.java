package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket clientSocket = new Socket(url.getHost(), 80);

            String request = "GET " + url.getFile() + " HTTP/1.1\r\n";
            request += "Host: " + url.getHost() + "\r\n\r\n";

            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(request.getBytes());
            outputStream.flush();


            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println(in.readLine());
            outputStream.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}