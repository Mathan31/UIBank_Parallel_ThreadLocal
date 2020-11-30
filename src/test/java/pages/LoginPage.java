package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.PreAndPost;
import libraries.SeleniumWrapper;

public class LoginPage extends PreAndPost{
	private SeleniumWrapper oWrap;
	
	public LoginPage(WebDriver driver,ExtentTest node) {
		this.driver = driver;
		this.node = node;
		oWrap = new SeleniumWrapper(driver, node);
	} 
	
	public LoginPage enterUserName(String userName) {
		oWrap.type(driver.findElement(By.id("username")), userName);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		oWrap.type(driver.findElement(By.id("password")), password);
		return this;
	}
	
	public AccountPage clickSignIn() {
		oWrap.click(driver.findElement(By.xpath("//button[text()='Sign In']")));
		return new AccountPage(driver,node);
	}
	
	public RegistrationPage clickRegisterLink() {
		oWrap.click(driver.findElement(By.xpath("//*[text()='Register For Account']")));
		return new RegistrationPage(driver,node);
	}
	
}
