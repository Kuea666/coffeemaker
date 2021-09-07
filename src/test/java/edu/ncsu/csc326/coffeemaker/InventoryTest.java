package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 *
 * @author Siravit Ruethaiwat
 */
public class InventoryTest {
    /**
     * The object under test.
     */
    private Inventory inventory;
    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        inventory = new Inventory();
    }

    @Test
    public void testAddChocolateNegativeNum() throws InventoryException{
        inventory.addChocolate("-1");
    }

    @Test
    public void testAddChocolateNotNum() throws InventoryException{
        inventory.addChocolate("Chocolate");
    }

    @Test
    public void testAddCoffeeNegativeNum() throws InventoryException{
        inventory.addCoffee("-1");
    }

    @Test
    public void testAddCoffeeNotNum() throws InventoryException{
        inventory.addCoffee("Coffee");
    }

    @Test
    public void testAddMilkNegativeNum() throws InventoryException{
        inventory.addMilk("-1");
    }

    @Test
    public void testAddMilkNotNum() throws InventoryException{
        inventory.addMilk("Milk");
    }

    @Test
    public void testAddSugarNegativenum() throws InventoryException{
        inventory.addSugar("-1");
        inventory.addSugar("-666");
    }

    @Test
    public void testAddSugarNotNum() throws InventoryException{
        inventory.addSugar("Sugar");
    }

    @Test
    public void testEnoughIngredients() throws InventoryException{
        inventory.setChocolate(-1);
        assertEquals(15,inventory.getChocolate());
        inventory.setCoffee(-1);
        assertEquals(15,inventory.getCoffee());
        inventory.setMilk(-1);
        assertEquals(15,inventory.getMilk());
        inventory.setSugar(-1);
        assertEquals(15,inventory.getSugar());
    }

}