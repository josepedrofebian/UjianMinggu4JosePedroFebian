package com.juaracoding.pages;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProductToCartPage {
    private WebDriver driver;
    JavascriptExecutor js; // menambahkan atribut dari kelas JavascriptExecutor untuk scroll

    public AddProductToCartPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver; // casting dari WebDriver menjadi JavascriptExecutor
    }

    @FindBy(className = "cart-button") // locator button cart
    WebElement btnCart;
    @FindBy(xpath = "//a[@class='button wc-backward']") // locator button return shop
    WebElement btnReturnShop;
    @FindBy(className = "noo-product-inner") // locator item produk
    WebElement productItem;
    @FindBy(id = "pa_color") // locator warna item
    WebElement colorItem;
    @FindBy(id = "pa_size") // locator ukuran item
    WebElement sizeItem;
    @FindBy(className = "single_add_to_cart_button") // locator button add to cart
    WebElement btnAddToCart;
    @FindBy(className = "woocommerce-message") // locator message add to cart success
    WebElement messageAddToCartSuccess;
    @FindBy(xpath = "//a[contains(text(),'clear shopping cart')]") // locator button clear shopping cart
    WebElement btnClearShoppingCart;

    public void addToCartProduct() {
        DriverSingleton.scroll(0, -500); // scroll ke atas
        btnCart.click(); // click button cart
        /*
         * Membuat try and catch untuk mengatasi history cart user ketika ada item pada cart
         * */

        try {
            DriverSingleton.scroll(0, 500);
            btnClearShoppingCart.click();
            DriverSingleton.scroll(0, 500);
        } catch (Exception ignored) {
        }
        btnReturnShop.click(); // click button return shop
        DriverSingleton.scroll(0, 500); // scroll ke bawah
        productItem.click(); // click item produk
        DriverSingleton.scroll(0, 500); // scroll ke bawah
        colorItem.sendKeys("Beige"); // memilih warna item
        sizeItem.sendKeys("Large"); // memilih ukuran item
        btnAddToCart.click(); // click button add to cart
    }

    public String getAddToCartSuccess() {
        return messageAddToCartSuccess.getText();
    }
}
