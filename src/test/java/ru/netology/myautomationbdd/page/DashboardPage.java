package ru.netology.myautomationbdd.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private static String firstCardId = "[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']";
    private static String secondCardId = "[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']";
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        var text = $(firstCardId).getText();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        var text = $(secondCardId).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}