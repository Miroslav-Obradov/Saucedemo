package Saucedemo.SaucedemoPages;


import Saucedemo.SaucedemoBase.SaucedemoBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SaucedemoInventoryPage extends SaucedemoBaseTest{

public SaucedemoInventoryPage(){
    PageFactory.initElements(driver, this);
}

    @FindBy(className = "title")
    public WebElement PageHeader;
    @FindBy(id="react-burger-menu-btn")
    public WebElement SidebarMenu;
    @FindBy(id="logout_sidebar_link")
    public WebElement LogoutButton;
    @FindBy(id="add-to-cart-sauce-labs-onesie")
    public WebElement OrderOnesie;
    @FindBy(className = "shopping_cart_link")
    public WebElement ShoppingCart;
    @FindBy(id="remove-sauce-labs-onesie")
    public WebElement RemoveOnesie;
    @FindBy(id="about_sidebar_link")
    public WebElement AboutButton;
    @FindBy(id="reset_sidebar_link")
    public WebElement ResetAppStateButton;
    @FindBy(id = "react-burger-cross-btn")
    public WebElement CloseSidebarButton;
    @FindBy(xpath = "//*[@id='react-burger-cross-btn'][@tabindex='-1']")
    public WebElement CheckSidebarButton;
    @FindBy(className = "shopping_cart_badge")
    public WebElement CartNumberOfItems;
    @FindBy(className = "shopping_cart_badge")
    public WebElement CartBadge;

//------------------------------------------------
    public String pageHeader() {

        waitForVisibility(PageHeader);
        return PageHeader.getText();
    }
    public String currentURL(){

       String URL = driver.getCurrentUrl();
       return URL;
    }
    public void clickOnSidebarMenu(){
        waitForClickability(SidebarMenu);
        SidebarMenu.click();
    }
    public void clickOnLogOutButton(){
        waitForClickability(LogoutButton);
        LogoutButton.click();
    }
    public boolean visibilityOflogoutButton(){
        boolean logoutButton = false;
        try {
            waitForVisibility(LogoutButton);
            return logoutButton = true;
        }catch (Exception e){
        }
        return logoutButton;
    }
    public void clickOnAddToCardOnesieButton() {

        if(isDisplayed(RemoveOnesie)){
            scrollToElement(RemoveOnesie);
            waitForClickability(RemoveOnesie);
            RemoveOnesie.click();
        }
        scrollToElement(OrderOnesie);
        waitForClickability(OrderOnesie);
        OrderOnesie.click();
    }

    public void clickOnshoppingCart(){

        waitForClickability(ShoppingCart);
        ShoppingCart.click();
    }
    public void clickOnRemoveFromCartOnesiButton(){
        waitForClickability(RemoveOnesie);
        RemoveOnesie.click();

    }
    public void clickOnAbboutButton(){
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

//Check we don't have other windows open already
        assert driver.getWindowHandles().size() == 1;

//Click the link which opens in a new window
        waitForClickability(AboutButton);
        AboutButton.click();

//Wait for the new window or tab
        wdwait.until(numberOfWindowsToBe(1));

//Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

//Wait for the new tab to finish loading content
        wdwait.until(titleIs("Cross Browser Testing, Selenium Testing, Mobile Testing | Sauce Labs"));

    }
public void clickOnResetAppStateButton(){

        waitForClickability(ResetAppStateButton);
        ResetAppStateButton.click();
}
public void clickOnCloseSidebarButton(){

        waitForClickability(CloseSidebarButton);
        CloseSidebarButton.click();
}
public boolean visibilityOfCloseSidebarButton(){
    boolean sidebarButton = false;
    try {
        waitForVisibility(CheckSidebarButton);
        return sidebarButton = true;
    }catch (Exception e){
    }
    return sidebarButton;
}
public boolean visibilityOfCartNumberOfItems(){
    boolean sidebarButton = true;
    try {
        waitForVisibility(CartNumberOfItems);
        return sidebarButton = false;
    }catch (Exception e){
    }
    return sidebarButton;
}

    public boolean visibilityOfRemoveButton(){
        boolean remove = false;
        try {
            waitForVisibility(RemoveOnesie);
            return remove = true;
        }catch (Exception e){
        }
        return remove;
    }
    public boolean visibilityOfShoppingCartBadge(){
        boolean badge = false;
        try {
            waitForVisibility(CartBadge);
            return badge = true;
        }catch (Exception e){
        }
        return badge;
    }

}



