package Saucedemo.SaucedemoPages;

import Saucedemo.SaucedemoBase.SaucedemoBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SaucedemoHomePage extends SaucedemoBaseTest {
    public SaucedemoHomePage(){PageFactory.initElements(driver, this);
    }

    @FindBy(id="user-name")
    public WebElement UserNameField;
    @FindBy(id="password")
    public WebElement PasswordField;
    @FindBy(id="login-button")
    public WebElement LoginButton;
    @FindBy(css = ".error-message-container.error")
    public WebElement InvalidCredentialsNotification;
    @FindBy(css = ".svg-inline--fa.fa-times.fa-w-11")
    public WebElement CloseNotification;
    @FindBy(className = "product_sort_container")
    public WebElement ProductSortDropdown;
    @FindBy(xpath = "//option[text() = 'Name (A to Z)']")
    public WebElement DropdownMenuNameAZ;
    @FindBy(xpath = "//option[text() = 'Name (Z to A)']")
    public WebElement DropdownMenuNameZA;
    @FindBy(xpath = "//option[text() = 'Price (low to high)']")
    public WebElement DropdownMenuPriceLToH;
    @FindBy(xpath = "//option[text() = 'Price (high to low)']")
    public WebElement DropdownMenuPriceHToL;




    //----------------------------------------

    public void insertUserName(String userName){

        wdwait.until(ExpectedConditions.visibilityOf(UserNameField)).clear();
        UserNameField.sendKeys(userName);
    }
    public void insertPassword(String password){

        wdwait.until(ExpectedConditions.visibilityOf(PasswordField)).clear();
        PasswordField.sendKeys(password);
    }
    public void clickOnloginButton(){
        waitForClickability(LoginButton);
        LoginButton.click();
    }
    public String currentURL(){
        String URL = driver.getCurrentUrl();
        return URL;
    }
public String  invalidLoginUsernameAndPasswordNotificationText(){
        waitForVisibility(InvalidCredentialsNotification);
        return InvalidCredentialsNotification.getText();
}
public boolean invalidLoginUsernameAndPasswordNotification(){
        boolean show = false;
        try{
            waitForVisibility(InvalidCredentialsNotification);
            InvalidCredentialsNotification.isDisplayed();
            show = true;
        }catch (Exception e){
    }
        return show;
    }
    public void clearUsernameField(){

        waitForVisibility(UserNameField);
        UserNameField.clear();
    }
    public void clearPasswordField(){

        waitForVisibility(PasswordField);
        PasswordField.clear();
    }
    public void closeErrorNotificationButton(){

        waitForVisibility(CloseNotification);
        CloseNotification.click();
    }
    public boolean errorNotificationIsClosed(){
        boolean closed = false;
        try {
            waitForVisibility(CloseNotification);
            CloseNotification.isDisplayed();
            closed = true;
        }catch (Exception e){
        }
        return closed;
    }
    public void selectfromdropdownMenuNameAZ(){
        waitForVisibility(ProductSortDropdown);
        Select dropdown = new Select(ProductSortDropdown);
        dropdown.selectByIndex(0);
    }
    public String validateDropdownSelectedNameAZ(){

        waitForVisibility(ProductSortDropdown);
        String menuItem = DropdownMenuNameAZ.getText();
        return menuItem;
    }
    public void selectfromdropdownMenuNameZA(){

        waitForVisibility(ProductSortDropdown);
        Select dropdown = new Select(ProductSortDropdown);
        dropdown.selectByIndex(1);
    }
    public String validateDropdownSelectedNameZA(){

        waitForVisibility(ProductSortDropdown);
        String menuItem = DropdownMenuNameZA.getText();
        return menuItem;
    }
    public void selectfromdropdownMenuPriceLToH(){

        waitForVisibility(ProductSortDropdown);
        Select dropdown = new Select(ProductSortDropdown);
        dropdown.selectByIndex(2);
    }
    public String validateDropdownSelectedPriceLToH(){

        waitForVisibility(ProductSortDropdown);
        String menuItem = DropdownMenuPriceLToH.getText();
        return menuItem;
    }
    public void selectfromdropdownMenuPriceHToL(){

        waitForVisibility(ProductSortDropdown);
        Select dropdown = new Select(ProductSortDropdown);
        dropdown.selectByIndex(3);
    }
    public String validateDropdownSelectedPriceHToL(){

        waitForVisibility(ProductSortDropdown);
        String menuItem = DropdownMenuPriceHToL.getText();
        return menuItem;
    }

}

