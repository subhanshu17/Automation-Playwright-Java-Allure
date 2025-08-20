package com.framework.stepdefinitions;

import com.framework.utils.ConfigReader;
import com.framework.utils.DriverFactory;
import com.framework.utils.LoggerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;

import java.io.ByteArrayInputStream;

public class Hooks {
    private static final Logger log = LoggerUtil.getLogger(Hooks.class);

    @Before
    public void setup() {
        log.info("Initializing Playwright");
        DriverFactory.init();
        String baseUrl = ConfigReader.get("baseUrl");
        log.info("Navigating to {}", baseUrl);
        DriverFactory.getPage().navigate(baseUrl);
    }

    @After
    public void teardown(Scenario scenario) {
        Page page = DriverFactory.getPage();
        if (scenario.isFailed() && page != null) {
            log.error("Scenario failed: {}", scenario.getName());
            byte[] png = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("Failure Screenshot - " + scenario.getName(), new ByteArrayInputStream(png));
        }
        log.info("Closing Playwright");
        DriverFactory.close();
    }
}
