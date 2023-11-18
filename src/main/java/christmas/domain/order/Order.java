package christmas.domain.order;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
	private int visitDay;
	
	private Map<String,Integer> orderMenu = new HashMap<>();
	
	private int orderMoney;
	
	private boolean isGiftIncluded = false;
	
	private Map<String,Integer> rewardsList = new LinkedHashMap<>();

	public Order(int visitDay){
		this.visitDay = visitDay;
		this.rewardsList.put("크리스마스 디데이 할인:", 0);
		this.rewardsList.put("평일 할인:", 0);
		this.rewardsList.put("주말 할인:", 0);
		this.rewardsList.put("특별 할인:", 0);
		this.rewardsList.put("증정 이벤트:", 0);
	}

	public int getVisitDay() {
		return visitDay;
	}

	public void setVisitDay(int visitDay) {
		this.visitDay = visitDay;
	}

	public Map<String, Integer> getOrderMenu() {
		return orderMenu;
	}

	public void setOrderMenu(Map<String, Integer> orderMenu) {
		this.orderMenu = orderMenu;
	}

	public int getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(int orderMoney) {
		this.orderMoney = orderMoney;
	}

	public boolean isGiftIncluded() {
		return isGiftIncluded;
	}
	
	public void setGiftIncluded(boolean isGiftIncluded) {
		this.isGiftIncluded = isGiftIncluded;
	}

	public Map<String, Integer> getRewardsList() {
		return rewardsList;
	}

	public void setRewardsList(Map<String, Integer> rewardsList) {
		this.rewardsList = rewardsList;
	}
	
	
}
