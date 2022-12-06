package com.github.selenium.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageElement {

    @FindBy(id="login_field")
    private WebElement usernameOrEmail;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(css="input[type='submit']")
    private WebElement signInButton;

    public LoginPageElement(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    private void fillUsernameOrEmailField(String usernameOrEmailValue){
        usernameOrEmail.sendKeys(usernameOrEmailValue);
    }

    private void fillPasswordField(String passwordValue){
        password.sendKeys(passwordValue);
    }

    private void clickSignInButton(){
        signInButton.click();
    }

    public void loginProcess(String usernameOrEmailValue,String passwordValue){
        fillUsernameOrEmailField(usernameOrEmailValue);
        fillPasswordField(passwordValue);
        clickSignInButton();
    }
}
