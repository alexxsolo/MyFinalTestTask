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
    public CartCheck openPage() {
        open("https://colorlon.ru");
        return this;
    }

    public CartCheck setShopPopUp() {
        $(By.linkText("Новорязанское шоссе, 5")).click();
        return this;
    }

    public CartCheck openMenu() {
        $(".header__catalog").click();
        return this;
    }

    public CartCheck openCategory() {
        $(".menu-nav").$(byText("Двери, окна и фурнитура")).hover();
        $(".menu-nav").$(byText("Двери входные")).click();
        return this;
    }

    public CartCheck addFirstProduct() {
        $(".product-card .product-card__button", 0).click();
        $(".product-card .product-card__button_add", 0).should(Condition.visible, Duration.ofSeconds(1)); //не успевает добавить в корзину
        return this;
    }

    public CartCheck addSecondProduct() {
        $(".product-card .product-card__button", 1).click();
        $(".product-card .product-card__button_add", 1).should(Condition.visible, Duration.ofSeconds(1));
        return this;
    }

    public CartCheck openCart() {
        $(".header__icons .header__cart").click();
        return this;
    }
    public CartCheck checkTrueCount() {
        $(".basket__good .basket__good-val").shouldHave(text("2"));
        addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        return this;
    }

    public CartCheck checkFalseCount() {
        $(".basket__good .basket__good-val").shouldHave(text("123"));
        addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        return this;
    }

}
