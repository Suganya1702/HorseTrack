package com.assignment.horse.track.service;

import com.assignment.horse.track.model.Horse;
import com.assignment.horse.track.model.MoneyInventory;
import com.assignment.horse.track.repository.HorseRepository;
import com.assignment.horse.track.repository.MoneyInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DataLoaderServiceImpl can store the value in horse and money inventory details in DB
 *
 * @author Suganya Natarajan
 *
 */
@Service
public class DataLoaderServiceImpl implements DataLoaderService{
    @Autowired
    HorseRepository horseRepository;

    @Autowired
    MoneyInventoryRepository moneyInventoryRepository;

    /**
     *
     * saves the horse details in DB
     *
     * @param horse the horse details
     * @return the horse details
     */
    @Override
    public Horse saveHorse(Horse horse) {
        return horseRepository.save(horse);
    }
    /**
     *
     * saves the money inventory in db
     *
     * @param moneyInventory the moneyInventory details
     * @return the moneyInventory details
     */
    @Override
    public MoneyInventory saveMoneyInventory(MoneyInventory moneyInventory) {
        return moneyInventoryRepository.save(moneyInventory);
    }
    /**
     *
     * Initialize horse and moneyInventory details in DB
     *
     */
    @Override
    public void initializeDataInDB() {

        intializeHorseInDB();
        intializeMoneyInventoryInDB();

    }
    /**
     *
     * initialize horse details in DB
     *
     */
    @Override
    public void intializeHorseInDB(){
        horseRepository.save(new Horse(1,"That darn gray cat",5,"won"));
        horseRepository.save(new Horse(2,"Fort Utopia",10,"lost"));
        horseRepository.save(new Horse(3,"Count Sheep",9,"lost"));
        horseRepository.save(new Horse(4,"Ms Traitour",4,"lost"));
        horseRepository.save(new Horse(5,"Real Princess",3,"lost"));
        horseRepository.save(new Horse(6,"Pa Kettle",5,"lost"));
        horseRepository.save(new Horse(7,"Gin Stinger",6,"lost"));


    }
    /**
     *
     * initialize money inventory details in DB
     *
     */
    @Override
    public void intializeMoneyInventoryInDB(){
        moneyInventoryRepository.save(new MoneyInventory(100,10));
        moneyInventoryRepository.save(new MoneyInventory(20,10));
        moneyInventoryRepository.save(new MoneyInventory(10,10));
        moneyInventoryRepository.save(new MoneyInventory(5,10));
        moneyInventoryRepository.save(new MoneyInventory(1,10));
    }




}
