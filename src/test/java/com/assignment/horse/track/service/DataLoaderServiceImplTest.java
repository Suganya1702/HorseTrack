package com.assignment.horse.track.service;

import com.assignment.horse.track.model.Horse;
import com.assignment.horse.track.model.MoneyInventory;
import com.assignment.horse.track.repository.HorseRepository;
import com.assignment.horse.track.repository.MoneyInventoryRepository;
import com.fasterxml.jackson.core.JsonParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataLoaderServiceImplTest {
    @InjectMocks
    DataLoaderServiceImpl dataLoaderService;

    @Mock
    HorseRepository horseRepository;

    @Mock
    MoneyInventoryRepository moneyInventoryRepository;

    @Test
    public void saveHorse_ReturnsHorse(){
        Horse horse = new Horse(1,"horse name",55,"won");
        Mockito.when(horseRepository.save(Mockito.any())).thenReturn(horse);
        Horse expectedHorse = dataLoaderService.saveHorse(horse);
        Assert.assertEquals(horse.getHorseName(),expectedHorse.getHorseName());
        Assert.assertEquals(horse.getOdds(),expectedHorse.getOdds());
        Assert.assertEquals(horse.getDidWon(),expectedHorse.getDidWon());
        Assert.assertEquals(horse.getHorseNumber(),expectedHorse.getHorseNumber());
    }

    @Test
    public void saveMoneyInventory_ReturnMoneyInventory(){
        MoneyInventory moneyInventory = new MoneyInventory(100,10);
        Mockito.when(moneyInventoryRepository.save(Mockito.any())).thenReturn(moneyInventory);
        MoneyInventory expectedMoneyInventory = dataLoaderService.saveMoneyInventory(moneyInventory);
        Assert.assertEquals(moneyInventory.getInventory(),expectedMoneyInventory.getInventory());
        Assert.assertEquals(moneyInventory.getDenomination(),expectedMoneyInventory.getDenomination());

    }

    @Test
    public void initializeDataInDB_WheninitializeDataInDBCalled_InvokesSaveMethods(){
        Horse horse = new Horse(1,"horse name",55,"won");
        MoneyInventory moneyInventory = new MoneyInventory(100,10);

        Mockito.when(moneyInventoryRepository.save(Mockito.any())).thenReturn(moneyInventory).thenReturn(moneyInventory).thenReturn(moneyInventory).thenReturn(moneyInventory).thenReturn(moneyInventory).thenReturn(moneyInventory);
        Mockito.when(horseRepository.save(Mockito.any())).thenReturn(horse).thenReturn(horse).thenReturn(horse).thenReturn(horse).thenReturn(horse).thenReturn(horse).thenReturn(horse).thenReturn(horse);

        dataLoaderService.initializeDataInDB();
        Mockito.verify(moneyInventoryRepository,Mockito.times(5)).save(Mockito.any());
        Mockito.verify(horseRepository,Mockito.times(7)).save(Mockito.any());
    }



}
