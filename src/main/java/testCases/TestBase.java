package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBase {

    public static WebDriver driver = null;
    public Logger logger;
    public Properties p;

    @BeforeMethod(groups = {"sanity","Regression","Master"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String browser) throws IOException {

        FileReader fileReader = new FileReader("src//resources//config.properties");
        p = new Properties();
        p.load(fileReader);

        logger = LogManager.getLogger(this.getClass());

        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            if (os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN11);
            }
            else if(os.equalsIgnoreCase("mac")){
                capabilities.setPlatform(Platform.MAC);
            }
            else
            {
                System.out.println("No matching OS");
            }

            switch (browser.toLowerCase()){
                case "chrome" : capabilities.setBrowserName("chrome");break;
                case "edge" :  capabilities.setBrowserName("edge");break;
                case "firefox" :  capabilities.setBrowserName("firefox");break;
                default:
                    System.out.println("No matching browser");
                    return;
            }

            driver = new RemoteWebDriver(new URL("http://192.168.3.100:4444/wd/hub"), capabilities);


        }

        if (p.getProperty("execution_env").equalsIgnoreCase("local")){
            switch (browser.toLowerCase()){
                case "chrome" : driver = new ChromeDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                case "firefox": driver = new FirefoxDriver(); break;
                default:
                    System.out.println("Invalid driver"); return;
            }
        }



        //ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new"); // Uncomment if headless needed
        //options.addArguments("--window-size=1920,1080");
        //driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("applicationURL"));
        driver.manage().window().maximize();
    }

    public String randomeString(){
        String generateString = RandomStringUtils.randomAlphabetic(5);
        return generateString;
    }

    public String randomeNumber(){
        String generateNumber = RandomStringUtils.randomAlphabetic(10);
        return generateNumber;
    }

    public String randomeAlphaNumeric(){
        String generateString = RandomStringUtils.randomAlphabetic(3);
        String generateNumber = RandomStringUtils.randomAlphabetic(3);
        return (generateString + "@" + generateNumber);

    }

    public String captureScreen(String tname) {

        String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshot\\" + tname + "_" + timeStamp + ".png";
        File file = new File(targetFilePath);

        sourceFile.toString();

        return targetFilePath;
    }

   /* @After(groups = {"sanity","Regression","Master"})
    public void tearDown(){
        driver.quit();
    }

    public void goBack(){
        driver.navigate().back();
    }*/
}
