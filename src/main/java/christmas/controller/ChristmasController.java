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
		order.setOrderMoney(OrderService.getTotalOrderMoney(order));
	}

	void run() {
		Order order = createPlayerOrder();
		
	}
}
