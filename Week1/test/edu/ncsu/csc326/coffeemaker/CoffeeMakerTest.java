package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
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

    @Test
    public void deleteRecipe() {
        r.setName("testRecipie");
        test.addRecipe(r);
        assertEquals("testRecipie", test.deleteRecipe(0));
    }

    @Test
    public void editRecipe() {
        Recipe new_r = new Recipe();
        r.setName("testRecipie");
        test.addRecipe(r);
        assertEquals("testRecipie", test.editRecipe(0,new_r));
    }

    @Test
    public void addInventory() throws InventoryException {

      //  assertThrows(test.addInventory("0","0","0","0")," " );
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

    @Test
    public void makeCoffee() {
    }

    @Test
    public void getRecipes() {
    }
}