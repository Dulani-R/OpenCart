package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessRegistration extends BasePage {

    public SuccessRegistration(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    public WebElement successMsg;
    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement btnSuccessContinue;

    public String getSuccessMSG(){
        try{
            return (successMsg.getText());
        }
        catch(Exception e)
        {
            return (e.getMessage());
        }
    }

}
