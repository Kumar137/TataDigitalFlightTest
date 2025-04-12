package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CromaPage;
import pages.HomePage;
import utils.BaseTest;

public class MobileCartValidationTest extends BaseTest {

    @Test
    public void validateProductDetailsInCart() throws Exception {
        driver.get("https://www.tatadigital.com");

        HomePage homePage = new HomePage(driver);
        homePage.clickExploreCategories();
        homePage.clickMobiles();

        CromaPage cromaPage = new CromaPage(driver);
        cromaPage.goToNewOnCroma();
        cromaPage.openFirstProductInNewTab();

        cromaPage.switchToNewTab();
        String productName = cromaPage.getProductName();
        String productPrice = cromaPage.getProductPrice();
        cromaPage.clickBuyNow();

        boolean isMatch = cromaPage.verifyCartDetails(productName, productPrice, "screenshots/product_mismatch.png");
        Assert.assertTrue(isMatch, "❌ FAIL: Product name or price does not match in cart.");
    }
}
