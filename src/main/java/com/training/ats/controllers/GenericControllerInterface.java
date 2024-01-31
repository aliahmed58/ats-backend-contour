package com.training.ats.controllers;

import com.training.ats.dto.ResponseRecord;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A generic interface that all controllers will implement to keep consistent naming convention
 * @param <T> object
 * @param <K> key
 */
@RequestMapping("/default")
public interface GenericControllerInterface<T, K> {
    /**
     * get all records of a
     * @return list of all records
     */
    @GetMapping("/")
    ResponseEntity<List<T>> getAll();

    /**
     * get a record by id
     * @param id primary key id
     * @return object T
     */
    @GetMapping("/{id}")
    ResponseEntity<T> getById(@PathVariable  K id);

    /**
     * delete a record by id
     *
     * @param id key id
     * @return
     */
    @GetMapping("/delete/{id}")
    ResponseEntity<ResponseRecord> deleteById(K id);

    /**
     * delete all records
     *
     * @return
     */
    @GetMapping("/delete")
    ResponseEntity<ResponseRecord> deleteAll();

    /**
     * update a record given id
     *
     * @param id primary key
     * @return
     */
    @PostMapping("/update/{id}")
    ResponseEntity<ResponseRecord> update(@RequestBody @Valid T object, @PathVariable K id);

    /**
     * Save a given object into database
     *
     * @param object object to save
     * @return
     */
    @PostMapping("/save")
    ResponseEntity<ResponseRecord> save(@RequestBody @Valid T object);
}
