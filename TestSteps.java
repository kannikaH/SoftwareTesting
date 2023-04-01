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
 * Modified 20171114 by Ian De Silva -- Updated to adhere to coding standards.
 * 
 */
package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.CoffeeMakerUI.Mode;
import edu.ncsu.csc326.coffeemaker.CoffeeMakerUI.Status;
import edu.ncsu.csc326.coffeemaker.UICmd.AddInventory;
import edu.ncsu.csc326.coffeemaker.UICmd.CheckInventory;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseRecipe;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseService;
import edu.ncsu.csc326.coffeemaker.UICmd.Command;
import edu.ncsu.csc326.coffeemaker.UICmd.DescribeRecipe;
import edu.ncsu.csc326.coffeemaker.UICmd.InsertMoney;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import edu.ncsu.csc326.coffeemaker.UICmd.TakeMoneyFromTray;


/**
 * Contains the step definitions for the cucumber tests.  This parses the 
 * Gherkin steps and translates them into meaningful test steps.
 */
public class TestSteps {
	
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	//private Recipe recipe5;
	private CoffeeMakerUI coffeeMakerMain; 
	private CoffeeMaker coffeeMaker;
	private RecipeBook recipeBook;

	
	private void initialize() {
		recipeBook = new RecipeBook();
		coffeeMaker = new CoffeeMaker(recipeBook, new Inventory());
		coffeeMakerMain = new CoffeeMakerUI(coffeeMaker);
	}
	
    @Given("^an empty recipe book$")
    public void an_empty_recipe_book() throws Throwable {
        initialize();
    }


    @Given("a default recipe book")
	public void a_default_recipe_book() throws Throwable {
    	initialize();
    	
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
		//recipe4 = new Recipe();
		//recipe4.setName("Hot Chocolate");
		//recipe4.setAmtChocolate("4");
		//recipe4.setAmtCoffee("0");
		//recipe4.setAmtMilk("1");
		//recipe4.setAmtSugar("1");
		//recipe4.setPrice("65");
		
		//Set up for r5 (added by MWW)
		//recipe5 = new Recipe();
		//recipe5.setName("Super Hot Chocolate");
		//recipe5.setAmtChocolate("6");
		//recipe5.setAmtCoffee("0");
		//recipe5.setAmtMilk("1");
		//recipe5.setAmtSugar("1");
		//recipe5.setPrice("100");

		recipeBook.addRecipe(recipe1);
		recipeBook.addRecipe(recipe2);
		recipeBook.addRecipe(recipe3);
	    //recipeBook.addRecipe(recipe4);
		
	}
    @When("^the coffee maker not in use$")
    public void the_coffee_maker_not_in_use() throws Throwable {
    	
    	coffeeMakerMain.UI_Input(new ChooseService(0));
    }

    @Then("^the coffee maker has the mode Waiting$")
    public void the_coffee_maker_has_the_mode_Waiting() throws Throwable {
    	assertEquals("WAITING",coffeeMakerMain.getMode().toString());
    }
    @Then("^the coffee maker has the mode Add Recipe$")
    public void the_coffee_maker_has_the_mode_Add_Recipe() throws Throwable {
    	
    	assertEquals("ADD_RECIPE",coffeeMakerMain.getMode().toString());
    }

