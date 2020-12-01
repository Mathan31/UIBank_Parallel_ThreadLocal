package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.PreAndPost;
import pages.LoginPage;

public class TC002_RegistrationTestCase extends PreAndPost{
	
	@BeforeTest
	public void dataSetUp() {
		sDataExcelName="TC002";
		authors = "Mathan";
		category= "Smoke";
		testcase= "Registration TestCase";
		testdesc= "Registration with Valid Mandatory Fields";
		module =  "Registration";
		}
	
	@Test(priority = 1,dataProvider = "ExcelData")
	public void registrationWithMandatoryFields(String fname,String sex,String uname,String email,String pw) {
		new LoginPage()
			.clickRegisterLink()
			.enterFirstName(fname)
			.selectTitle()
			.selectSex(sex)
			.enterUserName(uname)
			.enterEmail(email)
			.enterPassword(pw)
			.clickOnRegisterButton()
			.validateWelcomePage()
			.clickOnUIBankImage();
			
	}

}
