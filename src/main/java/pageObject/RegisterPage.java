package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//input[@id='input-firstname'])[1]")
    WebElement txtFirstName;
    @FindBy(xpath = "(//input[@id='input-lastname'])[1]")
    WebElement txtLastName;
    @FindBy(xpath = "(//input[@id='input-email'])[1]")
    WebElement txtEmail;
    @FindBy(xpath = "(//button[normalize-space()='Continue'])[1]")
    WebElement btnContinue;
    @FindBy(xpath = "(//input[@id='input-password'])[1]")
    WebElement txtPassword;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement togglePrivacy;


    public void setTxtFirstName(String firstName){
        txtFirstName.sendKeys(firstName);
    }

    public void setTxtLastName(String lastName){
        txtLastName.sendKeys(lastName);
    }

    public void setTxtEmail(String mail){
        txtEmail.sendKeys(mail);
    }

    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }

    public void clickContinue(){
        btnContinue.click();
    }

    public void clickToggle(){
        togglePrivacy.click();
    }

    public void scrollDown() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000);");
        Thread.sleep(2000);
    }


}
