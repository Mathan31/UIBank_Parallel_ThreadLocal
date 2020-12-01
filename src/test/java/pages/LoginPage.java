package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.PreAndPost;
import libraries.SeleniumWrapper;

public class LoginPage extends PreAndPost{ 
	
	public LoginPage enterUserName(String userName) {
		type(getDriver().findElement(By.id("username")), userName);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		type(getDriver().findElement(By.id("password")), password);
		return this;
	}
	
	public AccountPage clickSignIn() {
		click(getDriver().findElement(By.xpath("//button[text()='Sign In']")));
		return new AccountPage();
	}
	
	public RegistrationPage clickRegisterLink() {
		click(getDriver().findElement(By.xpath("//*[text()='Register For Account']")));
		return new RegistrationPage();
	}
	
}
