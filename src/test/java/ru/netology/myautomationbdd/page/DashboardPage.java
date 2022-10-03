package ru.netology.myautomationbdd.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.myautomationbdd.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        var text = $(DataHelper.firstCardId).getText();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        var text = $(DataHelper.secondCardId).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}