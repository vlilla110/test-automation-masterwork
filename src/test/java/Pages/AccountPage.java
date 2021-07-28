package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage {
    WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getProfile() {
        return driver.findElement(By.linkText("View profile"));
    }

    public WebElement getFirstNameInput() {
        return driver.findElement(By.id("first_name"));
    }

    public WebElement getLastNameInput() {
        return driver.findElement(By.id("last_name"));
    }

    public WebElement getUserEmailInput() {
        return driver.findElement(By.id("user_email"));
    }

    public WebElement getUpdateAccountButton() {
        return driver.findElement(By.id("um_account_submit_general"));
    }

    public WebElement getSuccessMessage() {
        return driver.findElement(By.className("um-notice"));
    }
}
