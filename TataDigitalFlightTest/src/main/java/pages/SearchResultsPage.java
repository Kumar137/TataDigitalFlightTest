package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class SearchResultsPage {
    WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void applyFilters() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'flight-listing')]")));
        driver.findElement(By.xpath("//label[contains(text(),'Non-Stop')]")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Cheapest')]")).click();
    }

    public boolean verifyPriceOrder(String screenshotPath) throws IOException {
        List<WebElement> prices = driver.findElements(By.className("price-tag"));
        List<Integer> priceValues = new ArrayList<>();

        for (WebElement price : prices) {
            String text = price.getText().replaceAll("[^0-9]", "");
            if (!text.isEmpty()) {
                priceValues.add(Integer.parseInt(text));
            }
        }

        List<Integer> sorted = new ArrayList<>(priceValues);
        Collections.sort(sorted);

        if (!sorted.equals(priceValues)) {
            File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scr, new File(screenshotPath));
            return false;
        }
        return true;
    }
}
