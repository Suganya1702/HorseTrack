package com.assignment.horse.track.service;

import com.assignment.horse.track.model.MoneyInventory;

import java.util.List;

public interface MoneyInventoryService {
    /**
     * Checks if winning amount is greater than amount inventory
     * @param winingAmt the winning anount
     * @return true if winning amount is more
     */
    boolean checkAvailableMoneyInInventory(int winingAmt);
    /**
     * Dispense cash to the user
     *
     * @param winingAmt the winning amount
     * @param horseName the horse name
     * @return the list of money inventories
     */
    List<MoneyInventory> cashDispenser(int winingAmt, String horseName);
    /**
     * Restockes the money inventory
     *
     * @return the list of money inventory
     */
    List<MoneyInventory> restockMoneyInventory();
    /**
     * Fetch the money inventory details from DB
     * @return the list of money inventory
     */
    List<MoneyInventory> fetchAllMoneyInventoryList();
}