    @When("^user select service Add Recipe$")
    public void user_select_service_Add_Recipe() throws Throwable {
    	coffeeMakerMain.UI_Input(new ChooseService(1));
    }
    @When("^user can add only (\\d+) recipe to the CoffeeMaker$")
    public void user_can_add_only_recipe_to_the_CoffeeMaker(int arg1) throws Throwable {
    	recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		recipeBook.addRecipe(recipe1);
		recipeBook.addRecipe(recipe2);
		recipeBook.addRecipe(recipe3);
		
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
		assertEquals(recipeBook.getRecipes()[0].getName(),"Coffee");
		//System.out.println(recipeBook.getRecipes()[0].getName());
		
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe2));
		assertEquals(recipeBook.getRecipes()[1].getName(),"Mocha");
		//System.out.println(recipeBook.getRecipes()[1].getName());
		
		
	    coffeeMakerMain.UI_Input(new DescribeRecipe(recipe3));
	    assertEquals(recipeBook.getRecipes()[2].getName(),"Latte");
	    //System.out.println(recipeBook.getRecipes()[2].getName());
	   
    }

    @When("^user can add forthrecipe to the CoffeeMaker$")
    public void user_can_add_forthrecipe_to_the_CoffeeMaker() throws Throwable {
    	 
	    recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
    	coffeeMakerMain.UI_Input(new DescribeRecipe(recipe4));
    	
    	//System.out.println(recipeBook.getRecipes()[3].getName());
    }
    @Then("^the coffee maker has the mode xx$")
    public void the_coffee_maker_has_the_mode_xx() throws Throwable {
    	System.out.println(coffeeMakerMain.getMode().toString());
    }
        
    @Then("^status is OK$")
    public void status_is_OK() throws Throwable {
     assertEquals("OK",coffeeMakerMain.getStatus().toString());
    	
    }

   	@When("^user add same recipe1 to the CoffeeMaker$")
    public void user_add_same_recipe_to_the_CoffeeMaker() throws Throwable {
   		
   		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
	}
   
   	@When("^user add recipe with wrong values in the ingredients$")
   	public void user_add_recipe_with_wrong_values_in_the_ingredients() throws Throwable {
   		try {
   			recipe1.setName("Hot Coco"); 
   			recipe1.setAmtChocolate("-4"); 
   			recipe1.setAmtCoffee("0"); 
   			recipe1.setAmtMilk("-2");
   			recipe1.setAmtSugar("1"); 
   			recipe1.setPrice("60"); 
   			}
   			catch(RecipeException e) {}
   			coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
   	}
   
    @Then("^coffee maker has the status wrong_mode$")
    public void coffee_maker_has_the_status_wrong_mode() throws Throwable {
    	assertEquals("OK",coffeeMakerMain.getStatus().toString());
    }
  
  
    @When("^user select service Delete a Recipe$")
    public void user_select_service_Delete_a_Recipe() throws Throwable {
    	coffeeMakerMain.UI_Input(new ChooseService(2));
    }

    @When("^user delete recipeone$")
    public void user_delete_recipeone() throws Throwable {
    	coffeeMakerMain.UI_Input(new ChooseRecipe(1));
		assertEquals("Coffee",coffeeMaker.deleteRecipe(0));
		//assertEquals(null,coffeeMaker.deleteRecipe(0));
    }

    @Then("^the coffee maker has the mode DELETE RECIPE$")
    public void the_coffee_maker_has_the_mode_DELETE_RECIPE() throws Throwable {
    	assertEquals("DELETE_RECIPE",coffeeMakerMain.getMode().toString());
    	
    }
    
    @When("^user add a recipe and delete recip it$")
    public void user_add_a_recipe_and_delete_recip_it() throws Throwable {
    	coffeeMaker.addRecipe(recipe1);
    	coffeeMakerMain.UI_Input(new ChooseRecipe(1));
		assertEquals("Coffee",coffeeMaker.deleteRecipe(0));
    }

    @When("^user delete recipe not in the list$")
    public void user_delete_recipe_not_in_the_list() throws Throwable {
    	coffeeMakerMain.UI_Input(new ChooseRecipe(4));
    	assertEquals(null,coffeeMaker.deleteRecipe(3));
    	}
   
	@Then("^the coffee maker has the status OUT_OF_RANGE$")
    public void the_coffee_maker_has_the_status_OUT_OF_RANGE() throws Throwable {
		assertEquals("OUT_OF_RANGE",coffeeMakerMain.getStatus().toString());
    }
	@When("^user add inventory to recipe book$")
	public void user_add_inventory_to_recipe_book() throws Throwable {
		coffeeMakerMain.UI_Input(new AddInventory(4,7,0,9));
	}

		
	@When("^user edit a recepiOne$")
	public void user_edit_a_recepiOne() throws Throwable {
	
		//coffeeMaker.addRecipe(recipe1);
		//Set up for r1 
		//recipe1 = new Recipe(); 
		
		recipe1.setName("Boba Tea"); 
		recipe1.setAmtChocolate("4"); 
		recipe1.setAmtCoffee("0"); 
		recipe1.setAmtMilk("2");
		recipe1.setAmtSugar("1"); 
		recipe1.setPrice("70"); 
		
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
		assertEquals(recipeBook.getRecipes()[0].getName(),"Boba Tea");
		
	}

	
	@When("^user edit a recepiOne with wrong values$")
	public void user_edit_a_recepiOne_with_wrong_values() throws Throwable {
		try {
		recipe1.setName("Boba Tea"); 
		recipe1.setAmtChocolate("4"); 
		recipe1.setAmtCoffee("0"); 
		recipe1.setAmtMilk("2");
		recipe1.setAmtSugar("-1"); 
		recipe1.setPrice("70"); 
		}
		catch(RecipeException e) {}
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
	}
	
	@Then("^the coffee maker has the mode EDIT_RECIPE$")
	public void the_coffee_maker_has_the_mode_EDIT_RECIPE() throws Throwable {
		assertEquals("EDIT_RECIPE",coffeeMakerMain.getMode().toString());
	}
	
	@Then("^the coffee maker has the mode WAITING$")
	public void the_coffee_maker_has_the_mode_WAITING() throws Throwable {
		assertEquals("WAITING",coffeeMakerMain.getMode().toString());
	}

	@When("^user select service edit a Recipe$")
	public void user_select_service_edit_a_Recipe() throws Throwable {
		coffeeMakerMain.UI_Input(new ChooseService(3));
	}
	@When("^user select service ADD_INVENTORY$")
	public void user_select_service_ADD_INVENTORY() throws Throwable {
		coffeeMakerMain.UI_Input(new ChooseService(4));
	}

	@Then("^the coffee maker has the mode ADD_INVENTORY$")
	public void the_coffee_maker_has_the_mode_ADD_INVENTORY() throws Throwable {
		assertEquals("ADD_INVENTORY",coffeeMakerMain.getMode().toString());
	}
	@Then("^the coffee status is OK$")
	public void the_coffee_status_is_OK() throws Throwable {
		
		assertEquals("WAITING",coffeeMakerMain.getMode().toString());
	}
	@When("^user add inventory  with wrong values to recipe book$")
	public void user_add_inventory_with_wrong_values_to_recipe_book() throws Throwable {
		coffeeMakerMain.UI_Input(new AddInventory(-5,0,0,0));
	}
	
	@Then("^the coffee status is OUT_OF_RANGE$")
	public void the_coffee_status_is_OUT_OF_RANGE() throws Throwable {
		assertEquals("OUT_OF_RANGE",coffeeMakerMain.getStatus().toString());
	}
	
	@Then("^the coffee maker has the status RECIPE_NOT_ADDED$")
	public void the_coffee_maker_has_the_status_RECIPE_NOT_ADDED() throws Throwable {
		assertEquals("RECIPE_NOT_ADDED",coffeeMakerMain.getStatus().toString());
	}
	
	@When("^user select service CHECK_INVENTORY$")
	public void user_select_service_CHECK_INVENTORY() throws Throwable {
	    coffeeMakerMain.UI_Input(new ChooseService(5));
	}

	@Then("^the coffee maker has the mode CHECK_INVENTORY$")
	public void the_coffee_maker_has_the_mode_CHECK_INVENTORY() throws Throwable {
		assertEquals("CHECK_INVENTORY",coffeeMakerMain.getMode().toString());
	}

	@When("^User check inventory$")
	public void user_check_inventory() throws Throwable {
		
		coffeeMakerMain.UI_Input(new CheckInventory());
	}
	@When("^user select service beverage PURCHASE_BEVERAGE$")
	public void user_select_service_beverage_PURCHASE_BEVERAGE() throws Throwable {
		coffeeMakerMain.UI_Input(new ChooseService(6));
	}

	@Then("^the coffee maker has the mode PURCHASE_BEVERAGE$")
	public void the_coffee_maker_has_the_mode_PURCHASE_BEVERAGE() throws Throwable {
		assertEquals("PURCHASE_BEVERAGE",coffeeMakerMain.getMode().toString());
	}
	
	@When("^User select recipe not in the list and make Purchase$")
	public void user_select_recipe_nor_in_the_list_and_make_Purchase() throws Throwable {
		assertEquals(0, coffeeMaker.makeCoffee(4, 75));
	}

	
	@When("^user select recipe in the menu and insert money$")
	public void user_select_recipe_in_the_menu_and_insert_money() throws Throwable {
		coffeeMakerMain.UI_Input(new ChooseRecipe(1));
		coffeeMakerMain.getMoneyInTray();
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}

	@When("^User purchase beverage wrong amount$")
	public void user_purchase_beverage_wrong_amount() throws Throwable {
		coffeeMakerMain.UI_Input(new ChooseRecipe(1));
		assertEquals(-25, coffeeMaker.makeCoffee(0, -25));
	}

	@Then("^the coffee maker has the status INSUFFICIENT_FUNDS$")
	public void the_coffee_maker_has_the_status_INSUFFICIENT_FUNDS() throws Throwable {
		assertEquals("INSUFFICIENT_FUNDS",coffeeMakerMain.getStatus().toString());
	}
	
	@When("^User Add recipe without enough ingredients and make Purchase$")
	public void user_Add_recipe_without_enough_ingredients_and_make_Purchase() throws Throwable {
		//Set up for r1
				recipe1 = new Recipe();
				recipe1.setName("hot Coffee");
				recipe1.setAmtChocolate("18");
				recipe1.setAmtCoffee("3");
				recipe1.setAmtMilk("0");
				recipe1.setAmtSugar("1");
				recipe1.setPrice("50");
				
			recipeBook.addRecipe(recipe1);
			coffeeMakerMain.UI_Input(new ChooseRecipe(1));
			assertEquals(25, coffeeMaker.makeCoffee(0, 75));
				
	}
	@When("^user delete an empty Recipe$")
	public void user_delete_an_empty_Recipe() throws Throwable {
		coffeeMakerMain.UI_Input(new ChooseRecipe(4));
    	assertEquals(null,coffeeMaker.deleteRecipe(3)); 
	}
	
	@When("^user provide inventory ammount (\\d+) (\\d+) (\\d+) -(\\d+)$")
	public void user_provide_inventory_ammount1(int arg1, int arg2, int arg3, int arg4) throws Throwable {
		Command cmd = new AddInventory(arg1, arg2, arg3, -arg4);
		coffeeMakerMain.UI_Input(cmd);	
	}
	@When("^user provide inventory ammount (\\d+) (\\d+) (\\d+) (\\d+)$")
	public void user_provide_inventory_ammount2(int arg1, int arg2, int arg3, int arg4) throws Throwable {
		Command cmd = new AddInventory(arg1, arg2, arg3, arg4);
		coffeeMakerMain.UI_Input(cmd);	
	}

	@When("^user provide inventory ammount (\\d+) -(\\d+) (\\d+) (\\d+)$")
	public void user_provide_inventory_ammount3(int arg1, int arg2, int arg3, int arg4) throws Throwable {
		Command cmd = new AddInventory(arg1, -arg2, arg3, arg4);
		coffeeMakerMain.UI_Input(cmd);	
	}

	@When("^user provide inventory ammount (\\d+) (\\d+) -(\\d+) (\\d+)$")
	public void user_provide_inventory_ammount4(int arg1, int arg2, int arg3, int arg4) throws Throwable {
		Command cmd = new AddInventory(arg1, arg2, -arg3, arg4);
		coffeeMakerMain.UI_Input(cmd);	
	}
	
	@When("^user Select the Beverage: (\\d+) and I insert some money: (\\d+)$")
	public void user_Select_the_Beverage_and_I_insert_some_money(int arg1, int arg2) throws Throwable {
		  
		coffeeMaker.makeCoffee(arg1, arg2);
        
	}


	@Then("^the coffee maker has the status OK$")
	public void the_coffee_maker_has_the_status_OK() throws Throwable {
		assertEquals("OK",coffeeMakerMain.getStatus().toString());
	}

	@Then("^the coffee maker has the mod WAITING$")
	public void the_coffee_maker_has_the_mod_WAITING() throws Throwable {
		assertEquals("WAITING",coffeeMakerMain.getMode().toString());
	}

	@When("^user Select the Beverage: (\\d+) and I insert some money: -(\\d+)$")
	public void user_Select_the_Beverage_and_I_insert_some_money01(int arg1, int arg2) throws Throwable {
		  
		ChooseRecipe rec = new ChooseRecipe(arg1);
		Command cmd = new InsertMoney(-arg2);
		Command cmd1 = new TakeMoneyFromTray();
	        try{
	        	coffeeMakerMain.UI_Input(cmd);
	        	coffeeMakerMain.UI_Input(cmd1);
	        	coffeeMakerMain.UI_Input(rec);	
	        	coffeeMaker.makeCoffee(arg1, -arg2);
	           
	        }
	        catch (ArrayIndexOutOfBoundsException ignored){
	        }
		 
		  
	}
	

	@When("^user Select the Beverage: -(\\d+) and I insert some money: (\\d+)$")
	public void user_Select_the_Beverage_and_I_insert_some_money02(int arg1, int arg2) throws Throwable {
		
		ChooseRecipe rec = new ChooseRecipe(-arg1);
        try{
            coffeeMakerMain.UI_Input(rec);
	        
        }
        catch (ArrayIndexOutOfBoundsException ignored){
        }
        
	  Command cmd = new InsertMoney(-arg2);
	   coffeeMakerMain.UI_Input(cmd);
	  
		//System.out.println(recipeBook.getRecipes()[0].getPrice());
	}
}
