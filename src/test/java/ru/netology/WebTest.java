package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static com.codeborne.selenide.Selenide.$;
import org.openqa.selenium.By;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }
    public String genFormatDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(3);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formatDate = formatter.format(futureDate);
        return formatDate;

    }


    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");

    }


    @AfterEach
    void tesrDwn() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTest() throws InterruptedException {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=city] input]")).sendKeys("Москва");
        driver.findElement(By.xpath("//input[@placeholder='Дата встречи']")).sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        String formatDate = genFormatDate();
        driver.findElement(By.xpath("//input[@placeholder='Дата встречи']")).sendKeys(formatDate);
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Елена");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+77772100609");
        driver.findElement(By.className("checkbox")).click();
        String text = driver.findElement(By.className("notification__icon")).getText();
        assertEquals("Успешно!", text.trim());


    }
}



