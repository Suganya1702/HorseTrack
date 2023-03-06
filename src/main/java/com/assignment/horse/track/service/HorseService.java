package com.assignment.horse.track.service;

import com.assignment.horse.track.model.Horse;

import java.util.List;
/**
 * HorseService can do CRUD operations and other validations on Horse details in DB
 *
 * @author Suganya Natarajan
 *
 */
public interface HorseService {
    /**
     *
     * updates winning status to specified index number
     *
     * @param index the horse number
     * @return the horse object
     *
     */
    public Horse updateHorseDidWin(int index);
    /**
     *
     * fetch all the horse details from DB
     *
     * @return the list of horse object
     *
     */
    List<Horse> fetchAllHorseList();
    /**
     * checks if user entered horse number is present
     *
     * @param firstCommand the horse number
     * @return true if horse number is present
     *
     */
    boolean isValidHorseNumberEntered(int firstCommand);
    /**
     * checks if horse won the race
     *
     * @param firstCommand the horse index number
     * @return true if horse didwon status is won
     */
    boolean didHorseWon(int firstCommand);
    /**
     * Gives the winning amount
     *
     * @param odds the horse number
     * @param betNumber the bet number
     *
     * @return the winning amount
     */
    int getWinningAmount(int odds, int betNumber);
    /**
     *
     * Gets the horsename based on index passed
     *
     * @param index the horse position in list
     * @return the horse name
     */
    String getHorseName(int index);
}
