package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//h2[normalize-space()=\"My Account\"]") WebElement txtMyAccount;
	
	@FindBy(xpath="//div[@class=\"list-group\"]/a[text()=\"Logout\"]") WebElement lnkLogout;
	
	@FindBy(xpath="//input[@name=\"search\"]") WebElement inpSearch;
	
	@FindBy(xpath="//span[@class=\"input-group-btn\"]") WebElement btnSearch;
	
	@FindBy(xpath="//div[@class=\"product-thumb\"]") WebElement product;
	
	public boolean isMyAcountPageExists() {
		try {
			return(txtMyAccount.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	public void clickSearchBox() {
		inpSearch.click();
	}
	
	public void setSearchData() {
		inpSearch.sendKeys("iphone");
	}
	
	public void clickSearchButton() {
		btnSearch.click();
	}
	
	public void verifyProduct() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", product);
	}

}
