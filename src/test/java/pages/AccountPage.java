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
	
		public AccountPage validateAccountPage() {
		boolean oElement = verifyDisplayedwithReturn(getDriver().findElement(By.xpath("//h3[contains(text(),'Welcome')]")));
		if(oElement) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
		return this;
	}
	
	public LoginPage clickLogout() {
		click(getDriver().findElement(By.xpath("//a[text()='Logout']")));
		return new LoginPage();
	}
	
}
