package christmas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.util.Util;
import christmas.util.Validate;

public class ValidateTest {
	@DisplayName("숫자가 아닐시에 오류를 발생하는 유효성메소드 테스트")
	@Test
	void checkNumberTest() {
		assertDoesNotThrow(()->Validate.checkNumber("123"));
		assertThrows(IllegalArgumentException.class, () -> Validate.checkNumber("입력데이터는_숫자가_아닙니다."));
	}
	
	@DisplayName("메뉴에 포함되지 않다면 오류를 발생하는 유효성메소드 테스트")
	@Test
	void checkEqualsMenuTest() {
		
		String menuNotEqualsTestData = Util.parseInputDataToEngName("유효성테스트-1,우테코-2,메뉴에없는음식-3");
		String menuEqualsTestData =Util.parseInputDataToEngName("양송이수프-1,타파스-1,아이스크림-1,레드와인-1,티본스테이크-1");
		assertThrows(IllegalArgumentException.class,() -> Validate.checkEqualsMenu(menuNotEqualsTestData));
		assertDoesNotThrow(() -> Validate.checkEqualsMenu(menuEqualsTestData));
		
	}

	
	void checkOnlyBeverageTest() {
		
	}
}
