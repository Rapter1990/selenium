package com.github.selenium.elements;

import com.github.selenium.utils.WaitSecond;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindRepositoryElement {

    @FindBy(css=".Header-item.position-relative.mr-0.d-none.d-md-flex")
    private WebElement profileDropdown;

    @FindBy(css=".no-underline.user-profile-link.px-3.pt-2.pb-2.mb-n2.mt-n1.d-block")
    private WebElement profileButton;

    @FindBy(xpath="/html/body/div[1]/div[5]/main/div[1]/div/div/div[2]/div/nav/a[2]")
    private WebElement repositoryButton;

    @FindBy(id="your-repos-filter")
    private WebElement repositorySearchInputField;

    @FindBy(xpath="/html/body/div[1]/div[5]/main/div[2]/div/div[2]/turbo-frame/div/div[2]/div/ul/li/div[1]/div[1]/h3/a")
    private WebElement repositoryFound;

    public FindRepositoryElement(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickProfileDropdown(){
        profileDropdown.click();
    }

    public void clickProfileButton(){
        profileButton.click();
    }

    public void clickRepositoryButton(){
        repositoryButton.click();
    }

    public void fillRepositoryNameInRepositorySearchInputField(String repositoryNameValue){
        repositorySearchInputField.sendKeys(repositoryNameValue);
    }

    public void clickrepositoryFound(){
        repositoryFound.click();
    }

    public void findRepository(WaitSecond waitSecond, String repositoryName) throws InterruptedException {
        clickProfileDropdown();
        waitSecond.wait(1);
        clickProfileButton();
        waitSecond.wait(5);
        clickRepositoryButton();
        waitSecond.wait(5);
        fillRepositoryNameInRepositorySearchInputField(repositoryName);
        waitSecond.wait(3);
        clickrepositoryFound();
        waitSecond.wait(5);
    }
}
