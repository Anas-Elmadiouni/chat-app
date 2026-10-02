package com.example.chat.model;

public class Message {
    private int id;
    private String content;
    private User sender;

    public Message(int id, String content, User sender) {
        this.id = id;
        this.content = content;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message{id=" + id + ", content='" + content + "', sender=" + sender + "}";
    }
}
