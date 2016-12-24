package com.mycompany.app;


import org.junit.Test;
import org.openqa.selenium.By;
import com.codeborne.selenide.CollectionCondition;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleTest {


    @Test
    public void userCanSearchKeywordWithGoogle() throws Exception {
        open("https://www.google.com");
        $(By.name("q")).setValue("Selenide").pressEnter();
        //$$("#ires li.g").shouldHave(CollectionCondition.size(10));
        $(".r>a").shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));

    }
}

