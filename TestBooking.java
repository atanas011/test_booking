package booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBooking {

	protected static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\bin\\chromedriver.exe");
		driver = new ChromeDriver();

		// Saves last session user data
//		ChromeOptions testProfile = new ChromeOptions();
//		testProfile.addArguments("user-data-dir=" + "C:/Users/Lenovo/AppData/Local/Google/Chrome/User Data/TestProfile");
//		driver = new ChromeDriver(testProfile);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testSearch() {
		driver.get("https://www.booking.com/");
		driver.findElement(By.id("ss")).sendKeys("Paris");
		driver.findElement(By.xpath("//div[contains(@class, 'b-datepicker')][@data-mode='checkin']")).click();
		driver.findElement(By.className("bui-calendar__control--next")).click();
		LocalDate checkIn = LocalDate.of(2021, 10, 19);
		LocalDate checkOut = LocalDate.of(2021, 10, 25);
		selectDate(driver, checkIn);
		selectDate(driver, checkOut);
		driver.findElement(By.className("xp__guests")).click();
		driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[2]")).click();
		Select drpAge = new Select(driver.findElement(By.name("age")));
		drpAge.selectByVisibleText("6 years old");
		driver.findElement(By.className("xp__button")).click();
		driver.findElement(By.id("ss")).clear();
		driver.findElement(By.id("ss")).sendKeys("Novotel Paris Les Halles");
		driver.findElement(By.className("sb-autocomplete--photo")).click();
		driver.findElement(By.className("sb-searchbox__button")).click();
		driver.findElement(By.xpath(".//*[contains(@href,'novotel-paris-les-halles')]")).click();
	}

	private void selectDate(WebDriver driver, LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		driver.findElement(By.xpath(String.format("//td[@data-date='%s']", formatter.format(date)))).click();
	}

	@AfterClass
	public void endSession() {
		try {
			Thread.sleep(9999);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
