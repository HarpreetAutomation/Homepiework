package com.homepie.qa.pages;

import com.homepie.qa.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Homepage extends Base
{
	@FindBy(xpath= "(//div[@class='header-menu'])")
	WebElement headerMenu;
	@FindBy(xpath= "(//div[@class='popover-trigger more-hover' and contains(text(),'More')])")
	WebElement menuItemMore;

	@FindBy(xpath = "(//div[@class='header-menu'])//a[@class='option'][normalize-space()='Create New Listing']")
	//@FindBy(xpath = "//div[@class='header-menu']//div[@class='user-menu']//a[@href='/listings/address']")
	WebElement CreateNewListing;
	String createNewListingXPath = "(//div[@class='header-menu'])//a[@class='option'][normalize-space()='Create New Listing']";

	public  Homepage() {
		PageFactory.initElements(driver, this);
		//waitUntilPageIsLoaded();
	}

	public void clickOnCreateNewListing() throws InterruptedException {
		Actions action = new Actions(driver);
		//driver.navigate().refresh();

		Thread.sleep(10000);
		action.moveToElement(menuItemMore);
		System.out.println("Done Mouse hover on 'More' from Menu");

		Thread.sleep(2000);
		action.perform();

		wait.until(ExpectedConditions.visibilityOf(CreateNewListing));
		//To mouseover on sub menu
		action.moveToElement(CreateNewListing);
		System.out.println("Done Mouse hover on 'Create New Listing' from Sub-Menu");
		//build()- used to compile all the actions into a single step
		action.click().build().perform();
	}

}
