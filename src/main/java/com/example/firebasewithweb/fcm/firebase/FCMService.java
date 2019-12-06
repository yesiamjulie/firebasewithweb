package com.example.firebasewithweb.fcm.firebase;

import com.example.firebasewithweb.fcm.model.PushNotificationRequest;
import com.google.api.services.storage.model.Channel;
import com.google.firebase.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FCMService {

    private Logger logger = LoggerFactory.getLogger(FCMService.class);

    public void sendMessage(Map<String , String >data, PushNotificationRequest request)
        throws  InterruptedException, ExecutionException{
        Message message = getPreconfiguredMessageWithData(data, request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message with data . Topic:" + request.getTopic() + ","
        + response);
    }

    public void sendMessageWithoutData(PushNotificationRequest request)
        throws  InterruptedException, ExecutionException {

        Message message = getPreconfiguredMessageToToken(request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message to token. Device token: "+ request.getToken() + "," + response);

    }

    public String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException{

        return FirebaseMessaging.getInstance().sendAsync(message).get();

    }

    private AndroidConfig getAndroidConfig(String topic){
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                        .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();

    }
    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }
    private Message getPreconfiguredMessageToToken(PushNotificationRequest request){
        return getPreconfiguredMessageBuiler(request).setToken(request.getToken()).build();
    }

    private Channel getPreconfiguredMessageBuiler(PushNotificationRequest request) {
        return null;
    }

    private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request){
        return getPreconfiguredMessageBuiler(request).setTopic(request.getTopic()).build();
    }
    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request){
        return getPreconfiguredMessageBuiler(request).putAllData(data).setTopic(request.getTopic()).build();
    }


    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request){
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage()));

    }

    public void sendMessageToToken(PushNotificationRequest request) {

    }
}

