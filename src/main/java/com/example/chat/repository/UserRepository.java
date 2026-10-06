package com.example.chat.repository;

import com.example.chat.database.MongoDBConnection;
import com.example.chat.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final MongoCollection<Document> collection;

    public UserRepository() {

        MongoDatabase database = MongoDBConnection.getDatabase();

        collection = database.getCollection("users");
    }

    public void save(User user) {

        Document document = new Document("id", user.getId())
                .append("username", user.getUsername());

        // Vérifier si l'utilisateur existe déjà
        Document existingUser = collection.find(
                new Document("username", user.getUsername())
        ).first();

        if (existingUser == null) {

            collection.insertOne(document);

            System.out.println(
                    "Utilisateur enregistré : " + user.getUsername()
            );

        } else {

            System.out.println(
                    "Utilisateur déjà existant : " + user.getUsername()
            );
        }
    }

    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        for (Document document : collection.find()) {

            User user = new User(
                    document.getInteger("id"),
                    document.getString("username")
            );

            users.add(user);
        }

        return users;
    }
}