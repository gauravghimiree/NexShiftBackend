package com.gaurav.nexshiftbackend.Repo;


import com.gaurav.nexshiftbackend.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {
    Job findJobById(long jobId);


}
