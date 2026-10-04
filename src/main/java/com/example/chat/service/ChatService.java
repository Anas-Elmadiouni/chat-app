package com.example.chat.service;

import com.example.chat.model.Message;
import com.example.chat.model.User;
import com.example.chat.repository.MessageRepository;
import com.example.chat.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ChatService {

    private final List<Message> messages = new ArrayList<>();

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ChatService() {
        userRepository = new UserRepository();
        messageRepository = new MessageRepository();
    }

    public void sendMessage(User sender, String content) {

        int id = messages.size() + 1;

        Message message = new Message(id, content, sender);

        messages.add(message);

        messageRepository.save(message);

        System.out.println("Message envoyé : " + content);
    }

    public void addUser(User user) {

        userRepository.save(user);

        System.out.println("Utilisateur ajouté : " + user.getUsername());
    }

    public List<Message> getMessages() {
        return messages;
    }
}