package testScenarios;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import base.Config;
import base.LTCapability;

@RunWith(DataProviderRunner.class) 
public class SimpleForm extends BaseTest {

	  @Test
	  @UseDataProvider(value = "getDefaultTestCapability", location = LTCapability.class)
	  public void sampleTest1(JsonObject capability) throws Exception {

		  
		Driver driver = null;
	    Page page = null;
		try {
			 driver = super.createConnection(capability);
		      page = driver.getPage();
		      page.navigate(Config.testURL);
		      
		      Locator locSimpleForm = page.locator("//a[text() = 'Simple Form Demo']");
		      locSimpleForm.click();
			String currentUrl = page.url();
			System.out.println(currentUrl);
			assertTrue(currentUrl.contains("simple-form-demo"));
			System.out.println("Expected page loaded");	
			
			String message = "Welcome to Lambda Test";
			page.locator("//input[@id='user-message']").fill(message);
			//Thread.sleep(1000);
			page.locator("//button[@id='showInput' and @type= 'button']").click();
			//Thread.sleep(2000);
			String yourMessage = page.locator("//div[@id= 'user-message' ]//p[@id= 'message']").textContent();
			assertTrue(yourMessage.equals(message));
			System.out.println("Same message displayed in 'Your Message' section.");

			if (page.title().equalsIgnoreCase("Selenium Grid Online | Run Selenium Test On cloud")) {
				super.setTestStatus("Passed", "Title matched", page);
			}else {
				super.setTestStatus("Failed", "Title not matched",page);
				
		}		
			
		}
		catch (Exception e) {
			e.printStackTrace();
			super.setTestStatus("FAILED", e.getMessage(), page);
		      super.closeConnection(driver);
		}
	}

}
