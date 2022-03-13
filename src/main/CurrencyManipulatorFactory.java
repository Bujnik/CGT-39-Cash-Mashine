package main;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        String currencyCodeUpperCase = currencyCode.toUpperCase(Locale.ROOT);
        if (!map.containsKey(currencyCodeUpperCase)) {
            map.put(currencyCodeUpperCase, new CurrencyManipulator(currencyCodeUpperCase));
        }
        return map.get(currencyCodeUpperCase);
    }
}
