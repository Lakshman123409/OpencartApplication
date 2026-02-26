package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCart extends BasePage{

	public ShoppingCart(WebDriver driver) {
		super(driver);
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(xpath="//h1[contains(text(),\"Shopping Cart\")]") WebElement titleShoppingCart;
	
	@FindBy(xpath="//form//table[@class=\"table table-bordered\"]//tbody/tr") List<WebElement> rows;
	
	@FindBy(xpath="//form//table[@class=\"table table-bordered\"]//tbody/tr[1]/td") List<WebElement> cols;
	
	@FindBy(xpath="//form//table//tbody/tr/td//img[@title=\"iPhone\"]") WebElement imgiPhone;
	
	@FindBy(xpath="//td[text()=\"product 11\"]/following-sibling::td//input") WebElement quantity;
	
	@FindBy(xpath="//td[text()=\"product 11\"]/following-sibling::td//span/button[2]") WebElement deleteButton;
	
	//@FindBy(xpath="//form//table//tbody/tr/td//img[@title=\"iPhone\"]") WebElement imgiPhone;
	
	public boolean verifyShoppingCartTitle() {
		return titleShoppingCart.isDisplayed();
	}
	
	public String[] verifyProductDetails() {
		String[] pricevalue = new String[cols.size()];
		for(int r=1;r<=rows.size();r++) {
			 
			 WebElement product = driver.findElement(By.xpath("//form//table[@class=\"table table-bordered\"]//tbody/tr["+r+"]/td[2]"));
			 
			 if(product.getText().equals("iPhone ***")) {
				  List<WebElement> price = driver.findElements(By.xpath("//form//table[@class=\"table table-bordered\"]//tbody/tr["+r+"]/td"));
				  
				  pricevalue[0]=imgiPhone.getAttribute("title");
				  pricevalue[1]=price.get(1).getText();
				  pricevalue[2]=price.get(2).getText();
				  pricevalue[3]=quantity.getAttribute("size");
				  pricevalue[4]=price.get(4).getText();
				  pricevalue[5]=price.get(5).getText();
				  
				  break;
			 }
			 
		}
		return pricevalue;
	}
	
	public void deleteProduct() {
		js.executeScript("arguments[0].scrollIntoView(true);", deleteButton);
		deleteButton.click();
	}

}
