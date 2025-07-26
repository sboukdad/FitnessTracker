package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * provides database access for Record objects
 */
public interface RecordRepository extends CrudRepository<Record, Long> {

    /**
     * Find by ID.
     */
    Record findById(long id);


    /**
     * Find by Route.
     */
    List<Record> findByRoute(String route);
}