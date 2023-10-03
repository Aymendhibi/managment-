package com.springjwt.controllers;

import com.springjwt.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = {"http://localhost:4200" , "http://localhost:4000"}, maxAge = 3600, allowCredentials="true")
public class MailController {
    @Autowired
    IEmailService iEmailService;
public  MailController(IEmailService iEmailService){

    this.iEmailService = iEmailService;
}

    @PostMapping("/send")
    public   String sendmail(@RequestParam(value = "file", required = false)MultipartFile[] file, String to , String[]  cc, String subject, String body){

        return iEmailService.sendemail(file,to,cc,subject,body);
    }
}
