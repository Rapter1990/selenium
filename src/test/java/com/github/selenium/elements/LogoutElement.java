package com.github.selenium.elements;

import com.github.selenium.utils.WaitSecond;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutElement {

    @FindBy(css=".Header-item.position-relative.mr-0.d-none.d-md-flex")
    private WebElement profileDropdown;

    @FindBy(css=".dropdown-item.dropdown-signout")
    private WebElement signOutButton;

    public LogoutElement(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickProfileDropdown() {
        profileDropdown.click();
    }

    public void clickSignOutButton() {
        signOutButton.click();
    }

    public void logoutProcess(WaitSecond waitSecond) throws InterruptedException {
        clickProfileDropdown();
        waitSecond.wait(1);
        clickSignOutButton();
        waitSecond.wait(2);

    }
}
