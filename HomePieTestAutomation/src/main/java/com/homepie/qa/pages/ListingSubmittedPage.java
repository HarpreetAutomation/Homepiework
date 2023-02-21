package com.homepie.qa.pages;

import com.homepie.qa.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListingSubmittedPage extends Base {

	@FindBy(className = "text-h1")
	WebElement greatJobTextElement;
	@FindBy(xpath="//*[@class='btn btn-primary2' and contains(text(),'Next Step')]")
	WebElement nextStepBtn;

	public ListingSubmittedPage()
	{
		PageFactory.initElements(driver, this);
		waitUntilPageIsLoaded();
	}

	public String getGreatJobText(){
		return greatJobTextElement.getText();
	}

	public String getListingIdFromUrl(){
		String id =  driver.getCurrentUrl().substring((prop.getProperty("url") + "listings/").length(), driver.getCurrentUrl().lastIndexOf("/"));
		System.out.println("Id : "+ id);
		return id;
	}

	public UserDashboardListings MoveToNextpage() throws InterruptedException
	{
		Thread.sleep(2000);
		nextStepBtn.click();
		return new UserDashboardListings();
	}
}
