package christmas.controller;

import christmas.domain.order.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
	private static Order createPlayerOrder() {
		OutputView.printWelcomeMessage();
		Order order = new Order(InputView.promptForVisitDate());
		return order;
	}
	private static void playerOrderItem(Order order) {
		OutputView.printOrderMenuInformation();
		
	}
	
	
	void run() {
		Order order = createPlayerOrder();
		
	}
}
