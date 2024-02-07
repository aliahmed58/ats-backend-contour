package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.dto.StatusRecord;
import com.training.ats.models.Status;
import com.training.ats.repositories.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jdk.javadoc.doclet.Reporter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusService implements GenericServiceInterface<StatusRecord, Long> {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<StatusRecord> get() {
        return statusRepository.findAll()
                .stream()
                .map(status -> new StatusRecord(status.getStatusType()))
                .collect(Collectors.toList());
    }

    @Override
    public StatusRecord get(Long id) {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isEmpty())
            throw new EntityNotFoundException("Status entity not found");
        return new StatusRecord(status.get().getStatusType());
    }

    @Override
    public ResponseRecord delete(Long id) {
        statusRepository.deleteById(id);
        return new ResponseRecord(HttpStatus.OK.value(), "Status deleted");
    }

    @Override
    public ResponseRecord delete() {
        statusRepository.deleteAll();
        return new ResponseRecord(HttpStatus.OK.value(), "All status entities deleted");
    }

    @Override
    public ResponseRecord post(StatusRecord object, Long id) {
        statusRepository.save(
                Status.builder()
                        .statusId(id)
                        .statusType(object.statusType())
                        .build()
        );
        return new ResponseRecord(HttpStatus.OK.value(), "Status updated");
    }

    @Override
    public ResponseRecord put(StatusRecord object) {
        statusRepository.save(
                Status.builder()
                        .statusType(object.statusType())
                        .build()
        );
        return new ResponseRecord(HttpStatus.OK.value(), "Status saved");
    }
}
