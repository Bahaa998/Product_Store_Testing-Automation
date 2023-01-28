package Product_Store;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class productStoreTestNG extends Parameters {
	
//***************************************** Check Actual Title ***********************************************
	@Test(priority = 1, enabled = true)
	public void Check_The_Title() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String ActualTitle =  driver.getTitle();
		softAssertProccess.assertEquals(ActualTitle, ExpectedTitle, "Check the actual title : ");
		softAssertProccess.assertAll();
	}
	
//****************************************** images Slider Check ***********************************************
	@Test(priority = 2, enabled = true)
	  public void images_Slider_Check() {
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    
	    List<WebElement> sliderImages =driver.findElements(By.className("d-block"));
	    
	    boolean flag =true;
	    for (int i = 0; i < sliderImages.size(); i++) {
	      for (int j = 0; j < sliderImages.size(); j++) {
	        if ((i!=j) && sliderImages.get(i).getAttribute("src").equals(sliderImages.get(j).getAttribute("src"))) {
	          flag=false;
	        }else continue;
	      }
	    }
	    softAssertProccess.assertEquals(flag, true,"images slider check : ");
	    softAssertProccess.assertAll();
	  }
	
//************************************ Validate Contact Form Information ******************************************
	@Test(priority = 3, enabled = true , invocationCount = 1)
	public void Validate_Contact_Form_Information() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
//		String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
//		System.out.println(email);
		
		String[] namesOfEmails = {"bop@gmail.com","bah@gmail", "boppgmail.com", "bahaa@gmail.com"};
		Random emailIndex = new Random();
		int index = emailIndex.nextInt(4);
		
		driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[2]/a")).click();
		Thread.sleep(750);
		driver.findElement(By.id("recipient-email")).sendKeys(namesOfEmails[index]);
		Thread.sleep(750);
//		driver.findElement(By.id("recipient-email")).clear();
//		driver.findElement(By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[1]")).click();
		String myValue = driver.findElement(By.id("recipient-email")).getAttribute("value");
		
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(namesOfEmails[index]);
		System.out.println(myValue + " valid email : " + matcher.matches());
		
		boolean checkEmail = matcher.matches();
		
		softAssertProccess.assertEquals(checkEmail, true, "The email structure in not matching the correct one : ");
		
		driver.findElement(By.id("recipient-name")).sendKeys(contactName);
		driver.findElement(By.id("message-text")).sendKeys("Hi, i'm here : ");
		driver.findElement(By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]")).click();
		Thread.sleep(1000);
		String alertMSG =  driver.switchTo().alert().getText();
		
		boolean CheckSuccessfullySendMSG = alertMSG.contains("Thanks");
		softAssertProccess.assertEquals(CheckSuccessfullySendMSG, true,"Verify that the message was sent successfully : ");
		driver.switchTo().alert().accept();
		
		softAssertProccess.assertAll();

	}
	
//********************************* Check Sign up successful & Check Login successful *****************************
	@Test(priority = 4, enabled = true)
	public void SignUp_Login() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		
		//--------- Sign up -----------
		driver.findElement(By.id("signin2")).click();
		driver.findElement(By.id("sign-username")).sendKeys(UserName + Keys.TAB + password); 
		driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
		Thread.sleep(2000);
		String alertSignUp =  driver.switchTo().alert().getText();		
		boolean checkSignUp = alertSignUp.contains("successful");
		softAssertProccess.assertEquals(checkSignUp, true, "Check Sign up successful : ");
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
		//-------- Login ------------
		driver.findElement(By.id("login2")).click();
		driver.findElement(By.id("loginusername")).sendKeys(UserName + Keys.TAB + password);
		driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
		Thread.sleep(5000);
		String nameOfUser =  driver.findElement(By.xpath("//*[@id=\"nameofuser\"]")).getText();
		boolean checkLoginUser = nameOfUser.contains(UserName);
		
		softAssertProccess.assertEquals(checkLoginUser, true, "Check Login successful : ");
		softAssertProccess.assertAll();
	}
	
