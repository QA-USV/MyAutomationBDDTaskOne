package ru.netology.myautomationbdd.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.myautomationbdd.data.DataHelper;
import ru.netology.myautomationbdd.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    }

    @Test
    void shouldTransferMoneyToMyOwnCard() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

    }

//        new LoginPage()
//                .validLogin(authInfo)
//                .validVerify(verificationCode);
//
//    }

}
