package com.homepie.qa.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.homepie.qa.base.Base;
import com.homepie.qa.util.Util;

public class ListingDetailsPage extends Base {
	JavascriptExecutor je = (JavascriptExecutor) driver;
	@FindBy(id = "listing_details_form_owner_type_true")
	WebElement indOrJtOptBtn;
	@FindBy(id = "listing_details_form_owner_name_0")
	WebElement prOwnerNameTxtBox;
	@FindBy(id = "listing_details_form_owner_email_0")
	WebElement emailAddrsTxtBox;
	@FindBy(id = "listing_details_form_address")
	WebElement streetAddrsTxtBox;
	@FindBy(id = "updateChangesetOnDropdownSelect-property_type")
	WebElement propTypeLstBox;
	//@FindBy(xpath = "//div[@id='updateChangesetOnDropdownSelect-property_type']//div[@class='options']")
	@FindBy(xpath= "//div[@class='option' and contains(text(),'Condo/Townhome')]")
	WebElement propTypeLstBoxContents;
	@FindBy(id = "updateChangesetOnDropdownSelect-occupancy")
	WebElement currOccLstBox;
	//@FindBy(xpath = "//div[@id='updateChangesetOnDropdownSelect-occupancy']//div[@class='options']")
	@FindBy(xpath = "//div[@class='option' and contains(text(),'Owner')]")
	WebElement currOccLstBoxContents;
	@FindBy(id = "listing_details_form_bedrooms")
	WebElement bedRoomsTxtBox;
	@FindBy(id = "listing_details_form_bathrooms")
	WebElement bathRoomsTxtBox;
	@FindBy(id = "the-textarea")
	WebElement descYourPropTxtBox;
	@FindBy(id = "panalBtn3")
	WebElement basicDtlSaveBtn;
	@FindBy(xpath = "//label[normalize-space()='Oven']")
	WebElement applOvenChkBox;
	@FindBy(xpath = "//label[normalize-space()='Awning']")
	WebElement extFeatAwningChkBox;
	@FindBy(id = "panalBtn4")
	WebElement OptnlSaveBtn;
	@FindBy(id = "value_total-view")
	WebElement LstPriceTxtBox;
	@FindBy(id = "panalBtn5")
	WebElement LstPriceSaveBtn;
	@FindBy(id = "listing-image-dropzone")
	WebElement uploadPhotosVdosDropBox;
	@FindBy(xpath = "(//div[contains(@class,'image-wrap-0')][1]")
	WebElement image1;
	@FindBy(xpath = "(//div[contains(@class,'image-wrap-0')][2]")
	WebElement image2;
	@FindBy(id = "listing_details_form_url_3d_tour")
	WebElement tourLnkTxtBox;
	@FindBy(id = "panalBtn6")
	WebElement photosVdosSaveBtn;
	@FindBy(xpath = "//input[@type='file']")
	WebElement imageInput;
	@FindBy(id = "workflowSubmit")
	WebElement saveAndCntBtn;
	@FindBy(xpath = "//div[starts-with(@id,'change-mls-package-')]")
	WebElement mlsPackage;

	public ListingDetailsPage()
	{
		PageFactory.initElements(driver, this);
		waitUntilPageIsLoaded();
	}

	public void FillBasicDetails(String ownerName, String email, String propertyType, String currentOccupancy, int bedRooms, double bathRooms, String propertyDescription) throws InterruptedException, IOException
	{
		Actions action = new Actions(driver);
		prOwnerNameTxtBox.sendKeys(ownerName);
		Thread.sleep(5000);
		emailAddrsTxtBox.sendKeys(email);
		Thread.sleep(5000);
		je.executeScript("arguments[0].scrollIntoView(true);", propTypeLstBox);
		propTypeLstBox.click();
		Thread.sleep(5000);
		action.moveToElement(propTypeLstBoxContents);
	
		//build()- used to compile all the actions into a single step
		action.click().build().perform();

		if(!currOccLstBox.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", currOccLstBox);
		}
		currOccLstBox.click();

		Thread.sleep(5000);
		action.moveToElement(currOccLstBoxContents);
		action.click().build().perform();

		if(!bedRoomsTxtBox.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", bedRoomsTxtBox);
		}
		bedRoomsTxtBox.sendKeys(String.valueOf(bedRooms));
		bathRoomsTxtBox.sendKeys(String.valueOf(bathRooms));

		if(!descYourPropTxtBox.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", descYourPropTxtBox);
		}
		descYourPropTxtBox.sendKeys(propertyDescription);
		Thread.sleep(4000);

		if(!basicDtlSaveBtn.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", basicDtlSaveBtn);
		}
		Util.click(basicDtlSaveBtn);
	}

