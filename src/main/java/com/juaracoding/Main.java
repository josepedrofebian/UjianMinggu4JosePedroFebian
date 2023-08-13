package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.AddProductToCartPage2;
import com.juaracoding.pages.CheckoutPage;
import com.juaracoding.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class Main {
    static WebDriver driver;

    public static void main(String[] args) {
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        driver.get("https://shop.demoqa.com");

        LoginPage loginPage = new LoginPage();
        System.out.println("Test Login Success");
        loginPage.login("josepedrofebian", "Password190299$"); // menjalankan method login dari kelas loginPage
        DriverSingleton.assertEqual(loginPage.getTextLoginSuccess(), "Hello");

        AddProductToCartPage2 add2ProductToCart = new AddProductToCartPage2();
        System.out.println("Test Add 2 Product To Cart Success");
        add2ProductToCart.add2ProductToCart(); // menjalankan method addProductToCart dari kelas AddProductToCart
        DriverSingleton.assertEqual(add2ProductToCart.getAddToCartSuccess(), "“black lux graphic t-shirt” has been added to your cart.");

        CheckoutPage checkoutPage = new CheckoutPage();
        System.out.println("Test Checkout failed without click checkbox");
        checkoutPage.checkout("JOSE", "FEBIAN", "ID", "JL. ATOT AHMAD", "Kota Pontianak", "KB", "78113", "082153263842", "josepedro190299@gmail.com", false); // menjalankan method checkout dari kelas CheckoutPage
        DriverSingleton.assertEqual(checkoutPage.getErrorMessageNoClickCheckbox(), "Please read and accept");

        System.out.println("Test Checkout Success");
        checkoutPage.checkout("JOSE", "FEBIAN", "ID", "JL. ATOT AHMAD", "Kota Pontianak", "KB", "78113", "082153263842", "josepedro190299@gmail.com", true); // menjalankan method checkout dari kelas CheckoutPage
        DriverSingleton.assertEqual(checkoutPage.getCheckoutSuccess(), "Thank you.");

        DriverSingleton.delay(3);
        DriverSingleton.closeObjectInstance();
    }

}
