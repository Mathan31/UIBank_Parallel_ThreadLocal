package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import base.PreAndPost;
import libraries.SeleniumWrapper;

public class AccountPage extends PreAndPost {
	
	private SeleniumWrapper oWrap;

	public AccountPage(WebDriver driver , ExtentTest node) {
		this.driver = driver;
		this.node = node;
		oWrap = new SeleniumWrapper(driver, node);
	}
	

	public AccountPage validateAccountPage() {
		boolean oElement = oWrap.verifyDisplayedwithReturn(driver.findElement(By.xpath("//h3[contains(text(),'Welcome')]")));
		if(oElement) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
		return this;
	}
	
	public LoginPage clickLogout() {
		oWrap.click(driver.findElement(By.xpath("//a[text()='Logout']")));
		return new LoginPage(driver,node);
	}
	
}