//******************************************* Buy Products *********************************************
	@Test(priority = 5, enabled = true)
	public void Buy_Product() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(2000);
		
		//((((Samsung galaxy s6)))) 
		driver.findElement(By.xpath("//*[@id=\"itemc\"]")).click();
		String nameAtHome =  driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a")).getText();
		driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a")).click();
		String nameInside = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h2")).getText();
		softAssertProccess.assertEquals(nameInside, nameAtHome, "Check Actual name of item [1] : ");
		Thread.sleep(1000);

		for(int i = 0; i < numberOfTry;i++) {
		driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
		Thread.sleep(1500);
		String Added = driver.switchTo().alert().getText();
		boolean checkAddedProduct_1 = Added.contains("added");
		softAssertProccess.assertEquals(checkAddedProduct_1, true, "Check add Samsung galaxy s6 : ");
		driver.switchTo().alert().accept();
		}
		Thread.sleep(1000);
		driver.navigate().to("https://www.demoblaze.com/index.html");		
		
		//((((Sony xperia z5))))
		String nameAtHome2 = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[6]/div/div/h4/a")).getText();
		driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[6]/div/a/img")).click();
		String nameInside2 = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h2")).getText();
		softAssertProccess.assertEquals(nameInside2, nameAtHome2, "Check Actual name of item [2] : ");
		Thread.sleep(1000);

		for(int i = 0; i < numberOfTry;i++) {
			driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
			Thread.sleep(1500);
			String Added = driver.switchTo().alert().getText();
			boolean checkAddedProduct_2 = Added.contains("added");
			softAssertProccess.assertEquals(checkAddedProduct_2, true, "Check add Sony xperia z5 : ");
			driver.switchTo().alert().accept();
			}
		Thread.sleep(1000);
		driver.navigate().to("https://www.demoblaze.com/index.html");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"next2\"]")).click();
		Thread.sleep(2000);

		//((((MacBook Pro))))
		String nameAtHome3 =  driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[6]/div/div/h4/a")).getText();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[6]/div/div/h4/a")).click();
		String nameInside3 = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h2")).getText();
		softAssertProccess.assertEquals(nameInside3, nameAtHome3, "Check Actual name of item [3] : ");
		Thread.sleep(1000);
		
		for(int i = 0; i < numberOfTry;i++) {
			driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
			Thread.sleep(1500);
			String Added = driver.switchTo().alert().getText();
			boolean checkAddedProduct_3 = Added.contains("added");
			softAssertProccess.assertEquals(checkAddedProduct_3, true, "Check add MacBook Pro : ");
			driver.switchTo().alert().accept();
			}
		
		softAssertProccess.assertAll();	
		Thread.sleep(2000);
	}

//********************************************** Check Total Price ***********************************************
	@Test(priority = 6, enabled = true)
	public void Check_Cart() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("cartur")).click();
		Thread.sleep(2000);

		String TotalPrice = driver.findElement(By.xpath("//*[@id=\"totalp\"]")).getText();
		String TotalPriceUpdated = TotalPrice.trim();
		int Actual_Total_Price = Integer.parseInt(TotalPriceUpdated);
		
		List<WebElement> price = driver.findElements(By.xpath("//tbody/tr/td[3]"));
		List<Integer> newListPrice = new ArrayList<>();
		
		int counts = 0;
		for(int i=0; i < price.size(); i++) {
			String priceUpdate = price.get(i).getText().trim();
			int priceInt = Integer.parseInt(priceUpdate);

			newListPrice.add(priceInt);
			counts += priceInt;
		}
		int Expected_Total_Praice = counts;
		System.out.println("Actual Total Price : "+Actual_Total_Price);
		System.out.println("Expected Totlal Price : " + Expected_Total_Praice);
		
		softAssertProccess.assertEquals(Actual_Total_Price, Expected_Total_Praice, "Check Actual Total Price : ");
		softAssertProccess.assertAll();
	}
	
//********************************************* Purchase ********************************************
	@Test(priority = 7, enabled = true)
	public void Checkout() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(2000);

		int userID = (int) (Math.random() * 100);
		StringBuilder userName = new StringBuilder();
		userName.append(userID);
		String userIDAssString = userName.toString();
		
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
		
		driver.findElement(By.id("name")).sendKeys(Buyer_name+userIDAssString);
		driver.findElement(By.id("country")).sendKeys(Country+userIDAssString);
		driver.findElement(By.id("city")).sendKeys(City+userIDAssString);
		driver.findElement(By.id("card")).sendKeys(Credit_card+userIDAssString);
		driver.findElement(By.id("month")).sendKeys(Month+userIDAssString);
		driver.findElement(By.id("year")).sendKeys(Year+userIDAssString);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
		String successfull_Purchase = driver.findElement(By.xpath("/html/body/div[10]/h2")).getText();
		boolean purchase = successfull_Purchase.contains("purchase");
		
		softAssertProccess.assertEquals(purchase, true, "Check purchase successfully : ");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click();
		softAssertProccess.assertAll();
	}
}
