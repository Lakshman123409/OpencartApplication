package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCart extends BasePage{

	public ShoppingCart(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h1[contains(text(),\"Shopping Cart\")]") WebElement titleShoppingCart;
	
	@FindBy(xpath="//form//table[@class=\"table table-bordered\"]//tbody/tr") List<WebElement> rows;
	
	public boolean verifyShoppingCartTitle() {
		return titleShoppingCart.isDisplayed();
	}
	
	public String verifyProductDetails() {
		String pricevalue="";
		for(int r=1;r<=rows.size();r++) {
			 
			 WebElement product = driver.findElement(By.xpath("//form//table[@class=\"table table-bordered\"]//tbody/tr["+r+"]/td[2]"));
			 if(product.getText().equals("iPhone ***")) {
				  WebElement price = driver.findElement(By.xpath("//form//table[@class=\"table table-bordered\"]//tbody/tr["+r+"]/td[5]"));
				  pricevalue=price.getText();
				  break;
			 }
			 
		}
		return pricevalue;
	}

}
