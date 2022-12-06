package com.github.selenium.elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RepositoryCheckElement {

    @FindBy(css="[name='q']")
    private WebElement searchInput;

    public RepositoryCheckElement(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private void fillInputInSearchInput(String searchInputValue){
        searchInput.sendKeys(searchInputValue);
    }

    private void pressEnter(){
        searchInput.sendKeys(Keys.ENTER);
    }

    public void checkRepository(String searchPhrase){
        fillInputInSearchInput(searchPhrase);
        pressEnter();
    }
}
