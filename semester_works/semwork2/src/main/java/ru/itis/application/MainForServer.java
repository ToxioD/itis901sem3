package ru.itis.application;

import ru.itis.sockets.GameServerSocket;

public class MainForServer {
    public static void main(String[] args) {
        GameServerSocket serverSocket = new GameServerSocket();
        serverSocket.start(7777);
    }
}
