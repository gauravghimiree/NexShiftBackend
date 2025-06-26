package com.gaurav.nexshiftbackend.Controller;


import com.gaurav.nexshiftbackend.Model.Job;
import com.gaurav.nexshiftbackend.Model.JobDTO;
import com.gaurav.nexshiftbackend.Service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/newJob")
    public String addNewJob(@RequestBody JobDTO jobDTO) {

        Job job = modelMapper.map(jobDTO, Job.class);
       return jobService.addJob(job);

    }

    @GetMapping("/update/{id}")
    public String updateJob(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        Job job = modelMapper.map(jobDTO, Job.class);
       return jobService.updateJob(jobDTO,id);
    }
  @GetMapping("/allJobs")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
  }
  @GetMapping("/job/{id}")
  public Optional<Job> getJob(@PathVariable Long id) {
        return jobService.getJobById(id);
  }

  @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable long id) {
        return jobService.deleteJob(id);
  }

}
