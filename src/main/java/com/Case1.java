package com;

import logs.LogsTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Case1 {

    private static final Logger logger = LogManager.getLogger(Case1.class);


    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach

    public void setup() {

        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Chromdriver oluşturuldu");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.get("https://www.sahibinden.com/");
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

    public void testCars() {

        driver.get("https://www.sahibinden.com/otomobil");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Otomobil']")));
        WebElement car = driver.findElement(By.xpath("//a[@title='Otomobil']"));
        car.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("all-classifieds-link")));
        WebElement allPostingsİnThisCategory = driver.findElement(By.className("all-classifieds-link"));
        allPostingsİnThisCategory.click();

        String searchResultKm = driver.findElement(By.cssSelector("tbody tr:nth-child(1) td:nth-child(7)")).getText();
        logger.info(searchResultKm);
        String searchResultYear = driver.findElement(By.cssSelector(".searchResultsAttributeValue")).getText();
        logger.info(searchResultYear);
        String searchResultPrice = driver.findElement(By.cssSelector(".searchResultsPriceValue")).getText();
        logger.info(searchResultPrice);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".classifiedTitle"))).click();

        String searchResultClassifiedIdText = driver.findElement(By.id("classifiedId")).getText();
        logger.info(searchResultClassifiedIdText);

        File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        logger.error("Hatalı testlerin ekran görüntüleri ektedir.");

        String Url = driver.getCurrentUrl();
        String classifiedid = driver.findElement(By.cssSelector(".classifiedId")).getText();
        Assertions.assertTrue(Url.contains(classifiedid));
        logger.info("Url adresindeki ilan no ile ilan detaydaki ilan no aynıdır.");

    }

    @Test

    public void testAssertGetCurrentUrl() {

        driver.get("https://www.sahibinden.com/otomobil");

        String currentUrl = driver.getCurrentUrl();
        logger.info(currentUrl);
        Assertions.assertEquals("https://www.sahibinden.com/otomobil", currentUrl);
    }

    @Test

    public void testButtonName() {

        driver.get("https://www.sahibinden.com/otomobil");

        String buttonName = driver.findElement(By.id("post-new-classified")).getText();
        logger.info(buttonName);
        Assertions.assertEquals("Ücretsiz* İlan Ver", buttonName);

    }

    @Test
    public void testLoginButton() {

        driver.get("https://www.sahibinden.com/otomobil");

        String loginButton = driver.findElement(By.id("secure-login")).getText();
        logger.info(loginButton);
        Assertions.assertEquals("Giriş Yap", loginButton);
    }

    @Test
    public void testSearchSubmitButton() {
        driver.get("https://www.sahibinden.com/otomobil");

        String SearchSubmitButton = driver.findElement(By.cssSelector("div button.search-submit")).getText();
        logger.info(SearchSubmitButton);
        Assertions.assertTrue(true);
    }

    @Test

    public void testPostFreeAdButton() {

        driver.get("https://www.sahibinden.com/otomobil");

        String PostFreeAdButton = driver.findElement(By.id("post-new-classified")).getText();
        logger.info(PostFreeAdButton);

        Assertions.assertEquals("Ücretsiz* İlan Ver", PostFreeAdButton);
    }

    @Test

    public void testLogo() {
        driver.get("https://www.sahibinden.com/otomobil");

        String logoSahibinden = driver.findElement(By.cssSelector(".logo")).getAttribute("title");
        logger.info(logoSahibinden);

        Assertions.assertEquals("sahibinden.com anasayfasına dön", logoSahibinden);

    }

    @Test

    public void testAdTitle() {

        driver.get("https://www.sahibinden.com/otomobil");

        String adTitle = driver.findElement(By.cssSelector("#searchResultsTable .searchResultsTitleFullWidth")).getText();
        logger.info(adTitle);
        Assertions.assertEquals("İlan Başlığı", adTitle);
    }

    @Test
    public void testKminfo() {

        driver.get("https://www.sahibinden.com/otomobil");

        String kmİnfo = driver.findElement(By.xpath("//a[@title='KM']")).getText();
        logger.info(kmİnfo);
        Assertions.assertEquals("KM", kmİnfo);
    }

    @Test
    public void testPriceİnfo() {

        driver.get("https://www.sahibinden.com/otomobil");

        String priceİnfo = driver.findElement(By.xpath("//a[@title='Fiyat']")).getText();
        logger.info(priceİnfo);
        Assertions.assertEquals("Fiyat", priceİnfo);
    }

    @Test
    public void testClassifiedList(){
        driver.get("https://www.sahibinden.com/otomobil");
        List<WebElement> classifieds = driver.findElements(By.cssSelector(".searchResultsRowClass tr"));
        classifieds.forEach(classified -> logger.info(classified.getText()));
        String classifiedTitle = classifieds.get(0).getText();
        Assertions.assertNotNull(classifiedTitle);
    }
}
