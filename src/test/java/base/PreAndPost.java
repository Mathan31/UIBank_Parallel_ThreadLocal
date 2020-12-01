package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import libraries.SeleniumWrapper;
import utilities.ExcelReader;
import utilities.PropertiesReader;

public class PreAndPost extends SeleniumWrapper{
	 
	public WebDriver driver;
	public String dataFileName;
	public String sFileName = "AppURL";
	public String sDataExcelName = "";
	public String testcase,testdesc,module;
	
	@BeforeSuite
	public void initReport() { 
		startReport();
	}
	
	@AfterSuite
	public void flushReport() {
		endReport();
	}
	
	@BeforeClass
	public void detailsTestCase() {
		startTestCase(testcase, testdesc);
		}
	
	@BeforeClass 
	public void initBrowser() {
		startTestcase(module);
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		tlDriver.set(driver);
		getDriver().manage().window().maximize();
		String sProValue = PropertiesReader.getPropertyValue(sFileName, "URL");
		System.out.println(sProValue);
		getDriver().get(sProValue);
		getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void closeBrowser() {
		getDriver().quit();
	}
	
	@DataProvider(name="ExcelData",parallel = true)
	public Object[][] getExcelData() {
		Object[][] values = ExcelReader.getSheet(sDataExcelName);
		return values;
	}

	
	
	
	
	

}
