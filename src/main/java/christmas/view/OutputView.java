package christmas.view;

import java.text.NumberFormat;
import java.util.Map;

import christmas.domain.order.Order;
import christmas.util.Util;

public class OutputView {
	public static void printWelcomeMessage() {
		System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
	}
	public static void printEventPreviewForDate(Order order) {
		int visitDay = order.getVisitDay();
		System.out.println("12월 "+visitDay+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
	}
	public static void printOrderMenuDetail(Order order) {
		System.out.println();
		System.out.println("<주문 메뉴>");
		Map<String,Integer> orderMenu = order.getOrderMenu();
		for(Map.Entry<String, Integer> entry : orderMenu.entrySet()) {
			if(Util.getTotalMenu().containsKey(entry.getKey())) {
				System.out.println(entry.getKey()+" "+entry.getValue()+"개");
			}
		}
	}
	public static void printOrderAmountPreDiscount(Order order) {
		System.out.println();
		System.out.println("<할인 전 총주문 금액>");
		NumberFormat numberFormat = NumberFormat.getInstance();
		String orderMoney = numberFormat.format(order.getOrderMoney());
		System.out.println(orderMoney+"원");
		
	}
	
	
	
}
