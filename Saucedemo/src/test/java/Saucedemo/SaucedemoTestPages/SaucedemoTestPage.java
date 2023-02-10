package Saucedemo.SaucedemoTestPages;


import Saucedemo.SaucedemoBase.SaucedemoBaseTest;
import Saucedemo.SaucedemoPages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;




public class SaucedemoTestPage extends SaucedemoBaseTest {

    @BeforeMethod
    public void pageSetUp() {

        driver.manage().window().maximize();
        driver.get(homeURL);
        saucedemoHomePage = new SaucedemoHomePage();
        saucedemoInventoryPage = new SaucedemoInventoryPage();
        saucedemoCartPage = new SaucedemoCartPage();
        saucedemoCheckoutStepOnePage = new SaucedemoCheckoutStepOnePage();
        saucedemoCheckoutStepTwoPage = new SaucedemoCheckoutStepTwoPage();
        saucedemoAboutPage = new SaucedemoAboutPage();

    }
    public void multipleUserNameAndPasswordCkecking(){
        for (int i = 1; i <= excelReader.getLastRow("Login"); i++) {
            String validUsername = excelReader.getStringData("Login", i, 0);
            String validPassword = excelReader.getStringData("Login", i, 1);
            saucedemoHomePage.insertUserName(validUsername);
            saucedemoHomePage.insertPassword(validPassword);
            saucedemoHomePage.clickOnloginButton();
            Assert.assertEquals(saucedemoInventoryPage.currentURL(), "https://www.saucedemo.com/inventory.html");
            Assert.assertEquals(saucedemoInventoryPage.pageHeader(), "PRODUCTS");
            saucedemoInventoryPage.clickOnSidebarMenu();
            saucedemoInventoryPage.clickOnLogOutButton();
        }

    }

    public void validCredentials() {

            String validUsername = excelReader.getStringData("Login", 1, 0);
            String validPassword = excelReader.getStringData("Login", 1, 1);
            saucedemoHomePage.insertUserName(validUsername);
            saucedemoHomePage.insertPassword(validPassword);
    }

    public void invalidUsername() {
        String invalidUsername = excelReader.getStringData("Login", 1, 2);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        saucedemoHomePage.insertUserName(invalidUsername);
        saucedemoHomePage.insertPassword(validPassword);
    }

    public void invalidPassword() {
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String invalidPassword = excelReader.getStringData("Login", 1, 3);
        saucedemoHomePage.insertUserName(validUsername);
        saucedemoHomePage.insertPassword(invalidPassword);
    }

    public void emptyUsername() {

        String validPassword = excelReader.getStringData("Login", 1, 1);
        saucedemoHomePage.insertPassword(validPassword);

    }

