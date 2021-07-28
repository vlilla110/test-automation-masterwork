package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage {
    WebDriver driver;

    public UserPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getProfileEditIcon() {
        return driver.findElement(By.className("um-profile-edit-a"));
    }

    public WebElement getEditProfileLink() {
        return driver.findElement(By.linkText("Edit Profile"));
    }

    public WebElement getTextArea() {
        return driver.findElement(By.id("um-meta-bio"));
    }

    public WebElement getSubmitButton() {
        return driver.findElement(By.className("um-left"));
    }

    public WebElement getProfileDescription() {
        return driver.findElement(By.className("um-meta-text"));
    }
}
