import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.meta.TypeQualifier;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class BasicTest extends TestHelper {


    private String username = "admin";
    private String password = "admin";
    String registerName = "register";

    /*
    In class Exercise

    Fill in loginLogoutTest() and login mehtod in TestHelper, so that the test passes correctly.

     */
    @Test
    public void loginLogoutTest(){

        login(username, password);
        waitForElementById("menu");

        driver.findElement(By.linkText("Admin")).click();

        String actualText = driver.findElement(By.id("admin")).getText();
        String loginText = "Logged in user: "+username+" Edit Delete";

        assertEquals(actualText,loginText);

        logout();
    }

    @Test
    public void registerTest()
    {
        driver.get(baseUrlAdmin);
        driver.findElement(By.linkText("Register")).click();



        driver.findElement(By.name("user[name]")).sendKeys("register");
        driver.findElement(By.name("user[password]")).sendKeys("register");
        driver.findElement(By.name("user[password_confirmation]")).sendKeys("register");
        driver.findElement(By.name("commit")).click();

        String expected = "User "+registerName+" was successfully created.";
        String actual = driver.findElement(By.id("notice")).getText();

        assertEquals(expected,actual);

    }

    @Test
    public void adminDeleteTest() throws InterruptedException {

        login(registerName, registerName);
        waitForElementById("menu");

        driver.findElement(By.linkText("Admin")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("/html/body[@class='admin']/" +
                "div[@id='column2']/div[@id='main']/p[@id='register']/a[2]")).click();

        String expected = "User was successfully deleted.";
        String actual = driver.findElement(By.id("notice")).getText();

        assertEquals(expected,actual);

    }

    @Test
    public void logoutTest()
    {
        login(username,password);
        logout();
        String actual = driver.findElement(By.id("menu")).getText();
        String expected = "Admin Login Register";

        assertEquals(expected,actual);


    }

    @Test
    public void addProductTest() throws InterruptedException {
        login(username,password);
        driver.findElement(By.linkText("New product")).click();
        waitForElementById("product_title");

        driver.findElement(By.name("product[title]")).sendKeys("prod1");
        driver.findElement(By.name("product[description]")).sendKeys("prod1");
        driver.findElement(By.name("product[prod_type]")).sendKeys("Sunglasses");
        driver.findElement(By.name("product[price]")).sendKeys("10.0");
        driver.findElement(By.name("commit")).click();



        driver.findElement(By.linkText("prod1")).click();
        Thread.sleep(1500);

        List<WebElement> elements = driver.findElements(By.className("products_column"));

        assertEquals("Title: prod1\n" +
                "Description: prod1\n" +
                "Type: Sunglasses\n" +
                "Price: €10.00\n" +
                "Edit | Back", elements.get(0).getText());

    }

    @Test
    public void prodDeleteTest() throws InterruptedException {
        login(username,password);
        driver.findElement(By.id("prod1")).findElement(By.linkText("Delete")).click();

        Thread.sleep(1500);

        String expected = "Product was successfully destroyed.";
        String actual = driver.findElement(By.id("notice")).getText();

        Thread.sleep(1500);

        assertEquals(expected,actual);

        String prod_list = driver.findElement(By.className("products_column")).getText();
        boolean contain=true;
        if(prod_list.contains("prod1")) contain =false;

        assertEquals(true,contain);
    }

    @Test
    public void prodEditTest() throws InterruptedException {
        login(username,password);
        driver.findElement(By.id("prod1")).findElement(By.linkText("Edit")).click();

        Thread.sleep(1500);

        driver.findElement(By.name("product[price]")).clear();
        driver.findElement(By.name("product[price]")).sendKeys("20.0");
        driver.findElement(By.name("commit")).click();

        Thread.sleep(1500);

        String expected = "Product was successfully updated.";
        String actual = driver.findElement(By.id("notice")).getText();

        Thread.sleep(1500);

        assertEquals(expected,actual);

        String prod_list = driver.findElement(By.className("products_column")).getText();
        boolean contain=false;
        if(prod_list.contains("20.0")) contain =true;

        assertEquals(true,contain);

    }

    @Test
    public void addCartTest() throws InterruptedException {
        driver.findElement(By.id("a_entry")).findElement(By.className("button_to")).click();
        driver.findElement(By.id("b_entry")).findElement(By.className("button_to")).click();
        driver.findElement(By.id("c_entry")).findElement(By.className("button_to")).click();
        driver.findElement(By.id("d_entry")).findElement(By.className("button_to")).click();

        Thread.sleep(1500);
        String cart_list = driver.findElement(By.id("cart")).getText();
        boolean exist = true;
        if(cart_list.contains("a")==false) exist = false;
        if(cart_list.contains("b")==false) exist = false;
        if(cart_list.contains("c")==false) exist = false;
        if(cart_list.contains("d")==false) exist = false;

        assertEquals("Ooo\n" +
                "Your Cart\n" +
                "1× a €1.00 ↓ ↑ X\n" +
                "1× b €2.00 ↓ ↑ X\n" +
                "1× c €4.00 ↓ ↑ X\n" +
                "1× d €12.00 ↓ ↑ X\n" +
                "Total €19.00",cart_list);
        assertEquals(true,exist);
    }

    @Test
    public void inDeCartTest() throws InterruptedException {
        driver.findElement(By.id("a_entry")).findElement(By.className("button_to")).click();
        Thread.sleep(1500);

        //increase
        driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@id='column2']/div[@id='side']/div[@id='cart']/" +
                "table/tbody/tr[@class='cart_row']/td[@class='quantity'][2]")).click();
        Thread.sleep(1500);

        String actual = driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@id='column2']/div[@id='side']/" +
                "div[@id='cart']/table/tbody/tr[@class='cart_row'][1]/td[1]")).getText();
        String expect = "2×";

        assertEquals(expect, actual);

        //decrease
        driver.findElement(By.xpath("//html/body[@class='store']/" +
                "div[@id='column2']/div[@id='side']/div[@id='cart']/" +
                "table/tbody/tr[@class='cart_row']/td[@class='quantity'][1]")).click();
        Thread.sleep(1500);

        actual = driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@id='column2']/div[@id='side']/" +
                "div[@id='cart']/table/tbody/tr[@class='cart_row'][1]/td[1]")).getText();
        expect = "1×";

        assertEquals(expect, actual);

        //emptyCart

        driver.findElement(By.xpath("/html/body[@class='store']/div[@id='column2']/" +
                "div[@id='side']/" +
                "div[@id='cart']/form[@class='button_to'][1]/input[2]")).click();



    }

    @Test
    public void deleteProdCartTest() throws InterruptedException {
        driver.findElement(By.id("a_entry")).findElement(By.className("button_to")).click();
        driver.findElement(By.id("b_entry")).findElement(By.className("button_to")).click();
        Thread.sleep(1500);

        //delete b
        driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@id='column2']/div[@id='side']/div[@id='cart']/" +
                "table/tbody/tr[@id='current_item']/td[@id='delete_button']/a")).click();
        Thread.sleep(1500);

        String actual = driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@id='column2']/div[@id='main']/p[@id='notice']")).getText();
        String expect = "Item successfully deleted from cart.";

        assertEquals(expect, actual);

        //empty cart
        driver.findElement(By.xpath("/html/body[@class='store']/div[@id='column2']/" +
                "div[@id='side']/" +
                "div[@id='cart']/form[@class='button_to'][1]/input[2]")).click();
    }

    @Test
    public void emptyCartTest() throws InterruptedException {
        driver.findElement(By.id("a_entry")).findElement(By.className("button_to")).click();
        driver.findElement(By.id("b_entry")).findElement(By.className("button_to")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("/html/body[@class='store']/div[@id='column2']/" +
                "div[@id='side']/" +
                "div[@id='cart']/form[@class='button_to'][1]/input[2]")).click();
        Thread.sleep(1500);

        String actual = driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@id='column2']/div[@id='main']/p[@id='notice']")).getText();
        String expect = "Cart successfully deleted.";

        assertEquals(expect, actual);

    }

    @Test
    public void findProdByNameTest() throws InterruptedException {
        driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@id='column2']/div[@id='main']/" +
                "div[@id='search_box']/form/input[@id='search_input']")).sendKeys("a");

        Thread.sleep(1500);

        String actual = driver.findElement(By.xpath("/html/body[@class='store']" +
                "/div[@id='column2']/div[@id='main']")).getText();

        boolean contain = false;
        if(actual.contains("a")==true) contain = true;
        if(actual.contains("b")==true) contain = false;
        if(actual.contains("c")==true) contain = false;
        if(actual.contains("d")==true) contain = false;

        assertEquals(true,contain);

    }

    @Test
    public void searchByCategorySunglassesTest() throws InterruptedException {
        driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@class='columns'][1]/div[@id='menu']/ul/li[2]/a")).click();
        Thread.sleep(1500);

        String actual =  driver.findElement(By.xpath("/html/body[@class='store']" +
                "/div[@id='column2']/div[@id='main']")).getText();
        boolean contain = false;
        if(actual.contains("f")==true) contain =true;
        if(actual.contains("Sunglasses")==true && actual.contains("book")==false
        && actual.contains("other")==false) {contain = true;}
        else
            contain = false;

        assertEquals(true,contain);
    }

    @Test
    public void searchByCategoryBooksTest() throws InterruptedException {
        driver.findElement(By.xpath("/html/body[@class='store']/div[@class='columns'][1]/div[@id='menu']/ul/li[3]/a")).click();
        Thread.sleep(1500);

        String actual =  driver.findElement(By.xpath("/html/body[@class='store']" +
                "/div[@id='column2']/div[@id='main']")).getText();
        boolean contain = false;
        if(actual.contains("e")==true && actual.contains("c")==true && actual.contains("a")==true) contain =true;
        if(actual.contains("Sunglasses")==false && actual.contains("Books")==true
                && actual.contains("other")==false) {contain = true;}
        else
            contain = false;

        assertEquals(true,contain);
    }

    @Test
    public void searchByCategoryOthersTest() throws InterruptedException {
        driver.findElement(By.xpath("/html/body[@class='store']/div[@class='columns'][1]/div[@id='menu']/ul/li[4]/a")).click();
        Thread.sleep(1500);

        String actual =  driver.findElement(By.xpath("/html/body[@class='store']" +
                "/div[@id='column2']/div[@id='main']")).getText();
        boolean contain = false;
        if(actual.contains("b")==true && actual.contains("d")==true) contain =true;
        if(actual.contains("Sunglasses")==false && actual.contains("Books")==false
                && actual.contains("Other")==true) {contain = true;}
        else
            contain = false;

        assertEquals(true,contain);
    }

    @Test
    public void orderTest() throws InterruptedException {
        driver.findElement(By.id("a_entry")).findElement(By.className("button_to")).click();
        driver.findElement(By.id("b_entry")).findElement(By.className("button_to")).click();
        driver.findElement(By.id("b_entry")).findElement(By.className("button_to")).click();
        Thread.sleep(1500);

        String total = driver.findElement(By.xpath("/html/body[@class='store']/div[@id='column2']/" +
                "div[@id='side']/div[@id='cart']/table/tbody/tr[@class='total_line']/td[@class='total_cell']")).getText();
        driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@id='column2']/div[@id='side']/div[@id='cart']/form[@id='checkout_button']/input")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("/html/body[@class='orders']/div[@id='column2']/" +
                "div[@id='main']/div[@class='depot_form']/" +
                "form[@id='new_order']/div[@class='field'][1]/input[@id='order_name']")).sendKeys("dsaf");
        driver.findElement(By.xpath("/html/body[@class='orders']/" +
                "div[@id='column2']/div[@id='main']/div[@class='depot_form']/" +
                "form[@id='new_order']/div[@class='field'][2]/textarea[@id='order_address']")).sendKeys("dsaf");
        driver.findElement(By.xpath("/html/body[@class='orders']/" +
                "div[@id='column2']/div[@id='main']/div[@class='depot_form']/" +
                "form[@id='new_order']/div[@class='field'][3]/input[@id='order_email']")).sendKeys("dsaf@na.com");
        driver.findElement(By.xpath("/html/body[@class='orders']/div[@id='column2']/" +
                "div[@id='main']/div[@class='depot_form']/form[@id='new_order']/" +
                "div[@class='field'][4]/select[@id='order_pay_type']")).sendKeys("Purchase Order");
        driver.findElement(By.xpath("/html/body[@class='orders']/" +
                "div[@id='column2']/div[@id='main']/div[@class='depot_form']/form[@id='new_order']/" +
                "div[@id='place_order']/input")).click();
        Thread.sleep(1500);

        String actual = driver.findElement(By.xpath("/html/body[@class='orders']/" +
                "div[@id='column2']/div[@id='main']/div[@id='order_receipt']/h3")).getText();
        String expect = "Thank you for your order";
        assertEquals(expect,actual);

        String orderTotal = driver.findElement(By.xpath("/html/body[@class='orders']/div[@id='column2']/div[@id='main']/" +
                "div[@id='order_receipt']/" +
                "table[@id='check_out']/tbody/tr[@class='total_line']/td[@class='total_cell']/strong")).getText();
    assertEquals(total,orderTotal);

    }
    @Test
    public void loginWrongPasswordtest()
    {
        login(username, "password");
        waitForElementById("menu");

        driver.findElement(By.linkText("Admin")).click();

        String actualText = driver.findElement(By.id("admin")).getText();
        String loginText = "Logged in user: "+username+" Edit Delete";

        assertEquals(actualText,loginText);
    }

    @Test
    public void registerNegativeTest()
    {
        driver.get(baseUrlAdmin);
        driver.findElement(By.linkText("Register")).click();



        driver.findElement(By.name("user[name]")).sendKeys("admin");
        driver.findElement(By.name("user[password]")).sendKeys("register");
        driver.findElement(By.name("user[password_confirmation]")).sendKeys("register");
        driver.findElement(By.name("commit")).click();

        String expected = "User "+registerName+" was successfully created.";
        String actual = driver.findElement(By.id("notice")).getText();

        assertEquals(expected,actual);

    }

    @Test
    public void findProdByNameNegativeTest() throws InterruptedException {
        driver.findElement(By.xpath("/html/body[@class='store']/" +
                "div[@id='column2']/div[@id='main']/" +
                "div[@id='search_box']/form/input[@id='search_input']")).sendKeys("prod");

        Thread.sleep(1500);

        String actual = driver.findElement(By.xpath("/html/body[@class='store']" +
                "/div[@id='column2']/div[@id='main']")).getText();

        boolean contain = false;
        if(actual.contains("a")==true) contain = true;
        if(actual.contains("b")==true) contain = false;
        if(actual.contains("c")==true) contain = false;
        if(actual.contains("d")==true) contain = false;

        assertEquals(true,contain);

    }

    @Test
    public void addProductNegativeTest() throws InterruptedException {
        login(username,password);
        driver.findElement(By.linkText("New product")).click();
        waitForElementById("product_title");

        driver.findElement(By.name("product[title]")).sendKeys("a");
        driver.findElement(By.name("product[description]")).sendKeys("a");
        driver.findElement(By.name("product[prod_type]")).sendKeys("books");
        driver.findElement(By.name("product[price]")).sendKeys("10.0");
        driver.findElement(By.name("commit")).click();



        driver.findElement(By.linkText("a")).click();
        Thread.sleep(1500);

        List<WebElement> elements = driver.findElements(By.className("products_column"));

        assertEquals("Title: a\n" +
                "Description: a\n" +
                "Type: books\n" +
                "Price: €10.00\n" +
                "Edit | Back", elements.get(0).getText());

    }

    @Test
    public void prodDeleteNegativeTest() throws InterruptedException {

        driver.findElement(By.id("a_entry")).findElement(By.className("button_to")).click();
        login(username,password);
        Thread.sleep(1500);

        Thread.sleep(1500);

        driver.findElement(By.id("a")).findElement(By.linkText("Delete")).click();

        Thread.sleep(1500);

        String expected = "Product was successfully destroyed.";
        String actual = driver.findElement(By.id("notice")).getText();

        Thread.sleep(1500);

        assertEquals(expected,actual);

        String prod_list = driver.findElement(By.className("products_column")).getText();
        boolean contain=true;
        if(prod_list.contains("prod1")) contain =false;

        assertEquals(true,contain);

        driver.get(baseUrl);

        driver.findElement(By.xpath("/html/body[@class='store']/div[@id='column2']/" +
                "div[@id='side']/" +
                "div[@id='cart']/form[@class='button_to'][1]/input[2]")).click();
    }

}
