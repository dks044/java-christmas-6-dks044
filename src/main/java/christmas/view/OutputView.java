package christmas.view;

import java.text.NumberFormat;
import java.util.Map;

import christmas.domain.order.Order;
import christmas.domain.order.OrderService;
import christmas.util.Util;

public class OutputView {
	public static void printWelcomeMessage() {
		System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
	}
	private static void printEventPreviewForDate(Order order) {
		int visitDay = order.getVisitDay();
		System.out.println("12월 "+visitDay+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
	}
	private static void printOrderMenuDetail(Order order) {
	    System.out.println("\n<주문 메뉴>");
	    Map<String, Integer> orderMenu = OrderService.parseOrderMenuToKorName(order);
	    for(Map.Entry<String, Integer> entry : orderMenu.entrySet()) {
	    	System.out.println(entry.getKey()+" "+entry.getValue()+"개");
	    }
	}
	private static void printOrderAmountPreDiscount(Order order) {
		System.out.println("\n<할인 전 총주문 금액>");
		NumberFormat numberFormat = NumberFormat.getInstance();
		String orderMoney = numberFormat.format(order.getOrderMoney());
		System.out.println(orderMoney+"원");
	}
	
	private static void printGiftEventStatus(Order order) {
		System.out.println("\n<증정 메뉴>");
		if(order.isGiftIncluded()) System.out.println("샴페인 1개");
		if(!order.isGiftIncluded()) System.out.println("없음");
	}
	private static void printEventBenefitsDetails(Order order) {
		System.out.println("\n<혜택 내역>");
		if(!OrderService.isDiscountEventEmpty(order)) {
			for(Map.Entry<String, Integer> entry : order.getRewardsList().entrySet()) {
				if(entry.getValue() != 0) System.out.println(entry.getKey()+" "+entry.getValue());
			}
		}
		if(OrderService.isDiscountEventEmpty(order)) System.out.println("없음");
	}
	private static void printTotalBenefitsAmount(Order order) {
		System.out.println("\n<총혜택 금액>");
		if(OrderService.isDiscountEventEmpty(order)) System.out.println("0원");
		if(!OrderService.isDiscountEventEmpty(order)) {
			NumberFormat numberFormat = NumberFormat.getInstance();
			String totalBenefitsAmount = numberFormat.format(OrderService.getTotalBenefitsAmount(order));
			System.out.println("-"+totalBenefitsAmount+"원");
		}
	}
	
	private static void printOrderAmountPostDiscount(Order order) {
		int orderAmountPostDiscount = 0;
		System.out.println("\n<할인 후 예상 결제 금액>");
		if(OrderService.isDiscountEventEmpty(order)) orderAmountPostDiscount = order.getOrderMoney();
		if(!OrderService.isDiscountEventEmpty(order)) {
			orderAmountPostDiscount = order.getOrderMoney() - OrderService.getTotalBenefitsAmount(order);
		}
		NumberFormat numberFormat = NumberFormat.getInstance();
		System.out.println(numberFormat.format(orderAmountPostDiscount)+"원");
	}
	
	private static void printEventBadge(Order order) {
		System.out.println("\n<12월 이벤트 배지>");
		System.out.println(OrderService.getEventBadgeForPurchaseAmount(order));
	}
	public static void printOrderDetail(Order order) {
		printEventPreviewForDate(order);
		printOrderMenuDetail(order);
		printOrderAmountPreDiscount(order);
		printGiftEventStatus(order);
		printEventBenefitsDetails(order);
		printTotalBenefitsAmount(order);
		printOrderAmountPostDiscount(order);
		printEventBadge(order);
	}
	
	
}
