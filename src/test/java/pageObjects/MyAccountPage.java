package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@FindBy(xpath="//h2[normalize-space()=\"My Account\"]") WebElement txtMyAccount;
	
	@FindBy(xpath="//div[@class=\"list-group\"]/a[text()=\"Logout\"]") WebElement lnkLogout;
	
	@FindBy(xpath="//input[@name=\"search\"]") WebElement inpSearch;
	
	@FindBy(xpath="//span[@class=\"input-group-btn\"]") WebElement btnSearch;
	
	@FindBy(xpath="//div[@class=\"product-thumb\"]") WebElement product;
	
	@FindBy(xpath="//h4/a[normalize-space()=\"iPhone\"]") WebElement txtProduct;
	
	@FindBy(xpath="//span[text()=\"Add to Cart\"]/parent::button") WebElement btnAddToCart;
	
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']") WebElement msgBox;
	
	@FindBy(xpath="//a[@title=\"Shopping Cart\"]") WebElement lnkShoppingCart;
	
	
	
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
		js.executeScript("arguments[0].scrollIntoView(true);", product);
		
	}
	public String verifyProductName() {
		wait.until(ExpectedConditions.visibilityOf(txtProduct));
		if(txtProduct.isDisplayed()==true) {
			return txtProduct.getText();
		}
		else {
			return "";
		}
	}
	
	public void clickAddToCart() {
		btnAddToCart.click();
	}
	
	public void navigateToMessage() {
		js.executeScript("arguments[0].scrollIntoView(true);", msgBox);
		
	}
	
	public String verifyMsgBox() {
		return msgBox.getText();
	}
	
	public void clickShoppingCart() {
		js.executeScript("arguments[0].scrollIntoView(true);", lnkShoppingCart);
		wait.until(ExpectedConditions.visibilityOf(lnkShoppingCart));
		//lnkShoppingCart.click();
		js.executeScript("arguments[0].click()", lnkShoppingCart);
		
	}

}
