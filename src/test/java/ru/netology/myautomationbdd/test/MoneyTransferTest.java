package ru.netology.myautomationbdd.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.myautomationbdd.data.DataHelper;
import ru.netology.myautomationbdd.page.DashboardPage;
import ru.netology.myautomationbdd.page.LoginPage;
import ru.netology.myautomationbdd.page.TransferPage;

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
    void shouldTransferMoneyToFirstCard() {
        int sumToAdd = 25;
        DashboardPage page = new DashboardPage();
        int balanceFirstCardBeforeTransfer = page.getFirstCardBalance();
        int balanceSecondCardBeforeTransfer = page.getSecondCardBalance();
        page.pressFirstCardButtonToAdd();
        new TransferPage()
                .transferToCard(sumToAdd, DataHelper.secondCardNumber().getCardsNumber());
        Assertions.assertEquals(balanceFirstCardBeforeTransfer + sumToAdd, page.getFirstCardBalance());
        Assertions.assertEquals(balanceSecondCardBeforeTransfer - sumToAdd, page.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyToSecondCard() {
        int sumToAdd = 250;
        DashboardPage page = new DashboardPage();
        int balanceFirstCardBeforeTransfer = page.getFirstCardBalance();
        int balanceSecondCardBeforeTransfer = page.getSecondCardBalance();
        page.pressSecondCardButtonToAdd();
        new TransferPage()
                .transferToCard(sumToAdd, DataHelper.firstCardNumber().getCardsNumber());
        Assertions.assertEquals(balanceFirstCardBeforeTransfer - sumToAdd, page.getFirstCardBalance());
        Assertions.assertEquals(balanceSecondCardBeforeTransfer + sumToAdd, page.getSecondCardBalance());
    }

    @Test
    void shouldNotTransferFromFirstCardAboveItsBalance() {
        DashboardPage page = new DashboardPage();
        int sumToAdd = page.getFirstCardBalance() + 100;
        int balanceFirstCardBeforeTransfer = page.getFirstCardBalance();
        int balanceSecondCardBeforeTransfer = page.getSecondCardBalance();
        page.pressSecondCardButtonToAdd();
        new TransferPage()
                .transferAboveBalanceAlert(sumToAdd, DataHelper.firstCardNumber().getCardsNumber());
        Assertions.assertEquals(balanceFirstCardBeforeTransfer, page.getFirstCardBalance());
        Assertions.assertEquals(balanceSecondCardBeforeTransfer, page.getSecondCardBalance());
    }

    @Test
    void shouldNotTransferFromSecondCardAboveItsBalance() {
        DashboardPage page = new DashboardPage();
        int sumToAdd = page.getSecondCardBalance() + 100;
        int balanceFirstCardBeforeTransfer = page.getFirstCardBalance();
        int balanceSecondCardBeforeTransfer = page.getSecondCardBalance();
        page.pressFirstCardButtonToAdd();
        new TransferPage()
                .transferAboveBalanceAlert(sumToAdd, DataHelper.secondCardNumber().getCardsNumber());
        Assertions.assertEquals(balanceFirstCardBeforeTransfer, page.getFirstCardBalance());
        Assertions.assertEquals(balanceSecondCardBeforeTransfer, page.getSecondCardBalance());
    }
}