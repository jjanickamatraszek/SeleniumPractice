package com.solvd.tests;

import com.solvd.pages.HomePage;
import com.solvd.pages.ProductPage;
import com.solvd.tests.base.BaseTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import propertiesReader.TestDataReader;

import java.net.MalformedURLException;

public class DisplayProductInfoTests extends BaseTest {
    private HomePage homePage;
    private ProductPage productPage;
    private String newInCategory;
    private String productTitle;

    @BeforeClass
    public void loadTestData(){
        newInCategory = TestDataReader.getNewInCategory();
        productTitle = TestDataReader.getNewInProductTitle();
    }

    @BeforeMethod
    @Parameters("browserType")
    public void driverSetup (String browserType) throws MalformedURLException {
        super.driverSetup(browserType);
        homePage = new HomePage(driver);
        homePage.goToPage().getCookieDialog().acceptCookies();
        productPage = homePage.getMainMenu()
                .hoverOverNewInCategory()
                .clickOnSubcategory(newInCategory)
                .clickOnProductByTitle(productTitle);
    }

    @Test(description = "Display product page")
    @MethodOwner(owner = "jjanickamatraszek")
    public void displayProductInfoTest() {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(productPage.getTitle(), productTitle, "Product title is different than expected");
        soft.assertFalse(productPage.getPrice().isBlank(), "Product price is blank");
        soft.assertTrue(productPage.mainImageIsDisplayed(), "Product main image is not displayed");
        soft.assertTrue(productPage.isDescriptionExpanded(), "Product description section is not expanded after page launch");
        soft.assertFalse(productPage.getDescriptionText().isBlank(), "Product description text is blank");
        soft.assertFalse(productPage.isMaterialAndCareExpanded(), "Product material and care section is expanded after page launch");

        productPage.expandCollapseMaterialAndCareSection();

        soft.assertFalse(productPage.getMaterialAndCareText().isBlank(), "Product material and care section is blank when expanded");
        soft.assertAll();
    }

    @Test(description = "Expand and collapse description section")
    @MethodOwner(owner = "jjanickamatraszek")
    public void expandAndCollapseDescTest() {
        ProductPage productPageWithCollapsedAllSections = productPage
                .expandCollapseDescriptionSection();

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(productPageWithCollapsedAllSections.isDescriptionExpanded(), "Desc section is expanded " +
                "when after click on desc label should have collapsed");
        soft.assertFalse(productPageWithCollapsedAllSections.isMaterialAndCareExpanded(), "Material section is " +
                "expanded when originally should be collapsed");

        ProductPage productPageWithExpandedMaterialAndCare = productPageWithCollapsedAllSections
                .expandCollapseMaterialAndCareSection();

        soft.assertFalse(productPageWithExpandedMaterialAndCare.isDescriptionExpanded(), "Desc section is expanded" +
                " when after click on material label should have collapsed");
        soft.assertTrue(productPageWithExpandedMaterialAndCare.isMaterialAndCareExpanded(), "Material section is " +
                "collapsed when after click on material label should have been expanded");

        ProductPage productPageWithExpandedDesc = productPageWithExpandedMaterialAndCare
                .expandCollapseDescriptionSection();

        soft.assertFalse(productPageWithExpandedDesc.isMaterialAndCareExpanded(), "Material section stayed expanded" +
                " when after click on desc label should have collapsed");
        soft.assertTrue(productPageWithExpandedDesc.isDescriptionExpanded(), "Desc section is collapsed when after" +
                " click on desc label should have become expanded");
        soft.assertAll();
    }
}
