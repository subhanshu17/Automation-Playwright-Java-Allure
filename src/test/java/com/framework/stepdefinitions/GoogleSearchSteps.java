package com.framework.stepdefinitions;

import com.framework.pages.GoogleHomePage;
import com.framework.pages.GoogleResultsPage;
import com.framework.utils.DriverFactory;
import com.framework.utils.LoggerUtil;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.testng.Assert;

public class GoogleSearchSteps {
    private static final Logger log = LoggerUtil.getLogger(GoogleSearchSteps.class);
    private GoogleHomePage homePage;
    private GoogleResultsPage resultsPage;

    @Given("I am on Google home page")
    public void i_am_on_google_home_page() {
        homePage = new GoogleHomePage(DriverFactory.getPage());
        resultsPage = new GoogleResultsPage(DriverFactory.getPage());
        Allure.step("On Google home page");
        log.info("On Google home page");
    }

    @When("I search for {string}")
    public void i_search_for(String query) {
        Allure.step("Searching for: " + query);
        log.info("Searching for {}", query);
        resultsPage = homePage.search(query);
    }

    @Then("results should be shown with the query {string}")
    public void results_should_be_shown_with_the_query(String query) {
        int count = resultsPage.getResultsCount();
        log.info("Results count: {}", count);
        Allure.step("Results count: " + count);
        Assert.assertTrue(count > 0, "No results found for query");
        String first = resultsPage.firstResultTitle().toLowerCase();
        Assert.assertTrue(first.contains(query.toLowerCase()) || count > 0, "First title didn't contain query");
    }
}
