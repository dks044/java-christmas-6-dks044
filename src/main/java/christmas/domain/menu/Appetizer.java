package christmas.domain.menu;

public enum Appetizer {
	MUSHROOM_SOUP(6000),
	TAPAS(5500),
	CAESAR_SALAD(8000);
	
	private final int price;
	
	Appetizer(int price){
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
}
