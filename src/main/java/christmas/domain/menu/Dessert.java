package christmas.domain.menu;

public enum Dessert {
	CHOCOLATE_CAKE(15000),
	ICE_CREAM(5000);
	
	private final int price;
	
	Dessert(int price){
		this.price = price;
	}

	public int getPrice() {
		return price;
	}
}
