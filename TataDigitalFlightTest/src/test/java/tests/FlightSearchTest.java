package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;

public class FlightSearchTest extends BaseTest {

    @Test
    public void validateSortedPrices() throws Exception {
        driver.get("https://www.tatadigital.com");

        HomePage homePage = new HomePage(driver);
        homePage.clickExploreCategories();
        homePage.clickFlights();

        FlightSearchPage searchPage = new FlightSearchPage(driver);
        searchPage.enterCities("DEL", "BLR");
        searchPage.selectDate(7);
        searchPage.selectDate(10);
        searchPage.selectTravellers(2, 1);
        searchPage.clickSearch();

        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        resultsPage.applyFilters();

        boolean isSorted = resultsPage.verifyPriceOrder("screenshots/price_mismatch.png");
        Assert.assertTrue(isSorted, "❌ FAIL: Prices are not in ascending order.");
    }
}
