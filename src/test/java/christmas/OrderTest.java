package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.order.Order;
import christmas.domain.order.OrderService;
import christmas.util.Util;

public class OrderTest {
	private static final int MINIMUM_PURCHASE_FOR_DISCOUNT = 10000;
	
	
	@DisplayName("order 객체가 생성될떄, rewardList 인스턴스변수(map)이 제대로 자동 할당 되는지 검사한다.")
	@Test
	void 생성자테스트() {
		Order order = new Order(1);
		String[] rewardList = {"크리스마스 디데이 할인:","평일 할인:","주말 할인:","특별 할인:","증정 이벤트:"};
		int rewardListIndex =0;
		for(Map.Entry<String, Integer> entry : order.getRewardsList().entrySet()) {
			assertEquals(entry.getKey(), rewardList[rewardListIndex++]);
		}
	}
	
	@DisplayName("문자열을 받고, 주문메뉴가 제대로 할당되는지 검사한다.")
	@Test
	void 주문메뉴생성테스트() {
	    Order order = new Order(1);
	    String normalData = Util.parseInputDataToEngName("양송이수프-1,타파스-1,아이스크림-1,레드와인-1,티본스테이크-1");
	    OrderService.appendOrderMenu(order, normalData);

	    Map<String, Integer> expectedMenu = Map.of(
	        "MUSHROOM_SOUP", 1,
	        "TAPAS", 1,
	        "ICE_CREAM", 1,
	        "RED_WINE", 1,
	        "T_BONE_STEAK", 1
	    );

	    assertEquals(expectedMenu, order.getOrderMenu(), "주문 메뉴가 정확히 할당되지 않았습니다.");
	}
	
	@DisplayName("문자열을 받고, 주문메뉴를 할당하고, 그에 따른 총주문금액을 계산한다.")
	@Test
	void 총주문금액계산테스트() {
	    Order order = new Order(1);
	    String normalData = Util.parseInputDataToEngName("양송이수프-1,타파스-1,아이스크림-1,레드와인-1,티본스테이크-1");
	    OrderService.appendOrderMenu(order, normalData);
	    int collectTotalOrderMoney = 131500;
	    assertEquals(collectTotalOrderMoney, OrderService.getTotalOrderMoney(order));
	}
	
	@DisplayName("입력 날짜에 따른 크리스마스 디데이 할인이 적용되는지 검사한다.")
	@Test
	void 크리스마스디데이할인테스트() {
		Order order = new Order(25);
		int testCollectData = 3400;
		String normalData = Util.parseInputDataToEngName("양송이수프-1,타파스-1,아이스크림-1,레드와인-1,티본스테이크-1");
	    OrderService.appendOrderMenu(order, normalData);
	    order.setOrderMoney(OrderService.getTotalOrderMoney(order));
	    if(order.getOrderMoney() >= MINIMUM_PURCHASE_FOR_DISCOUNT) OrderService.chrismasDayEvent(order);
	    assertEquals(testCollectData, order.getRewardsList().get("크리스마스 디데이 할인:"));
	}
	
	@DisplayName("5가지의 할인이벤트가 총 적용되는지 검사한다")
	@Test
	void 할인이벤트전체테스트() {
		Order order = new Order(3);
		String inputTestData = Util.parseInputDataToEngName("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
		OrderService.appendOrderMenu(order, inputTestData);
		order.setOrderMoney(OrderService.getTotalOrderMoney(order));
		OrderService.totalDiscountEvent(order);
		assertEquals(1200, order.getRewardsList().get("크리스마스 디데이 할인:"),"3일이니 1200원 할인되어야 합니다.");
		assertEquals(4046, order.getRewardsList().get("평일 할인:"),"디저트메뉴는 2개니까, 4046원 할인되어야 합니다.");
		assertEquals(0, order.getRewardsList().get("주말 할인:"),"주말이 아니므로 0원이어야 합니다.");
		assertEquals(1000, order.getRewardsList().get("특별 할인:"),"이벤트 달력에 별이 있는날이므로, 1000원 할인되어야 합니다.");
		assertEquals(25000, order.getRewardsList().get("증정 이벤트:"),"12만원 이상 주문했으므로, 25000원치 혜택이 적용되어야 합니다.");
	}
	
	
	
}
