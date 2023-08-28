import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.hc.core5.reactor.Command;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utilities.Driver;

import java.time.Duration;

public class Task {


    private String password = "thispasswillfailforsure";
    String username = "thisuserwillnotwork";
    private String homePage = "https://www.wsecu.org";
    String expectedLoginPage = "https://digital.wsecu.org/banking/signin";
    String expectedErrorMessage = "Sorry, incorrect username.";


    @Test(priority = 1)
    public void websiteTest() {

        Driver.getDriver().navigate().to(homePage);
        WebElement inputUsername = Driver.getDriver().findElement(By.xpath("//input[@id=\"digital-banking-username\"]"));
        inputUsername.sendKeys(username + Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        WebElement onlineBanking = Driver.getDriver().findElement(By.xpath("//h1[.='Online Banking']"));
        wait.until(ExpectedConditions.visibilityOf(onlineBanking));
        //Assert.assertEquals(Driver.getDriver().getCurrentUrl(), expectedLoginPage);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedLoginPage));
    }

    @Test(priority = 2)
    public void errorMessageTest() {

        Driver.getDriver().findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(password + Keys.ENTER);
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String actualErrorMessage = Driver.getDriver().findElement(By.xpath("//div[@class='ng-binding ng-scope']")).getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);


    }

    @AfterClass
    public void tearDown() {
        Driver.closeBrowser();
    }
}
