package com.example.chat.network;

import com.example.chat.model.User;
import com.example.chat.service.ChatService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private static final List<ClientHandler> clients = new ArrayList<>();

    private static final ChatService chatService = new ChatService();

    public static void main(String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Serveur démarré sur le port 5000");

            while (true) {

                Socket socket = serverSocket.accept();

                ClientHandler client = new ClientHandler(socket);

                clients.add(client);

                client.start();

                System.out.println("Client connecté");
                System.out.println("Clients connectés : " + clients.size());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private static void broadcast(String message, ClientHandler sender) {

        for (ClientHandler client : clients) {

            if (client != sender) {

                client.getWriter().println(message);
            }
        }
    }

    private static void removeClient(ClientHandler client) {

        clients.remove(client);

        if (client.getUsername() != null) {

            System.out.println(client.getUsername() + " déconnecté");
        }

        System.out.println("Clients connectés : " + clients.size());
    }

    private static class ClientHandler extends Thread {

        private final Socket socket;

        private final BufferedReader reader;

        private final PrintWriter writer;

        private String username;

        private User user;

        public ClientHandler(Socket socket) throws Exception {

            this.socket = socket;

            this.reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            this.writer = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );
        }

        public PrintWriter getWriter() {

            return writer;
        }

        public String getUsername() {

            return username;
        }

        @Override
        public void run() {

            try {

                // Lire le username envoyé par le client
                String name = reader.readLine();

                if (name == null || name.trim().isEmpty()) {

                    socket.close();

                    return;
                }

                this.username = name;

                // Créer l'utilisateur
                this.user = new User(
                        clients.size(),
                        username
                );

                // Sauvegarder l'utilisateur dans MongoDB
                chatService.addUser(user);

                System.out.println(username + " connecté");

                System.out.println(
                        "Clients connectés : " + clients.size()
                );

                // Lire les messages
                while (true) {

                    String message = reader.readLine();

                    // Déconnexion
                    if (message == null ||
                            message.equalsIgnoreCase("exit")) {

                        removeClient(this);

                        socket.close();

                        break;
                    }

                    // Afficher le message
                    String messageToSend =
                            username + " : " + message;

                    System.out.println(messageToSend);

                    // Sauvegarder le message dans MongoDB
                    chatService.sendMessage(
                            user,
                            message
                    );

                    // Envoyer aux autres clients
                    broadcast(
                            messageToSend,
                            this
                    );
                }

            } catch (Exception e) {

                if (username != null) {

                    removeClient(this);
                }

                try {

                    socket.close();

                } catch (Exception ignored) {
                }
            }
        }
    }
}