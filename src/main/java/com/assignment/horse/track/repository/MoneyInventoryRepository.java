package com.assignment.horse.track.repository;

import com.assignment.horse.track.model.MoneyInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * MoneyInventoryRepository can be used to all CRUD operations on Horse Table
 * All the methods are inherited from JpaRepository
 * @author suganya.natarajan
 *
 */
@Repository
public interface MoneyInventoryRepository extends JpaRepository<MoneyInventory,Long> {
}
