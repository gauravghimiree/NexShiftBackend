package com.gaurav.nexshiftbackend;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing
public class NexShiftBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NexShiftBackendApplication.class, args);

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Cloudinary getCloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", "dqgxxahb5");
        config.put("api_key", "138138959562412");
        config.put("api_secret", "ma1rZSTE6gRmbP7TUVDUjS46DZo");
        config.put("secure", true);
        return new Cloudinary(config);
    }

}

