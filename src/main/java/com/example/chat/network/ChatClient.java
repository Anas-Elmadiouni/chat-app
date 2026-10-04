package com.example.chat.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connecté au serveur");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            Scanner scanner = new Scanner(System.in);

            System.out.print("Entrez votre nom : ");
            String username = scanner.nextLine();
            writer.println(username);

            Thread receiverThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = reader.readLine();

                        if (message == null || message.equalsIgnoreCase("exit")) {
                            System.out.println("Serveur déconnecté");
                            break;
                        }

                        System.out.println(message);
                    }
                } catch (Exception e) {
                    System.out.println("Connexion fermée");
                }
            });

            receiverThread.start();

            while (true) {
                System.out.print("Votre message : ");
                String message = scanner.nextLine();

                writer.println(message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            writer.close();
            reader.close();
            scanner.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}