package christmas.domain.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Beverage;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Main;
import christmas.domain.menu.MenuEnumInterface;
import christmas.util.Util;

public class OrderService {
	private static final List<MenuEnumInterface> allMenuItems = new ArrayList<>();
	private static final int CHRISTMAS_DAY_EVENT_START = 1;
	private static final int CHRISTMAS_DAY_EVENT_END = 25;
	private static final List<Integer> WEEKDAY = List.of(3,4,5,6,7,10,11,12,13,14,17,18,19,20,21,24,25,26,27,28,31);
	private static final List<Integer> WEEKEND = List.of(1,2,8,9,15,16,22,23,29,30);
	private static final List<Integer> SPECIAL_DAY = List.of(3,10,17,24,25,31); 
	private static final List<String> DESSERT_ITEM_LIST = List.of("CHOCOLATE_CAKE","ICE_CREAM");
	private static final List<String> MAIN_ITEM_LIST = List.of("T_BONE_STEAK","BBQ_RIBS","SEAFOOD_PASTA","CHRISTMAS_PASTA");
	private static final int DISCOUNT_MONEY = 2023;
	private static final int GIFT_EVENT_MINIMUM_ORDER_AMOUNT = 120000;
	private static final int MINIMUM_PURCHASE_FOR_DISCOUNT = 10000;
	private static final int EVENT_BADGE_STAR = 5000;
	private static final int EVENT_BADGE_TREE = 10000;
	private static final int EVENT_BADGE_SANTA = 20000;
	
    static {
        allMenuItems.addAll(Arrays.asList(Main.values()));
        allMenuItems.addAll(Arrays.asList(Dessert.values()));
        allMenuItems.addAll(Arrays.asList(Appetizer.values()));
        allMenuItems.addAll(Arrays.asList(Beverage.values()));
    }
    
    public static void appendOrderMenu(Order order, String menuInputData) {
        Map<String, Integer> orderMenu = Arrays.stream(menuInputData.split(","))
            .map(input -> input.replaceAll("-", ""))
            .collect(Collectors.toMap(
                input -> input.replaceAll("\\d", ""),
                input -> Integer.parseInt(input.replaceAll("\\D", ""))
            ));

        order.setOrderMenu(orderMenu);
    }

	
	
	public static int getTotalOrderMoney(Order order) {
		int totalOrderMoney =0;
		for(Map.Entry<String, Integer> entry : order.getOrderMenu().entrySet()) {
			for(MenuEnumInterface menuItem : allMenuItems) {
				if(menuItem.getName().equals(entry.getKey())) totalOrderMoney += (menuItem.getPrice() * entry.getValue());
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
	
	private static void weekdayEvent(Order order) {
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
	
	private static void weekendEvent(Order order) {
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
	
	private static void specialDayEvent(Order order) {
		Map<String,Integer> rewardList = order.getRewardsList();
		if(SPECIAL_DAY.contains(order.getVisitDay())) {
			rewardList.put("특별 할인:", 1000);
		}
		order.setRewardsList(rewardList);
	}
	private static void giftEvent(Order order) {
		Map<String,Integer> rewardList = order.getRewardsList();
		if(order.getOrderMoney() >= GIFT_EVENT_MINIMUM_ORDER_AMOUNT) {
			rewardList.put("증정 이벤트:", 25000);
			order.setRewardsList(rewardList);
			order.setGiftIncluded(true);
		}
	}
		
	public static void totalDiscountEvent(Order order) {
		if(order.getOrderMoney()>=MINIMUM_PURCHASE_FOR_DISCOUNT) {
			OrderService.chrismasDayEvent(order);
			OrderService.weekdayEvent(order);
			OrderService.weekendEvent(order);
			OrderService.specialDayEvent(order);
			OrderService.giftEvent(order);
		}
	}
	public static boolean isDiscountEventEmpty(Order order) {
		Map<String,Integer> rewardsList = order.getRewardsList();
		for(Map.Entry<String, Integer> entry : rewardsList.entrySet()) {
			if(entry.getValue() !=0) return false; 
		}
		return true;
	}
	public static int getTotalBenefitsAmount(Order order) {
		int totalBenefitAmount = 0;
		Map<String,Integer> rewardList = order.getRewardsList();
		for(Map.Entry<String, Integer> entry : rewardList.entrySet()) {
			totalBenefitAmount += entry.getValue();
		}
		return totalBenefitAmount;
	}
	
	public static String getEventBadgeForPurchaseAmount(Order order) {
		String eventBadge ="없음";
		int totalBenefitsAmount = OrderService.getTotalBenefitsAmount(order);
		if(totalBenefitsAmount >= EVENT_BADGE_STAR && totalBenefitsAmount < EVENT_BADGE_TREE) eventBadge ="별";
		if(totalBenefitsAmount >= EVENT_BADGE_TREE && totalBenefitsAmount < EVENT_BADGE_SANTA) eventBadge ="트리";
		if(totalBenefitsAmount >= EVENT_BADGE_SANTA) eventBadge ="산타";
		return eventBadge;
	}
	public static Map<String,Integer> parseOrderMenuToKorName(Order order) {
		Map<String,Integer> orderMenu = order.getOrderMenu();
		for(Map.Entry<String, String> entry : Util.getTotalMenu().entrySet()) {
			if(orderMenu.containsKey(entry.getValue())) {
				orderMenu.put(entry.getKey(), orderMenu.get(entry.getValue()));
				orderMenu.remove(entry.getValue());
			}
		}
		return orderMenu;
	}
}
