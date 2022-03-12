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
    //동일한 이름의 레시피를 addRecipe메소드를 통해 추가 할 경우
    @Test
    public void addRecipeNegative() {
        r.setName("testRecipie");
        test.addRecipe(r);
        assertTrue(test.addRecipe(r));
    }

    @Test
    public void deleteRecipe() {
        r.setName("testRecipie");
        test.addRecipe(r);
        assertEquals("testRecipie", test.deleteRecipe(0));
    }

    //negative test
    //삭제할 레시피가 존재하지 않는 경우 삭제 시도
    @Test
    public void deleteRecipeNegative() {
        assertEquals("testRecipie", test.deleteRecipe(0));
    }

    @Test
    public void editRecipe() {
        Recipe new_r = new Recipe();
        r.setName("testRecipie");
        test.addRecipe(r);
        assertEquals("testRecipie", test.editRecipe(0,new_r));
    }

    //negative test
    //수정할 레시피가 존재하지 않는 경우
    @Test
    public void editRecipeNegative() {
        Recipe new_r = new Recipe();
        assertEquals("testRecipie", test.editRecipe(0,new_r));
    }

    @Test
    public void addInventory(){
        assertThrows(InventoryException.class, () -> test.addInventory("10","10","10","10"));
    }

    //negative test
    //add된 데이터와 기대값이 다를 때
    @Test
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
    //set된 값과 expected된 값이 다를 때
    @Test
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

    @Test
    public void makeCoffee() throws RecipeException {
        r.setPrice("50");
        test.addRecipe(r);
        int testChange = test.makeCoffee(0,100);
        assertEquals(50, testChange);
    }

    //negative test
    //change가 일치하지 않을 때
    @Test
    public void makeCoffeeNegative() throws RecipeException {
        r.setPrice("50");
        test.addRecipe(r);
        int testChange = test.makeCoffee(0,100);
        assertEquals(40, testChange);
    }

    @Test
    public void getRecipes() {
        test.addRecipe(r);
        Recipe[] recipes = new Recipe[4];
        recipes[0] = r;
        assertArrayEquals(recipes, test.getRecipes());
    }

    //negative test
    //가져올 레시피가 없을 때
    @Test
    public void getRecipesNegative() {
        Recipe[] recipes = new Recipe[4];
        recipes[0] = r;
        assertArrayEquals(recipes, test.getRecipes());
    }
}