package ru.itis.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerSocket {

    public void start(int port) {
        ServerSocket socket;
        UserThread user1;
        UserThread user2;

        try {
            socket = new ServerSocket(port);
            Socket firstClient = socket.accept();
            Socket secondClient = socket.accept();

            user1 = new UserThread("User 1",
                    new BufferedReader(new InputStreamReader(firstClient.getInputStream())),
                    new PrintWriter(secondClient.getOutputStream(), true));

            user2 = new UserThread("User 2",
                    new BufferedReader(new InputStreamReader(secondClient.getInputStream())),
                    new PrintWriter(firstClient.getOutputStream(), true));

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private class UserThread implements Runnable {
        Thread t;
        BufferedReader from;
        PrintWriter to;

        UserThread(String name, BufferedReader from, PrintWriter to) {
            t = new Thread(this, name);
            this.from = from;
            this.to = to;
            t.start();
        }

        public void run() {
            try {
                System.out.println(t.getName() + " connected and running");
                while (true) {
                    String message = from.readLine();
                    if (message != null) {
                        System.out.println("Message from " + t.getName() + ": " + message);
                        to.println("Message from " + t.getName() + ": " + message);
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

    }
}
