package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.PreAndPost;
import pages.LoginPage;

public class TC001_LoginTestCase extends PreAndPost{
	
	@BeforeTest
	public void dataSetUp() {
		sDataExcelName="TC001";
		authors = "Mathan";
		category= "Smoke";
		testcase= "Login TestCase";
		testdesc= "Login with Valid Credentials";
		module =  "Login Valid";
		} 
 
	@Test(priority = 1,dataProvider = "ExcelData")
	public void loginWithValidCredential(String uName,String pwd) {
			
		new LoginPage()
		.enterUserName(uName)
		.enterPassword(pwd)
		.clickSignIn()
		.validateAccountPage()
		.clickLogout();
		
	}
	
	
}
