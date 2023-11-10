package christmas.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	private static final int CALLENDER_RANGE_START= 1;
	private static final int CALLENDER_RANGE_END= 31;
	
	private static final String NOT_NUMBER = "[ERROR] 숫자만 입력해주세요.";
	private static final String NUMBER_RANGE ="[ERROR] 1~31 범위의 숫자만 입력해주세요";
	private static final String IS_NOT_EQUALS_MENU ="[ERROR] 메뉴에 포함된 음식만 입력해주세요.";
	private static final String IS_NOT_STRING_PATTERN ="[ERROR] 문자열,'-',숫자만 입력해주세요.";
	
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
	public static void checkEqualsMenu(String inputData) {
		Util.parseInputDataToEngName(inputData);
		if(!Util.getTotalMenu().containsValue(inputData)) throw new  IllegalArgumentException(IS_NOT_EQUALS_MENU);
	}
	public static void checkInputDataPattern(String inputData) {
		String regex = "^[a-zA-Z0-9-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputData);
        if(!matcher.matches()) throw new  IllegalArgumentException(IS_NOT_STRING_PATTERN);
	}
	
	
}
