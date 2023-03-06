package com.assignment.horse.track.service;
/**
 * PrintMessageToUserService can display messages to user
 *
 * @author Suganya Natarajan
 *
 */
public interface PrintMessageToUserService {
    /**
     *
     * Prints the messages to user
     *
     * @param messages
     *
     */
    void printMessages(String messages);
    /**
     * Prints the moneyInventory details to the user
     */
    void displayMoneyInventory();
    /**
     * Prints the horse details to the user
     */
    void displayHorses();
    /**
     * Prints the invalid command to the user
     */
    void printInvalidCommandMessages(String command);
    /**
     * Prints the quit message to the user
     */
    void printQuitAppMessages();
    /**
     * Prints the No Payout messages to the user
     * @param command the user entered command
     */
    void printNoPayoutMessages(String command);
    /**
     * Prints the insufficient fund messages to the user
     * @param command the user entered command
     */
    void printInsufficientFundsMessages(String command);
    /**
     * Prints the invalid horse number to the user
     * @param command the user entered command
     */
    void printInvalidHorseNumberMessages(String command);
    /**
     * Prints the invalid bet messages to the user
     * @param command the user entered command
     */
    void printInvalidBetMessages(String command);

    /**
     * Prints the amount dispensed to the user
     * @param denominator the denominator
     * @param remainingInventory the remaining number present
     */
    void printAmountMessages(int denominator, int remainingInventory);
    /**
     * Prints dispensing messages to the user
     */
    void printDispensingMessages();
    /**
     * Prints payout messages to the user
     * @param horseName the horse name
     * @param winingAmt the winning amount
     */
    void printPayoutMessages(String horseName, int winingAmt);
}
