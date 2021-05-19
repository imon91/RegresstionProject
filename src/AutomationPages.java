import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AutomationPages {
	private String sellInput = "//*[@id='currency-exchange-app']/div/div/div[2]/div[1]/form/div[1]/input";
	private String buyInput = "//*[@id='currency-exchange-app']/div/div/div[2]/div[1]/form/div[3]/input";
	private String countryIcon = "/html/body/footer/div[2]/div/div/div[2]/div/span/span[1]";
	private String countryDropdwn = "//*[@id='countries-dropdown']";
	private String countryName1 = "//*[@id='popover373650']/div[2]/div/form/div[1]/div/div/ul/li[9]/a/span";
	private String currencyName = "//*[@id='currency-exchange-app']/div/div/div[2]/div[1]/form/div[1]/div/div[1]/span";
	private String firstRowOfferedCurrency = "//*[@id='currency-exchange-app']/div/div/div[2]/table/tbody/tr[1]";
	private String paySeraValue = "//*[@id='currency-exchange-app']/div/div/div[2]/table/tbody/tr[1]/td[4]/span/span/span";
	
	
	public String isBuyFieldNull(WebDriver driver){
		Utility extra = new Utility();
		extra.waitUntilFindElement(driver, sellInput);
		driver.findElement(By.xpath(sellInput)).sendKeys("50");
		extra.waitUntilFindElement(driver, buyInput);		
		String result = driver.findElement(By.xpath(buyInput)).getAttribute("value");
			
		if(result.isEmpty()){
			result = null;
			System.out.println(result +"result");
		}
		return result;
				
	}
	
	public String isSellFieldnull(WebDriver driver){
		Utility extra = new Utility();
		extra.waitUntilFindElement(driver, buyInput);
		driver.findElement(By.xpath(buyInput)).sendKeys("50");
		extra.waitUntilFindElement(driver, sellInput);		
		String result = driver.findElement(By.xpath(sellInput)).getAttribute("value");
		
		if(result.isEmpty()){
			result = null;
			System.out.println(result +"result");
		}
		return result;		
	}
	
	public void changeCountryDropdown(WebDriver driver,String countryName) throws InterruptedException{
		driver.findElement(By.xpath(countryIcon)).click();
		Utility extra = new Utility();
		extra.waitUntilFindElement(driver, countryDropdwn);		
		driver.findElement(By.xpath(countryDropdwn)).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText(countryName)).click();		
	}
		
	public String getRate(WebDriver driver) throws InterruptedException{
		Utility extra = new Utility();
		extra.scrollDown(driver,sellInput);
		Thread.sleep(5000);
		String currentCurrency = driver.findElement(By.xpath(currencyName)).getText();
		System.out.println(currentCurrency);
		return currentCurrency;		
	}
	
	public ArrayList<String> ammountChecking(WebDriver driver){
	   ArrayList<String> list = new ArrayList<String>();
	   String peySera = driver.findElement(By.xpath(paySeraValue)).getText();
	   float floatPaySera =Float.parseFloat(peySera);  
	   for(int i=5;i<9;i++){
		  String otherbank = driver.findElement(By.xpath("//*[@id='currency-exchange-app']/div/div/div[2]/table/tbody/tr[1]/td["+i+"]/span/span/span[1]")).getText();
		  float floatOtherbank=Float.parseFloat(otherbank); 
		  
		  if(floatPaySera>floatOtherbank){
			  String lossValue = checkingLossValue(driver,"//*[@id='currency-exchange-app']/div/div/div[2]/table/tbody/tr[1]/td["+i+"]/span/span/span[2]");
			  list.add(lossValue);	  			  
		    }
		  else{
			  list.add("No Loss");
		  }		  
	   }
	   return list;	   		
	}
	
	public String checkingLossValue(WebDriver driver,String xpath){
		Utility extra = new Utility();
     	String lossValue 	=driver.findElement(By.xpath(xpath)).getText();     	
     	if(lossValue.isEmpty()){     		
     		lossValue = null;
     	}
     	else{
     		lossValue = extra.removeFirstLastChar(lossValue);     		
     	}
		return lossValue;		
	}
			
	public void checkLoss(WebDriver driver){
		Utility extra = new Utility();
		ArrayList<String> list = new ArrayList<String>();		
		for(int i=5;i<9;i++){
		   String diffrence =	driver.findElement(By.xpath("//*[@id='currency-exchange-app']/div/div/div[2]/table/tbody/tr[1]/td["+i+"]/span/span/span[2]")).getText();
		   String newdiffrence = extra.removeFirstLastChar(diffrence);
		   list.add(newdiffrence);
		}
		
		for (int i = 0; i < list.size();i++) 
		  { 		      
		    System.out.println(list.get(i)); 		
		  }				
	   }
   }
