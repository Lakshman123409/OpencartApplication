package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		logger.info("*****   Started TC_002 LoginTest      ******");
		
		try {
		//Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount Page
		MyAccountPage ma = new MyAccountPage(driver);
		boolean targetpage =ma.isMyAcountPageExists();
		Assert.assertEquals(targetpage, true);
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("*****   Finished TC_002 LoginTest      ******");
	}
	
	
}
