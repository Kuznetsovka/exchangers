package com.systemair.exchangers.domain;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

@Getter
public class Browser {
    private final WebDriver driver;
    private final Wait<WebDriver> wait;

    public Browser(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }
}
