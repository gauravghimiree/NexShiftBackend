package com.gaurav.nexshiftbackend.Repo;

import com.gaurav.nexshiftbackend.Model.BlogImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogImageRepo extends JpaRepository<BlogImage, Long> {
}
