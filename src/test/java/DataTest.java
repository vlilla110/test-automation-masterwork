import Pages.AccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.UserPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class DataTest extends BaseTest {

    @Test
    @DisplayName("Listing data size should be 10")
    public void listingData() {
        HomePage home = new HomePage(driver);
        home.open();
        assertThat(home.getListOfBlogs().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Navigate to the next page should successfully")
    public void pagination() {
        HomePage home = new HomePage(driver);
        home.open();
        home.toNextPage();
        WebElement newerPosts = driver.findElement(By.className("newer-posts"));
        assertThat(newerPosts.isDisplayed()).isTrue();
    }

    @Test
    @DisplayName("New profile description should add successfully")
    public void inputData() {
        String profileDescription = "Hello, it's a test so I have to write something here.";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        HomePage homePage = new HomePage(driver);
        homePage.getAccountLink().click();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.getProfile().click();
        UserPage userPage = new UserPage(driver);
        userPage.getProfileEditIcon().click();
        userPage.getEditProfileLink().click();
        userPage.getTextArea().clear();
        userPage.getTextArea().sendKeys(profileDescription);
        userPage.getSubmitButton().click();
        assertThat(userPage.getProfileDescription().getText()).isEqualTo(profileDescription);
    }

    @ParameterizedTest
    @DisplayName("Repetitive data entry from file")
    @CsvFileSource(resources = "/account_test_data.csv", numLinesToSkip = 1)
    public void modifiedUserData(String firstName, String lastName, String email) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        HomePage homePage = new HomePage(driver);
        homePage.getAccountLink().click();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.getFirstNameInput().clear();
        accountPage.getLastNameInput().clear();
        accountPage.getUserEmailInput().clear();
        accountPage.getFirstNameInput().sendKeys(firstName);
        accountPage.getLastNameInput().sendKeys(lastName);
        accountPage.getUserEmailInput().sendKeys(email);
        accountPage.getUpdateAccountButton().click();
        assertThat(accountPage.getSuccessMessage().getText()).isEqualTo("Your account was updated successfully.");
        assertThat(accountPage.getFirstNameInput().getAttribute("value")).isEqualTo(firstName);
    }

    @Test
    @DisplayName("Modifying First Name on the Account page should be successful")
    public void changeFirstName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        HomePage homePage = new HomePage(driver);
        homePage.getAccountLink().click();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.getFirstNameInput().clear();
        accountPage.getFirstNameInput().sendKeys("Test00");
        accountPage.getUpdateAccountButton().click();
        assertThat(accountPage.getSuccessMessage().isDisplayed()).isTrue();
        assertThat(accountPage.getSuccessMessage().getText()).isEqualTo("Your account was updated successfully.");
    }

    @Test
    @DisplayName("Deleting profile description should be successful.")
    public void deleteData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        HomePage homePage = new HomePage(driver);
        homePage.getAccountLink().click();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.getProfile().click();
        UserPage userPage = new UserPage(driver);
        userPage.getProfileEditIcon().click();
        userPage.getEditProfileLink().click();
        userPage.getTextArea().clear();
        userPage.getSubmitButton().click();
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()
                -> userPage.getProfileDescription().isDisplayed());
    }

    @Test
    @DisplayName("Saving blog posts into a text file")
    public void savingPosts() {
        String file = "src/test/resources/blog_posts_list.txt";
        Path filePath = Paths.get(file);
        List<String> fileList = new ArrayList<>(Collections.emptyList());
        try {
            Files.write(filePath, fileList);
        } catch (IOException e) {
            System.out.println("Unable to write file");
        }
        HomePage home = new HomePage(driver);
        home.open();
        for (WebElement e : home.getListOfBlogs()){
            fileList.add(e.getText());
        }
        try {
            Files.write(filePath, fileList);
        } catch (IOException e) {
            System.out.println("Unable to write file");
        }
    }
}
