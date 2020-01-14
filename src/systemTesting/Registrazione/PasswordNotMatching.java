package systemTesting.Registrazione;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PasswordNotMatching {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  /**
   * Before.
   */

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPasswordNotMatching() throws Exception {
    driver.get("http://localhost:8000/EnglishValidation/");
    driver.findElement(By.linkText("Registrati")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("an");
    driver.findElement(By.id("name")).sendKeys(Keys.DOWN);
    driver.findElement(By.id("name")).sendKeys(Keys.DOWN);
    driver.findElement(By.id("name")).sendKeys(Keys.TAB);
    driver.findElement(By.id("surname")).clear();
    driver.findElement(By.id("surname")).sendKeys("I");
    driver.findElement(By.id("surname")).sendKeys(Keys.DOWN);
    driver.findElement(By.id("surname")).sendKeys(Keys.TAB);
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("z.prov@studenti.unisa.it");
    driver.findElement(By.xpath(
        "(.//*[normalize-space(text()) and "
        + "normalize-space(.)='Registrazione'])[1]/following::label[1]"))
        .click();
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("verifyPassword")).clear();
    driver.findElement(By.id("verifyPassword")).sendKeys("passworf");
    driver
        .findElement(By.xpath(
            "(.//*[normalize-space(text()) and normalize-space(.)='F'])[1]/following::button[1]"))
        .click();
  }

  /**
   * After.
   */

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

