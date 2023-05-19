package com.solvd.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static String extractSelector(WebElement element){
        String name = element.toString();
        return name.substring(name.indexOf("->")).replace("]","");
    }
}
