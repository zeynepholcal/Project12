
import Utilities.BaseClass;
import net.bytebuddy.jar.asm.Handle;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Project extends BaseClass {
    public static void main(String[] args) {
        WebDriverWait wait = new WebDriverWait(driver,5);
        driver.get("https://app.hubspot.com/login");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

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
            if(!windowHandle.equals(main)){

                driver.switchTo().window(windowHandle);
            }
        }
String url = driver.getCurrentUrl();
        Assert.assertEquals("https://app.hubspot.com/pricing/8080093/sales?upgradeSource=deals-" +
                "create-deal-general-create-deal-multiple-pipelines-pql-feature-lock&term=annual&edition=starter",url);








        // Click on Deals


    }
}
