package com.homepie.qa.pages;

import com.homepie.qa.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.osgl.util.Str;

public class ListingReviewPage extends Base {

	@FindBy(xpath = "//div[@id='image']/span")
	WebElement photosCount;
	@FindBy(xpath = "//div[@class='info-panel']//h1")
	WebElement address;
	@FindBy(xpath = "//span[@class='text-h1 price']")
	WebElement price;
	@FindBy(xpath = "//div[@class='desc desc-big']")
	WebElement propertyDescription;
	@FindBy(xpath = "//div[@class='quick-info']//div[contains(text(),'Bed')]")
	WebElement summaryBeds;

	@FindBy(xpath = "//div[@class='section basic-details']")
	WebElement basicDetails;
	@FindBy(xpath = "//div[@class='section basic-details']//div[normalize-space()='Bedrooms']/following-sibling::div")
	WebElement basicDetailsBeds;
	@FindBy(xpath = "//div[@class='quick-info']//div[contains(text(),' Bath')]")
	WebElement summaryBaths;
	@FindBy(xpath = "//div[@class='section basic-details']/div[@class='content']/div[2]/div[2]")
	WebElement basicDetailsBaths;
	@FindBy(xpath = "//div[@class='section basic-details']/div[@class='content']/div[3]/div[2]")
	WebElement basicDetailsPropTyps;
	@FindBy(xpath = "//div[@class='section basic-details']/div[@class='content']/div[4]/div[2]")
	WebElement basicDetailsCurrOcpncy;
	@FindBy(xpath = "(//div[@class='section'])[1]//div[@class='value']")
	WebElement interiorDetailsAppliances;
	@FindBy(xpath = "(//div[@class='section'])[2]//div[2]/div[@class='value']")
	WebElement extDetailsExtFeatures;
	@FindBy(xpath = "//label[@id='agreementCheckbox']/span")
	WebElement agreementChkBox;
	@FindBy(xpath = "//*[@id='workflowSubmit']")
	WebElement submitForReviewBtn;


	public ListingReviewPage() throws InterruptedException {
		PageFactory.initElements(driver, this);
		waitUntilPageIsLoaded();
	}

	public boolean validateAddress(String addressInput){
		String add =  address.getText();
		String addToBeValidated = add.replace("\n",", ");

		return addToBeValidated.equals(addressInput);
	}

	public boolean validatePrice(long priceInput){
		String priceMentioned = price.getText();
		long priceToBeValidated =  Long.parseLong((priceMentioned.replace("$",""))
																.replace(",",""));
		return (priceToBeValidated==priceInput);
	}

	public boolean validatePropertyType(String propertyType) {
		String propTypeToBeValidated =  basicDetailsPropTyps.getText();
		return propTypeToBeValidated.equals(propertyType);
	}

	public boolean validateCurrentOccupancy(String currOccupancy) {
		String currOccToBeValidated =  basicDetailsCurrOcpncy.getText();
		return currOccToBeValidated.equals(currOccupancy);
	}

	public boolean validateBedrooms(int beds) {
		String bedsSummary = summaryBeds.getText();
		String bedsString = bedsSummary.replace(" Bed","");
		int bedsInSummary =  Integer.parseInt(bedsString);
		int bedsInBasicDtls = Integer.parseInt(basicDetailsBeds.getText());

		boolean areBedsInSummaryEqual = (bedsInSummary == beds);
		boolean areBedsInBasicDtlsEqual = (bedsInBasicDtls == beds);
		if(areBedsInSummaryEqual && areBedsInBasicDtlsEqual){
			return true;
		}
		else
			return false;
	}

	public boolean validateBathrooms(double baths) {
		double bathsInSummary =  Double.parseDouble((summaryBaths.getText()).replace(" Bath",""));
		double bathsInBasicDtls = Double.parseDouble(basicDetailsBaths.getText());

		boolean areBathsInSummaryEqual = (bathsInSummary == baths);
		boolean areBathsInBasicDtlsEqual = (bathsInBasicDtls == baths);
		if(areBathsInBasicDtlsEqual && areBathsInSummaryEqual){
			return true;
		}
		else
			return false;
	}

	public boolean validatePropertyDesc(String propDesc) {
		String propDescToBeValidated =  propertyDescription.getText();
		return propDescToBeValidated.equals(propDesc);
	}

	public boolean validateAppliances(String appliances) {
		String appliancesText =  interiorDetailsAppliances.getText();
		return appliancesText.equals(appliances);
	}

	public boolean validateExteriorFeatures(String extFeatures) {
		String extFeaturesText =  extDetailsExtFeatures.getText();
		return extFeaturesText.equals(extFeatures);
	}

	public boolean validateCountOfImages(int imagesCount){
		int numOfPhotos = Integer.parseInt((photosCount.getText()).replace(" Photos",""));

		return (numOfPhotos==imagesCount);
	}

	public ListingSubmittedPage SubmitForReview() throws InterruptedException
	{
		agreementChkBox.click();
		Thread.sleep(5000);
		submitForReviewBtn.click();
		Thread.sleep(5000);
		return new ListingSubmittedPage();
	}
}
