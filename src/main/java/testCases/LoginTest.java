package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;


public class LoginTest extends TestBase{

    @Test(groups = {"sanity", "Master"})
    public void verifyLogin(){

        logger.info("**************Login  test execution ****************");

        try {
            HomePage homePage = new HomePage(driver);
            homePage.btnAccountClick();
            homePage.btnLoginClick();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setTxtLoginMail(p.getProperty("email"));
            loginPage.setTxtLoginPassword(p.getProperty("password"));
            loginPage.btnLoginClick();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean visiblePage = myAccountPage.isHeadingDisplay();
            Assert.assertTrue(visiblePage);
        }
        catch (Exception e){
            Assert.fail();
        }

        logger.info("**************Login  test execution is completed ****************");

    }
}
