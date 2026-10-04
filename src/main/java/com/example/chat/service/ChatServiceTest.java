package com.example.chat.service;

import com.example.chat.model.User;

public class ChatServiceTest {

    public static void main(String[] args) {

        ChatService chatService = new ChatService();

        User user = new User(2, "Anas");

        chatService.addUser(user);

        chatService.sendMessage(user, "Bonjour depuis ChatService !");

        System.out.println("Test terminé !");
    }
}