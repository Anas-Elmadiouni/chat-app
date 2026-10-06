package com.example.chat.service;

import com.example.chat.model.Message;
import com.example.chat.model.User;
import com.example.chat.repository.MessageRepository;
import com.example.chat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ChatService() {

        userRepository = new UserRepository();
        messageRepository = new MessageRepository();
    }

    public void addUser(User user) {

        userRepository.save(user);

        System.out.println(
                "Utilisateur ajouté : " + user.getUsername()
        );
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public void sendMessage(User sender, String content) {

        int id = (int) (System.currentTimeMillis() / 1000);

        Message message = new Message(
                id,
                content,
                sender
        );

        messageRepository.save(message);

        System.out.println(
                "Message envoyé : " + content
        );
    }
}