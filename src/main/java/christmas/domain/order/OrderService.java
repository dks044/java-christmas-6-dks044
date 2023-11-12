package christmas.domain.order;

import java.util.HashMap;
import java.util.Map;

public class OrderService {
	public static void appendOrderMenu(Order order,String memuInputData) {
		Map<String,Integer> appendOrderMenu = new HashMap<>();
		String[] memuInputDatas = memuInputData.split(",");
		StringBuilder menuName = new StringBuilder(); 
		StringBuilder menuCount = new StringBuilder();
		for(String inputData : memuInputDatas) {
			inputData = inputData.replaceAll("-", "");
			for(char inputDataWord : inputData.toCharArray()) {
				if(!Character.isDigit(inputDataWord)) menuName.append(inputDataWord);
				if(Character.isDigit(inputDataWord)) menuCount.append(inputDataWord);
			}
			appendOrderMenu.put(menuName.toString(), Integer.parseInt(menuCount.toString()));
			menuName.setLength(0); menuCount.setLength(0);
		}
		order.setOrderMenu(appendOrderMenu);
	}
	
}
