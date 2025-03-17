package base;

import com.google.gson.JsonObject;
import com.tngtech.java.junit.dataprovider.DataProvider;


public class LTCapability {
  @DataProvider
  public static Object[] getDefaultTestCapability() {
	  JsonObject capabilities1 = new JsonObject();
	    JsonObject ltOptions1 = new JsonObject();

	    String user = "annwesa_basu";
	    String accessKey = "DxEu7pnp8VtKuv5cMUvH14gizjRE47hJUu9j8wmE0zpKSF1swK";

        capabilities1.addProperty("browserName", "Chrome");
	    capabilities1.addProperty("browserVersion", "latest");
	    ltOptions1.addProperty("platform",  "Windows 10");
	    ltOptions1.addProperty("name", "Playwright Test");
	    ltOptions1.addProperty("build", "Playwright102 certification-hyperexecute");
	    ltOptions1.addProperty("user", user);
	    ltOptions1.addProperty("accessKey", accessKey);
	    ltOptions1.addProperty("video",true);
	    capabilities1.add("LT:Options", ltOptions1);
       
	    JsonObject capabilities2 = new JsonObject();
	    JsonObject ltOptions2 = new JsonObject();
	    capabilities2.addProperty("browserName", "MicrosoftEdge"); // Browsers allowed: `Chrome`, `MicrosoftEdge`, `pw-chromium`, `pw-firefox` and `pw-webkit`
	    capabilities2.addProperty("browserVersion", "latest");
	    ltOptions2.addProperty("platform", "Windows 10");
	    ltOptions2.addProperty("name", "Playwright Test");
	    ltOptions2.addProperty("build", "Playwright102 certification-hyperexecute");
	    ltOptions2.addProperty("user", user);
	    ltOptions2.addProperty("accessKey", accessKey);
	    capabilities2.add("LT:Options", ltOptions2);
	    return new Object[]{
	      capabilities1,capabilities2
    
    };
  }
  
  private static String getBrowserName() {
	    String browserName = null;

	    // Check for common environment variables
	    if (System.getenv("BROWSER") != null) {
	        browserName = System.getenv("BROWSER");
	    } else if (System.getenv("BROWSER_NAME") != null) {
	        browserName = System.getenv("BROWSER_NAME");
	    } else if (System.getenv("PLAYWRIGHT_CHROMIUM") != null){
	        browserName = "Chrome";
	    } else if (System.getenv("PLAYWRIGHT_FIREFOX") != null){
	        browserName = "Firefox";
	    } else if (System.getenv("PLAYWRIGHT_WEBKIT") != null){
	        browserName = "webkit";
	    } else if (System.getenv("PLAYWRIGHT_EDGE") != null){
	        browserName = "Edge";
	    } else if (System.getProperty("browser") != null) {
	        browserName = System.getProperty("browser");
	    } else {
	        System.err.println("Error: Browser information not found in environment variables or system properties.");
	        System.exit(1);
	    }

	    return browserName;
	}
}
