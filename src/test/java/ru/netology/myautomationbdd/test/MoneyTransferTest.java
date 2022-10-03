package ru.netology.myautomationbdd.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.myautomationbdd.page.DashboardPage;
import ru.netology.myautomationbdd.page.LoginPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.myautomationbdd.data.DataHelper.*;

public class MoneyTransferTest {

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferMoneyToFirstCard() {
        String sumToAdd = "25";
        DashboardPage page = new DashboardPage();
        int balanceFirstCardBeforeTransfer = page.getFirstCardBalance();
        int balanceSecondCardBeforeTransfer = page.getSecondCardBalance();
        $(firstCardButton).click();
        $("[data-test-id=amount] input").setValue(sumToAdd);
        $("[data-test-id=from] input").setValue(secondCardNumber);
        $("[data-test-id=action-transfer]").click();
        int sum = Integer.parseInt(sumToAdd);
        int expectedBalanceFirstCard = balanceFirstCardBeforeTransfer + sum;
        int expectedBalanceSecondCard = balanceSecondCardBeforeTransfer - sum;
        Assertions.assertEquals(expectedBalanceFirstCard, page.getFirstCardBalance());
        Assertions.assertEquals(expectedBalanceSecondCard, page.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyToSecondCard() {
        String sumToAdd = "250";
        DashboardPage page = new DashboardPage();
        int balanceFirstCardBeforeTransfer = page.getFirstCardBalance();
        int balanceSecondCardBeforeTransfer = page.getSecondCardBalance();
        $(secondCardButton).click();
        $("[data-test-id=amount] input").setValue(sumToAdd);
        $("[data-test-id=from] input").setValue(firstCardNumber);
        $("[data-test-id=action-transfer]").click();
        int sum = Integer.parseInt(sumToAdd);
        int expectedBalanceFirstCard = balanceFirstCardBeforeTransfer - sum;
        int expectedBalanceSecondCard = balanceSecondCardBeforeTransfer + sum;
        Assertions.assertEquals(expectedBalanceFirstCard, page.getFirstCardBalance());
        Assertions.assertEquals(expectedBalanceSecondCard, page.getSecondCardBalance());
    }

    @Test
    void shouldNotTransferFromSecondCardAboveItsBalance() {
        DashboardPage page = new DashboardPage();
        String sumToAdd = String.valueOf(page.getSecondCardBalance() + 100);
        $(firstCardButton).click();
        $("[data-test-id=amount] input").setValue(sumToAdd);
        $("[data-test-id=from] input").setValue(secondCardNumber);
        $("[data-test-id=action-transfer]").click();
        $(notificationSelector).shouldHave(exactText(alertAboveBalance));
    }

    @Test
    void shouldNotTransferFromFirstCardAboveItsBalance() {
        DashboardPage page = new DashboardPage();
        String sumToAdd = String.valueOf(page.getFirstCardBalance() + 100);
        $(secondCardButton).click();
        $("[data-test-id=amount] input").setValue(sumToAdd);
        $("[data-test-id=from] input").setValue(firstCardNumber);
        $("[data-test-id=action-transfer]").click();
        $(notificationSelector).shouldHave(exactText(alertAboveBalance));
    }
}