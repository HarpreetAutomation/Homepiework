package com.homepie.qa.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import com.homepie.qa.base.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class Util extends Base {
	public static void click(WebElement we) throws IOException, InterruptedException {
		System.out.println(we.getText());
		if (we.isDisplayed()) {
			we.click();
		} else {
			System.out.println("Item not displaying");
		}
	}

	public static String GenerateRandomEmailId() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMMyyyy_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		String timeStamp = dtf.format(now);
		String email = "" + timeStamp + "@yopmail.com";
		return email;
	}

	public static void mouseHoverJScript(WebElement HoverElement) {
		try {
			if (isElementPresent(HoverElement)) {

				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript,HoverElement);
			} else {
				System.out.println("Element was not visible to hover " + "\n");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + HoverElement + "is not attached to the page document" + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering" + e.getStackTrace());
		}
	}
	private static boolean isElementPresent (WebElement element){
		boolean flag = false;
		try {
			if (element.isDisplayed()
					|| element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}

	public static String testDataLocation(){
		 String os = System.getProperty("os.name");
		if(os.contains("Mac"))
    	{
			String projectPath = System.getProperty("user.dir");
			String testDataRelativePath = prop.getProperty("MactestDataLocation");
			return projectPath + File.separator + testDataRelativePath;
    	}
    	else
    	{  
    		String projectPath = System.getProperty("user.dir");
    		String testDataRelativePath = prop.getProperty("WindowstestDataLocation");
    		return projectPath + File.separator + testDataRelativePath;
    	}
		
	}
}