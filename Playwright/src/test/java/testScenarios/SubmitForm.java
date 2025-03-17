package testScenarios;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import base.Config;
import base.LTCapability;

@RunWith(DataProviderRunner.class)
public class SubmitForm extends BaseTest {
	
	
	@Test
	@UseDataProvider(value = "getDefaultTestCapability", location = LTCapability.class)
	public void TestScenario3(JsonObject capability) {
		
		Driver driver= null;
		Page page = null;
		try {
			driver = super.createConnection(capability);
			page = driver.getPage();
			page.navigate(Config.testURL);
			
			Locator locInputForm =page.locator("//a[text() = 'Input Form Submit']");
			locInputForm.click();			
			Locator submitButton = page.locator("//button[text()='Submit']");
			submitButton.click();
			Thread.sleep(100);
			Locator validationMessage = page.locator("//div[@class='form-group w-4/12 smtablet:w-full text-section pr-20 smtablet:pr-0']/input[@type='text']");

			if (validationMessage.isVisible()) {
				System.out.println("Validation message is displayed: 'Please fill out this field.'");
			} else {
				System.out.println("Validation message is not displayed.");
			}			
			page.locator("//input[@placeholder='Name']").fill("Arya");
			page.locator("//input[@placeholder='Email']").fill("arya@gmail.com");
			page.locator("//input[@placeholder='Password']").fill("Arya@123");
			page.locator("//input[@placeholder='Company']").fill("Abc Pvt ltd");
			page.locator("//input[@placeholder='Website']").fill("www.abc.com");
			page.locator("//input[@placeholder='City']").fill("Pune");
			page.locator("//label[text()='Country*']/following-sibling::select").selectOption("India");
			page.locator("//input[@placeholder='Address 1']").fill("26 Main road");
			page.locator("//input[@placeholder='Address 2']").fill("Wakad");
			page.locator("//input[@placeholder='State']").fill("Pune");
			page.locator("//input[@placeholder='Zip code']").fill("560100");
			submitButton.click();
			assertTrue(page.locator("//p[contains(@class, 'success-msg')]").textContent().equals("Thanks for contacting us, we will get back to you shortly."));
			
			if (page.title().equalsIgnoreCase("Selenium Grid Online | Run Selenium Test On cloud")) {
				super.setTestStatus("Passed", "Title matched", page);
			}else {
				super.setTestStatus("Failed", "Title not matched",page);
				
		}
				
			
		}
		catch (Exception e) {
			e.printStackTrace();
			super.setTestStatus("Failed", e.getMessage(), page);
		}
	}

}
