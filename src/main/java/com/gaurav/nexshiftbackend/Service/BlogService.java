package com.gaurav.nexshiftbackend.Service;


import com.gaurav.nexshiftbackend.Model.Blog;
import com.gaurav.nexshiftbackend.Model.BlogDTO;
import com.gaurav.nexshiftbackend.Repo.BlogImageRepo;
import com.gaurav.nexshiftbackend.Repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    BlogRepo blogRepo;

    @Autowired
    BlogImageService blogImageService;

    public Blog  addBlog(Blog blog) {
        return blogRepo.save(blog);
    }


    public  String updateBlog(Long id, BlogDTO blog) {
        Blog blog1 = blogRepo.findById(id).get();
        if (blog1 != null) {
            blog1.setTitle(blog.getTitle());
            blog1.setContent(blog.getContent());
            blog1.setAuthor(blog.getAuthor());
            blogRepo.save(blog1);
        }
        else {
            throw  new RuntimeException("Blog not found");
        }
        return "blog updated";

    }


}
