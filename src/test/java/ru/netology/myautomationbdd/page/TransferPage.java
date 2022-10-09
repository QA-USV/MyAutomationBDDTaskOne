package ru.netology.myautomationbdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement sumToTransferField = $("[data-test-id=amount] input");
    private final SelenideElement cardNumberField = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement notificationSelector = $("[data-test-id='error-notification'] .notification__content");
    private static final String aboveBalanceAlertMessage = "Не хватает денег для перевода. Уменьшите сумму или выберете другую карту";


    public void transferToCard (int sumToAdd, String cardNumber) {
        sumToTransferField.setValue(Integer.toString(sumToAdd));
        cardNumberField.setValue(cardNumber);
        transferButton.click();
    }

    public void transferAboveBalanceAlert(int sumToAdd, String cardNumber) {
        sumToTransferField.setValue(Integer.toString(sumToAdd));
        cardNumberField.setValue(cardNumber);
        transferButton.click();
        notificationSelector.shouldHave(Condition.exactText(aboveBalanceAlertMessage));
    }
}