package main;

import main.exception.InsufficientFundsException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination)) denominations.put(denomination, denominations.get(denomination) + count);
        else denominations.put(denomination, count);
    }

    public int getTotalAmount(){
        int amount = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()) {
            amount += pair.getKey() * pair.getValue();
        }
        return amount;
    }

    public int getTotalAmount(Map<Integer, Integer> map){
        int amount = 0;
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            amount += pair.getKey() * pair.getValue();
        }
        return amount;
    }


    public boolean hasMoney(){
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() - expectedAmount >= 0;
    }


    /**
     * This method returns the smallest number of banknotes matching passing amount
     */
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws InsufficientFundsException, ConcurrentModificationException {
        Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
        //Copy denominations map
        Map<Integer, Integer> copyMap = new HashMap<>(denominations);
        //Get denomination set - convert to sorted list
        List<Integer> denominationsList = new ArrayList<>(copyMap.keySet());
        denominationsList.sort(Collections.reverseOrder());
        //Create intermediateMap to store results inside the loop
        Map<Integer, Integer> intermediateMap;
        //Go through the list until amountRemaining hits zero
        do {
            //Reset state of copyMap
            copyMap = new HashMap<>(denominations);
            //If we do not have any options to cash out, we throw exception
            if (denominationsList.size() == 0) throw new InsufficientFundsException();
            intermediateMap = getCombination(denominationsList, copyMap, expectedAmount);
            //Remove first element
            denominationsList.remove(0);
            //Continue until intermediateMap sum equals expected amount
        }while(expectedAmount != getTotalAmount(intermediateMap));
        //Add our intermediateMap (which contains proper combination of banknotes) as a result
        //Replace denominations with copyMap, which is updated for taken banknotes
        result.putAll(intermediateMap);
        denominations = new HashMap<>(copyMap);

        return result;
    }

    /**
     * This method returns intermediate combination of banknotes
     */
    private Map<Integer, Integer> getCombination(List<Integer> denominationsList, Map<Integer, Integer> copyMap, int amountRemaining) {
        Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
        for (Integer denomination : denominationsList) {
            if (amountRemaining <= 0) break;

            //Take all matching bills
            while (amountRemaining > 0) {
                //Check if we have enough bills
                int billCount = copyMap.get(denomination);
                if (billCount == 0) break;
                //check if we can take given bill
                if (amountRemaining - denomination < 0) break;
                amountRemaining -= denomination;
                //Change number of bills
                copyMap.put(denomination, copyMap.get(denomination) - 1);
                //Add entry to the map
                if (result.containsKey(denomination)) result.put(denomination, result.get(denomination) + 1);
                else result.put(denomination, 1);
            }
        }
        return result;
    }
}
