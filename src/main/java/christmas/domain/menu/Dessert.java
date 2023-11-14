package christmas.domain.menu;

public enum Dessert implements MenuEnumInterface{
	CHOCOLATE_CAKE(15000),
	ICE_CREAM(5000);
	
	private final int price;
	
	Dessert(int price){
		this.price = price;
	}

	@Override
	public String getName() {
		return this.name();
	}

	@Override
	public int getPrice() {
		return price;
	}
	
}
