package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_004_Search extends BaseClass {
	
	@Test
	public void searchProduct() throws InterruptedException {
		
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
				
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.clicklogin123(p.getProperty("email"), p.getProperty("password"));
				
		Thread.sleep(3000);

		//MyAccount Page
		MyAccountPage ma = new MyAccountPage(driver);
		boolean targetpage =ma.isMyAcountPageExists();
		Assert.assertEquals(targetpage, true);
		
		ma.clickSearchBox();
		ma.setSearchData();
		ma.clickSearchButton();
		ma.verifyProduct();
		
		
		Thread.sleep(3000);
		
	}

}
