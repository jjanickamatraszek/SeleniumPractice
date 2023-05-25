package com.solvd.tests;

import com.solvd.components.SortOption;
import com.solvd.pages.NewInSubCatPage;
import com.solvd.routes.Route;
import com.solvd.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryProductFiltersTests extends BaseTest {

    @Test(description = "Sort products by price asc")
    public void sortProductsByPriceAscTest() {
        NewInSubCatPage categoryPage = new NewInSubCatPage(driver);
        categoryPage.goToPage(Route.NEW_IN_WOMEN).getCookieDialog().acceptCookies();
        List<BigDecimal> expectedProductsPricesSorted = categoryPage
                .getProductsPricesAsNumbers()
                .stream()
                .sorted(BigDecimal::compareTo)
                .collect(Collectors.toList());

        NewInSubCatPage categoryPageSortedByPrice = categoryPage.getProductFilters()
                .sortBy(SortOption.PRICE_ASC);

        List<BigDecimal> actualProductsPricesSorted = categoryPageSortedByPrice
                .getProductsPricesAsNumbers();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProductsPricesSorted, expectedProductsPricesSorted,
                "Products aren't sorted by price asc");
        softAssert.assertEquals(categoryPageSortedByPrice.getProductFilters().getNumberOfActiveSortFilters(), 1,
                "Number of active sort filters is different than expected");
        softAssert.assertAll();
    }

    @Test(description = "Sort products by default order")
    public void sortProductsByDefaultOrderTest() {
        NewInSubCatPage categoryPage = new NewInSubCatPage(driver);
        categoryPage.goToPage(Route.NEW_IN_WOMEN).getCookieDialog().acceptCookies();
        List<String> expectedProductsTitles = categoryPage.getProductsTitles();

        NewInSubCatPage categoryPageSortedByPrice = categoryPage
                .getProductFilters()
                .sortBy(SortOption.PRICE_ASC);

        Assert.assertTrue(categoryPageSortedByPrice.areProductsLoaded(),
                "Products didn't load after sorting by price asc");

        NewInSubCatPage categoryPageSortedByDefaultOrder = categoryPageSortedByPrice
                .getProductFilters()
                .sortBy(SortOption.DEFAULT);

        Assert.assertTrue(categoryPageSortedByPrice.areProductsLoaded(),
                "Products didn't load after sorting by default order");

        List<String> actualProductsTitles = categoryPageSortedByDefaultOrder
                .getProductsTitles();
        int actualNumberOfActiveSortFilters = categoryPageSortedByDefaultOrder
                .getProductFilters()
                .getNumberOfActiveSortFilters();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProductsTitles, expectedProductsTitles,
                "Order of products is different than after page launch");
        softAssert.assertEquals(actualNumberOfActiveSortFilters, 0,
                "Number of active sort filters is displayed when should be removed");
        softAssert.assertAll();
    }

    @Test(description = "Filter products by price range")
    public void filterProductsByPriceRangeTest() {
        NewInSubCatPage categoryPage = new NewInSubCatPage(driver);
        categoryPage.goToPage(Route.NEW_IN_WOMEN).getCookieDialog().acceptCookies();
        List<BigDecimal> productPricesSortedAsc = categoryPage
                .getProductsPricesAsNumbers()
                .stream()
                .sorted(BigDecimal::compareTo)
                .collect(Collectors.toList());

        int amountOfProductBeforeFiltering = productPricesSortedAsc.size();
        BigDecimal priceFrom = productPricesSortedAsc.get(1).setScale(0, RoundingMode.CEILING);
        BigDecimal priceTo = productPricesSortedAsc.get(productPricesSortedAsc.size() - 2).setScale(0, RoundingMode.FLOOR);

        NewInSubCatPage categoryPageFiltered = categoryPage
                .getProductFilters()
                .filterByPriceRange(priceFrom, priceTo);

        List<BigDecimal> productsPricesFiltered = categoryPageFiltered.getProductsPricesAsNumbers();

        int amountOfProductsAfterFilter = productsPricesFiltered.size();
        long numberOfPricesWithValueLessPriceFrom = productsPricesFiltered
                .stream().filter(p -> p.compareTo(priceFrom) < 0)
                .count();
        long numberOfPricesWithValueOverPriceTo = productsPricesFiltered
                .stream().filter(p -> p.compareTo(priceTo) > 0)
                .count();
        int actualNumberOfActivePriceFilters = categoryPageFiltered
                .getProductFilters()
                .getNumberOfActivePriceFilters();

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(amountOfProductBeforeFiltering > amountOfProductsAfterFilter,
                "Amount of products after applying price range filter didn't change");
        soft.assertEquals(numberOfPricesWithValueLessPriceFrom, 0,
                "Products with price lower than lower price limit were displayed");
        soft.assertEquals(numberOfPricesWithValueOverPriceTo, 0,
                "Products with price greater than upper price limit were displayed");
        soft.assertEquals(actualNumberOfActivePriceFilters, 2,
                "Number of active price filters is different than expected");
        soft.assertAll();
    }
}
