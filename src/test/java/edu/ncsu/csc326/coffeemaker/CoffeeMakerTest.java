/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 *
 * Modifications
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;


public class CoffeeMakerTest {
    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;
    private CoffeeMaker coffeeMakerMock;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    private RecipeBook recipeBook;
    private Recipe[] recipes;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

        //Set up for r1
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Set up for r2
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        //Set up for r3
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Set up for r4
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtChocolate("4");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");
        recipeBook = Mockito.mock(RecipeBook.class);
        recipes = new Recipe[]{recipe1, recipe2, recipe3, recipe4};
        coffeeMakerMock = new CoffeeMaker(recipeBook, new Inventory());

    }


    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with well-formed quantities
     * Then we do not get an exception trying to read the inventory quantities.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("4", "7", "0", "9");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryException() throws InventoryException {
        coffeeMaker.addInventory("4", "-1", "asdf", "3");
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the valid recipe and paying more than
     * the coffee costs
     * Then we get the correct change back.
     */
    @Test
    public void testMakeCoffee() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }

    @Test
    public void testAddRecipes() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
    }

    @Test
    public void testDeleteRecipe() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(recipe1.getName(), coffeeMaker.deleteRecipe(0));
    }

    @Test
    public void testcheckInventory() {
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", coffeeMaker.checkInventory());
    }

    @Test
    public void testEditRecipe() {
        assertTrue(coffeeMaker.addRecipe(recipe2));
        coffeeMaker.editRecipe(0, recipe1);
        assertEquals(coffeeMaker.getRecipes()[0], recipe1);
    }

    @Test
    public void testPurchaseBeverage() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertEquals(50, coffeeMaker.makeCoffee(0, 100));
        when(recipeBook.getRecipes()).thenReturn(recipes);
        assertEquals(100, coffeeMakerMock.makeCoffee(0, 150));
        verify(recipeBook, times(4)).getRecipes();
    }

    @Test
    public void testPurchaseBeverageWithNotEnoughMoney(){
        when(recipeBook.getRecipes()).thenReturn(recipes);
        assertEquals(49,coffeeMakerMock.makeCoffee(0,49));
        verify(recipeBook, atLeastOnce()).getRecipes();
    }

    @Test
    public void testPurchaseBeverageWithNotEnoughInventor(){
        when(recipeBook.getRecipes()).thenReturn(recipes);
        assertEquals(9000,coffeeMakerMock.makeCoffee(1,9000));
        verify(recipeBook, atLeastOnce()).getRecipes();
    }
}
