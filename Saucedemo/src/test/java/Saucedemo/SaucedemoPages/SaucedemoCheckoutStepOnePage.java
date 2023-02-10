package Saucedemo.SaucedemoPages;

import Saucedemo.SaucedemoBase.ExcelReader;
import Saucedemo.SaucedemoBase.SaucedemoBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SaucedemoCheckoutStepOnePage extends SaucedemoBaseTest {

    public SaucedemoCheckoutStepOnePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="first-name")
    public WebElement FirstName;

    @FindBy(id="last-name")
    public WebElement LastName;

    @FindBy(id="postal-code")
    public WebElement ZIPPostalCode;

    @FindBy(id="continue")
    public WebElement ContinueButton;
//------------------------------------------

    public void insertFirstNameField(String firstName){

        waitForVisibility(FirstName);
        FirstName.clear();
        FirstName.sendKeys(firstName);
    }
    public void insertLastNameField(String lastName){

        waitForVisibility(LastName);
        LastName.clear();
        LastName.sendKeys(lastName);
    }
    public void insertZIPPostalCodeField(String zipPostalCode){

        waitForVisibility(ZIPPostalCode);
        ZIPPostalCode.clear();
        ZIPPostalCode.sendKeys(zipPostalCode);
    }
    public void clickOnContinueButton(){

        waitForClickability(ContinueButton);
        ContinueButton.click();
    }
    public String currentURL() {

        String URL = driver.getCurrentUrl();
        return URL;
    }
}
