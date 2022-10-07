package ru.netology.myautomationbdd.data;

import lombok.Value;

public class DataHelper {

    public DataHelper() {
    }

    public static String firstCardNumber = "5559 0000 0000 0001";
    public static String secondCardNumber = "5559 0000 0000 0002";

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