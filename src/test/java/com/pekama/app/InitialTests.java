package com.pekama.app;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class InitialTests {
    @Test
    public void open_page(){
        open("https://google.com");
    }
}
