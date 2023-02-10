package Saucedemo.SaucedemoBase;

import Saucedemo.SaucedemoPages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class SaucedemoBaseTest {

        public static WebDriver driver;
        public static WebDriverWait wdwait;
        public static ExcelReader excelReader;
        public String homeURL;
        public SaucedemoHomePage saucedemoHomePage;
        public SaucedemoInventoryPage saucedemoInventoryPage;
        public SaucedemoCartPage saucedemoCartPage;
        public SaucedemoCheckoutStepOnePage saucedemoCheckoutStepOnePage;
        public SaucedemoCheckoutStepTwoPage saucedemoCheckoutStepTwoPage;
        public SaucedemoAboutPage saucedemoAboutPage;
        @BeforeClass
        public void setUp() throws IOException {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
            excelReader = new ExcelReader("src/test/java/Saucedemo/TestData.xlsx");
            homeURL = excelReader.getStringData("URL", 1, 0);
        }
        public void waitForVisibility(WebElement element) {
            wdwait.until(ExpectedConditions.visibilityOf(element));
        }
        public void waitForClickability(WebElement element) {
            wdwait.until(ExpectedConditions.elementToBeClickable(element));
        }
        public void scrollToElement(WebElement element) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }
        public boolean isDisplayed(WebElement element) {
            boolean webelement = false;
            try {
                webelement = element.isDisplayed();
            } catch (Exception e) {

            }
            return webelement;
        }
        
    }


