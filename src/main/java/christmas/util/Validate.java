package christmas.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	private static final int CALLENDER_RANGE_START= 1;
	private static final int CALLENDER_RANGE_END= 31;
	private static final int MAX_ORDER_LIMIT = 20;
	
	private static final String NOT_NUMBER = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
	private static final String NUMBER_RANGE ="[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
	private static final String IS_NOT_EQUALS_MENU ="[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
	private static final String IS_NOT_STRING_PATTERN ="[ERROR] 문자열,'-',숫자만 입력해주세요.";
	private static final String NOT_ONLY_BEVERAGE = "[ERROR] 음료만 주문할 수 없습니다.";
	private static final String ORDER_LIMIT_EXCEEDED ="[ERROR] 20개 초과해서 주문할 수 없습니다.";
	
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
		String[] inputDatas = inputData.split(",");
		int inputDatasIndex = 0;
		for(Map.Entry<String, String> entry : Util.getTotalMenu().entrySet()) {
			if(!entry.getValue().equals(inputDatasIndex++)) throw new IllegalArgumentException(IS_NOT_EQUALS_MENU);  
		}
	}
	public static void checkInputDataPattern(String inputData) {
		String regex = "^[a-zA-Z0-9-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputData);
        if(!matcher.matches()) throw new  IllegalArgumentException(IS_NOT_STRING_PATTERN);
	}
	
	//TODO: 테스트코드 구현
	public static void checkOnlyBeverage(String inputData) {
		String[] inputDatas = inputData.split(",");
		int inputDatasIndex = 0;
		int beverageCount = 0;
		int totalMenuCount =0;
		for(Map.Entry<String, String> entry : Util.getBeverageMenu().entrySet()) {
			if(entry.getValue().equals(inputDatasIndex++)) beverageCount++;
		}
		for(Map.Entry<String, String> entry : Util.getNonBeverageMenu().entrySet()) {
			if(entry.getValue().equals(inputDatasIndex++)) totalMenuCount++;
		}
		if(beverageCount>0 && totalMenuCount==0) throw new  IllegalArgumentException(NOT_ONLY_BEVERAGE);
	}
	
	//TODO: 테스트코드 구현
	public static void checkOrderCountRange(String inputData) {
		int orderTotalCount =0;
		String[] inputDatas = inputData.split(",");
		for(String data : inputDatas) {
			for(char dataWord  : data.toCharArray()) {
				if(Character.isDigit(dataWord)) orderTotalCount += dataWord - '0';
			}
		}
		if(orderTotalCount>MAX_ORDER_LIMIT) throw new  IllegalArgumentException(ORDER_LIMIT_EXCEEDED);
	}
	
}
