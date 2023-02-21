package com.homepie.qa.pages;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.homepie.qa.base.Base;
import com.homepie.qa.util.Util;

public class LoginPage extends Base
{
	@FindBy(xpath="//*[@class='hm-login' and contains(text(),'Log in')]")
	WebElement loginLink;
	@FindBy(id = "LogForm_email")
	WebElement username;
	@FindBy (id= "LogForm_password")
	WebElement password;
	@FindBy(xpath = "//button[text()='Log In']")
	WebElement loginSubmit;
	
	
	public  LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPageTitle()
	{
		return driver.getTitle();
	}
	
	public Homepage Login(String un ,String pwd) throws IOException, InterruptedException {
		Util.click(loginLink);
		username.sendKeys(prop.getProperty("un"));
		password.sendKeys(prop.getProperty("pwd"));
		Util.click(loginSubmit);
	    return new Homepage();
	}
}
	
	


