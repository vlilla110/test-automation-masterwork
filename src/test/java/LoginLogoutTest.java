import Pages.HomePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class LoginLogoutTest extends BaseTest {
    LoginPage loginPage;

    @BeforeEach
    public void setupLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test
    @DisplayName("Successful login")
    @Feature("Login")
    @Description("Successful login with a previously registered User Name")
    public void loginSuccessfully() {
        loginPage.login("UserMe00", "testPwd00");
        HomePage home = new HomePage(driver);
        assertThat(home.getHeader().getText()).isEqualTo("Hello, World!");
    }

    @Test
    @DisplayName("Unsuccessful login")
    @Feature("Login")
    @Description("Login with an unregistered User Name should give the error message")
    public void loginUnsuccessfully() {
        loginPage.login("UserMe03", "testPwd03");
        assertThat(loginPage.getErrorMessage().getText())
                .isEqualTo("Unknown username. Check again or try your email address.");
    }

    @Test
    @DisplayName("Successful logout with a logged-in user")
    @Feature("Logout")
    @Description("Logged-in user log out successfully should be give the logout message")
    public void logoutSuccessfully() {
        String logoutMessage = "You are now logged out.";
        loginPage.login("UserMe00", "testPwd00");
        HomePage homePage = new HomePage(driver);
        homePage.getLogoutLink().click();
        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.getLogoutLink().click();
        assertThat(logoutPage.getLogoutMessage().getText()).isEqualTo(logoutMessage);
    }
}
