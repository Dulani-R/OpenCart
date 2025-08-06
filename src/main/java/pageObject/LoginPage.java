package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//input[@id='input-email'])[1]")
    WebElement txtLoginMail;
    @FindBy(xpath = "(//input[@id='input-password'])[1]")
    WebElement txtLoginPassword;
    @FindBy(xpath = "(//button[normalize-space()='Login'])[1]")
    WebElement btnLogin;

    public void setTxtLoginMail(String loginMail){
        txtLoginMail.sendKeys(loginMail);
    }

    public void setTxtLoginPassword(String loginPassword){
        txtLoginPassword.sendKeys(loginPassword);
    }

    public void btnLoginClick(){
        btnLogin.click();
    }


}
