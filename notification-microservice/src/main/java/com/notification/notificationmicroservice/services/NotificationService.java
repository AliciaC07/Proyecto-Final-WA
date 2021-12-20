package com.notification.notificationmicroservice.services;

import com.notification.notificationmicroservice.models.EmailSend;
import com.notification.notificationmicroservice.models.dto.Order;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotificationService {

    @Value("${event.app.sendgrip}")
    private String SEND_GRIP_API_KEY;

    public Boolean sendEmailNotification(EmailSend emailSend) {
        String EMAIL = "devali00@zohomail.com";
        Email from = new Email(EMAIL);
        //String subject = "Sending with SendGrid is Fun";
        Email to = new Email(emailSend.getTo());
        Content content = new Content("text/plain", emailSend.getContent());
        Mail mail = new Mail(from, emailSend.getSubject(), to, content);

        SendGrid sg = new SendGrid(SEND_GRIP_API_KEY);
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        }catch (IOException exception){
            return false;
        }
        return true;

    }

//    public Boolean sendEmailOrderNotification(Order order){
//        String EMAIL = "devali00@zohomail.com";
//        Email from = new Email(EMAIL);
//        //String subject = "Sending with SendGrid is Fun";
//        Email to = new Email(order.getEmail());
//        //Content content = new Content("text/plain", emailSend.getContent());
//        Mail mail = new Mail();
//        mail.setFrom(from);
//        mail.setSubject("Order Information");
//        //mail.setReplyTo(to);
//        mail.setTemplateId("d-7dbb1ddfb6884dd6875cbed333e74065");
//        Personalization personalization = new Personalization();
//        personalization.addDynamicTemplateData("order_name", order.getOrderNumber());
//        personalization.addDynamicTemplateData("first_name", order.getUserName());
//        personalization.addDynamicTemplateData("amount", order.getTotalAmount());
//        personalization.addDynamicTemplateData("tax", 25.0);
//        personalization.addDynamicTemplateData("total_amount", 25.0 + order.getTotalAmount());
//        personalization.addTo(to);
//        mail.addPersonalization(personalization);
//
//
//        SendGrid sg = new SendGrid(SEND_GRIP_API_KEY);
//        Request request = new Request();
//        try{
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
//            Response response = sg.api(request);
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
//        }catch (IOException exception){
//            return false;
//        }
//        return true;
//    }
}

