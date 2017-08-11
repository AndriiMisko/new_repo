
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest
{
    private static WebDriver driver;
    private static String myVar;
    private static String testurl1 = System.getProperty("MY_VARIABLE");

    @BeforeClass // Runs this method before the first test method in the current class is invoked
    public static void setUp() throws IOException {
        // Create a new instance of the Firefox driver
        System.setProperty("webdriver.gecko.driver","D:/Automation/geckodriver-v0.18.0-win64/geckodriver.exe");
        driver = new FirefoxDriver();

        Properties mavenProps = new Properties();
        InputStream input = MainTest.class.getResourceAsStream("/maven.properties");
        mavenProps.load(input);

        String buildDir = mavenProps.getProperty("project.build.directory");
        myVar = mavenProps.getProperty("MY_VARIABLE");
    }

    @Test
    public void findBVapi()
    {
        driver.navigate().to("http://www.leifheit.de/details-test/details/wischtuchpresse-profi-compact.html");
        String source = driver.getPageSource();
        Pattern pattern = Pattern.compile("bvapi.js");
        Matcher matcher = pattern.matcher(source);
        int count = 0;
        while (matcher.find())
        {
            count++;
        }
        if (count != 0)
        {
            if (count != 1)
            {
                System.out.println("There are " + count + "bvapis found in source. Please take alook");
            }
        }
        else
        {
            Assert.fail("There is no bvapi.js on site");
        }
        //Assert.assertEquals(source.contains("bvapi.js"), true);
    }

    @Test
    public void checkContainerPage() throws IOException
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        String clientName = "ecarpiste-fr";
        driver.navigate().to("https://workbench.bazaarvoice.com/portal/app?service=page/Login");
        driver.findElement(By.id("j_clientname")).sendKeys(clientName);
        driver.findElement(By.id("j_loginname")).sendKeys("amisko");
        driver.findElement(By.id("j_password")).sendKeys("qN4uV0tV2");

        driver.findElement(By.id("bvLoginFormSubmit")).click();

        System.out.println(testurl1 + "123    12345667    123");
        try { Thread.sleep(4000); } catch (InterruptedException e) { e.printStackTrace(); }
    }


    @Test // Marking this method as part of the test
    public void gotoSeleniumWikiPage() {
        // Go to the Wikipedia home page
        driver.get("https://en.wikipedia.org/");
        // Find the text input element by its id and type "Selenium"
        driver.findElement(By.id("searchInput")).sendKeys("Selenium");
        // Click search button
        driver.findElement(By.id("searchButton")).click();
        // Get text from header of the Selenium page
        String header = driver.findElement(By.id("firstHeading")).getText();
        // Verify that header equals "Selenium"
        Assert.assertEquals(header, "Selenium");
    }

    @Test // Marking this method as part of the test
    public void gotoSeleniumWikiPageFailure() {
        // Go to the Wikipedia home page
        driver.get("https://en.wikipedia.org/");
        // Find the text input element by its id and type "Selenium"
        driver.findElement(By.id("searchInput")).sendKeys("Selenium");
        // Click search button
        driver.findElement(By.id("searchButton")).click();
        // Get text from header of the Selenium page
        String header = driver.findElement(By.id("firstHeading")).getText();
        // Verify that header equals "Selenium WebDriver"
        Assert.assertEquals(header, "Selenium WebDriver");
    }

    @AfterClass // Runs this method after all the test methods in the current class have been run
    public static void tearDown() {
        // Close all browser windows and safely end the session
        driver.quit();
    }

    void testFuuncMain()
    {
        System.out.println("MainTest printed");
    }
    void testFuuncMain2()
    {
        System.out.println("MainTest printed 2");
    }
    void testFuuncMain3()
    {
        System.out.println("MainTest printed 3");
    }
}
