package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://test-automation-blog.greenfox.academy/register/");
        driver.manage().window().maximize();
    }

    public WebElement getUserNameInput() {
        return driver.findElement(By.id("user_login-46"));
    }

    public WebElement getFirstNameInput() {
        return driver.findElement(By.id("first_name-46"));
    }

    public WebElement getLastNameInput() {
        return driver.findElement(By.id("last_name-46"));
    }

    public WebElement getEmailInput() {
        return driver.findElement(By.id("user_email-46"));
    }

    public WebElement getPasswordInput() {
        return driver.findElement(By.id("user_password-46"));
    }

    public WebElement getConfirmInput() {
        return driver.findElement(By.id("confirm_user_password-46"));
    }

    public WebElement getPrivacyCheckBox() {
        return driver.findElement(By.className("um-field-checkbox-state"));
    }

    public WebElement getPrivacyLink() {
        return driver.findElement(By.linkText("Show Privacy Statement"));
    }

    public WebElement getRegisterButton() {
        return driver.findElement(By.id("um-submit-btn"));
    }

    public void register(String userName, String firstName, String lastName, String email, String password) {
        getUserNameInput().sendKeys(userName);
        registerWithoutUserName(firstName, lastName, email, password);
    }

    public void openAndCheckPrivacyStatement() {
        String clickLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
        getPrivacyLink().sendKeys(clickLink);
        getPrivacyCheckBox().click();
    }

    public void registerWithoutUserName(String firstName, String lastName, String email, String password) {
        getFirstNameInput().sendKeys(firstName);
        getLastNameInput().sendKeys(lastName);
        getEmailInput().sendKeys(email);
        getPasswordInput().sendKeys(password);
        getConfirmInput().sendKeys(password);
        openAndCheckPrivacyStatement();
        getRegisterButton().click();
    }
}
