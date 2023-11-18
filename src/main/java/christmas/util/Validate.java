package christmas.util;

import java.util.Arrays;
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
	private static final String NOT_UNIQUE_ORDER_ITEM = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
	
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
		for(int i=0;i<inputDatas.length;i++) inputDatas[i] = inputDatas[i].replaceAll("[\\d-]", "");
		for(String data : inputDatas) {
			if(!Util.getMenuEngNameList().contains(data)) throw new IllegalArgumentException(IS_NOT_EQUALS_MENU);
		}
	}
	public static void checkInputDataPattern(String inputData) {
		String[] inputDatas = inputData.split(",");
		String regex = "^[a-zA-Z0-9-_]+$";
        Pattern pattern = Pattern.compile(regex);
        for(int i=0;i<inputDatas.length;i++) {
        	Matcher matcher = pattern.matcher(inputDatas[i]);
            if(!matcher.matches()) throw new  IllegalArgumentException(IS_NOT_STRING_PATTERN);
        }
	}
	
	public static void checkOnlyBeverage(String inputData) {
		String[] inputDatas = Arrays.stream(inputData.split(","))
                .map(s -> s.replaceAll("[\\d-]", ""))
                .toArray(String[]::new);

		long beverageCount = Arrays.stream(inputDatas)
		               .filter(s -> Util.getBeverageMenu().containsValue(s))
		               .count();
		
		long totalMenuCount = Arrays.stream(inputDatas)
		                .filter(s -> Util.getNonBeverageMenu().containsValue(s))
		                .count();
		
		if (beverageCount > 0 && totalMenuCount == 0) throw new IllegalArgumentException(NOT_ONLY_BEVERAGE);
	}

	public static void checkOrderCountRange(String inputData) {
		String[] inputDatas = inputData.split(",");
		StringBuilder num = new StringBuilder();
		for(String data : inputDatas) {
			for(char dataWord  : data.toCharArray()) {
				if(Character.isDigit(dataWord)) num.append(dataWord);
			}
		}
		int orderTotalCount = Integer.parseInt(num.toString());
		if(orderTotalCount>MAX_ORDER_LIMIT) throw new  IllegalArgumentException(ORDER_LIMIT_EXCEEDED);
	}
	
	public static void checkUniqueOrderItem(String inputData) {
		String[] inputDatas = Arrays.stream(inputData.split(","))
                .map(s -> s.replaceAll("[\\d-]", ""))
                .toArray(String[]::new);
		long inputDatasDistinctCount = Arrays.stream(inputDatas).distinct().count();
		if(inputDatas.length != inputDatasDistinctCount) throw new  IllegalArgumentException(NOT_UNIQUE_ORDER_ITEM);
	}
}
