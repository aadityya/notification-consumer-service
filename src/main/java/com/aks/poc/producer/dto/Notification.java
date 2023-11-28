package com.aks.poc.producer.dto;

public class Notification {
    private String notificationId;
    private String message;

    private String sdkUsed = "azure-stream-binder-servicebus";

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSdkUsed() {
        return sdkUsed;
    }

    public void setSdkUsed(String sdkUsed) {
        this.sdkUsed = sdkUsed;
    }
}
