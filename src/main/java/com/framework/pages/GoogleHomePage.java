package com.framework.pages;

import com.microsoft.playwright.Page;

public class GoogleHomePage extends BasePage {

    public GoogleHomePage(Page page) {
        super(page);
    }

    public void navigate(String baseUrl) {
        page.navigate(baseUrl);
    }

    public GoogleResultsPage search(String query) {
        page.fill("input[name='q']", query);
        page.keyboard().press("Enter");
        return new GoogleResultsPage(page);
    }
}
