package com.assignment.horse.track.service;

import com.assignment.horse.track.model.MoneyInventory;
import com.assignment.horse.track.repository.MoneyInventoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class MoneyInventoryServiceTest {

    @InjectMocks
    MoneyInventoryServiceImpl moneyInventoryService;

    @Mock
    MoneyInventoryRepository moneyInventoryRepository;
    @Mock
    PrintMessageToUserService printMessageToUserService;
    @Test
    public void testCheckAvailableMoneyInInventory_ReturnTrue(){
        List<MoneyInventory> moneyInventoryList = new ArrayList<>();
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(20,10));
        moneyInventoryList.add(new MoneyInventory(10,10));
        moneyInventoryList.add(new MoneyInventory(5,10));
        moneyInventoryList.add(new MoneyInventory(1,10));
        when(moneyInventoryRepository.findAll()).thenReturn(moneyInventoryList);
        Assert.assertTrue(moneyInventoryService.checkAvailableMoneyInInventory(2750));
    }

    @Test
    public void checkAvailableMoneyInInventory_WhenWiningAmtIsNotAvailable_ReturnTrue(){
        List<MoneyInventory> moneyInventoryList = new ArrayList<>();
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(20,10));
        moneyInventoryList.add(new MoneyInventory(10,10));
        moneyInventoryList.add(new MoneyInventory(5,10));
        moneyInventoryList.add(new MoneyInventory(1,10));
        when(moneyInventoryRepository.findAll()).thenReturn(moneyInventoryList);
        Assert.assertTrue(moneyInventoryService.checkAvailableMoneyInInventory(2750));
    }

    @Test
    public void checkAvailableMoneyInInventory_WhenWiningAmtIsAvailable_ReturnFalse(){
        List<MoneyInventory> moneyInventoryList = new ArrayList<>();
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(20,10));
        moneyInventoryList.add(new MoneyInventory(10,10));
        moneyInventoryList.add(new MoneyInventory(5,10));
        moneyInventoryList.add(new MoneyInventory(1,10));
        when(moneyInventoryRepository.findAll()).thenReturn(moneyInventoryList);
        Assert.assertFalse(moneyInventoryService.checkAvailableMoneyInInventory(275));
    }

    @Test
    public void restockMoneyInventory_ShouldRestoreToInitializeValues(){
        List<MoneyInventory> actualMoneyInventoryList = new ArrayList<>();
        actualMoneyInventoryList.add(new MoneyInventory(100,10));
        actualMoneyInventoryList.add(new MoneyInventory(100,10));
        actualMoneyInventoryList.add(new MoneyInventory(20,10));
        actualMoneyInventoryList.add(new MoneyInventory(10,10));
        actualMoneyInventoryList.add(new MoneyInventory(5,10));
        actualMoneyInventoryList.add(new MoneyInventory(1,10));
        List<MoneyInventory> moneyInventoryList = new ArrayList<>();
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(20,7));
        moneyInventoryList.add(new MoneyInventory(10,10));
        moneyInventoryList.add(new MoneyInventory(5,10));
        moneyInventoryList.add(new MoneyInventory(1,10));
        when(moneyInventoryRepository.findAll()).thenReturn(moneyInventoryList);
        moneyInventoryService.restockMoneyInventory();
        Assert.assertEquals(actualMoneyInventoryList.get(2).getInventory(),moneyInventoryService.restockMoneyInventory().get(2).getInventory());
    }
    @Test
    public void cashDispenser_WhenWinningAmtPresentInInventory_ReturnCash(){
        List<MoneyInventory> actualMoneyInventoryList = new ArrayList<>();
        actualMoneyInventoryList.add(new MoneyInventory(100,10));
        actualMoneyInventoryList.add(new MoneyInventory(20,10));
        actualMoneyInventoryList.add(new MoneyInventory(10,10));
        actualMoneyInventoryList.add(new MoneyInventory(5,10));
        actualMoneyInventoryList.add(new MoneyInventory(1,10));
        when(moneyInventoryRepository.findAll()).thenReturn(actualMoneyInventoryList);
        List<MoneyInventory> expectedMoneyInventories = moneyInventoryService.cashDispenser(275,"Horse Name");
        Assert.assertEquals(8,expectedMoneyInventories.get(0).getInventory());
        Assert.assertEquals(7,expectedMoneyInventories.get(1).getInventory());
        Assert.assertEquals(9,expectedMoneyInventories.get(2).getInventory());
        Assert.assertEquals(9,expectedMoneyInventories.get(3).getInventory());
        Assert.assertEquals(10,expectedMoneyInventories.get(4).getInventory());
        verify(printMessageToUserService, Mockito.times(1)).printPayoutMessages(Mockito.anyString(),Mockito.anyInt());
        verify(printMessageToUserService, Mockito.times(1)).printDispensingMessages();
        verify(printMessageToUserService, Mockito.times(5)).printAmountMessages(Mockito.anyInt(),Mockito.anyInt());
    }
}
