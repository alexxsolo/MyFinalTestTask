package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartCheck;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class CartTest {

    CartCheck cartCheck = new CartCheck();

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
    @DisplayName("Проверка корректного количества товара в корзине")
    @Description(
            "Этот тест проверяет корректно ли отображается количество добавленных товаров в корзине")
    @Link(value = "Сайт", url = "https://colorlon.ru")

    public void checkCounter() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            cartCheck.openPage();
        });
        step("Выбираем магазин", () -> {
            cartCheck.setShopPopUp();
        });
        step("Открываем мега-меню каталога", () -> {
            cartCheck.openMenu();
        });
        step("Переходим в подкатегорию Двери входные", () -> {
            cartCheck.openCategory();
        });
        step("Добавляем первый товар", () -> {
            cartCheck.addFirstProduct();
        });
        step("Добавляем второй товар", () -> {
            cartCheck.addSecondProduct();
        });
        step("Переходим в корзину", () -> {
            cartCheck.openCart();
        });
        step("Проверяем количество товаров в корзине", () -> {
            cartCheck.checkTrueCount();
        });
    }

    @Test
    @Owner("asolovev")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Корзина")
    @Story("Проверка корзины")
    @DisplayName("Проверка количества товаров в корзине, если оно некорректное")
    @Description(
            "Этот тест должен падать, чтобы проверить, верно ли мы сверяем количетсво в корзине")
    @Link(value = "Сайт", url = "https://colorlon.ru")

    public void checkFalseCounter() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            cartCheck.openPage();
        });
        step("Выбираем магазин", () -> {
            cartCheck.setShopPopUp();
        });
        step("Открываем мега-меню каталога", () -> {
            cartCheck.openMenu();
        });
        step("Переходим в подкатегорию Двери входные", () -> {
            cartCheck.openCategory();
        });
        step("Добавляем первый товар", () -> {
            cartCheck.addFirstProduct();
        });
        step("Добавляем второй товар", () -> {
            cartCheck.addSecondProduct();
        });
        step("Переходим в корзину", () -> {
            cartCheck.openCart();
        });
        step("Проверяем, что при некорректном количестве тест падает", () -> {
            cartCheck.checkFalseCount();
        });
    }
}

