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
	private SeleniumWrapper oWrap;
	
	public RegistrationPage(WebDriver driver , ExtentTest node) {
		this.driver = driver;
		this.node = node;
		oWrap = new SeleniumWrapper(driver, node);
	}
	
	
	public RegistrationPage validateRegistrationPage() {
		oWrap.verifyDisplayed(driver.findElement(By.xpath("//h1[text()='Register']")));
		return this;
	}

	
	public RegistrationPage enterFirstName(String sFirstName) {
		oWrap.type(driver.findElement(By.xpath("//input[@id='firstName']")), sFirstName);
		return this;
	}
	
	public RegistrationPage selectTitle() {
		oWrap.selectDropDownUsingVisibleText(driver.findElement(By.xpath("//select[@id='title']")), "Ms");
		return this;
		
	}
	
	public RegistrationPage selectSex(String sSex) {
		oWrap.selectDropDownUsingVisibleText(driver.findElement(By.xpath("//select[@id='sex']")), sSex);
		return this;
		
	}
	
	public RegistrationPage enterUserName(String sUserName) {
		oWrap.type(driver.findElement(By.xpath("//input[@id='username']")), sUserName);
		return this;
	}
	
	public RegistrationPage enterEmail(String sEmail) {
		oWrap.type(driver.findElement(By.xpath("//input[@id='email']")), sEmail);
		return this;
	}
	
	public RegistrationPage enterPassword(String sPassword) {
		oWrap.type(driver.findElement(By.xpath("//input[@id='password']")), sPassword);
		return this;
	}
	
	public WelComePage clickOnRegisterButton() {
		oWrap.click(driver.findElement(By.xpath("//button[text()='Register']")));
		return new WelComePage(driver,node);
	}
	
	
	

}
