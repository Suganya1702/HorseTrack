package com.assignment.horse.track.service;

import com.assignment.horse.track.model.MoneyInventory;
import com.assignment.horse.track.repository.MoneyInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * MoneyInventoryServiceImpl can do CRUD operations and other validations on Money Inventory details in DB
 *
 * @author Suganya Natarajan
 *
 */
@Service
public class MoneyInventoryServiceImpl implements MoneyInventoryService{

    @Autowired
    PrintMessageToUserService printMessageToUserService;

    @Autowired
    MoneyInventoryRepository moneyInventoryRepository;

    /**
     * Checks if winning amount is greater than amount inventory
     * @param winingAmt the winning anount
     * @return true if winning amount is more
     */
    @Override
    public boolean checkAvailableMoneyInInventory(int winingAmt) {
        List<MoneyInventory> moneyInventories = fetchAllMoneyInventoryList();
        int amountInInventory = moneyInventories.stream().mapToInt(money->money.getInventory() * money.getDenomination()).sum();
        return winingAmt > amountInInventory;
    }

    /**
     * Dispense cash to the user
     *
     * @param winingAmt the winning amount
     * @param horseName the horse name
     * @return the list of money inventories
     */
    @Override
    public List<MoneyInventory> cashDispenser(int winingAmt, String horseName) {
        List<MoneyInventory> moneyInventories = fetchAllMoneyInventoryList();
        int actualWiningAmt = winingAmt;
        Set<Integer> keySet = new HashSet<>();
        for (MoneyInventory money : moneyInventories)
            keySet.add(money.getDenomination());
        Integer[] keyArray = keySet.toArray(new Integer[keySet.size()]);
        Arrays.sort(keyArray, Collections.reverseOrder());
        Integer[] count = {0,0,0,0,0,0};
        boolean isWiningAmtRemaining = false;
        for(int i=0;i<keyArray.length ;i++)
        {
            if(winingAmt>=keyArray[i]) {
                count[i] = winingAmt / keyArray[i];
                if(moneyInventories.get(i).getInventory() ==0) {
                    count[i] =  0;
                }
                if(count[i] > moneyInventories.get(i).getInventory() && moneyInventories.get(i).getInventory() !=0) {
                    count[i] =  moneyInventories.get(i).getInventory();
                }
            }
            winingAmt = winingAmt - count[i] * keyArray[i];
            if(i==keyArray.length-1 && winingAmt!=0 ){
                isWiningAmtRemaining = true;
            }
        }
        if(isWiningAmtRemaining){
            printMessageToUserService.printMessages("Insufficient Denomination in Inventory: $"+actualWiningAmt);
        }else {
            printMessageToUserService.printPayoutMessages(horseName,actualWiningAmt);
            printMessageToUserService.printDispensingMessages();
            for (int j = 0; j < keyArray.length; j++) {
                printMessageToUserService.printAmountMessages(keyArray[j], count[j]);
                moneyInventories.get(j).setInventory(moneyInventories.get(j).getInventory() - count[j]);
                moneyInventoryRepository.save(moneyInventories.get(j));
            }
        }
        return moneyInventories;
    }
    /**
     * Restockes the money inventory
     *
     * @return the list of money inventory
     */
    @Override
    public List<MoneyInventory> restockMoneyInventory() {
        List<MoneyInventory> moneyInventories = fetchAllMoneyInventoryList();
        moneyInventories.forEach(moneyInventory -> {
            moneyInventory.setInventory(10);
            moneyInventoryRepository.save(moneyInventory);
        });

        return  moneyInventories;
    }

    /**
     * Fetch the money inventory details from DB
     * @return the list of money inventory
     */
    @Override
    public List<MoneyInventory> fetchAllMoneyInventoryList() {
        return moneyInventoryRepository.findAll();
    }
}
