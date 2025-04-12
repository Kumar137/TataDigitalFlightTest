package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightSearchPage {
    WebDriver driver;

    public FlightSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCities(String from, String to) {
        driver.findElement(By.id("fromCity")).sendKeys(from);
        driver.findElement(By.id("toCity")).sendKeys(to);
    }

    public void selectDate(int offset) {
        LocalDate date = LocalDate.now().plusDays(offset);
        String formatted = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        driver.findElement(By.xpath("//input[@name='departure']")).click();
        driver.findElement(By.xpath("//td[@data-date='" + formatted + "']")).click();
    }

    public void selectTravellers(int adults, int infants) {
        driver.findElement(By.id("travellerSelector")).click();
        for (int i = 1; i < adults; i++)
            driver.findElement(By.xpath("//button[@aria-label='Increase Adults']")).click();
        for (int i = 0; i < infants; i++)
            driver.findElement(By.xpath("//button[@aria-label='Increase Infants']")).click();
        driver.findElement(By.id("applyTravellers")).click();
    }

    public void clickSearch() {
        driver.findElement(By.xpath("//button[text()='Search']")).click();
    }
}
