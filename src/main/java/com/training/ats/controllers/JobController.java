package com.training.ats.controllers;

import com.training.ats.dto.JobRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController extends BaseController<JobRecord, Long> {

    public JobController(JobService service) {
        this.service = service;
    }

    @Secured("ROLE_RECRUITER")
    @Override
    public ResponseEntity<ResponseRecord> post(JobRecord object, Long id) {
        return super.post(object, id);
    }
    
    @Secured("ROLE_RECRUITER")
    @Override
    public ResponseEntity<ResponseRecord> put(JobRecord object) {
        return super.put(object);
    }

    @Secured("ROLE_RECRUITER")
    @Override
    public ResponseEntity<ResponseRecord> delete(Long id) {
        return super.delete(id);
    }

    @Secured("ROLE_RECRUITER")
    @Override
    public ResponseEntity<ResponseRecord> delete() {
        return super.delete();
    }
}
