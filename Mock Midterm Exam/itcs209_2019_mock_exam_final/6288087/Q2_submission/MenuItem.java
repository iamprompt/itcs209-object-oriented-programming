//Name: Supakarn Laorattanakul
//ID: 6288087
//Section: 3

public class MenuItem {
	private String name;
	private double price;
	private int calorie;

	public MenuItem(String _name, double _price, int _calorie) {
		this.name = _name;
		this.price = _price;
		this.calorie = _calorie;
	}

	public MenuItem(String _name, double _price) {
		this.name = _name;
		this.price = _price;
	}

	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getCalorie() {
		return this.calorie;
	}
	
	public double increasePrice(int percent) {
		return this.price + ((percent / 100.00) * this.price);
	}
}
