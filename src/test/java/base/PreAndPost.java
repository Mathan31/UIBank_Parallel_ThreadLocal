package base;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import libraries.HTMLReport;
import utilities.ExcelReader;
import utilities.PropertiesReader;

public class PreAndPost extends HTMLReport{
	 
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
		driver.manage().window().maximize();
		String sProValue = PropertiesReader.getPropertyValue(sFileName, "URL");
		System.out.println(sProValue);
		driver.get(sProValue);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
	@DataProvider(name="ExcelData")
	public Object[][] getExcelData() {
		Object[][] values = ExcelReader.getSheet(sDataExcelName);
		return values;
	}

	public String takeScreenshot() {
		String sPath = System.getProperty("user.dir")+"/snap/img"+System.currentTimeMillis()+".png";
		TakesScreenshot oShot = (TakesScreenshot)driver;
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
