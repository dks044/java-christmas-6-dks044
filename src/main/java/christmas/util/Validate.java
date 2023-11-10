package christmas.util;

public class Validate {
	private static final int CALLENDER_RANGE_START= 1;
	private static final int CALLENDER_RANGE_END= 31;
	
	private static final String NOT_NUMBER = "[ERROR] 숫자만 입력해주세요.";
	private static final String NUMBER_RANGE ="[ERROR] 1~31 범위의 숫자만 입력해주세요";
	
	public static void checkNumber(String inputData) {
		for(char word : inputData.toCharArray()) {
			if(!Character.isDigit(word)) throw new  IllegalArgumentException(NOT_NUMBER);
		}
	}
	public static void checkNumberRange(String inputData) {
		int day = Integer.parseInt(inputData);
		if(day < CALLENDER_RANGE_START || day >CALLENDER_RANGE_END) {
			throw new  IllegalArgumentException(NUMBER_RANGE);
		}
	}
}
