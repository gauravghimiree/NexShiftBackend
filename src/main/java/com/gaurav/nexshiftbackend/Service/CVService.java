package com.gaurav.nexshiftbackend.Service;

import com.cloudinary.Cloudinary;
import com.gaurav.nexshiftbackend.Model.Cv;
import com.gaurav.nexshiftbackend.Model.JobApplication;
import com.gaurav.nexshiftbackend.Repo.CvRepo;
import com.gaurav.nexshiftbackend.Repo.JobApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Service
public class CVService implements CloudinaryPdfService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    JobApplicationRepo jobApplicationRepo;

    @Autowired
    CvRepo cvRepo;

    @Override
    public Cv upload(MultipartFile file) throws IOException {
        Map data=   cloudinary.uploader().upload(file.getBytes(),Map.of( "resource_type", "raw"));

        Cv cv = new Cv();
        cv.setFileName((String) data.get("original_filename"));
        cv.setFileUrl((String) data.get("secure_url"));
        cv.setFileType(file.getContentType());
        cv.setFileSize(file.getSize());
         cvRepo.save(cv);
       return cv;
    }

}
