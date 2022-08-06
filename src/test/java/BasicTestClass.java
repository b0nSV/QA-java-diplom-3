import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import site.nomoreparties.stellarburgers.EnvConfig;

public abstract class BasicTestClass {

    private static final EnvConfig envConfig = ConfigFactory.create(EnvConfig.class);
    private static final String HOST = envConfig.getHost();
    private static final String BROWSER = envConfig.getBrowser();
    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        switch (BROWSER) {
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "yandexdriver.exe");
                var options = new ChromeOptions();
                options.setBinary("C:\\Users\\Wtme\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                driver = new ChromeDriver(options);
            default:
                break;
        }
        Configuration.baseUrl = HOST;
        Configuration.browserSize = "1920x1080";
    }
}

