package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
    }

    public void clickExploreCategories() {
        driver.findElement(By.linkText("Explore Categories")).click();
    }

    public void clickFlights() {
        driver.findElement(By.xpath("//span[text()='Flights']")).click();
    }

    public void clickMobiles() {
        driver.findElement(By.linkText("Mobiles")).click();
    }
}
