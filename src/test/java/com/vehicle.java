package com;

import com.oracle.tools.packager.Log;
import com.sun.corba.se.impl.presentation.rmi.StubFactoryBase;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class vehicle {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach

    public void setup() {

        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, 30);

    }

    @AfterEach()

    public void Testfinish () {

        driver.close();
        driver.quit();
    }

    @Test
        public void cars () {

            driver.get("https://www.sahibinden.com/");
            List<Cookie> cookieList = new ArrayList<>();
            cookieList.add(new Cookie("testBox", "51", ".sahibinden.com", "/", null));
            cookieList.add(new Cookie("tbSite", "x", ".sahibinden.com", "/", null));
            cookieList.forEach(cookie -> driver.manage().addCookie(cookie));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //a[contains(text(),\"Tüm vitrin ilanlarını göster\")]")));
            WebElement car = driver.findElement(By.xpath(" //a[contains(text(),\"Tüm vitrin ilanlarını göster\")]"));
            car.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("all-classifieds-link")));
            WebElement allPostingsinThisCategory = driver.findElement(By.className("all-classifieds-link"));
            allPostingsinThisCategory.click();


            String currentUrl = driver.getCurrentUrl();





        }

    }

