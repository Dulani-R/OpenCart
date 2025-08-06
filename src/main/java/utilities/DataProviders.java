package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider(name = "Login Test")
    public String [][] getData() throws IOException {
        String path = ".\\testData\\loginTestData.xlsx";

        ExcelUtility excelUtility = new ExcelUtility(path);

        int totalRows = excelUtility.getRowCount("sheet1");
        int totalColumns = excelUtility.getCellCount("sheet1",1);

        String loginData[][] = new String[totalRows][totalColumns];

        for(int i=1; i< totalRows; i++)
        {
            for(int j=0; j<totalColumns; j++)
            {
                loginData[i-1][j] = excelUtility.getCellData("sheet1", i, j);
            }
        }

    return loginData;
    }

}
