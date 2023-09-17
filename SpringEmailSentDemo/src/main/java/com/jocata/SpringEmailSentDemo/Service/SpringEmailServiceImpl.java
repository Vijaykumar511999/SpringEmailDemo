package com.jocata.SpringEmailSentDemo.Service;

import com.jocata.SpringEmailSentDemo.Model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SpringEmailServiceImpl implements SpringEmailService{

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public String sentMailtoCustomerwithoutAttachment(EmailDetails details) {

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(details.getRecipient());
            simpleMailMessage.setSubject(details.getSubject());
            simpleMailMessage.setText(details.getMsgbody());

            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            System.out.println("Email not sended..");
        }
        return "mail Sent successfully..";
    }

    @Override
    public String sentMailtoCustomerwithAttachment(EmailDetails details) {
        try{
           MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(sender);
            messageHelper.setTo(details.getRecipient());
            messageHelper.setSubject(details.getSubject());
            messageHelper.setText(details.getMsgbody());

            FileSystemResource fileSystemResource = new FileSystemResource(new File(details.getAttachment()));

            messageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);

            javaMailSender.send(mimeMessage);

            return "Mail sent with attachment successfull..";

        }catch (Exception e){
            System.out.println("email is not sent.."+e.getMessage());
        }

        return "error occured while sending email";
    }

}
