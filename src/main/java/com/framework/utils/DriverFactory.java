package com.framework.utils;

import com.microsoft.playwright.*;

public class DriverFactory {

    private static final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> tlContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static void init() {
        String headless = ConfigReader.get("headless");
        String slowMoStr = ConfigReader.get("slowMo");
        int slowMo = 0;
        try { slowMo = Integer.parseInt(slowMoStr); } catch (Exception ignored) {}

        tlPlaywright.set(Playwright.create());

        BrowserType chromium = tlPlaywright.get().chromium();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless("true".equalsIgnoreCase(headless))
                .setSlowMo((double) slowMo);

        tlBrowser.set(chromium.launch(launchOptions));
        tlContext.set(tlBrowser.get().newContext());
        tlPage.set(tlContext.get().newPage());
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public static void close() {
        if (tlContext.get() != null) tlContext.get().close();
        if (tlBrowser.get() != null) tlBrowser.get().close();
        if (tlPlaywright.get() != null) tlPlaywright.get().close();
        tlPage.remove();
        tlContext.remove();
        tlBrowser.remove();
        tlPlaywright.remove();
    }
}
