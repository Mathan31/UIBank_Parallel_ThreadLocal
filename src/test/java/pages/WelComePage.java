package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import base.PreAndPost;
import libraries.SeleniumWrapper;

public class WelComePage extends PreAndPost{
	
	public WelComePage validateWelcomePage() {
		boolean oElement = verifyDisplayedwithReturn(getDriver().findElement(By.xpath("//h2[contains(text(),'Welcome To The UiBank Family')]")));
		if(oElement) {
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		return this;
	}
	
	public LoginPage clickOnUIBankImage() {
		click(getDriver().findElement(By.xpath("//a[@class='navbar-brand']/img")));
		return new LoginPage();
	}
	

}
