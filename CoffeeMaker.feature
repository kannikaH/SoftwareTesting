Feature: CoffeeMakerFeature

In this feature, we are going to test the user stories and use cases for the CoffeeMaker
Example.  We have provided a CoffeeMakerMain.java file that you can use to examine the 
modal behavior in the coffee maker and issue UI commands to use it, so that we can 
adequately test the user stories.

Hints: to catch the mistakes, you might need to add out of range cases for 
recipes and amounts of ingredients.  Also, be sure to check machine state
after running the user story:
  - Are the amounts of ingredients correct?
  - Is the system in the right mode?
  - Is the status what you expect?
  - Is the change produced correct?
  - etc.

Scenario: Waiting State
      Priority: 1 Story Points: 2
      If the Coffee Maker is not in use it waits for user input. There are six different 
      options of user input: 1) add recipe, 2) delete a recipe, 3) edit a recipe, 
      4) add inventory, 5) check inventory, and 6) purchase beverage.
      
      For this scenario, what we will do is try each of the six user inputs and make sure 
      that the coffee maker ends up in the appropriate mode.  This would be a good place
      for a scenario outline with a table that described user inputs and expected final states.
      
      You might also want to try a couple of exceptional values to see what the 
      Coffee Maker does.
      
   Given a default recipe book
   When the coffee maker not in use
   Then the coffee maker has the mode Waiting 
   And  status is OK
   
Scenario: Add Recipes
      Priority: 1 Story Points: 2

      Only three recipes may be added to the CoffeeMaker. A recipe consists of a name, 
      price, units of coffee, units of milk, units of sugar, and units of chocolate. 
      Each recipe name must be unique in the recipe list. Price must be handled as an 
      integer. A status message is printed to specify if the recipe was successfully 
      added or not. Upon completion, the CoffeeMaker is returned to the waiting state.   
      
      For this scenario, you should try to add a recipe to the recipe book, and check to
      see whether the coffee maker returns to the Waiting state.  
      
   Given an empty recipe book
   When user select service Add Recipe
   Then the coffee maker has the mode Add Recipe
 	 When user can add only 3 recipe to the CoffeeMaker
   Then the coffee maker has the mode WAITING
   
  Scenario: Add forthrecipe
		Given a default recipe book
		When user select service Add Recipe
  	Then the coffee maker has the mode Add Recipe
 		When user can add forthrecipe to the CoffeeMaker
 		Then the coffee maker has the status RECIPE_NOT_ADDED
    And the coffee maker has the mode WAITING
   
Scenario: Add the same recipe twice
		Given a default recipe book
		When user select service Add Recipe
		Then the coffee maker has the mode Add Recipe
   	When user add same recipe1 to the CoffeeMaker
    Then the coffee maker has the status RECIPE_NOT_ADDED
    And the coffee maker has the mode WAITING
   
Scenario: Add a recipe with wrong values in the ingredients
		Given a default recipe book
		When user select service Add Recipe
		Then the coffee maker has the mode Add Recipe
   	When user add recipe with wrong values in the ingredients
    Then the coffee maker has the status RECIPE_NOT_ADDED
   	And the coffee maker has the mode WAITING
   	    
Scenario: Delete a Recipe
      Priority: 2 Story Points: 1

      A recipe may be deleted from the CoffeeMaker if it exists in the list of recipes in the
      CoffeeMaker. The recipes are listed by their name. Upon completion, a status message is
      printed and the Coffee Maker is returned to the waiting state.  

   Given a default recipe book
   When user select service Delete a Recipe 
   Then the coffee maker has the mode DELETE RECIPE
   When user delete recipeone
   Then the coffee maker has the mode WAITING
   And  status is OK
   
Scenario: Delete a Recipe after add to the list
   Given a default recipe book
   When user select service Delete a Recipe 
   Then the coffee maker has the mode DELETE RECIPE
   When user add a recipe and delete recip it
   Then the coffee maker has the mode WAITING
   And  status is OK
   
Scenario: Edit a Recipe
      Priority: 2 Story Points: 1

      A recipe may be edited in the CoffeeMaker if it exists in the list of recipes in the
      CoffeeMaker. The recipes are listed by their name. After selecting a recipe to edit, the user
      will then enter the new recipe information. A recipe name may not be changed. Upon
      completion, a status message is printed and the Coffee Maker is returned to the waiting
      state.  

   Given a default recipe book
   When user select service edit a Recipe
   Then the coffee maker has the mode EDIT_RECIPE
   When user edit a recepiOne
   Then the coffee maker has the mode WAITING
   And   the coffee status is OK

Scenario: Edit a Recipe with wrong values
	 Given a default recipe book
   When user select service edit a Recipe
   Then the coffee maker has the mode EDIT_RECIPE
   When user edit a recepiOne with wrong values
   Then the coffee maker has the status RECIPE_NOT_ADDED
   And  the coffee maker has the mode WAITING
   
