import Utility.BaseClass;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Project extends BaseClass {
    public static void main(String[] args) {
        driver.get("https://app.hubspot.com/login");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("username")).sendKeys("olcalzeynephale@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Istanbul1234");
    }
}
