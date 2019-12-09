package com.example.firebasewithweb.fcm.service;

import autovalue.shaded.com.google$.common.base.$Strings;
import com.example.firebasewithweb.fcm.firebase.FCMService;
import com.example.firebasewithweb.fcm.model.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationService {

    @Value("#{$app.notification.defaults}}")
    private Map<String, String>defaults;

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class );
    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService= fcmService;
    }
    @Scheduled(initialDelay =   60000, fixedDelay = 60000)
    public void sendSamplePushNotification(){
        try{
            fcmService.sendMessageWithoutData(getSamplePushNotificationRequest());
        }catch (InterruptedException | ExecutionException e){
            logger.error(e.getMessage());
        }
    }

    public void sendPushNotification(PushNotificationRequest request){
        try{
            fcmService.sendMessage(getSamplePayloadData(), request);

        }catch (InterruptedException | ExecutionException e){
            logger.error(e.getMessage());
        }
    }
    public void sendPushNotificationToToken(PushNotificationRequest request){
        try{
            fcmService.sendMessageToToken(request);
        }catch (InterruptedException | ExecutionException e){
            logger.error(e.getMessage());
        }
    }

    private Map<String, String> getSamplePayloadData() {
        Map<String, String> pushData = new HashMap<>();
        pushData.put("messageId",defaults.get("payloadMessageId"));
        pushData.put("text", defaults.get("payloadData") + "" + LocalDateTime.now());
        return pushData;
    }

    public void sendPushNotificationWithoutData(PushNotificationRequest request){
        try {
            fcmService.sendMessageWithoutData(request);
        }catch (InterruptedException | ExecutionException e){
            logger.error(e.getMessage());
        }
    }
    private PushNotificationRequest getSamplePushNotificationRequest() {
        PushNotificationRequest request = new PushNotificationRequest(defaults.get("title"),
                defaults.get("message"),
                defaults.get("topic"));
        return request;
    }

}
