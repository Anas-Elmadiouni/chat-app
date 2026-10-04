package com.example.chat.repository;

import com.example.chat.database.MongoDBConnection;
import com.example.chat.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UserRepository {

    private final MongoCollection<Document> collection;

    public UserRepository() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        collection = database.getCollection("users");
    }

    public void save(User user) {

        Document document = new Document("id", user.getId())
                .append("username", user.getUsername());

        collection.insertOne(document);

        System.out.println("Utilisateur enregistré : " + user.getUsername());
    }
}