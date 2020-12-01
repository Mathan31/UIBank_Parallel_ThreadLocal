package libraries;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import base.PreAndPost;

public class SeleniumWrapper extends HTMLReport{
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/*
	 * public void clickElement(WebElement element) { try { element.click();
	 * reportStep("Clicked on Sign-In is Successfully", "pass"); }catch
	 * (NoSuchElementException e) {
	 * reportStep("Clicked on Sign-In is not Successfully", "fail"); } }
	 */
	public WebElement locateElement(String locator, String locValue) {
	try {
		switch (locator) {
			case "id": return getDriver().findElement(By.id(locValue));
			case "name": return getDriver().findElement(By.name(locValue));
			case "class": return getDriver().findElement(By.className(locValue));
			case "link" : return getDriver().findElement(By.linkText(locValue));
			case "xpath": return getDriver().findElement(By.xpath(locValue));		
			default: break;
		}

	} catch (NoSuchElementException e) {
		reportStep("The element with locator "+locator+" not found.","FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while finding "+locator+" with the value "+locValue, "FAIL");
	}
	return null;
}

public WebElement locateElement(String locValue) {
	return getDriver().findElement(By.id(locValue));
}

public void type(WebElement ele, String data) {
	try {
		ele.clear();
		ele.sendKeys(data);
		reportStep("The data: "+data+" entered successfully in the field :", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The data: "+data+" could not be entered in the field :","FAIL");
	} catch (WebDriverException e) {
		e.printStackTrace();
		reportStep("Unknown exception occured while entering "+data+" in the field :", "FAIL");
	}
}

public void typeAndChoose(WebElement ele, String data) {
	try {
		ele.clear();
		ele.sendKeys(data, Keys.TAB);
		reportStep("The data: "+data+" entered successfully in the field :", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The data: "+data+" could not be entered in the field :","FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while entering "+data+" in the field :", "FAIL");
	}
}

public void typeAndEnter(WebElement ele, String data) {
	try {
		ele.clear();
		ele.sendKeys(data, Keys.ENTER);
		reportStep("The data: "+data+" entered successfully in the field :", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The data: "+data+" could not be entered in the field :","FAIL");
	} catch (WebDriverException e) {
		e.printStackTrace();
		reportStep("Unknown exception occured while entering "+data+" in the field :", "FAIL");
	}
}

public void click(WebElement ele) {
	String text = "";
	try {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));			
		text = ele.getText();
		ele.click();
		reportStep("The element "+text+" is clicked", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The element: "+text+" could not be clicked", "FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while clicking in the field :", "FAIL");
	} 
}

public void clickWithNoSnap(WebElement ele) {
	String text = ""; 
	try {
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));	
		text = ele.getText();
		ele.click();			
		reportStep("The element :"+text+"  is clicked.", "PASS",false);
	} catch (InvalidElementStateException e) {
		reportStep("The element: "+text+" could not be clicked", "FAIL",false);
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while clicking in the field :","FAIL",false);
	} 
}

public String getText(WebElement ele) {	
	String bReturn = "";
	try {
		bReturn = ele.getText();
		reportStep("The displayed Text is : "+bReturn+"", "PASS");
	} catch (WebDriverException e) {
		reportStep("The element could not be found.", "FAIL");
	}
	return bReturn;
}

public String getTitle() {		
	String bReturn = "";
	try {
		bReturn =  getDriver().getTitle();
	} catch (WebDriverException e) {
		reportStep("Unknown Exception Occured While fetching Title", "FAIL");
	} 
	return bReturn;
}

public String getAttribute(WebElement ele, String attribute) {		
	String bReturn = "";
	try {
		bReturn=  ele.getAttribute(attribute);
	} catch (WebDriverException e) {
		reportStep("The element: "+ele+" could not be found.", "FAIL");
	} 
	return bReturn;
}

public void selectDropDownUsingVisibleText(WebElement ele, String value) {
	try {
		new Select(ele).selectByVisibleText(value);
		reportStep("The dropdown is selected with text "+value,"PASS");
	} catch (WebDriverException e) {
		reportStep("The element: "+ele+" could not be found.", "FAIL");
	}

}

public void selectDropDownUsingIndex(WebElement ele, int index) {
	try {
		new Select(ele).selectByIndex(index);
		reportStep("The dropdown is selected with index "+index,"PASS");
	} catch (WebDriverException e) {
		reportStep("The element: "+ele+" could not be found.", "FAIL");
	} 

}

public boolean verifyExactTitle(String title) {
	boolean bReturn =false;
	try {
		if(getTitle().equals(title)) {
			reportStep("The title of the page matches with the value :"+title,"PASS");
			bReturn= true;
		}else {
			reportStep("The title of the page:"+getDriver().getTitle()+" did not match with the value :"+title, "FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the title", "FAIL");
	} 
	return bReturn;
}

public boolean verifyPartialTitle(String title) {
	boolean bReturn =false;
	try {
		if(getTitle().contains(title)) {
			reportStep("The title of the page matches with the value :"+title,"PASS");
			bReturn= true;
		}else {
			reportStep("The title of the page:"+getDriver().getTitle()+" did not match with the value :"+title, "FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the title", "FAIL");
	} 
	return bReturn;		
}

public void verifyExactText(WebElement ele, String expectedText) {
	try {
		if(getText(ele).equals(expectedText)) {
			reportStep("The text: "+getText(ele)+" matches with the value :"+expectedText,"PASS");
		}else {
			reportStep("The text "+getText(ele)+" doesn't matches the actual "+expectedText,"FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the Text", "FAIL");
	} 

}

public void verifyPartialText(WebElement ele, String expectedText) {
	try {
		if(getText(ele).contains(expectedText)) {
			reportStep("The expected text contains the actual "+expectedText,"PASS");
		}else {
			reportStep("The expected text doesn't contain the actual "+expectedText,"FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the Text", "FAIL");
	} 
}

public void verifyExactAttribute(WebElement ele, String attribute, String value) {
	try {
		if(getAttribute(ele, attribute).equals(value)) {
			reportStep("The expected attribute :"+attribute+" value matches the actual "+value,"PASS");
		}else {
			reportStep("The expected attribute :"+attribute+" value does not matches the actual "+value,"FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
	} 

}

public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
	try {
		if(getAttribute(ele, attribute).contains(value)) {
			reportStep("The expected attribute :"+attribute+" value contains the actual "+value,"PASS");
		}else {
			reportStep("The expected attribute :"+attribute+" value does not contains the actual "+value,"FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
	}
}

public void verifySelected(WebElement ele) {
	try {
		if(ele.isSelected()) {
			reportStep("The element "+ele+" is selected","PASS");
		} else {
			reportStep("The element "+ele+" is not selected","FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	}
}

public void verifyDisplayed(WebElement ele) {
	try {
		if(ele.isDisplayed()) {
			reportStep("The element is visible","PASS");
		} else {
			reportStep("The element is not visible","FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} 
}

public boolean verifyDisplayedwithReturn(WebElement ele) {
	boolean result = false;
	try {
		if(ele.isDisplayed()) { 
			reportStep("The element is visible","PASS");
			result = true;
			return result;
		} else {
			reportStep("The element is not visible","FAIL");
			return result;
		}
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	}
	return result;
}

public void switchToWindow(int index) {
	try {
		Set<String> allWindowHandles = getDriver().getWindowHandles();
		List<String> allHandles = new ArrayList<>();
		allHandles.addAll(allWindowHandles);
		getDriver().switchTo().window(allHandles.get(index));
	} catch (NoSuchWindowException e) {
		reportStep("The getDriver() could not move to the given window by index "+index,"PASS");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	}
}

public void switchToFrame(WebElement ele) {
	try {
		getDriver().switchTo().frame(ele);
		reportStep("switch In to the Frame "+ele,"PASS");
	} catch (NoSuchFrameException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} 
}

public void acceptAlert() {
	String text = "";		
	try {
		Alert alert = getDriver().switchTo().alert();
		text = alert.getText();
		alert.accept();
		reportStep("The alert "+text+" is accepted.","PASS");
	} catch (NoAlertPresentException e) {
		reportStep("There is no alert present.","FAIL");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	}  
}

public void dismissAlert() {
	String text = "";		
	try {
		Alert alert = getDriver().switchTo().alert();
		text = alert.getText();
		alert.dismiss();
		reportStep("The alert "+text+" is dismissed.","PASS");
	} catch (NoAlertPresentException e) {
		reportStep("There is no alert present.","FAIL");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} 

}

public String getAlertText() {
	String text = "";		
	try {
		Alert alert = getDriver().switchTo().alert();
		text = alert.getText();
	} catch (NoAlertPresentException e) {
		reportStep("There is no alert present.","FAIL");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} 
	return text;
}


public void closeActiveBrowser() {
	try {
		getDriver().close();
		reportStep("The browser is closed","PASS", false);
	} catch (Exception e) {
		reportStep("The browser could not be closed","FAIL", false);
	}
}

public void closeAllBrowsers() {
	try {
		getDriver().quit();
		reportStep("The opened browsers are closed","PASS", false);
	} catch (Exception e) {
		reportStep("Unexpected error occured in Browser","FAIL", false);
	}
}


public void selectDropDownUsingValue(WebElement ele, String value) {
	try {
		new Select(ele).selectByValue(value);
		reportStep("The dropdown is selected with text "+value,"PASS");
	} catch (WebDriverException e) {
		reportStep("The element: "+ele+" could not be found.", "FAIL");
	}

}
public String takeScreenshot() {
	String sPath = System.getProperty("user.dir")+"/snap/img"+System.currentTimeMillis()+".png";
	TakesScreenshot oShot = (TakesScreenshot)getDriver();
	File osrc = oShot.getScreenshotAs(OutputType.FILE);
	File dis = new File(sPath);
	try {
		FileUtils.copyFile(osrc, dis);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return sPath;
}

}
