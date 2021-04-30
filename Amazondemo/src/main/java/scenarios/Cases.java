package scenarios;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

@Listeners(list.Demo.class)
public class Cases extends Configuration {

	

	@Parameters({ "Bookname" })
	@Test(priority = 0, description = "verify filter option in search")
	public void scenario1(String book) throws IOException {

		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());

		WebElement dro = driver.findElement(By.cssSelector(
				"select#searchDropdownBox.nav-search-dropdown.searchSelect.nav-progressive-attrubute.nav-progressive-search-dropdown"));
		Select s = new Select(dro);
		s.selectByValue("search-alias=stripbooks");

		Actions ac = new Actions(driver);

		
		WebElement ele = driver.findElement(By.id("twotabsearchtextbox"));
		ac.click(ele).sendKeys(book).sendKeys(Keys.ENTER).build().perform();
		// Da Vinci code
		

		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "verifyfilteroptioninsearch")));

	}

	@Parameters({ "Item" })
	@Test(priority = 1, description = "Search an item from home page")
	public void scenario2(String item) throws IOException {
		// field-keywords
		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		Actions ac = new Actions(driver);
		WebElement ele = driver.findElement(By.name("field-keywords"));

		ac.click(ele).sendKeys(item).sendKeys(Keys.ENTER).build().perform();
		logger.log(LogStatus.INFO, "Scenario2 : Searching an item from home page");
		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "searchingitem")));


	}

	@Parameters({ "Item" })
	@Test(priority = 2, description = "Adding item to cart")
	public void scenario3(String item) throws InterruptedException, IOException {
		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		Actions ac = new Actions(driver);
		WebElement ele = driver.findElement(By.name("field-keywords"));

		ac.click(ele).sendKeys(item).sendKeys(Keys.ENTER).build().perform();

		String mainwindow = driver.getWindowHandle();
		// driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[1]/div/span[3]/div[2]/div[3]/div/span/div/div/div/div/div[2]/div[2]/div/div[1]/h2/a/span")).click();
		List<WebElement> listofProducts = driver
				.findElements(By.cssSelector("span.a-size-medium.a-color-base.a-text-normal"));
		for (WebElement webElement : listofProducts) {

			if (webElement.getText().equalsIgnoreCase("New Apple iPhone 12 (128GB) - Blue")) {

				webElement.click();
				break;

			}

		}

		Thread.sleep(5000);

		Set<String> setofWindows = driver.getWindowHandles();

		for (String string : setofWindows) {

			// System.out.println(string);
			if (mainwindow.equalsIgnoreCase(string)) {

			} else {
				driver.switchTo().window(string);
			}

		}
		Thread.sleep(5000);
		// for checking
		System.out.println(driver.getTitle());
		WebElement elem = driver.findElement(By.cssSelector("select#quantity.a-native-dropdown.a-declarative"));
		elem.click();
		Thread.sleep(5000);
		Select s = new Select(elem);
		s.selectByValue("3");
		driver.findElement(By.cssSelector("#add-to-cart-button")).click();
		Thread.sleep(5000);

		// after adding to cart
		driver.findElement(By.xpath("//*[@id=\"attach-sidesheet-view-cart-button\"]/span/input")).click();

		
		Thread.sleep(3000);
		
		logger.log(LogStatus.INFO, "Scenario3 : Adding item to the cart");
		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "addingitem")));

	}

	@Test(priority = 3, description = "navigation to Home page")

	public void scenario4() throws InterruptedException, IOException {
		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		driver.findElement(By.cssSelector("a#nav-hamburger-menu")).click();
		driver.findElement(By.linkText("Customer Service")).click();
		// Assert.assertEquals(driver.getTitle(), "Hello");
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div#nav-logo")).click();
		logger.log(LogStatus.INFO, "Scenario4: Navigation to home page");
		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "navigationtohome")));

	}

	@Test(priority = 4, description = "verifying sign page")
	public void scenario5() throws IOException {
		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		driver.findElement(By.cssSelector("a#nav-link-prime.nav-a")).click();
		driver.findElement(By.cssSelector("a#a-autoid-0-announce.a-button-text")).click();
		logger.log(LogStatus.INFO, "Scenario5 : Verifying signin page");
		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "verifyingsignin")));
	}

	@Test(priority = 5, description = "Invalid account opening")
	public void scenario6() throws IOException {
		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		driver.findElement(By.cssSelector("a#nav-link-prime.nav-a")).click();
		driver.findElement(By.cssSelector("a#a-autoid-0-announce.a-button-text")).click();
		driver.findElement(By.cssSelector("a#createAccountSubmit.a-button-text")).click();
		driver.findElement(By.cssSelector("input#continue.a-button-input")).click();
		logger.log(LogStatus.INFO, "Scenario6 : Invalid account opening");
		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "Invalidaccountopening")));
	}

	@Parameters({ "Item" })
	@Test(priority = 6, description = "verifying sorting ")
	public void scenario7(String item) throws IOException {
		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		Actions ac = new Actions(driver);
		WebElement ele = driver.findElement(By.name("field-keywords"));

		ac.click(ele).sendKeys(item).sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.cssSelector("span#a-autoid-0.a-button.a-button-dropdown.a-button-small")).click();
		driver.findElement(By.cssSelector("a#s-result-sort-select_2.a-dropdown-link")).click();
		logger.log(LogStatus.INFO, "Scenario7 : Verifying sorting");
		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "Verifyingsorting")));
	}

	@Test(priority = 7, description = "navigation to product details page")

	public void scenario8() throws IOException, InterruptedException {
		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		driver.findElement(By.cssSelector("a#nav-hamburger-menu")).click();
		driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[7]/a")).click();
		driver.findElement(By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible.hmenu-translateX > li:nth-child(3) > a")).click();
		Thread.sleep(2000);
		logger.log(LogStatus.INFO, "Scenario8 : Navigation to product details page");
		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "productdetails")));
	}

	@Test(priority = 8, description = "verifying footer elements ")
	public void scenario9() throws InterruptedException, IOException {
		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");

		Thread.sleep(2000);
		driver.findElement(By.linkText("About Us")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
		Thread.sleep(2000);
		logger.log(LogStatus.INFO, "Scenario9 : Verifying footer elements");
		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "verifyfooterelements")));
	}

	@Parameters({ "Item" })
	@Test(priority=9, description="Verifying filter option")
	public void scenario10(String item) throws IOException
	{
		logger = report.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		// clicking on price  filter => under 1000
		
		Actions ac = new Actions(driver);
		WebElement ele = driver.findElement(By.name("field-keywords"));

		ac.click(ele).sendKeys(item).sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.cssSelector("#p_36\\/1318503031 > span > a > span")).click();
		
		logger.log(LogStatus.INFO, "Scenario10 : Verifying filter options(price filter");
		logger.log(LogStatus.INFO, logger.addScreenCapture(captureScreenshot(driver, "verifyingfilteroption")));
	}
	
	
	
	public static String captureScreenshot(WebDriver driver, String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = "C:\\Users\\HP\\Desktop\\Amazondemo\\Amazondemo\\Screenshot\\report.html" + name + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
