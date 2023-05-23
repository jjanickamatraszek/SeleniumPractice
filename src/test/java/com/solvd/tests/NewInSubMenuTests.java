package com.solvd.tests;

import com.solvd.components.MainMenu;
import com.solvd.components.NewInSubMenu;
import com.solvd.pages.HomePage;
import com.solvd.pages.NewInSubCatPage;
import com.solvd.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.util.List;

public class NewInSubMenuTests extends BaseTest {
    private HomePage homePage;

    @BeforeMethod
    @Parameters("browserType")
    public void driverSetup(String browserType) throws MalformedURLException {
        super.driverSetup(browserType);
        homePage = new HomePage(driver);
        homePage.goToPage().getCookieDialog().acceptCookies();
    }

    @Test(description = "Display submenu of 'New In' menu category")
    public void displaySubMenuTest() {
        int expectedNumberOfSubcategories = 4;

        NewInSubMenu newInSubMenu = homePage.getMainMenu().hoverOverNewInCategory();

        Assert.assertTrue(newInSubMenu.isSubMenuDisplayed(), "'New In' submenu is not visible after hover on menu category");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(newInSubMenu.getNumberOfSubcategories(), expectedNumberOfSubcategories, "Number of subcategories" +
                "is different than expected ");
        soft.assertEquals(newInSubMenu.getNumberOfSubcategoriesWithoutImg(), 0, "Some subcategories don't have an image");
        soft.assertEquals(newInSubMenu.getNumberOfSubcategoriesWithoutTitle(), 0, "Some subcategories don't have a title");
        soft.assertAll();
    }

    @Test(description = "Go to subcategory page", dependsOnMethods = "displaySubMenuTest")
    public void goToSubcategoryPageTest() {
        MainMenu mainMenu = homePage.getMainMenu();
        List<String> subCategoriesTitles = mainMenu.hoverOverNewInCategory().getSubCategoriesTitles();

        SoftAssert soft = new SoftAssert();
        for (String subCategoryTitle : subCategoriesTitles) {
            NewInSubCatPage newInSubCatPage = mainMenu.hoverOverNewInCategory().clickOnSubcategory(subCategoryTitle);
            soft.assertEquals(newInSubCatPage.getTitle(), subCategoryTitle, "Title on 'New In' subcategory page is different " +
                    "than subcategory chosen from menu");
            soft.assertEquals(newInSubCatPage.getSideBar().getActiveCategoryTitle(), subCategoryTitle, "Active category in " +
                    "sidebar is different than subcategory chosen from menu");
        }
        soft.assertAll();
    }
}
