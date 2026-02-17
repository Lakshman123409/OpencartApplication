package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder=\"First Name\"]") WebElement txtFirstName;
	
	@FindBy(xpath="//input[@placeholder=\"Last Name\"]") WebElement txtLastName;
	
	@FindBy(xpath="//input[@placeholder=\"E-Mail\"]") WebElement txtEmail;
	
	@FindBy(xpath="//input[@placeholder=\"Telephone\"]") WebElement txtTelephone;
	
	@FindBy(xpath="//input[@placeholder=\"Password\"]") WebElement txtPassword;
	
	@FindBy(xpath="//input[@placeholder=\"Password Confirm\"]") WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name=\"agree\"]") WebElement checkContinue;
	
	@FindBy(xpath="//input[@value=\"Continue\"]") WebElement btnContinue;
	
	@FindBy(xpath="//h1[text()=\"Your Account Has Been Created!\"]") WebElement msgConfirmation;
	
	
	
	
	public void setFirstName(String fname) {
		
		txtFirstName.sendKeys(fname);
		
	}
	
    public void setLastName(String lname) {
		
    	txtLastName.sendKeys(lname);
		
	}
    public void setEmail(String email) {
	
    	txtEmail.sendKeys(email);
	
    }
    public void setTelephone(String telePhone) {
	
    	txtTelephone.sendKeys(telePhone);
	
    }
    public void setPassword(String password) {
	
    	txtPassword.sendKeys(password);
	
    }

    public void setConPassword(String password) {
	
    	txtConfirmPassword.sendKeys(password);
	
    }
    
    public void setPrivacyPolicy() {
    	
    	checkContinue.click();
	
    }
    public void clkContinue() {
    	
    	btnContinue.click();
	
    }
    
    public String getConfirmationmsg() {
    	try {
    		return(msgConfirmation.getText());
    	}
    	catch(Exception e) {
    		return(e.getMessage());
    	}
    }

}
