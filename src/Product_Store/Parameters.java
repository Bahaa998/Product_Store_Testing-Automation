package Product_Store;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Parameters {
	public WebDriver driver;
	SoftAssert softAssertProccess = new SoftAssert();
	String URL = "https://www.demoblaze.com/";
	
	String ExpectedTitle = "STORE";
	
	String contactName = "Bahaa" + RandomStringUtils.randomAlphanumeric(2);
	String UserName = "Baha" + RandomStringUtils.randomAlphanumeric(2);
	String password = "1998" + RandomStringUtils.randomAlphabetic(2);

	public int numberOfTry = 3;
	
	//Purchase information user
	String Buyer_name = "Bahaa";
	String Country = "Jordan";
	String City = "Amman";
	String Credit_card = "1-322";
	String Month = "";
	String Year = "19";

	@BeforeTest
	public void pre_Test() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get(URL);
	}
	
	@AfterTest
	public void afterMyTest() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("logout2")).click();
		Thread.sleep(3000);
		driver.quit();
	}

}
