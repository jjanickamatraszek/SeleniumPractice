package propertiesReader;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataReader {

    private static String getProperty(String key){
        return ResourceBundle.getBundle("testData").getString(key);
    }

    public static String[] getNewInCategories(){
        return getProperty("newIn.categories").split(",");
    }

    public static String getNewInProductTitle(){
        return getProperty("newIn.productTitle");
    }
}
