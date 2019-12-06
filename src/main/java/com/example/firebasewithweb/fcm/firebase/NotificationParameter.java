package com.example.firebasewithweb.fcm.firebase;

import javax.annotation.processing.Completion;

public enum  NotificationParameter {
    COLOR("#FFFF00"),
    SOUND("default");


    private String value;

    NotificationParameter(String value) {
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
