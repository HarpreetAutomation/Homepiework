package com.homepie.qa.pages;

import com.homepie.qa.base.Base;
import java.io.IOException;
import java.time.Duration;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListingAddressPage extends Base
{
	@FindBy(className = "workflow-page-title")
	WebElement headingTitle;
	@FindBy(id = "listing_address")
	WebElement listingAddress;
	@FindBy(xpath="//*[@id='workflowSubmit']")
	WebElement workflowSubmit;
	
	public ListingAddressPage()
	{
		PageFactory.initElements(driver, this);
		waitUntilPageIsLoaded();
	}

	public String getListingIdFromUrl(){
		String id =  driver.getCurrentUrl().substring((prop.getProperty("url") + "listings/").length(), driver.getCurrentUrl().lastIndexOf("/"));
		System.out.println("Id : "+ id);
		return id;
	}

	public void FillAddressDetails(String address) throws InterruptedException {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(listingAddress));
		action.moveToElement(listingAddress);
		action.sendKeys(listingAddress, address).build().perform();
		System.out.println("Listing Address " + listingAddress + " is entered");
	}

	public ListingAddressConfirmationPage clickOnContinue() throws InterruptedException
	{
		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.moveToElement(workflowSubmit);
		action.click().build().perform();
	    System.out.println("Continue button is clicked on Listing Address Page. Navigating to Address Confirmation Page");
	    return new ListingAddressConfirmationPage();
	}

	public String getHeading(){
		wait.until(ExpectedConditions.visibilityOf(headingTitle));
		return headingTitle.getText();
	}
}
