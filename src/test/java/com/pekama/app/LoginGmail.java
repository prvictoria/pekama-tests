package com.pekama.app;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.assertNoJavascriptErrors;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by VatslauX on 25-Dec-16.
 */
public class LoginGmail {
    @Test
    public void open_page() {
        open("http://gmail.com/");

    };
}