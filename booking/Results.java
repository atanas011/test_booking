package booking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Results {

	public static void resultsSearch(WebDriver driver) {
		
		// Select "Novotel Paris Les Halles"
		driver.findElement(By.id("ss")).clear();
		driver.findElement(By.id("ss")).sendKeys(Params.RES_NAME);
		driver.findElement(By.className("sb-autocomplete--photo")).click();
		driver.findElement(By.className("sb-searchbox__button")).click();
		driver.findElement(By.xpath(".//*[contains(@href,'novotel-paris-les-halles')]")).click();

		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
	}
}
