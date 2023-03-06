package com.assignment.horse.track.service;

import com.assignment.horse.track.model.Horse;
import com.assignment.horse.track.model.MoneyInventory;

/**
 * DataLoaderService can store the value in horse and money inventory details in DB
 *
 * @author Suganya Natarajan
 *
 */
public interface DataLoaderService {
    /**
     *
     * saves the horse details in DB
     *
     * @param horse the horse details
     * @return the horse details
     */
    Horse saveHorse(Horse horse);
    /**
     *
     * saves the money inventory in db
     *
     * @param moneyInventory the moneyInventory details
     * @return the moneyInventory details
     */
    MoneyInventory saveMoneyInventory(MoneyInventory moneyInventory);

    /**
     *
     * Initialize horse and moneyInventory details in DB
     *
     */
    void initializeDataInDB();
    /**
     *
     * initialize horse details in DB
     *
     */
    void intializeHorseInDB();
    /**
     *
     * initialize money inventory details in DB
     *
     */
    void intializeMoneyInventoryInDB();
}
