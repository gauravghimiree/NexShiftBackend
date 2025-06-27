package com.gaurav.nexshiftbackend.Service;

import com.gaurav.nexshiftbackend.Model.*;
import com.gaurav.nexshiftbackend.Repo.JobApplicationRepo;
import com.gaurav.nexshiftbackend.Repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class JobService {
    @Autowired
    private JobRepo jobRepo;
    @Autowired
  private JobApplicationRepo jobApplicationRepo;

    @Autowired
    ApplicationService applicationService;

    public String addJob(Job job)
    {
         jobRepo.save(job);
         return "Job added";
    }

    public String updateJob(JobDTO job,Long jobId)
    {
        Job job1= jobRepo.findJobById(jobId);
        if(job1!=null)
        {
         job1.setId(jobId);
         job1.setDeadline(job.getDeadline());
         job1.setDescription(job.getDescription());
         job1.setExperience(job.getExperience());
         job1.setPosition(job.getPosition());
         job1.setJobLevel(job.getJobLevel());

         jobRepo.save(job1);
        }
        else {
            throw new RuntimeException("Job not found");
        }
        return "Job updated";
    }
    public List<Job>getAllJobs()
    {
      List<Job>jobs= jobRepo.findAll();
      for (Job job:jobs)
      {
          if(LocalDate.now().plusDays(2).isBefore(job.getDeadline()))
          {
              jobRepo.delete(job);
          }
      }
      return jobs;
    }

    public String deleteJob(Long jobId)
    {
       Job job1= jobRepo.findJobById(jobId);
       if(job1!=null)
       {
           jobRepo.delete(job1);
       }
       else {
           throw new RuntimeException("Job not found");
       }
       return "deleted Successfully";
    }

    public Optional<Job> getJobById(Long jobId)
    {
        Job job1= Optional.ofNullable(jobRepo.findJobById(jobId))
                .orElseThrow(() -> new RuntimeException("job not found"));
        return Optional.ofNullable(job1);
    }

    public List<JobApplicationReturnDTO>getAllJobApplicationById(long id)
    {
        List<JobApplication>applications= jobApplicationRepo.findByjob_id(id);
        List<JobApplicationReturnDTO> jobApplicationReturnDTOs= new ArrayList<>();
        for (JobApplication jobApplication:applications)
        {
            JobApplicationReturnDTO dto = new JobApplicationReturnDTO();
            dto.setEmail(jobApplication.getEmail());
            dto.setFullName(jobApplication.getFullName());
            dto.setPhoneNumber(jobApplication.getPhoneNumber());
            Cv cv= jobApplication.getCv();
            String url = cv.getFileUrl();
            dto.setUrl(url);
         jobApplicationReturnDTOs.add(dto);
        }
        return jobApplicationReturnDTOs;
    }

    public String downloadCv(String url) throws Exception {

        BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
          String destinationPath = "C:/Users/user/Downloads/cv.pdf" ;
          File directory = new File("C:/Users/user/Downloads");
        if (!directory.exists()) {
            // Try to create the directory
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully: " + directory.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory: " + directory.getAbsolutePath());
            }
        } else {
            System.out.println("Directory already exists: " + directory.getAbsolutePath());
        }
          FileOutputStream out = new FileOutputStream(destinationPath) ;
              byte[] buffer = new byte[1024];
              int bytesRead;

        while((bytesRead =in.read(buffer))!=-1)

              {
                  out.write(buffer, 0, bytesRead);
              }
        return "file downloaded successfully";
          }
    }


