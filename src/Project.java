
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
        WebDriverWait wait = new WebDriverWait(driver, 7);

        driver.findElement(By.id("username")).sendKeys("fatihgul@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Asdf4321-");
//        driver.findElement(By.id("username")).sendKeys("olcalzeynephale@gmail.com");
//        driver.findElement(By.id("password")).sendKeys("Istanbul1234.");
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
//        Assert.assertEquals("https://app.hubspot.com/pricing/8080093/sales?upgradeSource=deals-" +
//                "create-deal-general-create-deal-multiple-pipelines-pql-feature-lock&term=annual&edition=starter", url);
        Assert.assertEquals("https://app.hubspot.com/pricing/8079994/sales?upgradeSource=deals-" +
                "create-deal-general-create-deal-multiple-pipelines-pql-feature-lock&term=annual&edition=starter", url);

        driver.close();
        driver.switchTo().window(main);
        driver.findElement(By.cssSelector("button[data-selenium-test='property-input-dealstage']")).click();
        //  driver.findElement(By.xpath("//span[text()='Appointment scheduled']")).click();
        List<WebElement> list1 = driver.findElements(By.cssSelector("li[id*='typeahead']"));
        Random rand = new Random();
        int a = rand.nextInt(list1.size());
        // select deal type randomly
        list1.get(a).click();
        // Enter the amount
        driver.findElement(By.cssSelector("input[data-field='amount']")).sendKeys("500");

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector("div[data-selenium-test='property-input-dealtype']"))).click().perform();

        int b = rand.nextInt(3); //Generate a random number up to 3
        for (int i = 0; i < b; i++)
            action.sendKeys(Keys.ARROW_DOWN).perform(); //Randomly click Arrow Down to select on drop down menu

        action.sendKeys(Keys.ENTER).perform();

        driver.findElement(By.xpath("//span[text()='Create']")).click();
        // click on edit button
        driver.findElement(By.xpath("//span[@data-selenium-test='highlight-editor-icon']")).click();
        // clear it
        driver.findElement(By.xpath("//div[@class='private-form__input-wrapper'] //input[@type='text'][1]")).clear();
        // enter new name
        String key2 = "Good Product";
        driver.findElement(By.xpath("//div[@class='private-form__input-wrapper'] //input[@type='text'][1]")).sendKeys(key2);
        // click on save
        driver.findElement(By.cssSelector("button[data-button-use='tertiary']")).click();


        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//span[@data-selenium-test='highlightTitle']")), key2));
        //get the name of deal and assign it to "after"
        String after = driver.findElement(By.xpath("//span[@data-selenium-test='highlightTitle']")).getText();
        after = after.replaceAll("[0-9$]", ""); //delete all numeric and $ chars
        after = after.trim(); // clear the space char from the string
        Assert.assertEquals(key2, after);  //Checks if the name is edited

        driver.findElement(By.xpath("(//i18n-string[text()='Deals'])[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-selenium-test='deal-chicklet-title']")));
        List<WebElement> column = driver.findElements(By.cssSelector("a[data-selenium-test='deal-chicklet-title']"));
        //  List<WebElement> column = driver.findElements(By.cssSelector("div[data-selenium-test='preview-header']"));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-button-use='link']")));
        //  List<WebElement> column = driver.findElements(By.cssSelector("a[data-button-use='link']"));

        int count = 0; //counter of deleted elements
        int size = column.size();
        for (int i = 0; i < column.size(); ) {
            //   System.out.println(column.get(i).getText());
            action.moveToElement(column.get(i)).click().perform();
            //Click on actions button
            driver.findElement(By.cssSelector("button[data-selenium-test='profile-settings-actions-btn']")).click();
            //        Click on Delete
            driver.findElement(By.cssSelector("button[data-selenium-test='profile-settings-profileSettings.delete']")).click();
            //        Click on Delete deal
            driver.findElement(By.cssSelector("button[data-selenium-test='delete-dialog-confirm-button']")).click();
            count++; //counter of deleted elements
            //Refresh the list, other wise it gives Stale element fault
            column = driver.findElements(By.cssSelector("a[data-selenium-test='deal-chicklet-title']"));
        }

        //If the number of count and size of the list are equal, all the elements were deleted by successfully
        Assert.assertEquals(count, size);


    }
}
