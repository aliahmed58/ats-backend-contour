package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;

import java.util.List;
public interface GenericServiceInterface<T, K> {
    /**
     * get all records of a
     * @return list of all records
     */
    List<T> get();

    /**
     * get a record by id
     * @param id primary key id
     * @return object T
     */
    T get(K id);

    /**
     * delete a record by id
     *
     * @param id key id
     * @return
     */
    ResponseRecord delete(K id);

    /**
     * delete all records
     *
     * @return
     */
    ResponseRecord delete();

    /**
     * update a record given id
     *
     * @param id primary key
     * @return
     */
    ResponseRecord post(T object, K id);

    /**
     * Save a given object into database
     *
     * @param object object to save
     * @return
     */
    ResponseRecord put(T object);
}
