package christmas.domain.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Beverage;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Main;
import christmas.domain.menu.MenuEnumInterface;

public class OrderService {
	private static final List<MenuEnumInterface> allMenuItems = new ArrayList<>();

    static {
        allMenuItems.addAll(Arrays.asList(Main.values()));
        allMenuItems.addAll(Arrays.asList(Dessert.values()));
        allMenuItems.addAll(Arrays.asList(Appetizer.values()));
        allMenuItems.addAll(Arrays.asList(Beverage.values()));
    }
    
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
	
	//TODO: 테스트 코드 구현
	public static int getTotalOrderMoney(Order order) {
		int totalOrderMoney =0;
		for(Map.Entry<String, Integer> entry : order.getOrderMenu().entrySet()) {
			for(MenuEnumInterface menuItem : allMenuItems) {
				if(menuItem.getName().equals(entry.getKey())) totalOrderMoney += (menuItem.getValue() * entry.getValue());
			}
		}
		return totalOrderMoney;
	}
	
}
