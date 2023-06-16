package testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test2 {
	static WebDriver driver=null;
	String rootfolder=System.getProperty("user.dir");
	@BeforeTest
	public void setup() {
		//setting up web driver
		String rootfolder=System.getProperty("user.dir");
		System.setProperty("WebDriver.chome.driver", rootfolder+"\"//src//test//resources//chromedriver.exe\"");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	
	}
	@Test
	private void testcase2() throws FileNotFoundException, IOException {
		// getting data from the property file
		Properties prop = new Properties();
		prop.load(new FileInputStream(rootfolder + "//src//test//resources//testdata1.properties"));
		String wesiteurl=prop.getProperty("websitelink");
		String signinxpath=prop.getProperty("signinxpath");
		String expected=prop.getProperty("ExpectedPT");
		String emailxpath=prop.getProperty("emailxpath");
		String Email=prop.getProperty("Email");
		String errorxpath=prop.getProperty("Errormsgxpath");
		String Cxpath=prop.getProperty("cxpath");
		String expectedmsg=prop.getProperty("expectedmsg");
		
		
		//opening the browser
		driver.get(wesiteurl);
		
		//fetching and printing the pagetitle
		String pagetitle1=driver.getTitle();
		System.out.println(pagetitle1);
		
		//verifing sign in page title with expected title
		driver.findElement(By.xpath(signinxpath)).click();
		String actual=driver.getTitle();
		Assert.assertEquals(actual, expected);
		
		//sending the email id and clicking continue
		driver.findElement(By.xpath(emailxpath)).sendKeys(Email);
		driver.findElement(By.xpath(Cxpath)).click();
		
		//fetching error message and verifing it with the expected message
		String actualmsg=driver.findElement(By.xpath(errorxpath)).getText();
		System.out.println(actualmsg);
		Assert.assertEquals(actualmsg, expectedmsg);
		
		
	}
	@AfterMethod
	public void closebrowser() {
		//closing web browser
		driver.close();
	}

}
