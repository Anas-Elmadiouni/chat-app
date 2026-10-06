package com.example.chat.controller;

import com.example.chat.model.User;
import com.example.chat.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final ChatService chatService;

    public UserController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String addUser(@RequestBody User user) {

        chatService.addUser(user);

        return "Utilisateur ajouté : " + user.getUsername();
    }

    @GetMapping
    public List<User> getUsers() {

        return chatService.getAllUsers();
    }
}