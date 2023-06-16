package testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1 {
	static WebDriver driver=null;
	String rootfolder=System.getProperty("user.dir");
	@BeforeTest
	public void setup() {
		String rootfolder=System.getProperty("user.dir");
		System.setProperty("WebDriver.chome.driver", rootfolder+"\"//src//test//resources//chromedriver.exe\"");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	
	}
	@Test
	private void testcase1() throws FileNotFoundException, IOException {
		// getting data from the property file
		Properties prop = new Properties();
		prop.load(new FileInputStream(rootfolder + "//src//test//resources//testdata.properties"));
		String wesiteurl=prop.getProperty("websitelink");
		String menulink=prop.getProperty("linktext");
		
		//opening the browser
		driver.get(wesiteurl);
		
		//fetching and printing the pagetitle
		String pagetitle1=driver.getTitle();
		System.out.println(pagetitle1);
		
		//opening the menu link and fetching  the pagetitle
		driver.findElement(By.linkText(menulink)).click();
		String pagetitle2=driver.getTitle();
		System.out.println(pagetitle2);
		
		//navigating back and fetching page title
		driver.navigate().back();
		String pagetitle3=driver.getTitle();
		System.out.println(pagetitle3);
		
		//verifing that both the pagetitles are equal or not
		if(pagetitle1.equals(pagetitle3))
		{
			System.out.println("Both the page titles are same");
		}
		else
		{
			System.out.println("Both page titles are different");
		}
		
		}
	@AfterTest
	public void closebrowser() {
		driver.close();
	}
		
	}


