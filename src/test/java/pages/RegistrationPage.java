package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import base.PreAndPost;
import libraries.SeleniumWrapper;


public class RegistrationPage extends PreAndPost {
	
	public RegistrationPage validateRegistrationPage() {
		verifyDisplayed(getDriver().findElement(By.xpath("//h1[text()='Register']")));
		return this;
	}

	
	public RegistrationPage enterFirstName(String sFirstName) {
		type(getDriver().findElement(By.xpath("//input[@id='firstName']")), sFirstName);
		return this;
	}
	
	public RegistrationPage selectTitle() {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@id='title']")), "Ms");
		return this;
		
	}
	
	public RegistrationPage selectSex(String sSex) {
		selectDropDownUsingVisibleText(getDriver().findElement(By.xpath("//select[@id='sex']")), sSex);
		return this;
		
	} 
	
	public RegistrationPage enterUserName(String sUserName) {
		type(getDriver().findElement(By.xpath("//input[@id='username']")), sUserName);
		return this;
	}
	
	public RegistrationPage enterEmail(String sEmail) {
		type(getDriver().findElement(By.xpath("//input[@id='email']")), sEmail);
		return this;
	}
	
	public RegistrationPage enterPassword(String sPassword) {
		type(getDriver().findElement(By.xpath("//input[@id='password']")), sPassword);
		return this;
	}
	
	public WelComePage clickOnRegisterButton() {
		click(getDriver().findElement(By.xpath("//button[text()='Register']")));
		return new WelComePage();
	}
	
	
	

}
