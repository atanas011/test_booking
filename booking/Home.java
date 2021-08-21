package booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Home {

	public static void homeSearch(WebDriver driver, String str) {
		
		// Go to www.booking.com
		driver.get(Params.HOME_URL);
		
		// Enter "Paris" as destination
		driver.findElement(By.id("ss")).sendKeys(str);
		
		// Choose the start and end dates between 19th and 25th of October
		driver.findElement(By.xpath("//div[contains(@class, 'b-datepicker')][@data-mode='checkin']")).click();
		driver.findElement(By.className("bui-calendar__control--next")).click();
		LocalDate checkIn = LocalDate.of(Params.HOME_Y, Params.HOME_M, Params.HOME_IN);
		LocalDate checkOut = LocalDate.of(Params.HOME_Y, Params.HOME_M, Params.HOME_OUT);
		selectDate(driver, checkIn);
		selectDate(driver, checkOut);

		// Select: 2 Adults, 1 Child and 1 Room
		driver.findElement(By.className("xp__guests")).click();
		driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[2]")).click();
		Select drpAge = new Select(driver.findElement(By.name("age")));
		drpAge.selectByVisibleText(Params.HOME_AGE);

		// Click on "Search" button
		driver.findElement(By.className("xp__button")).click();
	}

	private static void selectDate(WebDriver driver, LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		driver.findElement(By.xpath(String.format("//td[@data-date='%s']", formatter.format(date)))).click();
	}
}
