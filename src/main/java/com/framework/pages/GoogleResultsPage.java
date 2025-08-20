package com.framework.pages;

public class GoogleResultsPage extends BasePage {

    public GoogleResultsPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public int getResultsCount() {
        return page.locator("div#search a h3").count();
    }

    public String firstResultTitle() {
        if (getResultsCount() == 0) return "";
        return page.locator("div#search a h3").first().innerText();
    }
}
