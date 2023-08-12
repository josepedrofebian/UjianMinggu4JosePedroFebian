package com.juaracoding.pages;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='button wc-forward']")
    WebElement btnViewCart;
    @FindBy(xpath = "//a[@class='checkout-button button alt wc-forward']")
    WebElement btnCheckout;
    @FindBy(xpath = "//input[@id='billing_first_name']")
    WebElement fieldFirstName;
    @FindBy(xpath = "//input[@id='billing_last_name']")
    WebElement fieldLastName;
    @FindBy(id = "billing_country")
    WebElement dropDownCountry;
    @FindBy(xpath = "//input[@id='billing_address_1']")
    WebElement fieldStreetAddress;
    @FindBy(xpath = "//input[@id='billing_city']")
    WebElement fieldTownCity;
    @FindBy(id = "billing_state")
    WebElement dropDownProvince;
    @FindBy(xpath = "//input[@id='billing_postcode']")
    WebElement fieldPostalCode;
    @FindBy(xpath = "//input[@id='billing_phone']")
    WebElement fieldPhoneNumber;
    @FindBy(xpath = "//input[@id='billing_email']")
    WebElement fieldEmail;
    @FindBy(xpath = "//input[@id='terms']")
    WebElement checkBox;
    @FindBy(xpath = "//button[@id='place_order']")
    WebElement btnPlaceOrder;
    @FindBy(xpath = "//p[@class='woocommerce-thankyou-order-received']")
    WebElement textCheckoutSuccess;
    @FindBy(xpath = "//li[contains(text(),'Please read and accept the terms and conditions to')]")
    WebElement textMessageErrorNoClickChecbox;

    public void checkout(String paramFirstName, String paramLastName, String paramValueCountry,
                         String paramStreetAddress,
                         String paramfieldTownCity, String paramValueProvince,
                         String paramPostalCode, String paramPhoneNumber, String paramEmail, boolean checkBoxValue) {
        try{
            btnViewCart.click(); // click button view cart
            DriverSingleton.scroll(0, 500); // scroll ke bawah
            btnCheckout.click(); // click button progress to checkout
        }catch (Exception ignored){
        }
        DriverSingleton.scroll(0, 500); // scroll ke bawah
        fieldFirstName.clear(); // membersihkan field firstName untuk mencegah auto complete
        fieldFirstName.sendKeys(paramFirstName); // input field first dari paramFirstName
        fieldLastName.clear(); // membersihkan field last name untuk mencegah auto complete
        fieldLastName.sendKeys(paramLastName); // input field last dari paramLastName
        DriverSingleton.selectDropdownByValue(dropDownCountry, paramValueCountry);
        fieldStreetAddress.clear(); // membersihkan field Street address untuk mencegah auto complete
        fieldStreetAddress.sendKeys(paramStreetAddress); // input field Street address dari paramStreetAdress
        fieldTownCity.clear(); // membersihkan field town city untuk mencegah auto complete
        fieldTownCity.sendKeys(paramfieldTownCity); // input field Town City dari paramTownCity
        DriverSingleton.selectDropdownByValue(dropDownProvince, paramValueProvince);
        fieldPostalCode.clear(); // membersihkan field porstal code untuk mencegah auto complete
        fieldPostalCode.sendKeys(paramPostalCode); // input field Postal Code dari paramPostalCode
        fieldPhoneNumber.clear(); // membersihkan field phone number untuk mencegah auto complete
        fieldPhoneNumber.sendKeys(paramPhoneNumber); // input field Phone dari paramPhoneNumber
        fieldEmail.clear(); // membersihkan field email untuk mencegah auto complete
        fieldEmail.sendKeys(paramEmail); // input field Email dari paramEmail
        DriverSingleton.scroll(0, -250); // scroll keatas
        if (checkBoxValue){ // membuat kondisi untuk klik checkbox
            checkBox.click(); // click checkbox
            btnPlaceOrder.click(); // click button place order
        }else {
            btnPlaceOrder.click(); // click button place order
        }
    }

    public String getCheckoutSuccess() {
        return textCheckoutSuccess.getText();
    }

    public String getErrorMessageNoClickCheckbox() {
        return textMessageErrorNoClickChecbox.getText();
    }

}
