package lib.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import util.DataInputProvider;

public class PSM extends WebDriverServiceImpl{
	public Browser browserName;
	public String dataSheetName;
	public String url;

	@BeforeSuite
	public void beforeSuite(){
		startResult();
	}

	@BeforeClass
	public void beforeClass(){		
		startTestModule(testCaseName, testDescription);	
	}


	@AfterSuite
	public void afterSuite(){
		endResult();
	}

	@AfterTest
	public void afterTest(){
	}

	@AfterMethod
	public void afterMethod(){
		closeAllBrowsers();

	}

	@DataProvider(name="fetchData")
	public  Object[][] getData(){
		return DataInputProvider.getSheet(dataSheetName);		
	}	

	@BeforeMethod
	public void startApp() {
		// if url not defined in test case default url should be app url
		if(url == null) {
			url = "http://leaftaps.com/opentaps/control/main";
		}
		test = startTestCase(testNodes);
		test.assignCategory(category);
		test.assignAuthor(authors);
		switch (browserName) {
		case Chrome:
			webdriver = new ChromeDriver();
			break;
		case Edge:
			webdriver = new EdgeDriver();
			break;
		case Firefox:
			webdriver = new FirefoxDriver();
			break;
		case IE:
			webdriver = new InternetExplorerDriver();
			break;
		case Opera:
			webdriver = new OperaDriver();
			break;
		case Safari:
			webdriver = new SafariDriver();
			break;
		default:
			break;
	
		}
		driver = new EventFiringWebDriver(webdriver);
		driver.register(this);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(03, TimeUnit.SECONDS);
		driver.get(url);
	}



}
