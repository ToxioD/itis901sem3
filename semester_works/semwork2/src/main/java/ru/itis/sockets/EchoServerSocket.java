package ru.itis.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerSocket {

    public void start(int port) {
        ServerSocket socket;
        PlayerThread player1;
        PlayerThread player2;

        try {
            socket = new ServerSocket(port);
            Socket firstClient = socket.accept();
            Socket secondClient = socket.accept();

            player1 = new PlayerThread("Player 1",
                    new BufferedReader(new InputStreamReader(firstClient.getInputStream())),
                    new PrintWriter(firstClient.getOutputStream(), true),
                    new PrintWriter(secondClient.getOutputStream(), true));

            player2 = new PlayerThread("Player 2",
                    new BufferedReader(new InputStreamReader(secondClient.getInputStream())),
                    new PrintWriter(secondClient.getOutputStream(), true),
                    new PrintWriter(firstClient.getOutputStream(), true));

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private class PlayerThread implements Runnable {
        private Thread t;
        private BufferedReader from;
        private PrintWriter self;
        private PrintWriter to;

        PlayerThread(String name, BufferedReader from, PrintWriter self, PrintWriter to) {
            t = new Thread(this, name);
            this.from = from;
            this.self = self;
            this.to = to;
            t.start();
        }

        public void run() {
            try {
                System.out.println(t.getName() + " connected and running");
                while (true) {
                    String message = from.readLine();
                    if (message != null) {
                        if (message.equals("ready")) {
                            self.println("disable");
                            to.println("ready");
                        } else if (message.startsWith(":")) {
                            self.println("Message from " + t.getName() + message);
                            to.println("Message from " + t.getName() + message);
                        } else {
                            System.out.println(t.getName() + " " + message);
                            to.println(message);
                        }
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
