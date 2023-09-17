package com.jocata.SpringEmailSentDemo.Service;

import com.jocata.SpringEmailSentDemo.Model.EmailDetails;

public interface SpringEmailService {


    String sentMailtoCustomerwithoutAttachment(EmailDetails details);

    String sentMailtoCustomerwithAttachment(EmailDetails details);
}
