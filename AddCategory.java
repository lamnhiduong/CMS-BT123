package BT123;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

    public class AddCategory extends BaseTest {

        public void loginCMS() {
            driver.get("https://cms.anhtester.com/login");
            WebElement headerLogin = driver.findElement(By.xpath(LocatorsCMS.headerLoginPage));
            System.out.println("Title của Login page là: " + headerLogin.getText());
            Assert.assertTrue(headerLogin.isDisplayed(), "Wrong Login page's header");
            setText(LocatorsCMS.inputEmail, "admin@example.com");
            setText(LocatorsCMS.inputPassword, "123456");
            clickElement(LocatorsCMS.buttonLogin);
            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCMS.itemDashboards)).isDisplayed(), "Wrong Dashboard");
            System.out.println("Information of Dashboard title: " + driver.findElement(By.xpath(LocatorsCMS.itemDashboards)).getText());

        }

        String CATEGORY_NAME= "day la 1 con bug";

        @Test
        public void addNewCategory() {
            loginCMS();
            clickElement(LocatorsCMS.menuProducts);
            clickElement(LocatorsCMS.menuCategory);
            sleep(1);

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(driver.findElement(By.xpath(LocatorsCMS.headerCategoryPage)).isDisplayed(), "Not display Customer page");
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCMS.headerCategoryPage)).getText(), "All categories", "Wrong header of Customer page");
            System.out.println("Title of Customer page: " + driver.findElement(By.xpath(LocatorsCMS.headerCategoryPage)).getText());
            clickElement(LocatorsCMS.buttonAddNewCategory);
            sleep(2);

            softAssert.assertTrue(driver.findElement(By.xpath(LocatorsCMS.headerAddNewCategory)).isDisplayed(), "Not display Add new category form");
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCMS.headerAddNewCategory)).getText(), "Category Information", "Wrong header of Add new category page");
            setText(LocatorsCMS.inputName,CATEGORY_NAME);
            setText(LocatorsCMS.inputOrderingNumber,"514633");
            clickElement(LocatorsCMS.buttonBanner200x200);
            setText(LocatorsCMS.inputSearchFileImg,"999");
            clickElement(LocatorsCMS.optionImg);
            clickElement(LocatorsCMS.buttonAddFiles);
            setText(LocatorsCMS.inputMetaTitle, "pic is a pic");
            clickElement(LocatorsCMS.dropdownFilteringAttributes);
            setText(LocatorsCMS.searchFilteringAttribute, "Size");
            clickElement(LocatorsCMS.dropdownFilteringAttributes);
            sleep(3);
            clickElement(LocatorsCMS.buttonSave);
            sleep(3);

            clickElement(LocatorsCMS.menuCategory);
            driver.findElement(By.xpath(LocatorsCMS.searchCategoryPage)).sendKeys(CATEGORY_NAME,Keys.ENTER);
            sleep(2);

            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCMS.fistItemlCategories)).isDisplayed(), "Category not found");
            sleep(2);

            clickElement(LocatorsCMS.iconEditCategories);
            sleep(2);
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCMS.inputName)).getAttribute("value"), CATEGORY_NAME, "Category name not match.");
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCMS.inputOrderingNumber)).getAttribute("value"), "98765", "Ordering number not match.");
            softAssert.assertAll();
        }
    }

