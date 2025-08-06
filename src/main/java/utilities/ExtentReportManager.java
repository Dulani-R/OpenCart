package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.BaseClassFinder;
import testCases.TestBase;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkerReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    String reportName;

    public void onStart(ITestContext tsetContext) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss");
//        Date date = new Date();
//        String currentDateTimeStamp = dateFormat.format(date);

        String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
        reportName = "TestReport - " + timeStamp + ".html";
        sparkerReporter = new ExtentSparkReporter(".\\reports\\" + reportName);

        sparkerReporter.config().setDocumentTitle("Open Cart Test Automation Report");
        sparkerReporter.config().setReportName("Open Cart Functional Testing");
        sparkerReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkerReporter);
        extentReports.setSystemInfo("Application", "opencart");
        extentReports.setSystemInfo("Module", "Admin");
        extentReports.setSystemInfo("Sub Module", "Customers");
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");

        String operatingSystem = tsetContext.getCurrentXmlTest().getParameter("os");
        extentReports.setSystemInfo("Operating System", operatingSystem);

        String browser = tsetContext.getCurrentXmlTest().getParameter("browser");
        extentReports.setSystemInfo("Browser", browser);

        List<String> includedGroups = tsetContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()){
            extentReports.setSystemInfo("Groups", includedGroups.toString());
        }

    }

    public void onTestSuccess(ITestResult result){
        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.PASS, result.getName() + "got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());


        extentTest.log(Status.FAIL,result.getName() + "is failed");
        extentTest.log(Status.INFO, result.getThrowable().getMessage());

        String imgPath = new TestBase().captureScreen(result.getName());
        extentTest.addScreenCaptureFromPath(imgPath);

        imgPath = new TestBase().captureScreen(result.getName());
        extentTest.addScreenCaptureFromPath(imgPath);
    }

    public void onTestSkipped(ITestResult result){
        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());


        extentTest.log(Status.FAIL,result.getName() + "skipped");
        extentTest.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext){

        extentReports.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + reportName;
        File extentReport = new File(pathOfExtentReport);

        try{
            Desktop.getDesktop().browse(extentReport.toURI());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    }


