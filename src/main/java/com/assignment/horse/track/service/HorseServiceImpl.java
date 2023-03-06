package com.assignment.horse.track.service;

import com.assignment.horse.track.model.Horse;
import com.assignment.horse.track.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HorseServiceImpl can do CRUD operations and other validations on Horse details in DB
 *
 * @author Suganya Natarajan
 *
 */
@Service
public class HorseServiceImpl implements  HorseService{

    @Autowired
    HorseRepository horseRepository;

    @Autowired
    PrintMessageToUserService printMessageToUserService;

    /**
     *
     * updates winning status to specified index number
     *
     * @param index the horse number
     * @return the horse object
     *
     */
    @Override
    public Horse updateHorseDidWin(int index) {
        List<Horse> horseList = fetchAllHorseList();
        horseList.get(index).setDidWon("won");
        return horseRepository.save(horseList.get(index));
    }

    /**
     *
     * fetch all the horse details from DB
     *
     * @return the list of horse object
     *
     */
    @Override
    public List<Horse> fetchAllHorseList() {
        return horseRepository.findAll();
    }

    /**
     * checks if user entered horse number is present
     *
     * @param firstCommand the horse number
     * @return true if horse number is present
     *
     */
    @Override
    public boolean isValidHorseNumberEntered(int firstCommand) {
        List<Horse> horseList = fetchAllHorseList();
        return horseList.stream().anyMatch( horse -> horse.getHorseNumber() == firstCommand);
    }

    /**
     * checks if horse won the race
     *
     * @param firstCommand the horse index number
     * @return true if horse didwon status is won
     */
    @Override
    public boolean didHorseWon(int firstCommand) {
        List<Horse> horseList = fetchAllHorseList();
        return horseList.get(firstCommand-1).getDidWon().equals("won");
    }

    /**
     * Gives the winning amount
     *
     * @param odds the horse number
     * @param betNumber the bet number
     *
     * @return the winning amount
     */
    @Override
    public int getWinningAmount(int odds, int betNumber) {
        List<Horse> horseList = fetchAllHorseList();
        return horseList.get(odds - 1).getOdds() * betNumber;
    }

    /**
     *
     * Gets the horsename based on index passed
     *
     * @param index the horse position in list
     * @return the horse name
     */
    @Override
    public String getHorseName(int index) {
        List<Horse> horseList = fetchAllHorseList();
        return horseList.get(index).getHorseName();
    }


}
