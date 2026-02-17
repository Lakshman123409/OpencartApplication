package testCases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
    public void verify_account_registration() {
		
		//write some code for logs
		logger.info("******  Starting TC001_AccountRegistrationTest    ****");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		logger.info(" clicked on myaccount link ");
		
		hp.clickRegister();
		logger.info(" clicked on register link ");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info(" provideing register data  ");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");//call randomString method to pass random emails
		regpage.setTelephone(randomNumber());
		
		String password = randomAlpahaNumeric();
		regpage.setPassword(password);
		regpage.setConPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clkContinue();
		
		logger.info(" validating expected message ");
		String confmsg=regpage.getConfirmationmsg();
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.info("test failed...");
			logger.debug("Debug logs...");
			Assert.assertFalse(false);
		    }
		
		}
		catch(Exception e) {
			
			Assert.fail();
		}
    }
	


}
