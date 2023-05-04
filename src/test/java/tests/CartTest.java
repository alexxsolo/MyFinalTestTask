package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class CartTest {

    CartPage cartPage = new CartPage();

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
            cartPage.openPage();
        });
        step("Выбираем магазин", () -> {
            cartPage.setShopPopUp();
        });
        step("Открываем мега-меню каталога", () -> {
            cartPage.openMenu();
        });
        step("Переходим в подкатегорию Двери входные", () -> {
            cartPage.openCategory();
        });
        step("Добавляем первый товар", () -> {
            cartPage.addProduct(0);
        });
        step("Добавляем второй товар", () -> {
            cartPage.addProduct(1);
        });
        step("Переходим в корзину", () -> {
            cartPage.openCart();
        });
        step("Проверяем количество товаров в корзине", () -> {
            cartPage.checkTrueCount();
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
            cartPage.openPage();
        });
        step("Выбираем магазин", () -> {
            cartPage.setShopPopUp();
        });
        step("Открываем мега-меню каталога", () -> {
            cartPage.openMenu();
        });
        step("Переходим в подкатегорию Двери входные", () -> {
            cartPage.openCategory();
        });
        step("Добавляем первый товар", () -> {
            cartPage.addFirstProduct();
        });
        step("Добавляем второй товар", () -> {
            cartPage.addSecondProduct();
        });
        step("Переходим в корзину", () -> {
            cartPage.openCart();
        });
        step("Проверяем, что при некорректном количестве тест падает", () -> {
            cartPage.checkFalseCount();
        });
    }
}

