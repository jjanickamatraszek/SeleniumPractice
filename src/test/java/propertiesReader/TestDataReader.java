package propertiesReader;

import com.solvd.model.Product;
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

    public static Product getNewInProduct(){
        String title = getProperty("newIn.product.title");
        String dataId = getProperty("newIn.product.data-id");
        return new Product(title, dataId);
    }
}
