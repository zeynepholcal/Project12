
import Utilities.BaseClass;
import net.bytebuddy.jar.asm.Handle;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Project extends BaseClass {
    public static void main(String[] args) throws InterruptedException {


        driver.get("https://app.hubspot.com/login");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.findElement(By.id("username")).sendKeys("olcalzeynephale@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Istanbul1234.");
        driver.findElement(By.id("loginBtn")).click();


        // Click on Sales
        driver.findElement(By.xpath("(//a[@id='nav-primary-sales-branch'])[1]")).click();
        // Click on Deals
        driver.findElement(By.xpath("(//a[@id='nav-secondary-deals'])[1]")).click();
        driver.navigate().refresh();
        driver.findElement(By.cssSelector("span[data-key='contentToolbar.addDealButton']")).click();
        //Enter the Deal Name
        driver.findElement(By.cssSelector(" input[id=UIFormControl-19]")).sendKeys("Best Product");
        //Click Pipe Line
        driver.findElement(By.id("uiabstractdropdown-button-49")).click();

        String main = driver.getWindowHandle();
        //Switch to new window
        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(main)) {

                driver.switchTo().window(windowHandle);
            }
        }
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://app.hubspot.com/pricing/8080093/sales?upgradeSource=deals-" +
                "create-deal-general-create-deal-multiple-pipelines-pql-feature-lock&term=annual&edition=starter", url);

        driver.close();
        driver.switchTo().window(main);
        driver.findElement(By.cssSelector("button[data-selenium-test='property-input-dealstage']")).click();
        //  driver.findElement(By.xpath("//span[text()='Appointment scheduled']")).click();
        List<WebElement> list1 = driver.findElements(By.cssSelector("li[id*='typeahead']"));
        Random rand = new Random();
        int a = rand.nextInt(list1.size());
        list1.get(a).click();
        // Enter the amount
        driver.findElement(By.cssSelector("input[data-field='amount']")).sendKeys("500");

        // select deal type randomly
        // driver.findElement(By.cssSelector("div[id='uiabstractdropdown-button-37']")).click();

        Actions action = new Actions(driver);
        //    driver.switchTo().frame(0);
        action.moveToElement(driver.findElement(By.cssSelector("div[data-selenium-test='property-input-dealtype']"))).click().perform();

        int b = rand.nextInt(3);
        for (int i = 0; i < b; i++)
            action.sendKeys(Keys.ARROW_DOWN).perform();

        action.sendKeys(Keys.ENTER).perform();

        driver.findElement(By.xpath("//span[text()='Create']")).click();
// click on edit button
        driver.findElement(By.xpath("//span[@data-selenium-test='highlight-editor-icon']")).click();
        // clear it
        driver.findElement(By.xpath("//div[@class='private-form__input-wrapper'] //input[@type='text'][1]")).clear();
        // enter new name
        String key2 = "Perfect Product";
        driver.findElement(By.xpath("//div[@class='private-form__input-wrapper'] //input[@type='text'][1]")).sendKeys(key2);
        // click on save
        driver.findElement(By.cssSelector("button[data-button-use='tertiary']")).click();

        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//span[@data-selenium-test='highlightTitle']")), key2));
        String after = driver.findElement(By.xpath("//span[@data-selenium-test='highlightTitle']")).getText();
        after = after.replaceAll("[0-9$]", "");
        after = after.trim();
        Assert.assertEquals(key2, after);

        //Click on actions button
        driver.findElement(By.cssSelector("button[data-selenium-test='profile-settings-actions-btn']")).click();

        //        Click on Delete
        driver.findElement(By.cssSelector("button[data-selenium-test='profile-settings-profileSettings.delete']")).click();

        //        Click on Delete deal
        driver.findElement(By.cssSelector("button[data-selenium-test='delete-dialog-confirm-button']")).click();

    }
}
