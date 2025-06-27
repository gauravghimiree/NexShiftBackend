package com.gaurav.nexshiftbackend.Service;

import com.gaurav.nexshiftbackend.Model.ApplicationDTO;
import com.gaurav.nexshiftbackend.Model.Cv;
import com.gaurav.nexshiftbackend.Model.Job;
import com.gaurav.nexshiftbackend.Model.JobApplication;
import com.gaurav.nexshiftbackend.Repo.CvRepo;
import com.gaurav.nexshiftbackend.Repo.JobApplicationRepo;
import com.gaurav.nexshiftbackend.Repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
   private JobRepo jobrepo;

    @Autowired
    private JobApplicationRepo jobapplicationrepo;

    @Autowired
    private CvRepo cvrepo;

    @Autowired
    private CVService cvservice;

    public String applyToJob(long id, ApplicationDTO dto, MultipartFile file) throws IOException {
        Job job = jobrepo.findJobById(id);
        JobApplication application = new JobApplication();
        application.setJob(job);
        application.setFullName(dto.getFullName());
        application.setPhoneNumber(dto.getPhoneNumber());

        jobapplicationrepo.save(application);
        Cv cv = cvservice.upload(file);
        cv.setApplication(application);
         cvrepo.save(cv);
        jobapplicationrepo.save(application);
        return "success";
    }

    }
