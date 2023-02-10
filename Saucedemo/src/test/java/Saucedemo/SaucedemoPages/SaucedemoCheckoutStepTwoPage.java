package Saucedemo.SaucedemoPages;

import Saucedemo.SaucedemoBase.SaucedemoBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaucedemoCheckoutStepTwoPage extends SaucedemoBaseTest {
    public SaucedemoCheckoutStepTwoPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="finish")
    public WebElement FinishButton;

    @FindBy(className = "complete-text")
    public WebElement OrderFinished;

    //------------------------------------------------

    public void clickOnFinishButton(){
        //scrollToElement(FinishButton);
        waitForClickability(FinishButton);
        FinishButton.click();
    }
    public String orderFinishedNotification(){

        waitForVisibility(OrderFinished);
        String order = OrderFinished.getText();
        return order;
    }
    public String currentURL(){

        String URL = driver.getCurrentUrl();
        return URL;
    }
}
