import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	
     public void windowMaximize(WebDriver driver) throws InterruptedException{    	
    	driver.manage().window().maximize();
    	Thread.sleep(5000);
    }
      
     public void scrollDown(WebDriver driver,String xpath){
    	    waitUntilFindElement(driver,xpath);
    		WebElement element = driver.findElement(By.xpath(xpath));
    		Actions actions = new Actions(driver);
    		actions.moveToElement(element);
    		actions.perform();   		
     }
     
     public void waitUntilFindElement(WebDriver driver,String xpath){
    	 new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
     }

	 public String removeFirstLastChar(String str){
		 str = str.substring(1, str.length() - 1);
	     return str;		
	}

}
