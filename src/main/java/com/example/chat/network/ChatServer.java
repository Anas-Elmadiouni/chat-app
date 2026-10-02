package com.example.chat.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Serveur démarré sur le port 5000");
            System.out.println("En attente d'un client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connecté");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String message;

            while ((message = reader.readLine()) != null) {
                System.out.println("Message reçu : " + message);
            }

            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}