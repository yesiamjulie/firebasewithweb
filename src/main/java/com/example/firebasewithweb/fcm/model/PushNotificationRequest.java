package com.example.firebasewithweb.fcm.model;

public class PushNotificationRequest {

    private String topic;
    private String message;
    private String title;
    private String token;

    public PushNotificationRequest() {
    }

    public PushNotificationRequest(String title, String messageBody, String topicName) {
        this.title = title;
        this.message = messageBody;
        this.topic = topicName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return null;
    }


    public String getToken() {

        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
