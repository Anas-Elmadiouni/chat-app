package com.example.chat;

import com.example.chat.model.Message;
import com.example.chat.model.User;

public class Main {
    public static void main(String[] args) {
        ChatApp app = new ChatApp();
        app.run();

        User sender = new User(1, "Alice");
        Message message = new Message(10, "Bonjour !", sender);

        System.out.println("Utilisateur : " + sender.getUsername());
        System.out.println("Message : " + message.getContent());
        System.out.println("Envoye par : " + message.getSender().getUsername());
    }
}
