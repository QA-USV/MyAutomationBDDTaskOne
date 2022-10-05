package ru.netology.myautomationbdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    public static String firstCardNumber = "5559 0000 0000 0001";
    public static String secondCardNumber = "5559 0000 0000 0002";
    private SelenideElement firstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");

    private SelenideElement secondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");

    private SelenideElement sumToTransfer = $("[data-test-id=amount] input");
    private SelenideElement cardNumber = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement notificationSelector = $("[data-test-id='error-notification'] .notification__content");
    private static String alertAboveBalance = "Не хватает денег для перевода. Уменьшите сумму или выберете другую карту";

    int sumToAdd;
    public TransferPage transferToFirstCard(int sumToAdd, String secondCardNumber) {
        firstCardButton.click();
        sumToTransfer.setValue(Integer.toString(sumToAdd));
        cardNumber.setValue(secondCardNumber);
        transferButton.click();
        return new TransferPage();
    }

    public TransferPage transferToSecondCard(int sumToAdd, String firstCardNumber) {
        secondCardButton.click();
        sumToTransfer.setValue(Integer.toString(sumToAdd));
        cardNumber.setValue(firstCardNumber);
        transferButton.click();
        return new TransferPage();
    }

    public TransferPage transferFromFirstCardAboveBalance(int sumToAdd, String firstCardNumber) {
        secondCardButton.click();
        sumToTransfer.setValue(Integer.toString(sumToAdd));
        cardNumber.setValue(firstCardNumber);
        transferButton.click();
        notificationSelector.shouldHave(Condition.exactText(alertAboveBalance));
        return new TransferPage();
    }

    public TransferPage transferFromSecondCardAboveBalance(int sumToAdd, String secondCardNumber) {
        firstCardButton.click();
        sumToTransfer.setValue(Integer.toString(sumToAdd));
        cardNumber.setValue(secondCardNumber);
        transferButton.click();
        notificationSelector.shouldHave(Condition.exactText(alertAboveBalance));
        return new TransferPage();
    }
}
