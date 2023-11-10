package christmas.domain.order;

import java.util.HashMap;
import java.util.Map;

public class Order {
	private int visitDay;
	
	private Map<String,Integer> orderMenu = new HashMap<>();
	
	private int orderMoney;
	
	private boolean isGiftIncluded;
	
	private Map<String,Integer> rewardsList = new HashMap<>();
	
	Order(int visitDay){
		this.visitDay = visitDay;
		this.rewardsList.put("크리스마스 디데이 할인:", 0);
		this.rewardsList.put("평일 할인:", 0);
		this.rewardsList.put("특별 할인:", 0);
		this.rewardsList.put("증정 이벤트:", 0);
	}
}
