import Pages.RegisterPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.*;

public class RegisterTest extends BaseTest {

    @Test
    public void registrationSuccessfully() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register("UserMe01", "My", "Test01",
                "test01@test01.com", "testPwd01");
        WebElement actualHeader = driver.findElement(By.className("page-header-title"));
        //assertThat(actualHeader.getText()).isEqualTo("Account");
    }

    @Test
    @DisplayName("Registration without User Name should give an error message.")
    public void registrationUnsuccessfully() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.registerWithoutUserName("My", "Test01",
                "test01@test01.com", "testPwd01");
        WebElement actualError = driver.findElement(By.className("um-field-error"));
        assertThat(actualError.getText()).isEqualTo("Username is required");
    }

    @Test
    @DisplayName("Open Privacy Statement and check the box.")
    public void usingPrivacyStatement() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.openAndCheckPrivacyStatement();
        WebElement checkBox = driver.findElement(By.name("privacy_statement[]"));
        assertThat(checkBox.isSelected()).isTrue();
    }

}
