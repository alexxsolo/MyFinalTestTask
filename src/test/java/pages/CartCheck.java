package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.addAttachment;

public class CartCheck {
    public void openPage() {
        open("https://colorlon.ru");
    }

    public void setShopPopUp() {
        $(By.linkText("Новорязанское шоссе, 5")).click();
    }

    public void openMenu() {
        $(".header__catalog").click();
    }

    public void openCategory() {
        $(".menu-nav").$(byText("Двери, окна и фурнитура")).hover();
        $(".menu-nav").$(byText("Двери входные")).click();
    }

    public void addFirstProduct() {
        $(".product-card .product-card__button", 0).click();
        $(".product-card .product-card__button_add", 0).should(Condition.visible, Duration.ofSeconds(1)); //не успевает добавить в корзину
    }

    public void addSecondProduct() {
        $(".product-card .product-card__button", 1).click();
        $(".product-card .product-card__button_add", 1).should(Condition.visible, Duration.ofSeconds(1));
    }

    public void openCart() {
        $(".header__icons .header__cart").click();
    }
    public void checkTrueCount() {
        $(".basket__good .basket__good-val").shouldHave(text("2"));
        addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
    }

    public void checkFalseCount() {
        $(".basket__good .basket__good-val").shouldHave(text("123"));
        addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
    }
}
