package libraries;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public abstract class HTMLReport {
	
	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public ExtentTest test, node;
	public String authors,category;
	
	public void startReport() {
		html = new ExtentHtmlReporter("./report/report.html");
		extent = new ExtentReports();
		html.setAppendExisting(false);
		extent.attachReporter(html);
	}
	
	public void endReport() {
		extent.flush();
	}
	
	public ExtentTest startTestCase(String testCaseName, String testDescription) {
		test = extent.createTest(testCaseName, testDescription);
		test.assignAuthor(authors);
		test.assignCategory(category);
		return test;
	}

	public ExtentTest startTestcase(String nodes) {
		node = test.createNode(nodes);
		//tlNode.set(testSuite.createNode(nodes));
		return node;
	}
	
	public void reportStep(String desc,String status) {
		try {
		if(status.equalsIgnoreCase("pass")){
			node.pass(desc, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
			} else {
				node.fail(desc, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());	
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reportStep(String desc,String status, boolean bSnap) {

		MediaEntityModelProvider img = null;
		if(bSnap && !status.equalsIgnoreCase("INFO")){
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build();
			} catch (IOException e) {

			}
		}		

		if(status.equalsIgnoreCase("PASS")) {
			node.pass(desc, img);		
		}else if(status.equalsIgnoreCase("FAIL")) {
			node.fail(desc, img);
			throw new RuntimeException();
		}else if(status.equalsIgnoreCase("WARNING")) {
			node.warning(desc, img);		
		}else {
			node.info(desc);
		}
	}
	
	public abstract String takeScreenshot();

}
