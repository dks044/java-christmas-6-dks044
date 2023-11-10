package christmas.view;

import camp.nextstep.edu.missionutils.Console;
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
	
	
}
