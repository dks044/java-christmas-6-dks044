package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.order.Order;
import christmas.domain.order.OrderService;
import christmas.util.Util;

public class OrderTest {
	@DisplayName("order 객체가 생성될떄, rewardList 인스턴스변수(map)이 제대로 자동 할당 되는지 검사한다.")
	@Test
	void 생성자테스트() {
		Order order = new Order(1);
		String[] rewardList = {"크리스마스 디데이 할인:","평일 할인:","특별 할인:","증정 이벤트:"};
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
	
	
}
