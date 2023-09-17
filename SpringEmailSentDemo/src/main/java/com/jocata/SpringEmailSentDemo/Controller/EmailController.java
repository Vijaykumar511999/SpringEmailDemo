package com.jocata.SpringEmailSentDemo.Controller;

import com.jocata.SpringEmailSentDemo.Model.EmailDetails;
import com.jocata.SpringEmailSentDemo.Service.SpringEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private SpringEmailService springEmailService;

    @PostMapping("/sentmail")
    public String sentEmailtocustomer(@RequestBody EmailDetails details){

        String status = springEmailService.sentMailtoCustomerwithoutAttachment(details);

        return status;
    }

    @PostMapping("/sentMailWithAttachment")
    public String sentEmailtoCustomerWithAttachment(@RequestBody EmailDetails details){
        String status = springEmailService.sentMailtoCustomerwithAttachment(details);

        return status;
    }
}
