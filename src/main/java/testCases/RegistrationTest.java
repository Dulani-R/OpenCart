package testCases;

import pageObject.HomePage;
import pageObject.RegisterPage;
import pageObject.SuccessRegistration;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RegistrationTest extends TestBase{

    @Test(groups = {"Regression", "Master"})
    public void verifyRegistration() throws InterruptedException {

        logger.info("***************Registration Test Logs************");


        try {
            HomePage hp = new HomePage(driver);
            logger.info("Account button click");
            hp.btnAccountClick();
            logger.info("Register button click");
            hp.btnRegisterClick();

            RegisterPage rp = new RegisterPage(driver);
            logger.info("Fill first name");
            rp.setTxtFirstName(randomeString().toUpperCase());
            logger.info("Fill second name");
            rp.setTxtLastName(randomeString().toUpperCase());
            logger.info("Fill mail");
            rp.setTxtEmail(randomeString() + "@al.com");
            logger.info("Fill password");
            rp.setPassword(randomeAlphaNumeric());
            rp.scrollDown();
            logger.info("Drag toggle");
            rp.clickToggle();
            logger.info("Click button");
            rp.clickContinue();

            SuccessRegistration successRP = new SuccessRegistration(driver);
            logger.info("Validate success message");
            String msg = successRP.getSuccessMSG();
            Assert.assertEquals("Your Account Has Been Created!", msg);

             }
        catch(Exception e){
            logger.error("Test failed....");
            logger.debug("Debug info ...");
            Assert.fail();
        }
    }




}
