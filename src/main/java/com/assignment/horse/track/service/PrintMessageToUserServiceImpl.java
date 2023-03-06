package com.assignment.horse.track.service;

import com.assignment.horse.track.model.Horse;
import com.assignment.horse.track.model.MoneyInventory;
import com.assignment.horse.track.repository.HorseRepository;
import com.assignment.horse.track.repository.MoneyInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * PrintMessageToUserServiceImpl can display messages to user
 *
 * @author Suganya Natarajan
 *
 */
@Service
public class PrintMessageToUserServiceImpl implements PrintMessageToUserService{
    /**
     * A string to display invalid command message to user
     */
    @Value("${message.display.error.invalid.command}")
    public  String invalidCommand;
    /**
     * A string to display inventory header to user
     */
    @Value("${message.display.header.inventory}")
    public String inventory;
    /**
     * A string to display horses header to user
     */
    @Value("${message.display.header.horses}")
    public String horses;
    /**
     * A string to display quit message to user
     */
    @Value("${message.display.error.quit}")
    public String quit;
    /**
     * A string to display no payout message to user
     */
    @Value("${message.display.error.no.payout}")
    public String noPayout;
    /**
     * A string to display insufficient funds message to user
     */
    @Value("${message.display.error.insufficient.funds}")
    public String insufficientFunds;
    /**
     * A string to display invalid horse number message to user
     */
    @Value("${message.display.error.invalid.horse.number}")
    public String invalidHorseNumber;
    /**
     * A string to display invalid bet message to user
     */
    @Value("${message.display.error.invalid.bet}")
    public String invalidBet;
    /**
     * A string to display payout message to user
     */
    @Value("${message.display.payout}")
    public String payout;
    /**
     * A string to display dispensing header message to user
     */
    @Value("${message.display.dispensing}")
    public String dispensing;
    /**
     * A string to display dollar symbol to user
     */
    public String DOLLAR = "$";
    /**
     * A string to display comma with space to user
     */
    public String COMMA_WITH_SPACE = ", " ;
    /**
     * A string to display comma without space to user
     */
    public String COMMA_WITHOUT_SPACE = "," ;

    @Autowired
    MoneyInventoryRepository moneyInventoryRepository;

    @Autowired
    HorseRepository horseRepository;

    /**
     *
     * Prints the messages to user
     *
     * @param messages
     *
     */
    @Override
    public void printMessages(String messages) {
        System.out.println(messages);

    }

    /**
     * Prints the moneyInventory details to the user
     */
    @Override
    public void displayMoneyInventory() {
        printMessages(inventory);
        List<MoneyInventory> moneyInventories = moneyInventoryRepository.findAll();
        moneyInventories.forEach(moneyInventory -> {
            printMessages(DOLLAR+ moneyInventory.getDenomination() + COMMA_WITH_SPACE + moneyInventory.getInventory());
        });
    }

    /**
     * Prints the horse details to the user
     */
    @Override
    public void displayHorses() {
        printMessages(horses);
        List<Horse> horseList = horseRepository.findAll();
        horseList.forEach(horse -> {
            printMessages(horse.getId()+COMMA_WITHOUT_SPACE+ horse.getHorseName()+COMMA_WITHOUT_SPACE+horse.getOdds()+COMMA_WITHOUT_SPACE+horse.getDidWon());
        });
    }
    /**
     * Prints the invalid command to the user
     */
    @Override
    public void printInvalidCommandMessages(String command) {
        printMessages(invalidCommand + command);
    }

    /**
     * Prints the quit message to the user
     */
    @Override
    public void printQuitAppMessages() {
        printMessages(quit);
    }

    /**
     * Prints the No Payout messages to the user
     * @param command the user entered command
     */
    @Override
    public void printNoPayoutMessages(String command) {
        printMessages(noPayout+command);
    }
    /**
     * Prints the insufficient fund messages to the user
     * @param command the user entered command
     */
    @Override
    public void printInsufficientFundsMessages(String command) {
        printMessages(insufficientFunds+DOLLAR+command);
    }
    /**
     * Prints the invalid horse number to the user
     * @param command the user entered command
     */
    @Override
    public void printInvalidHorseNumberMessages(String command) {
        printMessages(invalidHorseNumber+command);
    }
    /**
     * Prints the invalid bet messages to the user
     * @param command the user entered command
     */
    @Override
    public void printInvalidBetMessages(String command) {
        printMessages(invalidBet+command);
    }
    /**
     * Prints the amount dispensed to the user
     * @param denominator the denominator
     * @param remainingInventory the remaining number present
     */
    @Override
    public void printAmountMessages(int denominator, int remainingInventory) {
        printMessages(DOLLAR + denominator + COMMA_WITHOUT_SPACE + remainingInventory);
    }

    /**
     * Prints dispensing messages to the user
     */
    @Override
    public void printDispensingMessages() {
        printMessages(dispensing);
    }

    /**
     * Prints payout messages to the user
     * @param horseName the horse name
     * @param winingAmt the winning amount
     */
    @Override
    public void printPayoutMessages(String horseName, int winingAmt) {
        printMessages(payout + horseName + COMMA_WITHOUT_SPACE+ winingAmt);
    }
}
