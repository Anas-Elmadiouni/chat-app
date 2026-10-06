package com.example.chat.repository;
import java.util.ArrayList;
import java.util.List;

import com.example.chat.database.MongoDBConnection;
import com.example.chat.model.Message;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MessageRepository {

    private final MongoCollection<Document> collection;

    public MessageRepository() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        collection = database.getCollection("messages");
    }

    public void save(Message message) {


        Document document = new Document("id", message.getId())
                .append("content", message.getContent())
                .append("sender", message.getSender().getUsername());

        collection.insertOne(document);

        System.out.println("Message enregistré : " + message.getContent());

    }
    public List<Document> findAll() {
        return collection.find().into(new ArrayList<>());
    }
}