import Pages.HomePage;
import Pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.*;

public class LoginLogoutTest extends BaseTest {

    @Test
    @DisplayName("Successful registration with a previously registered User Name")
    public void loginSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        WebElement actualHeader = driver.findElement(By.className("page-header-title"));
        assertThat(actualHeader.getText()).isEqualTo("Hello, World!");
    }

    @Test
    @DisplayName("Login with unregistered User Name")
    public void loginUnsuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("UserMe03", "testPwd03");
        WebElement actualErrorMessage = driver.findElement(By.id("login_error"));
        assertThat(actualErrorMessage.getText()).isEqualTo("Unknown username. Check again or try your email address.");
    }

    @Test
    @DisplayName("Logout with a logged-in user.")
    public void logoutSuccessfully() {
        String logoutMessage = "You are now logged out.";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        HomePage homePage = new HomePage(driver);
        homePage.getLogoutLink().click();
        WebElement logout = driver.findElement(By.linkText("log out"));
        logout.click();
        WebElement message = driver.findElement(By.className("message"));
        assertThat(message.getText()).isEqualTo(logoutMessage);
    }
}
