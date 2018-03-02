package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ConsoleHelper consoleHelper = new ConsoleHelper();
        int port = consoleHelper.readInt();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Cервер запущен.");

        try {
            while (true){
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException e) {
            serverSocket.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static class Handler extends Thread {
        private final Socket socket;

        private Handler(Socket socket) {
            this.socket = socket;
        }
    }
}