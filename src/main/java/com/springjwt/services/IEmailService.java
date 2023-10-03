package com.springjwt.services;

import org.springframework.web.multipart.MultipartFile;

public interface IEmailService {
    String sendemail(MultipartFile[] file, String to, String[] cc, String subject, String body);
}
