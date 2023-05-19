package com.solvd.propertiesReader;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigReader {

    private static String getProperty(String key){
        return ResourceBundle.getBundle("config").getString(key);
    }

    public static String getBaseUrl(){
        return getProperty("baseUrl");
    }

    public static String getHubUrl(){
        return getProperty("hubUrl");
    }
}
