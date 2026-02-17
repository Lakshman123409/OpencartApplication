package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;


import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid  - login success - test pass - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail - logout
Data is invalid -- login failed - test pass
*/

public class TC003_LoginTestDDT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")//getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp) {
		
		logger.info("*****   TC003_LoginDDT Test started    ******");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount Page
		MyAccountPage ma = new MyAccountPage(driver);
		boolean targetpage =ma.isMyAcountPageExists();
		
		/*Data is valid  - login success - test pass - logout
		                   login failed - test fail

		Data is invalid - login success - test fail - logout
		                  login failed - test pass
		*/
		
		if(exp.equalsIgnoreCase("valid")) {
			if(targetpage==true) {
				ma.clickLogout();
				Assert.assertTrue(true);
				
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		
		if(exp.equalsIgnoreCase("invalid")) {
			if(targetpage==true) {
				ma.clickLogout();
				Assert.assertTrue(false);
				
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e){
			Assert.fail();
		}
		
		
		logger.info("*****   TC003_LoginDDT Test finished    ******");
		
	}

}
