package com.github.selenium.process;

import com.github.selenium.constants.Constants;
import com.github.selenium.elements.FindRepositoryElement;
import com.github.selenium.elements.LoginPageElement;
import com.github.selenium.elements.LogoutElement;
import com.github.selenium.elements.RepositoryCheckElement;
import com.github.selenium.utils.WaitSecond;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GithubProcess {

    private static WebDriver driver;

    private static WaitSecond waitSecond;

    @Value("${github-username}")
    String usernameOrEmailField;

    @Value("${github-password}")
    String passwordField;

    @Value("${github-searchPhrase}")
    String searchPhrase;

    @Value("${github-reporitoryName}")
    String repositoryName;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        waitSecond = new WaitSecond();
    }

    @Test
    public void login() throws InterruptedException {

        driver.get(Constants.LOGIN_URL);

        LoginPageElement loginPageElement = new LoginPageElement(driver);

        loginPageElement.loginProcess(usernameOrEmailField,passwordField);

        waitSecond.wait(2); // 2 second

    }

    @Test
    public void repositoryCheck() throws InterruptedException {

        login();

        driver.get(Constants.BASEURL);

        RepositoryCheckElement repositoryCheckElement = new RepositoryCheckElement(driver);
        repositoryCheckElement.checkRepository(searchPhrase);

        waitSecond.wait(2); // 2 second

        List<String> actualItems = driver.findElements(By.cssSelector(".repo-list-item")).stream()
                .map(element -> element.getText().toLowerCase())
                .collect(Collectors.toList());

        List<String> expectedItems = actualItems.stream()
                .filter(item -> item.contains(searchPhrase))
                .collect(Collectors.toList());

        Assertions.assertEquals(expectedItems, actualItems);
    }

    @Test
    public void findRepositoryForAuthenticatedUser() throws InterruptedException {

        login();

        driver.get(Constants.BASEURL);

        waitSecond.wait(1); // 1 second

        FindRepositoryElement findRepositoryElement = new FindRepositoryElement(driver);
        findRepositoryElement.findRepository(waitSecond,repositoryName);

    }

    @Test
    public void logout() throws InterruptedException {

        login();

        driver.get(Constants.BASEURL);

        waitSecond.wait(1); // 1 second

        LogoutElement logoutElement = new LogoutElement(driver);
        logoutElement.logoutProcess(waitSecond);

    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
