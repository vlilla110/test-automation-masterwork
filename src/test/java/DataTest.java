import Pages.AccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.UserPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    UserPage userPage;

    @BeforeEach
    public void setupDataTest() {
       homePage = new HomePage(driver);
       loginPage = new LoginPage(driver);
       accountPage = new AccountPage(driver);
       userPage = new UserPage(driver);
    }

    @Test
    @DisplayName("Data listing from the page")
    @Feature("Listing data")
    @Description("Size of blog posts should be 10 on one page")
    public void listingData() {
        homePage.open();
        assertThat(homePage.getListOfBlogs().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Navigate to the next page should be successful")
    @Feature("Pagination")
    @Description("After more than one page long data list should navigate to the next page")
    public void pagination() {
        homePage.open();
        homePage.toNextPage();
        assertThat(homePage.getNewerPosts().isDisplayed()).isTrue();
    }

    @Test
    @DisplayName("New profile description should add successfully")
    @Feature("Input of new data")
    @Description("Navigate to user's page and add a profile description to the text area")
    public void inputData() {
        String profileDescription = "Hello, it's a test so I have to write something here.";
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        homePage.getAccountLink().click();
        accountPage.getProfile().click();
        userPage.getProfileEditIcon().click();
        userPage.getEditProfileLink().click();
        userPage.getTextArea().clear();
        userPage.getTextArea().sendKeys(profileDescription);
        userPage.getSubmitButton().click();
        assertThat(userPage.getProfileDescription().getText()).isEqualTo(profileDescription);
    }

    @ParameterizedTest
    @DisplayName("Repetitive data entry from a file")
    @Description("From a csv file should fill the page up with 3 pieces of first name and last name successfully")
    @CsvFileSource(resources = "/account_test_data.csv", numLinesToSkip = 1)
    public void modifiedUserData(String firstName, String lastName, String email) {
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        homePage.getAccountLink().click();
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
    @DisplayName("Modifying First Name should be successful")
    @Feature("Existing data modification")
    @Description("Updating first name field with a new name on the Account page")
    public void changeFirstName() {
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        homePage.getAccountLink().click();
        accountPage.getFirstNameInput().clear();
        accountPage.getFirstNameInput().sendKeys("Test00");
        accountPage.getUpdateAccountButton().click();
        assertThat(accountPage.getSuccessMessage().getText()).isEqualTo("Your account was updated successfully.");
    }

    @Test
    @DisplayName("Deleting profile description should be successful.")
    @Feature("Deleting data")
    @Description("Deleting profile description on the user's page results profile description is not there")
    public void deleteData() {
        loginPage.open();
        loginPage.login("UserMe00", "testPwd00");
        homePage.getAccountLink().click();
        accountPage.getProfile().click();
        userPage.getProfileEditIcon().click();
        userPage.getEditProfileLink().click();
        userPage.getTextArea().clear();
        userPage.getSubmitButton().click();
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()
                -> userPage.getProfileDescription().isDisplayed());
    }

    @Test
    @DisplayName("Saving blog posts into a text file successfully")
    @Feature("Saving data")
    @Description("Saving blog posts from the home page to the text file")
    public void savingPosts() {
        String file = "src/test/resources/blog_posts_list.txt";
        Path filePath = Paths.get(file);
        List<String> fileList = new ArrayList<>(Collections.emptyList());
        homePage.open();
        for (WebElement element : homePage.getListOfBlogs()){
            fileList.add(element.getText());
        }
        try {
            Files.deleteIfExists(filePath);
            Files.write(filePath, fileList);
        } catch (IOException e) {
            System.out.println("Unable to write the file");
        }
        try {
            List<String> lines = Files.readAllLines(filePath);
            assertThat(lines.get(0)).isEqualTo("Post 11");
            assertThat(lines.get(lines.size()-1)).isEqualTo("Test automation rules");
        } catch (IOException e) {
            System.out.println("Unable to read the file");
        }
    }
}
