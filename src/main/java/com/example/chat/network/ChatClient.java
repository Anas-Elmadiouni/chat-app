package com.example.chat.network;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connecté au serveur");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String message;

            while (true) {
                System.out.print("Votre message : ");
                message = scanner.nextLine();

                if ("exit".equalsIgnoreCase(message)) {
                    writer.println(message);
                    break;
                }

                writer.println(message);
            }

            writer.close();
            scanner.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}