	public void FillOptionalDetails(String appliances, String extFeatures) throws IOException, InterruptedException
	{
		Thread.sleep(2000);
		if(appliances.equals("Oven")) {
			if(!applOvenChkBox.isDisplayed()){
				je.executeScript("arguments[0].scrollIntoView(true);", applOvenChkBox);
			}
			wait.until(ExpectedConditions.elementToBeClickable(applOvenChkBox));
			applOvenChkBox.click();
		}
		if(extFeatures.equals("Awning")) {
			if(!extFeatAwningChkBox.isDisplayed()){
				je.executeScript("arguments[0].scrollIntoView(true);", extFeatAwningChkBox);
			}
			wait.until(ExpectedConditions.elementToBeClickable(extFeatAwningChkBox));
			extFeatAwningChkBox.click();
		}

		if(!OptnlSaveBtn.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", OptnlSaveBtn);
		}
		wait.until(ExpectedConditions.elementToBeClickable(OptnlSaveBtn));
		Util.click(OptnlSaveBtn);
	}

	public void FillListPriceDetails(Long price) throws IOException, InterruptedException
	{
		Thread.sleep(2000);
		if(!LstPriceTxtBox.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", LstPriceTxtBox);
		}
		LstPriceTxtBox.sendKeys(String.valueOf(price));

		if(!LstPriceSaveBtn.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", LstPriceSaveBtn);
		}
		Util.click(LstPriceSaveBtn);
	}

	public void AddPhotos(List<String> images) throws InterruptedException
	{
		Thread.sleep(2000);
		String filePath = Util.testDataLocation();

		// disable the click event on an `<input>` file
		((JavascriptExecutor)driver).executeScript(
				"HTMLInputElement.prototype.click = function() {                     " +
						"  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
						"};                                                                  " );
		String imagePath = null;

		for (String image: images) {

			imagePath = filePath + File.separator + image;
			System.out.println("This image will be loaded: " + imagePath);

			if(!uploadPhotosVdosDropBox.isDisplayed()){
				je.executeScript("arguments[0].scrollIntoView(true);", uploadPhotosVdosDropBox);
			}

			// trigger the upload
			wait.until(ExpectedConditions.elementToBeClickable(uploadPhotosVdosDropBox));
			uploadPhotosVdosDropBox.click();

			Thread.sleep(2000);
			// assign the file to the `<input>`
			imageInput.sendKeys(imagePath);
			Thread.sleep(10000);
		}
	}

	public void ReorderPhotos()
	{
		Actions builder = new Actions(driver);
		WebElement from = image1;
		WebElement to = image2;
		builder.dragAndDrop(from, to).perform();
	}

	public void FillPhotosAndVideoDetails(String link) throws IOException, InterruptedException
	{
		Thread.sleep(2000);
		if(!tourLnkTxtBox.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", tourLnkTxtBox);
		}
		tourLnkTxtBox.sendKeys(link);

		if(!photosVdosSaveBtn.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", photosVdosSaveBtn);
		}
		Util.click(photosVdosSaveBtn);
	}

	public boolean ValidateDefaultOwnershipDetail()
	{
		if(!indOrJtOptBtn.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", indOrJtOptBtn);
		}

		if(indOrJtOptBtn.isSelected())
		{	return Boolean.TRUE;	}
		else
		{	return Boolean.FALSE;	}
	}

	public boolean ValidateStreetAddress(String address)
	{
		String expectedAddress = address.replace(",","") + " USA";

		if(!streetAddrsTxtBox.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", streetAddrsTxtBox);
		}
		String actualAddress = streetAddrsTxtBox.getAttribute("value");

		if(actualAddress.equals(expectedAddress))
		{	return Boolean.TRUE;	}
		else
		{	return Boolean.FALSE;	}
	}

	public void unselectSelectedPackage() throws InterruptedException {
		Thread.sleep(2000);

		if(!mlsPackage.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", mlsPackage);
		}
		try{
			WebElement selectedPackage = driver.findElement(By.xpath("//div[@class='pricingbox']//button[contains(@class,'selected')]"));
			selectedPackage.click();
		}catch(NoSuchElementException ex){

		}

	}
	public ListingReviewPage ClickSaveButton() throws InterruptedException
	{
		Thread.sleep(2000);
		if(!saveAndCntBtn.isDisplayed()){
			je.executeScript("arguments[0].scrollIntoView(true);", saveAndCntBtn);
		}
		saveAndCntBtn.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='swal2-confirm swal2-styled' and contains(text(),'Yes')]")).click();
		Thread.sleep(5000);
		//saveButton.click();
		 System.out.println("Navigating towards Submit for Review");
		return new ListingReviewPage();
	}
}
