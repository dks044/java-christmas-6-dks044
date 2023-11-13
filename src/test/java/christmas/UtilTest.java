package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.util.Util;

public class UtilTest {
	@DisplayName("한글입력데이터가 영어로 제대로 변환 되는지 테스트한다.")
	@Test
	void parseInputDataToEngNameTest() {
		String testData = "양송이수프-1,제로콜라-2";
		String testDataTranslate = "MUSHROOM_SOUP-1,ZERO_COLA-2";
		assertEquals(testDataTranslate, Util.parseInputDataToEngName(testData));
	}
}
