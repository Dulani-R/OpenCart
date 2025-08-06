package testCases;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utilities.DataProviders;


public class LoginDataDrivenTest extends TestBase implements ITest {

    private String testName = "";

    @Test(dataProvider = "Login Test", dataProviderClass = DataProviders.class, groups = "DataDriven")
    public void loginDataDriven(String password, String email, String expectedResult) {

        testName = "loginDataDriven(email=" + email + ", password=" + password + ", expectedResult=" + expectedResult + ")";

        logger.info("*********start DDTLogin****************");

        try {
            HomePage homePage = new HomePage(driver);
            homePage.btnAccountClick();
            homePage.btnLoginClick();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setTxtLoginMail(email);
            loginPage.setTxtLoginPassword(password);
            loginPage.btnLoginClick();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean visiblePage = myAccountPage.isHeadingDisplay();

            if (expectedResult.equalsIgnoreCase("Valid")) {

                if (visiblePage == true) {
                    myAccountPage.clickLogOut();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }

            }

            if (expectedResult.equalsIgnoreCase("Invalid")) {
                if (visiblePage == true) {
                    myAccountPage.clickLogOut();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch (Exception e){
            Assert.fail();
        }

        logger.info("*********End DDTLogin****************");
    }


    @Override
    public String getTestName() {
        return "";
    }
}
