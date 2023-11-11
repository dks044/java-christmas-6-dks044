package christmas.util;

import java.util.HashMap;
import java.util.Map;

public class Util {
	
	private static final Map<String, String> APPETIZER 
	= Map.of("양송이수프", "MUSHROOM_SOUP","타파스","TAPAS","시저샐러드","CAESAR_SALAD");
	
	private static final Map<String,String> BEVERAGE 
	= Map.of("제로콜라","ZERO_COLA","레드와인","RED_WINE","샴페인","CHAMPAGNE");
	
	private static final Map<String,String> DESSERT
	= Map.of("초코케이크","CHOCOLATE_CAKE","아이스크림","ICE_CREAM");
	
	private static final Map<String,String> MAIN
	= Map.of("티본스테이크","T_BONE_STEAK","바비큐립","BBQ_RIBS","해산물파스타","SEAFOOD_PASTA",
			"크리스마스파스타","CHRISTMAS_PASTA");
	
	public static Map<String,String> getTotalMenu(){
		Map<String,String> totalMenu = new HashMap<>();
		totalMenu.putAll(APPETIZER);
		totalMenu.putAll(BEVERAGE);
		totalMenu.putAll(DESSERT);
		totalMenu.putAll(MAIN);
		return totalMenu;
	}
	
	public static String parseInputDataToEngName(String inputData) {
		for(Map.Entry<String, String> entry : getTotalMenu().entrySet()) {
			if(inputData.contains(entry.getKey())) {
				inputData = inputData.replaceAll(entry.getKey(), entry.getValue());
			}
		}
		return inputData;
	}
}
