package christmas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import christmas.util.Validate;

public class ValidateTest {
	@DisplayName("숫자가 아닐시에 오류를 발생하는 유효성메소드 테스트")
	@Test
	void checkNumberTest() {
		assertDoesNotThrow(()->Validate.checkNumber("123"));
		assertThrows(IllegalArgumentException.class, () -> Validate.checkNumber("저는_숫자가_아닙니다."));
	}
	
}
