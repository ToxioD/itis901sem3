package ru.itis.application;

import ru.itis.sockets.EchoServerSocket;

public class MainForServer {
    public static void main(String[] args) {
        EchoServerSocket serverSocket = new EchoServerSocket();
        serverSocket.start(7777);
    }
}
