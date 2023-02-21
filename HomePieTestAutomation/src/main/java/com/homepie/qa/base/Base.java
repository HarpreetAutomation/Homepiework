package com.homepie.qa.base;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Base
{
	public static Properties prop;
	public static WebDriver driver;
	protected WebDriverWait wait;

	public Base(){
		loadConfigProperties();
		initialization();
	}
	public void loadConfigProperties() {
		if(null == prop){
			prop = new Properties();
			try(InputStream ip = ClassLoader.getSystemResourceAsStream("config.properties")) {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void initialization()
	{
		if(null == driver){
			String browsername = prop.getProperty("browser");

			if (browsername.equals("chrome"))
			{
				WebDriverManager.chromedriver().setup();
    			driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
				driver.get(prop.getProperty("url"));
			}
			else if (browsername.equalsIgnoreCase("Firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
    			driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
				driver.get(prop.getProperty("url"));		
			}
			else if (browsername.equalsIgnoreCase("Headless Chrome"))
			{
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options. addArguments("headless"); 
    			driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
				driver.get(prop.getProperty("url"));
			}
		}

		wait = new WebDriverWait(driver, Duration.ofSeconds(45));
	}

	public String getBaseUrl(){
		return prop.getProperty("url");
	}

	protected void waitUntilPageIsLoaded() {
		wait.until(
				webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
						.equals("complete"));
	}
}
