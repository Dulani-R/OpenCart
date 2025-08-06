package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//a[normalize-space()='Register'])[1]")
    WebElement btnRegister;
    @FindBy(xpath = "(//a[normalize-space()='Login'])[1]")
    WebElement btnLogin;
    @FindBy(xpath = "(//span[normalize-space()='My Account'])[1]")
    WebElement btnMyAccount;

    public void btnAccountClick(){
        btnMyAccount.click();
    }

    public void btnRegisterClick(){
        btnRegister.click();
    }

    public void btnLoginClick(){
        btnLogin.click();
    }


}
