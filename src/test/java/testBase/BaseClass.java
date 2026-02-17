package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

//Base is required for all testcases so that's why keep it in separate class in s
//separate package. so all testcases can extends this class to all required methods


import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Regression","Sanity","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());//load logj2.xml file
		
		if(p.getProperty("execution_env").equals("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox": capabilities.setBrowserName("firefox");break;
			default:System.out.println("No matching browser");return;
			}
			
			driver=new RemoteWebDriver(new URL("http://192.168.1.6:4444"),capabilities);//url is made upby using your ipaddress
			
			
		}
		
		if(p.getProperty("execution_env").equals("local")) {
			switch(br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			default: System.out.println("Invalid browser"); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Regression","Sanity","Master"})
    public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.secure().nextAlphanumeric(5);
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.secure().nextNumeric(6);
		return generatedNumber;
	}
	
	public String randomAlpahaNumeric() {
		String generatedString = RandomStringUtils.secure().nextAlphanumeric(3);
		String generatedNumber = RandomStringUtils.secure().nextNumeric(3);
		return (generatedString+"@"+generatedNumber+"#");
	}
	
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
         
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile= new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}


}
