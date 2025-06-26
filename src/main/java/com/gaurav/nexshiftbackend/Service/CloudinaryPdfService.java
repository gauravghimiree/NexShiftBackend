package com.gaurav.nexshiftbackend.Service;

import com.gaurav.nexshiftbackend.Model.Cv;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryPdfService {
    public Cv upload(MultipartFile file) throws IOException;
}
