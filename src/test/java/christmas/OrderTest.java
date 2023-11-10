package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.order.Order;

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
	
}
