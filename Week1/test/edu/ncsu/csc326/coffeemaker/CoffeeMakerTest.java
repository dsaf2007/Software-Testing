package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoffeeMakerTest {

    CoffeeMaker test = new CoffeeMaker();
    Recipe r = new Recipe();
    Inventory inventory = new Inventory();
    @Test
    public void addRecipe() {
        r.setName("testRecipie");
        assertTrue(test.addRecipe(r));
    }

    //negative test
    //�룞�씪�븳 �씠由꾩쓽 �젅�떆�뵾瑜� addRecipe硫붿냼�뱶瑜� �넻�빐 異붽� �븷 寃쎌슦
   /* @Test
    public void addRecipeNegative() {
        r.setName("testRecipie");
        test.addRecipe(r);
        assertTrue(test.addRecipe(r));
    }
    */

    @Test
    public void deleteRecipe() {
        r.setName("testRecipie");
        test.addRecipe(r);
        assertEquals("testRecipie", test.deleteRecipe(0));
    }

    //negative test
    //�궘�젣�븷 �젅�떆�뵾媛� 議댁옱�븯吏� �븡�뒗 寃쎌슦 �궘�젣 �떆�룄
    /*@Test
    public void deleteRecipeNegative() {
        assertEquals("testRecipie", test.deleteRecipe(0));
    }
    */

    @Test
    public void editRecipe() {
        Recipe new_r = new Recipe();
        r.setName("testRecipie");
        test.addRecipe(r);
        assertEquals("testRecipie", test.editRecipe(0,new_r));
    }

    //negative test
    //�닔�젙�븷 �젅�떆�뵾媛� 議댁옱�븯吏� �븡�뒗 寃쎌슦
    /*@Test
    public void editRecipeNegative() {
        Recipe new_r = new Recipe();
        assertEquals("testRecipie", test.editRecipe(0,new_r));
    }
    */

    @Test
    public void addInventory(){

        assertThrows(InventoryException.class, () -> test.addInventory("10","10","10","10"));
        
        assertThrows(InventoryException.class, () -> test.addInventory("0","10","10","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","0","10","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","10","0","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","10","10","0"));
        
        assertThrows(InventoryException.class, () -> test.addInventory(null,"10","10","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10",null,"10","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","10",null,"10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","10","10",null));
        
        assertThrows(InventoryException.class, () -> test.addInventory("-10","10","10","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","-10","10","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","10","-10","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","10","10","-10"));
        
        assertThrows(InventoryException.class, () -> test.addInventory("null","10","10","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","null","10","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","10","null","10"));
        assertThrows(InventoryException.class, () -> test.addInventory("10","10","10","null"));

    }

    //negative test
    //add�맂 �뜲�씠�꽣�� 湲곕�媛믪씠 �떎瑜� �븣
    /*@Test
    public void addInventoryNegative(){
        try {
            test.addInventory("0", "15", "15", "15");
        }
        catch (InventoryException e){
            e.printStackTrace();
            fail();
        }
        assertEquals("Coffee: 15\n" +
                "Milk: 15\n" +
                "Sugar: 15\n" +
                "Chocolate: 15\n", test.checkInventory());
    }
    */

    @Test
    public void checkInventory() {
        inventory.setMilk(10);
        inventory.setChocolate(10);
        inventory.setCoffee(10);
        inventory.setSugar(10);
        assertEquals("Coffee: 10\n" +
                "Milk: 10\n" +
                "Sugar: 10\n" +
                "Chocolate: 10\n" , test.checkInventory());
    }

    //negative test
    //set�맂 媛믨낵 expected�맂 媛믪씠 �떎瑜� �븣
    /*@Test
    public void checkInventoryNegative() {
        inventory.setMilk(0);
        inventory.setChocolate(10);
        inventory.setCoffee(10);
        inventory.setSugar(10);
        assertEquals("Coffee: 10\n" +
                "Milk: 10\n" +
                "Sugar: 10\n" +
                "Chocolate: 10\n" , test.checkInventory());
    }
    */

    @Test
    public void makeCoffee() throws RecipeException {
    	
    	//레시피가 없을 경우 추가
    	int testChange = test.makeCoffee(0, 100);
    	assertEquals(100, testChange);
    	
        r.setPrice("50");
        test.addRecipe(r);
        testChange = test.makeCoffee(0,100);
        assertEquals(50, testChange);
    }

    //negative test
    //change媛� �씪移섑븯吏� �븡�쓣 �븣
    /*@Test
    public void makeCoffeeNegative() throws RecipeException {
        r.setPrice("50");
        test.addRecipe(r);
        int testChange = test.makeCoffee(0,100);
        assertEquals(40, testChange);
    }
    */

    @Test
    public void getRecipes() {
        test.addRecipe(r);
        Recipe[] recipes = new Recipe[4];
        recipes[0] = r;
        assertArrayEquals(recipes, test.getRecipes());
    }

    //negative test
    //媛��졇�삱 �젅�떆�뵾媛� �뾾�쓣 �븣
    /*@Test
    public void getRecipesNegative() {
        Recipe[] recipes = new Recipe[4];
        recipes[0] = r;
        assertArrayEquals(recipes, test.getRecipes());
    }*/
}