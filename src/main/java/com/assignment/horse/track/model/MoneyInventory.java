package com.assignment.horse.track.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * Represents an MoneyInventory
 * @author suganya.natarajan
 *
 */
@Entity
public class MoneyInventory {
    /**
     *
     * The Long representing id of money inventory details generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The int reprsenting denomination
     */
    private int denomination;
    /**
     * The int reprsenting inventory
     */
    private int inventory;
    /**
     * Gets the {@link int} instance representing denomination of the money inventory.
     * @return The {@link int} instance representing id denomination of the money inventory.
     */
    public int getDenomination() {
        return denomination;
    }
    /**
     * Sets the {@link int} instance representing denomination of the money inventory.
     * @param denomination The {@link int} instance representing denomination of the money inventory.
     */
    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }
    /**
     * Gets the {@link int} instance representing inventory of the money inventory.
     * @return The {@link int} instance representing inventory of the money inventory.
     */
    public int getInventory() {
        return inventory;
    }
    /**
     * Sets the {@link int} instance representing inventory of the money inventory.
     * @param inventory The {@link int} instance representing inventory of the money inventory.
     */
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    /**
     * Gets the {@link Long} instance representing id of the money inventory.
     * @return The {@link Long} instance representing id of the money inventory.
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the {@link Long} instance representing id of the money inventory.
     * @param id The {@link Long} instance representing id of the money inventory.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Parameterized constructor of Money Inventory. Sets the value to current object.
     * @param denomination the denomination in money inventory
     * @param inventory the inventory in money inventory
     */
    public MoneyInventory(int denomination, int inventory) {
        this.denomination = denomination;
        this.inventory = inventory;
    }
    /**
     * Default constructor of money inventory
     */
    public MoneyInventory() {
    }
}
