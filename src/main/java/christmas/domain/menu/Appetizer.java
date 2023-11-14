package christmas.domain.menu;

public enum Appetizer implements MenuEnumInterface{
	MUSHROOM_SOUP(6000),
	TAPAS(5500),
	CAESAR_SALAD(8000);
	
	private final int price;
	
	Appetizer(int price){
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
