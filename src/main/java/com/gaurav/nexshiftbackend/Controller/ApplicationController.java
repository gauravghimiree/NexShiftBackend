package com.gaurav.nexshiftbackend.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaurav.nexshiftbackend.Model.ApplicationDTO;
import com.gaurav.nexshiftbackend.Model.JobApplicationReturnDTO;
import com.gaurav.nexshiftbackend.Service.ApplicationService;
import com.gaurav.nexshiftbackend.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;
    @Autowired
    private JobService jobService;

    @PostMapping(value = "/apply/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String apply(@PathVariable int id,
                        @RequestPart("application") String dtoJson,
                        @RequestPart("file") MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ApplicationDTO dto = objectMapper.readValue(dtoJson, ApplicationDTO.class);
        return applicationService.applyToJob(id, dto, file);
    }
    @GetMapping("/jobapplication/{id}")
    public List<JobApplicationReturnDTO> getJobApplication(@PathVariable long id) throws IOException {
       return jobService.getAllJobApplicationById(id);
    }
    @PostMapping("/cv/download")
    public String download(@RequestParam("cv") String url) throws Exception {
        ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
     return    jobService.downloadCv(url);
    }
}
