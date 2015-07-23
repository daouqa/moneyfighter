package com.example.tests;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;




public class listTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		    driver = new FirefoxDriver();
		    baseUrl = "http://52.69.190.23:8080/";
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  }

		  @Test
		  public void testListtestall() throws Exception {
		    driver.get(baseUrl + "/moneyfighter/write.jsp");
		    driver.findElement(By.linkText("입출금 내역")).click();
		    driver.findElement(By.linkText("내역 입력")).click();
		    new Select(driver.findElement(By.id("type"))).selectByVisibleText("수입");
		    new Select(driver.findElement(By.id("category"))).selectByVisibleText("월급");
		    driver.findElement(By.name("price")).clear();
		    driver.findElement(By.name("price")).sendKeys("100000");
		    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		    assertEquals("입력되었습니다~~~~~~~~~~~~", closeAlertAndGetItsText());
		    driver.findElement(By.linkText("내역 입력")).click();
		    new Select(driver.findElement(By.id("type"))).selectByVisibleText("지출");
		    new Select(driver.findElement(By.id("category"))).selectByVisibleText("보험비");
		    driver.findElement(By.name("price")).clear();
		    driver.findElement(By.name("price")).sendKeys("5000");
		    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		    assertEquals("입력되었습니다~~~~~~~~~~~~", closeAlertAndGetItsText());
		    driver.findElement(By.linkText("내역 입력")).click();
		    new Select(driver.findElement(By.id("type"))).selectByVisibleText("지출");
		    new Select(driver.findElement(By.id("category"))).selectByVisibleText("커피");
		    driver.findElement(By.name("price")).clear();
		    driver.findElement(By.name("price")).sendKeys("1000");
		    driver.findElement(By.name("note")).clear();
		    driver.findElement(By.name("note")).sendKeys("사다리타기");
		    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		    assertEquals("입력되었습니다~~~~~~~~~~~~", closeAlertAndGetItsText());
		  }
	 
		  @After
		  public void tearDown() throws Exception {
		    driver.quit();
		    String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      fail(verificationErrorString);
		    }
		  }

		  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

		  private boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }

		  private String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }
	 }
	 



