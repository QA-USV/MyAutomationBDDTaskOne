package ru.netology.myautomationbdd.data;

import lombok.Value;

public class DataHelper {

    public static String firstCardNumber = "5559 0000 0000 0001";
    public static String firstCardButton = "[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button";
    public static String firstCardId = "[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']";
    public static String secondCardNumber = "5559 0000 0000 0002";
    public static String secondCardButton = "[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button";
    public static String secondCardId = "[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']";
    public static String sumToTransfer = "[data-test-id=amount] input";
    public static String cardNumber = "[data-test-id=from] input";
    public static String transferButton = "[data-test-id=action-transfer]";
    public static String notificationSelector = "[data-test-id='error-notification'] .notification__content";
    public static String alertAboveBalance = "Не хватает денег для перевода. Уменьшите сумму или выберете другую карту";

    public DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}