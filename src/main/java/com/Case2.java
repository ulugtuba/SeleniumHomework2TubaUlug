package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Case2 {

    private static final Logger logger = LogManager.getLogger(Case2.class);


    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach

    public void setup() {

        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Chromdriver oluşturuldu");

        driver.get("https://www.sahibinden.com/");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        List<Cookie> cookieList = new ArrayList<>();
        cookieList.add(new Cookie("testBox", "51", ".sahibinden.com", "/", null));
        cookieList.add(new Cookie("tbSite", "x", ".sahibinden.com", "/", null));
        cookieList.forEach(cookie -> driver.manage().addCookie(cookie));
        wait = new WebDriverWait(driver, 30);

    }

    @AfterEach()

    public void afterEach() {

        driver.close();
        driver.quit();
        logger.info("Chromedriver kapandı");
    }

@Test
    public void testCase2Parametrized(){
}

@ParameterizedTest
@CsvSource({
        "iPhone 12 Mini,#search_cats ul li.cl4,1",
        " Koşu Bandı,#search_cats ul .cl3 div a h2,2",
        "Çapa Makinesi,#search_cats ul .cl4 div a h2,3",
        "Oppo Watch,#search_cats ul .cl3 div a h2,4",
        "PlayStation 5,#search_cats ul .cl3 div a h2,5"}
)

    public void parametrizedTest()
{
try {


    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='iphone 12 mini']"))).click();

    String categoryTest = driver.findElement(By.xpath("//a[@title='iphone 12 mini']")).getText();
    logger.info(categoryTest);


}
catch (Exception ex){

    File fileimage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    logger.error("Hatanın ekran görüntüsü ektedir.");
}
}

}
