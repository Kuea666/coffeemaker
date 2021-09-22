package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import static org.junit.Assert.*;
public class PurchaseCucumberTest {

    private CoffeeMaker coffeeMaker;

    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private int paidAmount;
    private Recipe[] recipes;
    private int selectRecipe;


    @Given("coffeemaker is ready to use")
    public void coffeemakerIsReadyToUse() throws RecipeException {
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

        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        coffeeMaker.addRecipe(recipe4);
        // Write code here that turns the phrase above into concrete actions

    }
    @When("I want to purchase a {word}")
    public void iWantToPurchaseA(String str1){
        for (int i = 0;i<coffeeMaker.getRecipes().length;i++){
            if(str1.equals(coffeeMaker.getRecipes()[i].getName())){
                selectRecipe=i;
            }
        }
    }

    @And("I give {int}")
    public void iGive(int int1) {
        paidAmount =int1;
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("I got change {int}")
    public void iGotChange(int int1) {
        assertEquals(int1,coffeeMaker.makeCoffee(selectRecipe,paidAmount));
        // Write code here that turns the phrase above into concrete actions
    }

}

