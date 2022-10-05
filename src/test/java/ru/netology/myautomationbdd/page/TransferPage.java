package ru.netology.myautomationbdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private static final String firstCardNumber = "5559 0000 0000 0001";
    private static final String secondCardNumber = "5559 0000 0000 0002";
    private final SelenideElement firstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");

    private final SelenideElement secondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");

    private final SelenideElement sumToTransfer = $("[data-test-id=amount] input");
    private final SelenideElement cardNumber = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement notificationSelector = $("[data-test-id='error-notification'] .notification__content");
    private static final String alertAboveBalance = "Не хватает денег для перевода. Уменьшите сумму или выберете другую карту";

    public void transferToFirstCard(int sumToAdd) {
        firstCardButton.click();
        sumToTransfer.setValue(Integer.toString(sumToAdd));
        cardNumber.setValue(secondCardNumber);
        transferButton.click();
        new TransferPage();
    }

    public void transferToSecondCard(int sumToAdd) {
        secondCardButton.click();
        sumToTransfer.setValue(Integer.toString(sumToAdd));
        cardNumber.setValue(firstCardNumber);
        transferButton.click();
        new TransferPage();
    }

    public void transferFromFirstCardAboveBalance(int sumToAdd) {
        secondCardButton.click();
        sumToTransfer.setValue(Integer.toString(sumToAdd));
        cardNumber.setValue(firstCardNumber);
        transferButton.click();
        notificationSelector.shouldHave(Condition.exactText(alertAboveBalance));
        new TransferPage();
    }

    public void transferFromSecondCardAboveBalance(int sumToAdd) {
        firstCardButton.click();
        sumToTransfer.setValue(Integer.toString(sumToAdd));
        cardNumber.setValue(secondCardNumber);
        transferButton.click();
        notificationSelector.shouldHave(Condition.exactText(alertAboveBalance));
        new TransferPage();
    }
}