    public void emptyPassword() {

        String validUsername = excelReader.getStringData("Login", 1, 0);
        saucedemoHomePage.insertUserName(validUsername);
    }
    public void multipleLockedUsers() {

        for (int i = 1; i <= excelReader.getLastRow("Locked"); i++) {
            String lockedUsername = excelReader.getStringData("Locked", i, 0);
            String lockedPassword = excelReader.getStringData("Locked", i, 1);
            saucedemoHomePage.insertUserName(lockedUsername);
            saucedemoHomePage.insertPassword(lockedPassword);
            saucedemoHomePage.clickOnloginButton();
            Assert.assertEquals(saucedemoHomePage.invalidLoginUsernameAndPasswordNotificationText(), "Epic sadface: Sorry, this user has been locked out.");
            Assert.assertTrue(saucedemoHomePage.invalidLoginUsernameAndPasswordNotification());
        }
    }
    public void inputUserDataAndFinishOrder(){
        for (int i = 1; i <= excelReader.getLastRow("UserInfo"); i++) {
            String FirstName = excelReader.getStringData("UserInfo", i, 0);
            String LastName = excelReader.getStringData("UserInfo", i, 1);
            String zipCode = excelReader.getStringData("UserInfo", i, 2);
            saucedemoCheckoutStepOnePage.insertFirstNameField(FirstName);
            saucedemoCheckoutStepOnePage.insertLastNameField(LastName);
            saucedemoCheckoutStepOnePage.insertZIPPostalCodeField(zipCode);
            saucedemoCheckoutStepOnePage.clickOnContinueButton();
            Assert.assertEquals(saucedemoInventoryPage.currentURL(), "https://www.saucedemo.com/checkout-step-two.html");
            saucedemoCheckoutStepTwoPage.clickOnFinishButton();
            Assert.assertEquals(saucedemoCheckoutStepTwoPage.orderFinishedNotification(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
            Assert.assertEquals(saucedemoCheckoutStepTwoPage.currentURL(), "https://www.saucedemo.com/checkout-complete.html");
        }
    }
    @Test(priority = 10)
    public void homePage() {

        Assert.assertEquals(saucedemoHomePage.currentURL(), homeURL);

    }

    @Test(priority = 20)
    public void userCanLogin() {

        multipleUserNameAndPasswordCkecking();
    }

    @Test(priority = 30)
    public void userCanLogout() {

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoInventoryPage.clickOnSidebarMenu();
        saucedemoInventoryPage.clickOnLogOutButton();
        Assert.assertEquals(saucedemoInventoryPage.currentURL(), "https://www.saucedemo.com/");
        Assert.assertFalse(saucedemoInventoryPage.visibilityOflogoutButton());
    }
    @Test(priority = 40)
    public void userCannotLoginWithInvalidUsername() {

        invalidUsername();
        saucedemoHomePage.clickOnloginButton();
        Assert.assertEquals(saucedemoHomePage.invalidLoginUsernameAndPasswordNotificationText(), "Epic sadface: Username and password do not match any user in this service");
        Assert.assertTrue(saucedemoHomePage.invalidLoginUsernameAndPasswordNotification());
    }

    @Test(priority = 50)
    public void userCannotLoginWithInvalidPassword() {

        invalidPassword();
        saucedemoHomePage.clickOnloginButton();
        Assert.assertEquals(saucedemoHomePage.invalidLoginUsernameAndPasswordNotificationText(), "Epic sadface: Username and password do not match any user in this service");
        Assert.assertTrue(saucedemoHomePage.invalidLoginUsernameAndPasswordNotification());
    }
    @Test(priority = 60)
    public void lockedUserCannotLogin() {

        multipleLockedUsers();
    }

    @Test(priority = 70)
    public void userCannotLoginWithEmptyusernameAndPasswordfield() {

        saucedemoHomePage.clearUsernameField();
        saucedemoHomePage.clearPasswordField();
        saucedemoHomePage.clickOnloginButton();
        Assert.assertEquals(saucedemoHomePage.invalidLoginUsernameAndPasswordNotificationText(), "Epic sadface: Username is required");
        Assert.assertTrue(saucedemoHomePage.invalidLoginUsernameAndPasswordNotification());

    }

    @Test(priority = 80)
    public void userCannotLoginWithEmptyUsernamefield() {


        saucedemoHomePage.clearUsernameField();
        emptyUsername();
        saucedemoHomePage.clickOnloginButton();
        Assert.assertEquals(saucedemoHomePage.invalidLoginUsernameAndPasswordNotificationText(), "Epic sadface: Username is required");
        Assert.assertTrue(saucedemoHomePage.invalidLoginUsernameAndPasswordNotification());

    }

    @Test(priority = 90)
    public void userCannotLoginWithEmptyPasswordfield() {

        saucedemoHomePage.clearPasswordField();
        emptyPassword();
        saucedemoHomePage.clickOnloginButton();
        Assert.assertEquals(saucedemoHomePage.invalidLoginUsernameAndPasswordNotificationText(), "Epic sadface: Password is required");
        Assert.assertTrue(saucedemoHomePage.invalidLoginUsernameAndPasswordNotification());

    }

    @Test(priority = 100)
    public void userCanCloseErrorNotification() {

        saucedemoHomePage.clearPasswordField();
        invalidPassword();
        saucedemoHomePage.clickOnloginButton();
        saucedemoHomePage.closeErrorNotificationButton();
        Assert.assertFalse(saucedemoHomePage.errorNotificationIsClosed());
    }
    @Test(priority = 110)
    public void selectFromDropDownMenuNameAZ(){

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoHomePage.selectfromdropdownMenuNameAZ();
        Assert.assertEquals(saucedemoHomePage.validateDropdownSelectedNameAZ(), "Name (A to Z)");
    }
    @Test(priority = 120)
    public void selectFromDropDownMenuNameZA(){

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoHomePage.selectfromdropdownMenuNameZA();
        Assert.assertEquals(saucedemoHomePage.validateDropdownSelectedNameZA(), "Name (Z to A)");
    }
    @Test(priority = 130)
    public void selectFromDropDownMenuPriceLToH(){

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoHomePage.selectfromdropdownMenuPriceLToH();
        Assert.assertEquals(saucedemoHomePage.validateDropdownSelectedPriceLToH(), "Price (low to high)");
    }
    @Test(priority = 140)
    public void selectFromDropDownPriceHToL(){

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoHomePage.selectfromdropdownMenuPriceHToL();
        Assert.assertEquals(saucedemoHomePage.validateDropdownSelectedPriceHToL(), "Price (high to low)");
    }

    @Test(priority = 150)
    public void userCanAddToBasketOneItem() {

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoInventoryPage.clickOnAddToCardOnesieButton();
        saucedemoInventoryPage.clickOnshoppingCart();
        Assert.assertEquals(saucedemoInventoryPage.currentURL(), "https://www.saucedemo.com/cart.html");
        Assert.assertEquals(saucedemoInventoryPage.pageHeader(), "YOUR CART");
    }
    @Test(priority = 160)
    public void userCanRemoveFromBasketOneItem() {

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoInventoryPage.clickOnRemoveFromCartOnesiButton();
        saucedemoInventoryPage.clickOnshoppingCart();
        Assert.assertEquals(saucedemoInventoryPage.currentURL(), "https://www.saucedemo.com/cart.html");
        Assert.assertEquals(saucedemoInventoryPage.pageHeader(), "YOUR CART");
    }

    @Test(priority = 170)
    public void userCanCheckout() {

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoInventoryPage.clickOnAddToCardOnesieButton();
        saucedemoInventoryPage.clickOnshoppingCart();
        saucedemoCartPage.clickOnCheckoutButton();
        Assert.assertEquals(saucedemoInventoryPage.currentURL(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(saucedemoInventoryPage.pageHeader(), "CHECKOUT: YOUR INFORMATION");
    }

    @Test(priority = 180)
    public void userCanBuyOneItem() {

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoInventoryPage.clickOnAddToCardOnesieButton();
        saucedemoInventoryPage.clickOnshoppingCart();
        saucedemoCartPage.clickOnCheckoutButton();
        inputUserDataAndFinishOrder();
    }
    @Test(priority = 190)
    public void userCanCloseSidebarMenu(){
        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoInventoryPage.clickOnSidebarMenu();
        saucedemoInventoryPage.clickOnCloseSidebarButton();
        Assert.assertTrue(saucedemoInventoryPage.visibilityOfCloseSidebarButton());
    }
    @Test(priority = 200)
    public void userCanGoToAboutPage(){
        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoInventoryPage.clickOnSidebarMenu();
        saucedemoInventoryPage.clickOnAbboutButton();
        Assert.assertEquals(saucedemoAboutPage.currentURL(), "https://saucelabs.com/");
        Assert.assertEquals(saucedemoAboutPage.validatePageTitle(), "Pass or fail. The world relies on your code.");
        Assert.assertTrue(saucedemoAboutPage.pageTitle());
    }
    @Test(priority = 210)
    public void userCanResetAppState(){
        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoInventoryPage.clickOnSidebarMenu();
        saucedemoInventoryPage.clickOnAddToCardOnesieButton();
        saucedemoInventoryPage.clickOnResetAppStateButton();
        saucedemoInventoryPage.clickOnCloseSidebarButton();
        Assert.assertTrue(saucedemoInventoryPage.visibilityOfCartNumberOfItems());
        Assert.assertFalse(saucedemoInventoryPage.visibilityOfShoppingCartBadge());
        Assert.assertFalse(saucedemoInventoryPage.visibilityOfRemoveButton());

    }
    @Test(priority = 220)
    public void userCannotCheckoutWithEmptyCart() {

        validCredentials();
        saucedemoHomePage.clickOnloginButton();
        saucedemoInventoryPage.clickOnshoppingCart();
        saucedemoCartPage.clickOnCheckoutButton();
        Assert.assertEquals(saucedemoCartPage.currentURL(), "https://www.saucedemo.com/cart.html");
        Assert.assertEquals(saucedemoCartPage.pageHeader(), "YOUR CART");
        Assert.assertEquals(saucedemoCartPage.errorNotificatonEmptyCart(), "You cannot proceed with empty card");
}
}