package christmas.domain.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Beverage;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Main;
import christmas.domain.menu.MenuEnumInterface;

public class OrderService {
	private static final List<MenuEnumInterface> allMenuItems = new ArrayList<>();
	private static final int CHRISTMAS_DAY_EVENT_START = 1;
	private static final int CHRISTMAS_DAY_EVENT_END = 25;
	private static final List<Integer> WEEKDAY = List.of(1,4,5,6,7,8,11,12,13,14,15,18,19,20,21,22,25,26,27,28,29);
	private static final List<Integer> WEEKEND = List.of(2,3,9,10,16,17,23,24,31);
	private static final List<Integer> SPECIAL_DAY = List.of(3,10,17,24,25,31); 
	private static final List<String> DESSERT_ITEM_LIST = List.of("CHOCOLATE_CAKE","ICE_CREAM");
	private static final List<String> MAIN_ITEM_LIST = List.of("T_BONE_STEAK","BBQ_RIBS","SEAFOOD_PASTA","CHRISTMAS_PASTA");
	private static final int DISCOUNT_MONEY = 2023;
	
    static {
        allMenuItems.addAll(Arrays.asList(Main.values()));
        allMenuItems.addAll(Arrays.asList(Dessert.values()));
        allMenuItems.addAll(Arrays.asList(Appetizer.values()));
        allMenuItems.addAll(Arrays.asList(Beverage.values()));
    }
    
	public static void appendOrderMenu(Order order,String memuInputData) {
		Map<String,Integer> appendOrderMenu = new HashMap<>();
		String[] memuInputDatas = memuInputData.split(",");
		StringBuilder menuName = new StringBuilder(); 
		StringBuilder menuCount = new StringBuilder();
		for(String inputData : memuInputDatas) {
			inputData = inputData.replaceAll("-", "");
			for(char inputDataWord : inputData.toCharArray()) {
				if(!Character.isDigit(inputDataWord)) menuName.append(inputDataWord);
				if(Character.isDigit(inputDataWord)) menuCount.append(inputDataWord);
			}
			appendOrderMenu.put(menuName.toString(), Integer.parseInt(menuCount.toString()));
			menuName.setLength(0); menuCount.setLength(0);
		}
		order.setOrderMenu(appendOrderMenu);
	}
	
	
	public static int getTotalOrderMoney(Order order) {
		int totalOrderMoney =0;
		for(Map.Entry<String, Integer> entry : order.getOrderMenu().entrySet()) {
			for(MenuEnumInterface menuItem : allMenuItems) {
				if(menuItem.getName().equals(entry.getKey())) totalOrderMoney += (menuItem.getValue() * entry.getValue());
			}
		}
		return totalOrderMoney;
	}
	
	public static void chrismasDayEvent(Order order) {
		int visitDay = order.getVisitDay();
		Map<String,Integer> rewardList = order.getRewardsList();
		if(visitDay >=CHRISTMAS_DAY_EVENT_START && visitDay <=CHRISTMAS_DAY_EVENT_END) {
			int discountMoney = 1000 + (order.getVisitDay()-1) * 100;
			rewardList.put("크리스마스 디데이 할인:", discountMoney);
			order.setRewardsList(rewardList);
		}
	}
	//TODO: 평일할인(디저트 메뉴당 1개할인), 주말할인(메인메뉴당 1개할인), 특별할인(특정날짜- 1000원할인),증정 구현
	
	public static void weekdayEvent(Order order) {
		int eventDiscountMoney = 0;
		Map<String,Integer> orderMenu = order.getOrderMenu();
		Map<String,Integer> rewardList = order.getRewardsList();
		if(WEEKDAY.contains(order.getVisitDay())) {
			for(Map.Entry<String, Integer> entry : orderMenu.entrySet()) {
				if(DESSERT_ITEM_LIST.contains(entry.getKey())) eventDiscountMoney += DISCOUNT_MONEY * entry.getValue();
			}
		}
		rewardList.put("평일 할인:",eventDiscountMoney);
		order.setRewardsList(rewardList);
	}
	
	public static void weekendEvent(Order order) {
		int eventDiscountMoney = 0;
		Map<String,Integer> orderMenu = order.getOrderMenu();
		Map<String,Integer> rewardList = order.getRewardsList();
		if(WEEKEND.contains(order.getVisitDay())) {
			for(Map.Entry<String, Integer> entry : orderMenu.entrySet()) {
				if(MAIN_ITEM_LIST.contains(entry.getKey())) eventDiscountMoney += DISCOUNT_MONEY * entry.getValue();
			}
		}
		rewardList.put("주말 할인:",eventDiscountMoney);
		order.setRewardsList(rewardList);
	}
	
	public static void specialDayEvent(Order order) {
		Map<String,Integer> rewardList = order.getRewardsList();
		if(SPECIAL_DAY.contains(order.getVisitDay())) {
			rewardList.put("특별 할인:", 1000);
		}
		order.setRewardsList(rewardList);
	}
	
	
}
