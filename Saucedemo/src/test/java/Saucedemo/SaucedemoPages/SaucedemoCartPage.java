package Saucedemo.SaucedemoPages;

import Saucedemo.SaucedemoBase.SaucedemoBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SaucedemoCartPage extends SaucedemoBaseTest {public SaucedemoCartPage(){PageFactory.initElements(driver, this);}


    @FindBy(id="checkout")
    public WebElement CheckoutButton;
    @FindBy(className = "title")
    public WebElement PageHeader;

    //---------------------------------------------------
public void clickOnCheckoutButton(){

        waitForClickability(CheckoutButton);
        CheckoutButton.click();
}
public String pageHeader(){

        waitForVisibility(PageHeader);
        return PageHeader.getText();

}
    public String currentURL(){

        String URL = driver.getCurrentUrl();
        return URL;
    }
    public boolean errorNotificatonEmptyCart(){
        boolean fake = false;
        return fake;
    }}
