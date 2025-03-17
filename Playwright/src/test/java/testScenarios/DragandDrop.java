package testScenarios;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.WaitUntilState;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import base.Config;
import base.LTCapability;

@RunWith(DataProviderRunner.class)
public class DragandDrop extends BaseTest {

	@Test
	@UseDataProvider(value = "getDefaultTestCapability", location = LTCapability.class)
	public void TestScenario2(JsonObject capability) {

		Driver driver = null;
		Page page = null;
		try {

			driver = super.createConnection(capability);
			page = driver.getPage();
			page.navigate(Config.testURL);
			
			Locator locDragDrop = page.locator("//a[text()= 'Drag & Drop Sliders']");
			locDragDrop.click();
			System.out.println("Navigated to drag and drop slider screen");

			Locator sliderValue = page.locator("//input[@type='range' and @value='15']");
			Locator outputValue = page.locator("//output[@id='rangeSuccess']");

			double slidermove = 0;
			String updatedValue = null;

			for (int i = 1; i <= 31; i++) {
				BoundingBox boundingbox = sliderValue.boundingBox();
				page.mouse().move(boundingbox.x + slidermove, boundingbox.y);
				page.mouse().down();
				slidermove += 15;
				page.mouse().move(boundingbox.x + slidermove, boundingbox.y);
				page.mouse().up();

				// Get updated slider value after each move
				updatedValue = outputValue.textContent();
				
				// Break loop if the slider reaches 95
				if (updatedValue.equals("95")) {
					break;
				}
			}
			// Verify updated value
			Thread.sleep(500); // Ensure UI updates before fetching value
			updatedValue = outputValue.textContent();
			System.out.println("Updated output value is " + updatedValue);

			assertTrue(updatedValue.equals("95"));
			System.out.println("Passed: Output value updated successfully.");

			if (page.title().equalsIgnoreCase("Selenium Grid Online | Run Selenium Test On cloud")) {
				super.setTestStatus("Passed", "Title matched", page);
			} else {
				super.setTestStatus("Failed", "Title not matched", page);

			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setTestStatus("Failed", e.getMessage(), page);
		}
	}
}
