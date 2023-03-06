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
        printMessageToUserService.printPayoutMessages(horseName,winingAmt);
        printMessageToUserService.printDispensingMessages();
        Set<Integer> keySet = new HashSet<>();
        for (MoneyInventory money : moneyInventories)
            keySet.add(money.getDenomination());
        Integer[] keyArray = keySet.toArray(new Integer[keySet.size()]);
        Arrays.sort(keyArray, Collections.reverseOrder());
        Integer[] count = {0,0,0,0,0,0};
        for(int i=0;i<keyArray.length ;i++)
        {
            if(winingAmt>=keyArray[i]) {
                count[i] = winingAmt / keyArray[i];
            }
            printMessageToUserService.printAmountMessages(keyArray[i],winingAmt / keyArray[i]);
            winingAmt=winingAmt%keyArray[i];
            moneyInventories.get(i).setInventory(moneyInventories.get(i).getInventory() - count[i] );
            moneyInventoryRepository.save(moneyInventories.get(i));
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
