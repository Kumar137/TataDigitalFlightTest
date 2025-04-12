package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class CromaPage {
    WebDriver driver;
    WebDriverWait wait;

    public CromaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void goToNewOnCroma() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='New on Croma']"))).click();
    }

    public void openFirstProductInNewTab() {
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-listing .product-title a")));
        String openInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
        firstProduct.sendKeys(openInNewTab);
    }

    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.product-title")));
    }

    public String getProductName() {
        return driver.findElement(By.cssSelector("h1.product-title")).getText().trim();
    }

    public String getProductPrice() {
        return driver.findElement(By.cssSelector(".product-price")).getText().replaceAll("[^0-9]", "").trim();
    }

    public void clickBuyNow() {
        driver.findElement(By.xpath("//button[text()='Buy Now']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-item-title")));
    }

    public boolean verifyCartDetails(String expectedName, String expectedPrice, String screenshotPath) throws IOException {
        String cartName = driver.findElement(By.cssSelector(".cart-item-title")).getText().trim();
        String cartPrice = driver.findElement(By.cssSelector(".cart-price")).getText().replaceAll("[^0-9]", "").trim();

        if (!expectedName.equalsIgnoreCase(cartName) || !expectedPrice.equals(cartPrice)) {
            File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scr, new File(screenshotPath));
            return false;
        }
        return true;
    }
}
