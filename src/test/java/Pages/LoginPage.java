package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://test-automation-blog.greenfox.academy/wp-login.php");
        driver.manage().window().maximize();
    }

    public WebElement getUserName() {
        return driver.findElement(By.id("user_login"));
    }

    public WebElement getPassword() {
        return driver.findElement(By.id("user_pass"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.id("wp-submit"));
    }

    public void login(String userName, String password) {
        getUserName().clear();
        getPassword().clear();
        getUserName().sendKeys(userName);
        getPassword().sendKeys(password);
        getLoginButton().click();
    }

}
