import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    WebDriver driver;

    @BeforeEach
    public void setup() throws IOException {
        String browser;
        Properties properties = new Properties();
        InputStream propertiesStream = this.getClass().getResourceAsStream("/test.properties");
        properties.load(propertiesStream);
        browser = properties.getProperty("browser");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
        } else  {
            WebDriverManager.edgedriver().setup();
            this.driver = new EdgeDriver();
        }
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}