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
	private SeleniumWrapper oWrap;
	
	public WelComePage(WebDriver driver , ExtentTest node) {
		this.driver = driver;
		this.node = node;
		oWrap = new SeleniumWrapper(driver, node);
	}
	
	public WelComePage validateWelcomePage() {
		boolean oElement = oWrap.verifyDisplayedwithReturn(driver.findElement(By.xpath("//h2[contains(text(),'Welcome To The UiBank Family')]")));
		if(oElement) {
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		return this;
	}
	
	public LoginPage clickOnUIBankImage() {
		oWrap.click(driver.findElement(By.xpath("//a[@class='navbar-brand']/img")));
		return new LoginPage(driver,node);
	}
	

}
