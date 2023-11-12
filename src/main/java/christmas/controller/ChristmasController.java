package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.order.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
	private static Order createPlayerOrder() {
		OutputView.printWelcomeMessage();
		Order order = new Order(InputView.promptForVisitDate());
		return order;
	}
	private static void submitOrder(Order order) {
		String inputMenuData = InputView.promptForOrderMenu();
		OrderService.appendOrderMenu(order, inputMenuData);
		//TODO: Orderservice 클래스에서 나머지 order 인스턴스변수 관련 메소드 구현
	}

	void run() {
		Order order = createPlayerOrder();
		
	}
}
