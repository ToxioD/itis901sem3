package ru.itis.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    private Socket client;

    private PrintWriter toServer;
    private BufferedReader fromServer;

    public SocketClient(String host, int port) {
        try {
            client = new Socket(host, port);
            toServer = new PrintWriter(client.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        toServer.println(": " + message);
    }

    public void sendString(String string) {
        toServer.println(string);
    }

    public void sendAttribute(String attribute, Integer value) {
        toServer.println(attribute + "," + value);
    }

    public void pingServer() {
        toServer.println("ping");
    }

    public void setReady() {
        toServer.println("ready");
    }

    public BufferedReader getFromServer() {
        return fromServer;
    }

    public void closeConnection() {
        try {
            toServer.println("close");
            client.close();
        } catch (IOException e) {}
    }
}
