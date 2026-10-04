package com.example.chat.database;

import com.example.chat.model.Message;
import com.example.chat.model.User;
import com.example.chat.repository.MessageRepository;
import com.example.chat.repository.UserRepository;

public class MongoTest {

    public static void main(String[] args) {


        User user = new User(1, "Anas");

        // Enregistrement de l'utilisateur
        UserRepository userRepository = new UserRepository();
        userRepository.save(user);

        // Création d'un message
        Message message = new Message(
                1,
                "Bonjour MongoDB !",
                user
        );


        MessageRepository messageRepository = new MessageRepository();
        messageRepository.save(message);

        System.out.println("Test MongoDB terminé !");
    }
}