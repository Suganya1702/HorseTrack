package com.assignment.horse.track.service;

import com.assignment.horse.track.model.Horse;
import com.assignment.horse.track.model.MoneyInventory;
import com.assignment.horse.track.repository.HorseRepository;
import com.assignment.horse.track.repository.MoneyInventoryRepository;
import org.junit.Assert;
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
import org.springframework.boot.json.JsonParser;

import java.io.IOException;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExecuteCommandFromUserServiceImplTest {
    @InjectMocks
    ExecuteCommandFromUserServiceImpl executeCommandFromUserService ;

    @Mock
    MoneyInventoryServiceImpl moneyInventoryService;

    @Mock
    HorseServiceImpl horseService;

    @Mock
    PrintMessageToUserService printMessageToUserService;

    @Mock
    HorseRepository horseRepository;

    @Mock
    MoneyInventoryRepository moneyInventoryRepository;

    @BeforeEach
    void setUp() {
        System.setSecurityManager(new NoExitSecurityManager());
    }

    @Test
    public void processSingleCommand_WhenRestockEntered_RestoreToDefaultValue(){
        String[] cmd = {"R"};
        List<MoneyInventory> moneyInventoryList = new ArrayList<>();
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(100,10));
        moneyInventoryList.add(new MoneyInventory(20,7));
        moneyInventoryList.add(new MoneyInventory(10,10));
        moneyInventoryList.add(new MoneyInventory(5,10));
        moneyInventoryList.add(new MoneyInventory(1,10));
        Mockito.when(moneyInventoryService.restockMoneyInventory()).thenReturn(moneyInventoryList);
        executeCommandFromUserService.executeCommand("R");
        Mockito.verify(moneyInventoryService,Mockito.times(1)).restockMoneyInventory();
        Mockito.verify(printMessageToUserService,Mockito.times(1)).displayHorses();
        Mockito.verify(printMessageToUserService,Mockito.times(1)).displayMoneyInventory();
    }
    @Test
    public void processSingleCommand_WhenQuitsEntered_InvokesPrintQuitAndSystemExitMethod(){
        try{
            executeCommandFromUserService.executeCommand("Q");
            Mockito.verify(printMessageToUserService,Mockito.times(1)).printQuitAppMessages();

        }catch (Exception e) {
            Assert.assertEquals("0", e.getMessage());
        }
    }

    @Test
    public void processMultipleCommand_WhenWinnerEntered_UpdateHorseWonInvoked(){
        Mockito.when(horseService.updateHorseDidWin(Mockito.anyInt())).thenReturn(new Horse(4,"Ms Traitour",4,"won"));
        executeCommandFromUserService.executeCommand("w 4");
        Mockito.verify(horseService,Mockito.times(1)).updateHorseDidWin(Mockito.anyInt());
        Mockito.verify(printMessageToUserService,Mockito.times(1)).displayHorses();
        Mockito.verify(printMessageToUserService,Mockito.times(1)).displayMoneyInventory();
    }

    @Test
    public void processMultipleCommand_WhenInvalidBetIsEntered_PrintsInvalidBetErrorMsg(){
        executeCommandFromUserService.executeCommand("1 5.5");
        Mockito.verify(printMessageToUserService,Mockito.times(1)).printInvalidBetMessages(Mockito.anyString());
        Mockito.verify(printMessageToUserService,Mockito.times(1)).displayHorses();
        Mockito.verify(printMessageToUserService,Mockito.times(1)).displayMoneyInventory();
    }

    @Test
    public void processMultipleCommand_WhenValidBetIsEntered_InvokesCashDispenserMethod(){
        when(horseService.isValidHorseNumberEntered(Mockito.anyInt())).thenReturn(true);
        when(horseService.didHorseWon(Mockito.anyInt())).thenReturn(true);
        when(horseService.getWinningAmount(Mockito.anyInt(),Mockito.anyInt())).thenReturn(275);
        when(moneyInventoryService.checkAvailableMoneyInInventory(Mockito.anyInt())).thenReturn(false);
        when(horseService.getHorseName(Mockito.anyInt())).thenReturn("Horse Name");
        executeCommandFromUserService.executeCommand("1 55");
        Mockito.verify(moneyInventoryService,Mockito.times(1)).cashDispenser(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void processMultipleCommand_WhenInsufficientCashAvailable_InvokesPrintInsufficientFundsMessages(){
        when(horseService.isValidHorseNumberEntered(Mockito.anyInt())).thenReturn(true);
        when(horseService.didHorseWon(Mockito.anyInt())).thenReturn(true);
        when(horseService.getWinningAmount(Mockito.anyInt(),Mockito.anyInt())).thenReturn(275);
        when(moneyInventoryService.checkAvailableMoneyInInventory(Mockito.anyInt())).thenReturn(true);
        executeCommandFromUserService.executeCommand("1 55");
        Mockito.verify(printMessageToUserService,Mockito.times(1)).printInsufficientFundsMessages(Mockito.anyString());
    }

    @Test
    public void processMultipleCommand_WhenInValidHorseNumEntered_InvokesPrintInvalidHorseNumberMessages(){
        when(horseService.isValidHorseNumberEntered(Mockito.anyInt())).thenReturn(false);
        executeCommandFromUserService.executeCommand("1 55");
        Mockito.verify(printMessageToUserService,Mockito.times(1)).printInvalidHorseNumberMessages(Mockito.anyString());
    }

    @Test
    public void processMultipleCommand_WhenLostHorseNumEntered_InvokesPrintNoPayoutMessages(){
        when(horseService.isValidHorseNumberEntered(Mockito.anyInt())).thenReturn(true);
        when(horseService.didHorseWon(Mockito.anyInt())).thenReturn(false);
        when(horseService.getHorseName(Mockito.anyInt())).thenReturn("Horse Name");
        executeCommandFromUserService.executeCommand("1 55");
        Mockito.verify(printMessageToUserService,Mockito.times(1)).printNoPayoutMessages(Mockito.anyString());
    }

    @Test
    public void executeCommand_WhenInvalidCommandEntered_InvokesPrintInvalidCommandMessages(){
        executeCommandFromUserService.executeCommand("wwww 5iii5");
        Mockito.verify(printMessageToUserService,Mockito.times(1)).printInvalidCommandMessages(Mockito.anyString());
    }

}
