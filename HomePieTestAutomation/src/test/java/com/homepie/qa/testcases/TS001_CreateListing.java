package com.homepie.qa.testcases;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.homepie.qa.pages.ListingAddressConfirmationPage;
import com.homepie.qa.pages.Homepage;
import com.homepie.qa.pages.ListingAddressPage;
import com.homepie.qa.pages.ListingDetailsPage;
import com.homepie.qa.pages.ListingReviewPage;
import com.homepie.qa.pages.ListingSubmittedPage;
import com.homepie.qa.pages.LoginPage;
import com.homepie.qa.pages.UserDashboardListings;
import com.homepie.qa.util.ExcelUtil;

public class TS001_CreateListing {
    //creating object of ExcelUtils class
    static ExcelUtil excelUtils = new ExcelUtil();
    static String excelFilePath = "/TS001_CreateListing.xlsx";
    Logger log = Logger.getLogger("TS001_CreateListing.java");

    String addressInput = "", propertyType = "", currentOccupancy = "", propertyDescription = "",
            appliances = "", extFeatures = "";
    int bedRooms, imagesCount;
    double bathRooms;
    long listPrice;

    String listingId;

    @Test
    public void TC001_LoginHomePieAutomation() throws IOException, InterruptedException {
    	log.info("********* starting test case 1******************");
        System.out.println("\n@Test....TC001_LoginHomePieAutomation\n");
        String tmpStr1 = "", tmpStr2 = "";
        LoginPage loginPage = new LoginPage();
        loginPage.Login(tmpStr1, tmpStr2);
        //TODO: No assert for successfully login
    }

    @Test
    public void TC002_NavigateToListingPage() throws IOException, InterruptedException {
    	log.info("********* starting test case 2******************");
        System.out.println("\n@Test....TC002_NavigateToListingPage\n");
        Homepage homePage = new Homepage();
        homePage.clickOnCreateNewListing();
    }

    @Test
    public void TC003_FillAddressForListing() throws InterruptedException, IOException {
    	log.info("********* starting test case 3******************");
        System.out.println("\n@Test....TC003_FillAddressForListing\n");
        //calling the ExcelUtils class method to initialise the workbook and sheet
        excelUtils.SetExcelFile(excelFilePath, "ListingAddressPage");

        ListingAddressPage lsAddressPage = new ListingAddressPage();
        String pageHeader = excelUtils.getCellDataString(1, 0);
        Assert.assertEquals(lsAddressPage.getHeading(), pageHeader);

        addressInput = excelUtils.getCellDataString(1, 1);
        lsAddressPage.FillAddressDetails(addressInput);
        listingId = lsAddressPage.getListingIdFromUrl();
        lsAddressPage.clickOnContinue();
    }

    @Test
    public void TC004_ConfirmAddressForListing() throws InterruptedException, IOException {
    	log.info("********* starting test case 4******************");
        System.out.println("\n@Test....TC004_ConfirmAddressForListing\n");

        String expectedAddress = null;
        if (addressInput.contains("Valencia Boulevard")) {
            addressInput = addressInput.replace("Boulevard", "Blvd");
            addressInput = addressInput.replace(", USA", " 91355");
            expectedAddress = addressInput;
        }

        ListingAddressConfirmationPage lsAdrssConfPage = new ListingAddressConfirmationPage();
        Assert.assertTrue(lsAdrssConfPage.ValidateAddress(expectedAddress));
        lsAdrssConfPage.ConfirmAddressAndClickYesContinue();
    }

    @Test
    public void TC005_AddDetailsForListing() throws IOException, InterruptedException{
        System.out.println("\n@Test....TC005_AddDetailsForListing\n");
        ListingDetailsPage lsDetailsPage = new ListingDetailsPage();
        excelUtils.SetExcelFile(excelFilePath,"ListingAddDetailsPage");

        String ownerName = excelUtils.getCellDataString(1, 0);
        String emailAddress = excelUtils.getCellDataString(1, 1);
        propertyType = excelUtils.getCellDataString(1, 2);
        currentOccupancy = excelUtils.getCellDataString(1, 3);

        bedRooms = excelUtils.getCellDataNumeric(1, 4).intValue();
        bathRooms = excelUtils.getCellDataNumeric(1, 5);
        propertyDescription = excelUtils.getCellDataString(1, 6);
        appliances = excelUtils.getCellDataString(1, 7);
        extFeatures = excelUtils.getCellDataString(1, 8);
        listPrice = excelUtils.getCellDataNumeric(1, 9).longValue();
        String image1 = excelUtils.getCellDataString(1, 10);
        String image2 = excelUtils.getCellDataString(1, 11);
        String tourLink = excelUtils.getCellDataString(1, 12);
        imagesCount = excelUtils.getCellDataNumeric(1,13).intValue();
        //imagesCount = 1;

        Assert.assertTrue(lsDetailsPage.ValidateDefaultOwnershipDetail());
        Assert.assertTrue(lsDetailsPage.ValidateStreetAddress(addressInput));
        lsDetailsPage.FillBasicDetails(ownerName, emailAddress, propertyType, currentOccupancy, bedRooms, bathRooms, propertyDescription);
        lsDetailsPage.FillOptionalDetails(appliances, extFeatures);
        lsDetailsPage.FillListPriceDetails(listPrice);
        lsDetailsPage.AddPhotos(Lists.newArrayList(image1,image2));
        lsDetailsPage.FillPhotosAndVideoDetails(tourLink);
        lsDetailsPage.unselectSelectedPackage();
        lsDetailsPage.ClickSaveButton();
        Thread.sleep(5000);
    }
    @Test
    public void TC006_ValidateListingDetails() throws InterruptedException {
        ListingReviewPage lsReviewPage = new ListingReviewPage();

        Assert.assertTrue(lsReviewPage.validateAddress(addressInput));
        Assert.assertTrue(lsReviewPage.validatePrice(listPrice));
        Assert.assertTrue(lsReviewPage.validatePropertyType(propertyType));
        Assert.assertTrue(lsReviewPage.validateCurrentOccupancy(currentOccupancy));
        Assert.assertTrue(lsReviewPage.validateBedrooms(bedRooms));
        Assert.assertTrue(lsReviewPage.validateBathrooms(bathRooms));
        Assert.assertTrue(lsReviewPage.validatePropertyDesc(propertyDescription));
        Assert.assertTrue(lsReviewPage.validateAppliances(appliances));
        Assert.assertTrue(lsReviewPage.validateExteriorFeatures(extFeatures));
        Assert.assertTrue(lsReviewPage.validateCountOfImages(imagesCount));

        lsReviewPage.SubmitForReview();
    }

    @Test
    public void TC007_GoToMyListingsPage() throws InterruptedException {

        ListingSubmittedPage submittedPage = new ListingSubmittedPage();
        Assert.assertEquals(submittedPage.getGreatJobText(), "Great job!");
        submittedPage.MoveToNextpage();
    }

    @Test
    public void TC008_validateUserListingsAdded() {

        UserDashboardListings ud = new UserDashboardListings();
        Map<String, String> listingValues = ud.findListing(listingId);
        Assert.assertFalse(listingValues.isEmpty());
        Assert.assertEquals(Long.parseLong(listingValues.get("price")), listPrice);
        Assert.assertEquals(listingValues.get("address"), addressInput);
    }
}