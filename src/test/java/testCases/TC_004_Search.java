package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.ShoppingCart;
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
		Assert.assertEquals(ma.verifyProductName(), "iPhone");
		ma.clickAddToCart();
		ma.navigateToMessage();
		String message=ma.verifyMsgBox();
		Assert.assertEquals(message, "Success: You have added iPhone to your shopping cart!\n×");
		ma.clickShoppingCart();
		Thread.sleep(3000);
		
		ShoppingCart sc = new ShoppingCart(driver);
		sc.verifyShoppingCartTitle();
		Assert.assertEquals(sc.verifyProductDetails()[0], "iPhone");
		Assert.assertEquals(sc.verifyProductDetails()[1], "iPhone ***");
		Assert.assertEquals(sc.verifyProductDetails()[2], "product 11");
		Assert.assertEquals(sc.verifyProductDetails()[3], "1");
		Assert.assertEquals(sc.verifyProductDetails()[4], "$123.20");
		Assert.assertEquals(sc.verifyProductDetails()[5], "$123.20");
		
		sc.deleteProduct();
		Thread.sleep(3000);
		
	}

}
