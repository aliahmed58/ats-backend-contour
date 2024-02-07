package com.training.ats.controllers;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.GenericServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class BaseController<T, K> implements GenericControllerInterface<T, K>{

    protected GenericServiceInterface<T, K> service;
    @Override
    public ResponseEntity<List<T>> get() {
        return ResponseEntity.ok(service.get());
    }

    @Override
    public ResponseEntity<T> get(@PathVariable K id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> post(@RequestBody @Valid T object, @PathVariable K id) {
        return ResponseEntity.ok(service.post(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> put(@RequestBody @Valid T object) {
        return ResponseEntity.ok(service.put(object));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete(@PathVariable K id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete() {
        return ResponseEntity.ok(service.delete());
    }
}
