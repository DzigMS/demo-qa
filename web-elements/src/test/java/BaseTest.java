import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.example.configuration.ConfigProvider;
import org.example.configuration.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

@Slf4j
public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp(){
//        log.info("Initialise web driver for {}", ConfigProvider.getDriverName());
        log.info("act=initialiseDriver driverName={}", ConfigProvider.getDriverName());
        driver = WebDriverProvider.getDriver();
    }

    @AfterEach
    public void tearDown(){
        driver.close();
        log.info("Driver has been closed");
    }
}
