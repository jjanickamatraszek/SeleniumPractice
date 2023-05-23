package com.solvd.tests.dataProviders;

import org.testng.annotations.DataProvider;
import propertiesReader.TestDataReader;

public class DataProviders {

    @DataProvider(name = "New In submenu categories")
    public Object[][] getNewInCategories() {
        int length = TestDataReader.getNewInCategories().length;
        Object[][] categories = new Object[length][1];
        for (int i = 0; i < length; i++) {
            categories[i][0] = TestDataReader.getNewInCategories()[i];
        }
        return categories;
    }
}
