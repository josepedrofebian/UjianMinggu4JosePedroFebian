package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.AddProductToCartPage;
import com.juaracoding.pages.CheckoutPage;
import com.juaracoding.pages.LoginPage;
import org.openqa.selenium.WebDriver;

import java.util.Scanner;

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

        AddProductToCartPage addProductToCart = new AddProductToCartPage();
        System.out.println("Test Add Product To Cart Success");
        addProductToCart.addToCartProduct(); // menjalankan method addProductToCart dari kelas AddProductToCart
        DriverSingleton.assertEqual(addProductToCart.getAddToCartSuccess(), "Black Cross Back Maxi Dress");

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
