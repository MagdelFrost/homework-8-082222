package ru.bmstu;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeAll
    static public void config() {
        Configuration.baseUrl = "https://bmstu.ru/";
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
    }

}
