package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;

public class CartTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";  //чтобы не открывался как планшет
    }

    @AfterEach
    void afterEach() {
        clearBrowserCookies();
        closeWebDriver();

    }
    @Test
    @Owner("asolovev")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Корзина")
    @Story("Проверка корзины")
    @DisplayName("Проверка корректного каунтера в корзине")
    @Description(
            "Этот тест проверяет корректно ли отображается количество добавленных товаров в корзине"
    )
    @Link(value = "Сайт", url = "https://colorlon.ru")
    public void checkCounter() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://colorlon.ru");
        });
        step("Выбираем магазин", () -> {
            $(By.linkText("Новорязанское шоссе, 5")).click();
        });
        step("Открываем мега-меню каталога", () -> {
            $(".header__catalog").click();
        });
        step("Переходим в подкатегорию Двери входные", () -> {
            $(".menu-nav").$(byText("Двери, окна и фурнитура")).hover(); //почему не работает с byPartialLinkText("окна и фурнитура") и если просто $(By.linkText("Двери, окна и фурнитура")) написать ?
            $(".menu-nav").$(byText("Двери входные")).click();
        });
        step("Добавляем первый товар", () -> {
            $(".product-card .product-card__button", 0).click();
            $(".product-card .product-card__button_add", 0).should(Condition.visible, Duration.ofSeconds(1)); //не успевает добавить в корзину
        });
        step("Добавляем второй товар", () -> {
            $(".product-card .product-card__button", 1).click();
            $(".product-card .product-card__button_add", 1).should(Condition.visible, Duration.ofSeconds(1));
        });
        step("Переходим в корзину", () -> {
            $(".header__icons .header__cart").click();
        });
        step("Проверяем количество товаров в корзине", () -> {
            $(".basket__good .basket__good-val").shouldHave(text("2"));
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        });
    }

    @Test
    @Owner("asolovev")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Корзина")
    @Story("Проверка корзины")
    @DisplayName("Проверка каунтера в корзине, если количество некорректное")
    @Description(
            "Этот тест должен падать, чтобы проверить, верно ли мы сверяем количетсво в корзине"
    )
    @Link(value = "Сайт", url = "https://colorlon.ru")
    public void checkFalseCounter() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://colorlon.ru");
        });
        step("Выбираем магазин", () -> {
            $(By.linkText("Новорязанское шоссе, 5")).click();
        });
        step("Открываем мега-меню каталога", () -> {
            $(".header__catalog").click();
        });
        step("Переходим в подкатегорию Двери входные", () -> {
            $(".menu-nav").$(byText("Двери, окна и фурнитура")).hover(); //почему не работает с byPartialLinkText("окна и фурнитура") и если просто $(By.linkText("Двери, окна и фурнитура")) написать ?
            $(".menu-nav").$(byText("Двери входные")).click();
        });
        step("Добавляем первый товар", () -> {
            $(".product-card .product-card__button", 0).click();
            $(".product-card .product-card__button_add", 0).should(Condition.visible, Duration.ofSeconds(1)); //не успевает добавить в корзину
        });
        step("Добавляем второй товар", () -> {
            $(".product-card .product-card__button", 1).click();
            $(".product-card .product-card__button_add", 1).should(Condition.visible, Duration.ofSeconds(1));
        });
        step("Переходим в корзину", () -> {
            $(".header__icons .header__cart").click();
        });
        step("Проверяем, что при некорректном количестве тест падает", () -> {
            $(".basket__good .basket__good-val").shouldHave(text("123"));
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        });
    }
}

