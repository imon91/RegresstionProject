import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Automation {
	protected WebDriver driver;
	//Automation class perform only action
	//AutomationPages Class Contains id/xpath for find-out element
	
	@Test(priority=0)
	public void baseUrl() throws InterruptedException{
		//this is base function to call base URL
		
		String path = System.getProperty("user.dir");
		System.out.println(path); 
		System.setProperty("webdriver.chrome.driver",path+"\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.paysera.lt/v2/en-LT/fees/currency-conversion-calculator");		
		Utility extra = new Utility();//In Utility class contains some function for several times use
		extra.windowMaximize(driver); //window maximize function calling			
		extra.scrollDown(driver,"//*[@id='currency-exchange-app']/div/div/div[2]/table/thead/tr/th[1]");
	}
	
	@Test(priority=1)
	public void checkingBuyFiledNullEnteredSellvalue(){
		//input the sell filled and checked buy field empty or not
		AutomationPages nullCheckedBuyField =new AutomationPages();
	    String result =	nullCheckedBuyField.isBuyFieldNull(driver); 	    
	    Assert.assertNull(result);	
	}
	
	@Test(priority=2)
	public void checkingSellFieldNullEnteredBuyValue(){
		//input the buy filled and checked sell field empty or not
		AutomationPages nullCheckedSellField =new AutomationPages();
	    String result =	nullCheckedSellField.isSellFieldnull(driver); 	    
	    Assert.assertNull(result);		
	}
	
	@Test(priority=3)
	public void changeCountrycheckCurrency() throws InterruptedException{
		//change the country name and then check the currency name changed or not
		
		Utility extra = new Utility();					
		extra.scrollDown(driver,"/html/body/footer/div[2]/div/div/div[2]/div/span/span[1]");
		AutomationPages selectCountry = new AutomationPages();
	    String countryName= "Poland";
		selectCountry.changeCountryDropdown(driver,countryName);		
		String result = selectCountry.getRate(driver);//getRate return currency name
		String forPolnd= "PLN";
		Assert.assertEquals(forPolnd,result,"For Poland currency would be PLN");		
	}
	
	@Test(priority=4)
	public void diffirenceAmmount() throws InterruptedException{
		//it compares paysera offer value with other bank if paysera offer more then it checked the display box showed or not how much  customer loss  
		
		String result="";
		AutomationPages selectCountry = new AutomationPages();
	    String countryName= "Lithuania";
		selectCountry.changeCountryDropdown(driver,countryName);
		Utility extra = new Utility();
		extra.scrollDown(driver,"//*[@id='currency-exchange-app']/div/div/div[2]/table/tbody/tr[5]");
		ArrayList<String> list = selectCountry.ammountChecking(driver);
		for (int i = 0; i < list.size();i++) 
		  { 		      
		   	    if(list.get(i).isEmpty()){
		    	result = "Failed";
		    	break;
		    }
		    else{
		    	 System.out.println(list.get(i)); 
		    	 result = "Passed";
		    }
		  }
		 Assert.assertEquals("Passed",result);
	    }

		
}
