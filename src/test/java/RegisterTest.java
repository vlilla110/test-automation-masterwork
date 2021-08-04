import Pages.AccountPage;
import Pages.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;

public class RegisterTest extends BaseTest {
    RegisterPage registerPage;

    @BeforeEach
    public void setupRegisterPage() {
        registerPage = new RegisterPage(driver);
        registerPage.open();
    }

    @Test
    @DisplayName("Successful registration")
    @Feature("Registration")
    @Description("Complete registration with all data of user")
    public void registrationSuccessfully() {
        registerPage.register("UserMe01", "My", "Test01",
                "test01@test01.com", "testPwd01");
        AccountPage accountPage = new AccountPage(driver);
        assertThat(accountPage.getHeader().getText()).isEqualTo("Account");
    }

    @Test
    @DisplayName("Unsuccessful registration")
    @Feature("Registration")
    @Description("Registration without User Name should give an error message")
    public void registrationUnsuccessfully() {
        registerPage.registerWithoutUserName("My", "Test01",
                "test01@test01.com", "testPwd01");
        assertThat(registerPage.getErrorMessage().getText()).isEqualTo("Username is required");
    }

    @Test
    @DisplayName("Open Privacy Statement and tick the box")
    @Feature("Using Privacy Statement")
    @Description("Open Privacy Statement in a new tab and tick the box")
    public void usingPrivacyStatement() {
        registerPage.openPrivacyStatement();
        registerPage.checkPrivacyStatement();
        assertThat(registerPage.getPrivacyCheckBoxStatus().isSelected()).isTrue();
        String currentTab = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String actualTab : handles) {
            if (!actualTab.equalsIgnoreCase(currentTab)) {
                driver.switchTo().window(actualTab);
            }
        }
        WebElement privacyStatementTitle = driver.findElement(By.id("S3BnEe"));
        assertThat(privacyStatementTitle.isDisplayed()).isTrue();
    }
}
