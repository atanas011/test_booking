package booking;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBooking {

	private static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", Params.DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testHomeSearch() {
		Home.homeSearch(driver, Params.HOME_WHERE);
		String act = driver.getTitle();
		String exp = Params.EXP_RES;
		Assert.assertEquals(act, exp);
	}

	@Test
	public void testResultsSearch() {
		Results.resultsSearch(driver);
		String act = driver.getTitle();
		String exp = Params.EXP_TARGET;
		Assert.assertEquals(act, exp);
	}
	
//	@Test
//	public void testHomeSearchNeg() {
//		Home.homeSearch(driver, "");
//		String act = driver.getTitle();
//		String exp = Params.EXP_RES;
//		Assert.assertNotEquals(act, exp);
//	}

	@AfterClass
	public void endSession() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
