package christmas.domain.menu;

public enum Beverage {
	ZERO_COLA(3000),
	RED_WINE(60000),
	CHAMPAGNE(25000);
	
	private final int price;
	
	Beverage(int price){
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
}
