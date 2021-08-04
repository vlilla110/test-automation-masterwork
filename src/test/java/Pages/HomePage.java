package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://test-automation-blog.greenfox.academy/");
        driver.manage().window().maximize();
    }

    public WebElement getHeader() {
        return driver.findElement(By.className("page-header-title"));
    }

    public WebElement getAccountLink() {
        return driver.findElement(By.linkText("Account"));
    }

    public WebElement getLogoutLink() {
        return driver.findElement(By.linkText("Logout"));
    }

    public List<WebElement> getListOfBlogs() {
        return driver.findElements(By.className("blog-entry-inner"));
    }

    public WebElement getOlderPosts() {
        return driver.findElement(By.className("older-posts"));
    }

    public WebElement getNewerPosts() {
        return driver.findElement(By.className("newer-posts"));
    }

    public void toNextPage() {
        getOlderPosts().click();
    }
}
