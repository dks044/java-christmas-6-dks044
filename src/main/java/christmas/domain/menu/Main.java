package christmas.domain.menu;

public enum Main {
	T_BONE_STEAK(55000),
	BBQ_RIBS(54000),
	SEAFOOD_PASTA(35000),
	CHRISTMAS_PASTA(25000);

	private final int price;
	
	Main(int price){
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
}
