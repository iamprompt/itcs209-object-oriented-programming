
import java.util.ArrayList;

public class MenuTester {
	public static void main(String[] args) {
		// Create Menu
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		menu.add(new MenuItem("Espresso", 60, 10));
		menu.add(new MenuItem("Cappuccino", 80, 155));
		menu.add(new MenuItem("Coffee Latte", 75, 150));
		menu.add(new MenuItem("Coffee Mocha", 80, 340));
		menu.add(new MenuItem("Water", 10));
		
		// Task3 (1): Print all menu items
		System.out.println("------------------------------");
		System.out.println("       Coffee Menu");
		System.out.println("------------------------------");
		printMenu(menu);
		
		// Increase the price 
		System.out.println("\nIncrease the price of Cappuccino and Coffee Latte by 8%");
		System.out.println(menu.get(1).getName() + " -> " + menu.get(1).increasePrice(8));
		System.out.println(menu.get(2).getName() + " -> " + menu.get(2).increasePrice(8));
		
		// Task3 (2): Show the summary of the order: total calories and total prices of the order
		System.out.println("\nShow the summary of the order");
		ArrayList<MenuItem> orders = new ArrayList<MenuItem>();
		orders.add(menu.get(3));
		orders.add(menu.get(4));
		showOrderSummary(orders);
	}
	
	public static void printMenu(ArrayList<MenuItem> menu) {
		for(int i = 0; i < menu.size(); i++) {
			System.out.println("[" + (i+1) + "] " 
				+ menu.get(i).getName() + ": " + menu.get(i).getPrice()
				+ " (" + menu.get(i).getCalorie() + " Kcal)");
		}
	} 
	
	public static void showOrderSummary(ArrayList<MenuItem> order) {
		// Your code goes here
	}


}
