package com.gaurav.nexshiftbackend.Repo;

import com.gaurav.nexshiftbackend.Model.Job;
import com.gaurav.nexshiftbackend.Model.JobApplication;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {
    List<JobApplication> getJobApplicationByJob(Job job);

    List<JobApplication> findByjob_id(long id);
}
