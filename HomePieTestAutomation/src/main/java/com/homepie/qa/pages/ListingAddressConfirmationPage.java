package com.homepie.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.homepie.qa.base.Base;
import org.osgl.util.Str;

public class ListingAddressConfirmationPage extends Base {
	JavascriptExecutor je = (JavascriptExecutor) driver;
	@FindBy(xpath = "//*[@class='text-grey']")
	WebElement addressLabel;
	@FindBy(xpath = "//button[normalize-space()='Yes, Continue']")
	WebElement continueButton;
	@FindBy(xpath = "//button[normalize-space()='No, go Back']")
	WebElement backButton;
	Actions action;
	public ListingAddressConfirmationPage()
	{
		PageFactory.initElements(driver, this);
		waitUntilPageIsLoaded();
		action = new Actions(driver);
	}

	public boolean ValidateAddress(String enteredAddress) throws InterruptedException {
		Thread.sleep(5000);
		//	wait.until(ExpectedConditions.visibilityOf(addressLabel));
		String displayedAddress = null;
		try {
			displayedAddress = addressLabel.getText();
		} catch (MoveTargetOutOfBoundsException e) {
			System.out.println(e);
			je.executeScript("arguments[0].scrollIntoView(true);", addressLabel);
		}
		String expectedAddress = displayedAddress.replace("\n", ", ");
		boolean result = expectedAddress.equals(enteredAddress);
		return result;
	}
	public ListingDetailsPage ConfirmAddressAndClickYesContinue()
	{
		je.executeScript("arguments[0].scrollIntoView(true);", continueButton);
		wait.until(ExpectedConditions.visibilityOf(continueButton));
		action.moveToElement(continueButton);
		action.click().build().perform();
		System.out.println("Navigating towards Filling Basic details on Listing Creation Page");
		return new ListingDetailsPage();
	}
}