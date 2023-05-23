package com.solvd.tests;

import com.solvd.components.NewInSubMenu;
import com.solvd.pages.HomePage;
import com.solvd.pages.NewInSubCatPage;
import com.solvd.tests.base.BaseTest;
import com.solvd.tests.dataProviders.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewInSubMenuTests extends BaseTest {

    @Test(description = "Display submenu of 'New In' menu category")
    public void displaySubMenuTest() {
        int expectedNumberOfSubcategories = 4;

        HomePage homePage = new HomePage(driver);
        homePage.goToPage().getCookieDialog().acceptCookies();
        NewInSubMenu newInSubMenu = homePage.getMainMenu().hoverOverNewInCategory();

        Assert.assertTrue(newInSubMenu.isSubMenuDisplayed(), "'New In' submenu is not visible after hover on menu category");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(newInSubMenu.getNumberOfSubcategories(), expectedNumberOfSubcategories, "Number of subcategories" +
                "is different than expected ");
        soft.assertEquals(newInSubMenu.getNumberOfSubcategoriesWithoutImg(), 0, "Some subcategories don't have an image");
        soft.assertEquals(newInSubMenu.getNumberOfSubcategoriesWithoutTitle(), 0, "Some subcategories don't have a title");
        soft.assertAll();
    }

    @Test(description = "Go to subcategory page",
            dataProvider = "New In submenu categories", dataProviderClass = DataProviders.class)
    public void goToSubcategoryPageTest(String subcategory) {
        HomePage homePage = new HomePage(driver);
        homePage.goToPage().getCookieDialog().acceptCookies();
        NewInSubCatPage newInSubCatPage = homePage.getMainMenu().hoverOverNewInCategory().clickOnSubcategory(subcategory);

        Assert.assertTrue(newInSubCatPage.areProductsLoaded(), "Products didn't load");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(newInSubCatPage.getTitle(), subcategory, "Title on 'New In' subcategory page is different " +
                "than subcategory chosen from menu");
        soft.assertEquals(newInSubCatPage.getSideBar().getActiveCategoryTitle(), subcategory, "Active category in " +
                "sidebar is different than subcategory chosen from menu");
        soft.assertAll();
    }
}
