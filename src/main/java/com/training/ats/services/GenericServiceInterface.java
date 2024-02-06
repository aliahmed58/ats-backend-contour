package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
import jakarta.transaction.Transactional;

import java.util.List;
public interface GenericServiceInterface<T, K> {
    /**
     * get all records of a
     * @return list of all records
     */
    List<T> getAll();

    /**
     * get a record by id
     * @param id primary key id
     * @return object T
     */
    T getById(K id);

    /**
     * delete a record by id
     *
     * @param id key id
     * @return
     */
    ResponseRecord deleteById(K id);

    /**
     * delete all records
     *
     * @return
     */
    ResponseRecord deleteAll();

    /**
     * update a record given id
     *
     * @param id primary key
     * @return
     */
    ResponseRecord update(T object, K id);

    /**
     * Save a given object into database
     *
     * @param object object to save
     * @return
     */
    ResponseRecord save(T object);
}
