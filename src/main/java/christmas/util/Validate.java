package christmas.util;

public class Validate {
	private static final String NOT_NUMBER = "[ERROR] 숫자만 입력해주세요.";
	
	public static void checkNumber(String inputData) {
		for(char word : inputData.toCharArray()) {
			if(!Character.isDigit(word)) throw new  IllegalArgumentException(inputData);
		}
	}
}
