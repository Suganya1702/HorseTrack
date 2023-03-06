package com.assignment.horse.track.model;

import javax.persistence.*;

/**
 *
 * Represents an Horse
 * @author suganya.natarajan
 *
 */
@Entity
@Table(name="HORSE")
public class Horse {
    /**
     *
     * The Long representing id of horse details generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     *  The string representing horse name
     */
    private String horseName;
    /**
     *  The int representing horse number
     */
    private int horseNumber;
    /**
     *  The int representing odds of horse
     */
    private int odds;
    /**
     *  The String representing didwon status of horse
     */
    private String didWon;
    /**
     * Gets the {@link Long} instance representing id of the horse.
     * @return The {@link Long} instance representing id of the horse.
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the {@link Long} instance representing id of the horse.
     * @param id The {@link Long} instance representing id of the horse.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Gets the {@link String} instance representing name of the horse.
     * @return The {@link String} instance representing name of the horse.
     */
    public String getHorseName() {
        return horseName;
    }
    /**
     * Sets the {@link String} instance representing name of the horse.
     * @param horseName The {@link String} instance representing name of the horse.
     */
    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }
    /**
     * Gets the {@link int} instance representing odds of the horse.
     * @return The {@link int} instance representing odds of the horse.
     */
    public int getOdds() {
        return odds;
    }
    /**
     * Sets the {@link int} instance representing odds of the horse.
     * @param odds The {@link int} instance representing odds of the horse.
     */
    public void setOdds(int odds) {
        this.odds = odds;
    }
    /**
     * Gets the {@link String} instance representing winning status of the horse.
     * @return The {@link String} instance representing winning status of the horse.
     */
    public String getDidWon() {
        return didWon;
    }
    /**
     * Sets the {@link String} instance representing winning status of the horse.
     * @param didWon The {@link String} instance representing winning status of the horse.
     */
    public void setDidWon(String didWon) {
        this.didWon = didWon;
    }
    /**
     * Gets the {@link int} instance representing number of the horse.
     * @return The {@link int} instance representing number of the horse.
     */
    public int getHorseNumber() {
        return horseNumber;
    }
    /**
     * Sets the {@link int} instance representing number of the horse.
     * @param horseNumber The {@link int} instance representing number of the horse.
     */
    public void setHorseNumber(int horseNumber) {
        this.horseNumber = horseNumber;
    }

    /**
     * Parameterized constructor of horse. Sets the value to current object.
     * @param horseNumber the number of the horse
     * @param horseName the name of the horse
     * @param odds the odds of the horse
     * @param didWon the winning status of the horse
     */
    public Horse(int horseNumber, String horseName, int odds, String didWon) {
        this.horseNumber = horseNumber;
        this.horseName = horseName;
        this.odds = odds;
        this.didWon = didWon;
    }

    /**
     * Default constructor of horse
     */
    public Horse() {
    }
}
