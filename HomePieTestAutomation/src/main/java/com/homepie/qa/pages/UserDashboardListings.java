package com.homepie.qa.pages;

import com.homepie.qa.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDashboardListings extends Base {

	JavascriptExecutor je = (JavascriptExecutor) driver;
	@FindBy(className = "cardpanal")
	List<WebElement> listingTiles;

	public UserDashboardListings()
	{
		PageFactory.initElements(driver, this);
		waitUntilPageIsLoaded();
	}

	public Map<String, String> findListing(String listingId) {
		WebElement listingElement = null;
		System.out.println("Total listings: "+ listingTiles.size());
		for (WebElement listing: listingTiles){
			try{
				WebElement linkElement = listing.findElement(By.tagName("a"));
				if(linkElement.getAttribute("href").contains(listingId)){
					listingElement = listing;
					break;
				}
			}catch (NoSuchElementException ex){}
		}
		if(null == listingElement) return Collections.emptyMap();

		if(!listingElement.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", listingElement);
		}

		Map<String, String> listingValues = new HashMap<>();
		String listingPrice = listingElement.findElement(By.xpath("//div[@class='listing-price']")).getText();
		String priceToValidate = listingPrice.replace("$","").replace(",","");
		System.out.println("listing price: "+ priceToValidate);
		listingValues.put("price", priceToValidate);

		String listingDate = listingElement.findElement(By.xpath("//div[@class='listing-date']")).getText();
		listingValues.put("creationDate", listingDate.replace("Created: ",""));

		String listingAddress = listingElement.findElement(By.xpath("//div[@class='listing-address']")).getText();
		listingValues.put("address", listingAddress.replace("\n",", "));

		String listingStatus = listingElement.findElement(By.xpath("//div[@class='badge-sec']")).getText();
		listingValues.put("status", listingStatus.trim());


		return listingValues;
	}
}
