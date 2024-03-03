import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class NegativeTest {
    WebDriver driver;

    @BeforeMethod
    public void setupDriver() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLoginEmptyUsername() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("fa-sign-in")).click();

        String successMessage = driver.findElement(By.id("flash")).getText();
        assert successMessage.contains("Your username is invalid!");

        boolean hasErrorClass = driver.findElement(By.id("flash")).getAttribute("class").contains("error");
        Assert.assertTrue(hasErrorClass);
    }

    @Test
    public void testLoginEmptyPassword() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.className("fa-sign-in")).click();

        String successMessage = driver.findElement(By.id("flash")).getText();
        assert successMessage.contains("Your password is invalid!");

        boolean hasErrorClass = driver.findElement(By.id("flash")).getAttribute("class").contains("error");
        Assert.assertTrue(hasErrorClass);
    }
}
