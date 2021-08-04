package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage {
    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLogoutLink() {
        return driver.findElement(By.linkText("log out"));
    }

    public WebElement getLogoutMessage() {
        return driver.findElement(By.className("message"));
    }
}