Scenario Outline: Add Inventory
      Priority: 1 Story Points: 2

      Inventory may be added to the machine at any time from the main menu, and is added to
      the current inventory in the CoffeeMaker. The types of inventory in the CoffeeMaker are
      coffee, milk, sugar, and chocolate. The inventory is measured in integer units. Inventory
      may only be removed from the CoffeeMaker by purchasing a beverage. Upon completion, a
      status message is printed and the CoffeeMaker is returned to the waiting state.   
  
   Given a default recipe book
   When user select service ADD_INVENTORY
   Then the coffee maker has the mode ADD_INVENTORY
   When user provide inventory ammount <coffeeUnit> <milkUnit> <sugarUnit> <chocolateUnit>
   Then the coffee status is <status>
   And the coffee maker has the mode WAITING

   Examples: 
 
|coffeeUnit|milkUnit| sugarUnit|chocolateUnit|added |status      |
|      5   |     2  |    1     |        3    |  1   |    OK      |
|      5   |    -2  |    1     |        3    |  0   |OUT_OF_RANGE|
|      5   |     2  |   -1     |        3    |  0   |OUT_OF_RANGE|
|      5   |     2  |    1     |       -3    |  0   |OUT_OF_RANGE|
 
Scenario: Add Inventory with wrong values
   Given a default recipe book
   When user select service ADD_INVENTORY
   Then the coffee maker has the mode ADD_INVENTORY
 	 When user add inventory  with wrong values to recipe book
   Then the coffee maker has the mode WAITING
   And  the coffee status is OUT_OF_RANGE
   
	
Scenario: Check Inventory 
      Priority: 2 Story Points: 1

      Inventory may be checked at any time from the main menu. The units of each item in the
      inventory are displayed. Upon completion, the Coffee Maker is returned to the waiting state.  
      
   Given a default recipe book
   When user select service CHECK_INVENTORY
   Then the coffee maker has the mode CHECK_INVENTORY 
   When User check inventory
   Then the coffee maker has the mode WAITING
   And   the coffee status is OK 
   
Scenario: Purchase Beverage
      Priority: 1 Story Points: 2

      The user selects a  and inserts an amount of money. The money must be an
      integer. If the beverage is in the RecipeBook and the user paid enough money the
      beverage will be dispensed and any change will be returned. The user will not be able to
      purchase a beverage if thebeveragey do not deposit enough money into the CoffeeMaker. A user's
      money will be returned if there is not enough inventory to make the beverage. Upon
      completion, the Coffee Maker displays a message about the purchase status and is
      returned to the main menu.  
      
   Given a default recipe book
   When user select service beverage PURCHASE_BEVERAGE
   Then the coffee maker has the mode PURCHASE_BEVERAGE 
   When user select recipe in the menu and insert money
   Then the coffee maker has the mode WAITING
   And  the coffee status is OK 
 
 Scenario: Purchase Beverage wrong amount in recipe
   Given a default recipe book
   When user select service beverage PURCHASE_BEVERAGE
   Then the coffee maker has the mode PURCHASE_BEVERAGE 
   When User purchase beverage wrong amount
   Then the coffee maker has the status INSUFFICIENT_FUNDS
   And  the coffee maker has the mode WAITING
   
Scenario: Purchase Beverage without enough ingredients
   Given a default recipe book
   When user select service beverage PURCHASE_BEVERAGE
   Then the coffee maker has the mode PURCHASE_BEVERAGE 
   When User Add recipe without enough ingredients and make Purchase
   Then the coffee maker has the status INSUFFICIENT_FUNDS
   And  the coffee maker has the mode WAITING
   
 Scenario Outline: Purchase Beverage with different beveragenumber
    Given a default recipe book
    When user Select the Beverage: <BeverageNumber> and I insert some money: <Money>
    Then the coffee maker has the status <Status>
    And the coffee maker has the mod <Mode>

    Examples: 
      | Money | BeverageNumber | Status             | Mode    |
      |   100 |              0 | OK                 | WAITING |
      |    50 |              0 | OK                 | WAITING |
      |   100 |             -4 | OUT_OF_RANGE       | WAITING |
    
   
 Scenario: Delete an empty Recipe
   Given an empty recipe book
   When user select service Delete a Recipe
   Then the coffee maker has the mode DELETE RECIPE 
   When user delete recipe not in the list 
   Then the coffee maker has the status OUT_OF_RANGE
   And  the coffee maker has the mode WAITING


# Add scenarios from the Use Cases here.  These can be Cucumber versions of the unit 
# tests that were required for course 1, or can be more direct expressions of the use
# case tests found in the Requirements-coffeemaker.pdf file. 