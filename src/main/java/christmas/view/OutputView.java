package christmas.view;

import christmas.domain.order.Order;

public class OutputView {
	public static void printWelcomeMessage() {
		System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
	}
	public static void printEventPreviewForDate(Order order) {
		int visitDay = order.getVisitDay();
		System.out.println("12월 "+visitDay+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
	}
	
}
