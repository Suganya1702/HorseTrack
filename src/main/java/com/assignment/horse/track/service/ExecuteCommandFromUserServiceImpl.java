package com.assignment.horse.track.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Pattern;
/**
 * ExecuteCommandFromUserServiceImpl can execute command based on the user input
 *
 * @author Suganya Natarajan
 *
 */
@Service
public class ExecuteCommandFromUserServiceImpl implements ExecuteCommandFromUserService{

    @Autowired
    PrintMessageToUserService printMessageToUserService;

    @Autowired
    MoneyInventoryService moneyInventoryService;

    @Autowired
    HorseService horseService;

    public static final String ALPHA_NUMERIC_REGEX_PATTERN = "[a-zA-Z0-9]";
    public static final String NUMERIC_REGEX_PATTERN = "\\d+";
    public static final String FLOAT_NUMERIC_REGEX_PATTERN = "\\d+.?\\d";

    /**
     *
     * Executes command based on the user input
     *
     * @param command the user input
     */

    @Override
    public void executeCommand(String command) {
        String[] cmd = command.split(" ");
        if(isMultiCommandEntered(cmd)){
            processMultiCommand(cmd);
        }else if(isSingleCommandEntered(cmd)){
            processSingleCommand(cmd);
        } else{
            printMessageToUserService.printInvalidCommandMessages(command);
        }
        printMessageToUserService.displayMoneyInventory();
        printMessageToUserService.displayHorses();
    }

    /**
     *
     * Restocks money inventory and quits the application based on command
     *
     * @param cmd the user entered command
     */
    private void processSingleCommand(String[] cmd) {
        if(Arrays.asList("R","r").contains(cmd[0])){
            moneyInventoryService.restockMoneyInventory();
        } else if (Arrays.asList("Q","q").contains(cmd[0])){
            printMessageToUserService.printQuitAppMessages();
            System.exit(0);
        }
    }

    /**
     *
     * checks single character command entered from user
     *
     * @param cmd the user inpt
     * @return true when single character command entered.
     *
     */
    private boolean isSingleCommandEntered(String[] cmd) {
       return cmd.length == 1 && Pattern.matches(ALPHA_NUMERIC_REGEX_PATTERN,cmd[0]);
    }

    /**
     *
     * Update winning horse, dispensing cash and validates horse number and bet number
     *
     * @param cmd the user entered command
     *
     */
    private void processMultiCommand(String[] cmd) {
        if (Arrays.asList("W","w").contains(cmd[0])){
            horseService.updateHorseDidWin(Integer.parseInt(cmd[1]) - 1);
        } else {
            int firstCommand = Integer.parseInt(cmd[0]);
            if(isFloatNumericPresent(cmd[1])){
                printMessageToUserService.printInvalidBetMessages(cmd[1]);
            }else if (horseService.isValidHorseNumberEntered(firstCommand) && horseService.didHorseWon(firstCommand)) {
                int secondCommand = Integer.parseInt(cmd[1]);
                cashDispense(firstCommand,secondCommand);
            }else{
                if (!horseService.isValidHorseNumberEntered(firstCommand)) {
                    printMessageToUserService.printInvalidHorseNumberMessages(String.valueOf(firstCommand));
                }else {
                    printMessageToUserService.printNoPayoutMessages(horseService.getHorseName(firstCommand - 1));
                }
            }
        }
    }

    /**
     *
     * Dispense the cash to the user if available in money inventory
     *
     * @param firstCommand the horse number
     * @param secondCommand the bet number
     *
     */
    private void cashDispense(int firstCommand,int secondCommand) {
        int winingAmt = horseService.getWinningAmount(firstCommand,secondCommand);
        if(moneyInventoryService.checkAvailableMoneyInInventory(winingAmt)) {
            printMessageToUserService.printInsufficientFundsMessages(String.valueOf(winingAmt));
        }else{
            moneyInventoryService.cashDispenser(winingAmt, horseService.getHorseName(firstCommand - 1));
        }
    }

    /**
     *
     *  checks floating number present
     *
     * @param cmd the user entered command
     * @return true if float number is entered.
     *
     */
    private boolean isFloatNumericPresent(String cmd) {
      return   Float.parseFloat(cmd) % 1 != 0.0;
    }

    /**
     * checks multiple command entered by user like "w 4", "1 55"
     *
     * @param cmd the user entered command
     * @return true if multiple command entered.
     *
     */
    private boolean isMultiCommandEntered(String[] cmd){
       return cmd.length == 2 && (Pattern.matches(ALPHA_NUMERIC_REGEX_PATTERN,cmd[0]) || Pattern.matches(NUMERIC_REGEX_PATTERN,cmd[0])) && (Pattern.matches(NUMERIC_REGEX_PATTERN,cmd[1]) || Pattern.matches(FLOAT_NUMERIC_REGEX_PATTERN,cmd[1]));
    }

}