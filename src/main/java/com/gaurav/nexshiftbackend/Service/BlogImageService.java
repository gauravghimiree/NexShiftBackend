package com.gaurav.nexshiftbackend.Service;

import com.cloudinary.Cloudinary;
import com.gaurav.nexshiftbackend.Model.BlogImage;
import com.gaurav.nexshiftbackend.Repo.BlogImageRepo;
import com.gaurav.nexshiftbackend.Repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Service
public class BlogImageService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    BlogImageRepo imageRepo;

    @Autowired
    BlogRepo blogRepo;
    public Map upload(MultipartFile file, Long id) throws IOException {
        Map data = cloudinary.uploader().upload(file.getBytes(), Map.of());
        BlogImage image = new BlogImage();
        image.setPublicId(data.get("public_id").toString());
        image.setHeight((Integer) data.get("height"));
        image.setWidth((Integer) data.get("width"));
        image.setOriginalFilename(data.get("original_filename").toString());
        image.setSize((Integer) data.get("bytes"));
        image.setFormat(data.get("format").toString());
        image.setSecureUrl(data.get("secure_url").toString());
        image.setBlog(blogRepo.findById(id).get());
        imageRepo.save(image);
        return data;

    }

}
