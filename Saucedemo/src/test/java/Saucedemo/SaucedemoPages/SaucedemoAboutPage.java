package Saucedemo.SaucedemoPages;

import Saucedemo.SaucedemoBase.SaucedemoBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaucedemoAboutPage extends SaucedemoBaseTest {

    public SaucedemoAboutPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".title.is-1")
    public WebElement PageTitle;

    //------------------------------------

    public String validatePageTitle(){

        waitForVisibility(PageTitle);
       return PageTitle.getText();

    }
    public String currentURL(){

        String URL = driver.getCurrentUrl();
        return URL;
    }
    public boolean pageTitle(){
        boolean pageTitle = false;
        try {
            waitForVisibility(PageTitle);
            return pageTitle = true;
        }catch (Exception e){
        }
        return pageTitle;
    }
}
