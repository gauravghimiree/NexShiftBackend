package com.gaurav.nexshiftbackend.Repo;

import com.gaurav.nexshiftbackend.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog, Long> {
}
