package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Util;
import christmas.util.Validate;

public class InputView {
	public static int promptForVisitDate() {
	    System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
	    do {
	    	String inputData = Console.readLine();
	    	try {
	    		Validate.checkNumber(inputData);
	    		Validate.checkNumberRange(inputData);
				return Integer.parseInt(inputData);
			} catch (IllegalArgumentException error) {
				System.out.println(error.getMessage());
			}
	    }while(true);
	}
	public static String promptForOrderMenu() {
		System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
		do {
			String inputData = Console.readLine();
			try {
				inputData = Util.parseInputDataToEngName(inputData);
				Validate.checkEqualsMenu(inputData);
				Validate.checkInputDataPattern(inputData);
				return inputData;
			} catch (IllegalArgumentException error) {
				System.out.println(error.getMessage());
			}
		}while(true);
	}
	
}
