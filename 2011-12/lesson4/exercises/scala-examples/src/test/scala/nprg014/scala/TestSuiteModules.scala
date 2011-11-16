package nprg014.scala
import org.scalatest.FunSuite

class TestSuiteModules extends FunSuite {
  
  object Attempt01 {
	  abstract class Ingredient(name: String);

	  class Pizza(
	      val name: String,
	      val ingredients:List[Ingredient])	    
	  
	  
	  abstract class Menu {
	    def ingredients:List[Ingredient]
	    def pizzas: List[Pizza]	    
	  }	  
	  	  
	  abstract class MenuService {
	    
	    val menu:Menu
	    
	    def pizzaWith(ingredient: Ingredient) = {
	      menu.pizzas.filter(pizza => pizza.ingredients.contains(ingredient))	      
	    }
	  }
	  
	  // create a new menu
	  trait WeekIngredients {
	    object Tomato extends Ingredient("tomato")
	    object Pepper extends Ingredient("peper")
	    
	    def ingredients = List(Tomato, Pepper) 	    
	  }
	  
	  trait WeekPizzas {
	    this: WeekIngredients => // NOTE: self-type definition 
	    	object Margarita extends Pizza("margarita", List(Tomato)) 
	    	def pizzas = List(Margarita)	    
	  }
	  
	  object weekMenu extends Menu with WeekIngredients with WeekPizzas {	    
	  }
	  
	  // NOTE: the same strategy would be used for weekend menu
  }
  
  test("note #1 - composition with objects (aka depenendency injection)") {
    import Attempt01._
        
    object weekService extends MenuService { //NOTE: there is no particular non-abstract class however the instance is created!
      val menu = weekMenu
    }
    
    assert(weekService.pizzaWith(weekMenu.Tomato) === List(weekMenu.Margarita))
    assert(weekService.pizzaWith(weekMenu.Pepper) === Nil)
  }
  
  /* More generic example */
  object Attempt02 {
    abstract class Meal (val name: String)
    
    abstract class Service[T <% Meal, MENU[_] <: Menu[_,_]] { //NOTE: <% means view-bound, <: means subtype, Menu subtype is not necessary in this example
      val menu : MENU[T] // NOTE: in fact type constructor
      
      def foodWithIngredient[I](ingredient: I):List[T] 
    }
    
    abstract class Menu[+M, I] {
    	def ingredients:List[I]
    	def meals: List[M]
    }
           
    import Attempt01._    
    // NOTE: I want to use defined ingredients in Attempt01
    abstract class MenuX[+M] extends Menu[M, Ingredient];
    
    trait NewPizzas {
      this: WeekIngredients =>
	      object NewMargarita extends Pizza("newMargarita", List(Tomato, Pepper));
	      def meals = List(NewMargarita)      
    }
    object menuWithNewPizzas extends MenuX[Pizza] with WeekIngredients with NewPizzas
    
    object service extends Service[Pizza, MenuX] {     
      
      val menu = menuWithNewPizzas // NOTE: Pizza is not compatible with Meal
      
      override def foodWithIngredient[Ingredient](ingredient: Ingredient):List[Pizza] = Nil
      
    }
    implicit def pizzaToMeal(pizza:Pizza):Meal = new Meal(pizza.name) {}
        
  }
  
  test("note #2 - type hell") {
    expect(List()) {      
      import Attempt02._;
      
      service.foodWithIngredient(menuWithNewPizzas.Tomato)
    }
  }

}