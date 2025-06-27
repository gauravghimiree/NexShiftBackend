package com.gaurav.nexshiftbackend.Controller;

import com.gaurav.nexshiftbackend.Model.Blog;
import com.gaurav.nexshiftbackend.Model.BlogDTO;
import com.gaurav.nexshiftbackend.Service.BlogImageService;
import com.gaurav.nexshiftbackend.Service.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {

   @Autowired
   private BlogService blogService;

   @Autowired
    private ModelMapper modelMapper;

   @Autowired
   private BlogImageService imageservice;

   @PostMapping("/postBlog")
   private Blog addBlog(@RequestBody BlogDTO blog) {
       Blog blogs = new Blog();
       blogs.setTitle(blog.getTitle());
       blogs.setContent(blog.getContent());
       blogs.setAuthor(blog.getAuthor());
      return blogService.addBlog(blogs);

   }
   @PostMapping("/Image/{id}")
    private Map addImage(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {

      return   imageservice.upload(file,id);
   }


}
