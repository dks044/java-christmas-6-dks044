package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.domain.menu.Appetizer;

public class MenuTest {
	@DisplayName("Appetizer enum 기능 테스트")
	@Test
	void appetizerValueOfTest() {
		String orderItem = "MUSHROOM_SOUP";
		assertEquals(Appetizer.valueOf(orderItem).getPrice(), 6000);
	}
	
	
}
