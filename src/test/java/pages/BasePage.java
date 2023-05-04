package pages;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    public void openMenu() {
        $(".header__catalog").click();
    }
}
