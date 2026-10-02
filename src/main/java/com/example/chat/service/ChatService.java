package com.example.chat.service;

import com.example.chat.model.Message;
import com.example.chat.model.User;

import java.util.ArrayList;
import java.util.List;

public class ChatService {

    private final List<Message> messages = new ArrayList<>();

    public void sendMessage(User sender, String content) {

        int id = messages.size() + 1;

        Message message = new Message(id, content, sender);

        messages.add(message);

        System.out.println("Message envoyé : " + content);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
