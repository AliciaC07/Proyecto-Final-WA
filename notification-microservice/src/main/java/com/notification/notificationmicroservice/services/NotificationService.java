package com.notification.notificationmicroservice.services;

import com.notification.notificationmicroservice.models.EmailSend;
import com.notification.notificationmicroservice.models.dto.Order;
import com.notification.notificationmicroservice.models.dto.OrderInfoEmployee;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.IOException;

@Service
public class NotificationService {

    @Value("${event.app.sendgrip}")
    private String SEND_GRIP_API_KEY;
    private final SpringTemplateEngine templateEngine;

    public NotificationService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public Boolean sendEmailNotification(EmailSend emailSend) {
        String EMAIL = "factory@em4795.traki-tech.games";
        Email from = new Email(EMAIL);
        Email to = new Email(emailSend.getTo());
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject(emailSend.getSubject());
        mail.setTemplateId("d-fef79ed2abc249a591dcf19fbd16d257");
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("client", emailSend.getName());
        personalization.addDynamicTemplateData("username", emailSend.getUserName());
        return notification(mail, personalization, to);

    }

    public Boolean sendEmailOrderNotification(Order order){
        String EMAIL = "factory@em4795.traki-tech.games";
        Email from = new Email(EMAIL);
        Email to = new Email(order.getEmail());
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject("Order Information");
        mail.setTemplateId("d-7dbb1ddfb6884dd6875cbed333e74065");
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("event", order.getEventSelected());
        personalization.addDynamicTemplateData("first_name", order.getUserName());
        personalization.addDynamicTemplateData("order_total", order.getTotalAmount());
        personalization.addDynamicTemplateData("order_date", order.getDate());
        personalization.addDynamicTemplateData("order_number", order.getOrderNumber());
        return notification(mail, personalization, to);
    }

    public Boolean sendEmailOrderNotificationEmployee(OrderInfoEmployee order){
        String EMAIL = "factory@em4795.traki-tech.games";
        Email from = new Email(EMAIL);
        Email to = new Email(order.getEmail());
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTemplateId("d-9e36cab2916f4a86a287d72bdf7f8253");
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("employee_name", order.getEmployee());
        personalization.addDynamicTemplateData("type", order.getEvent());
        personalization.addDynamicTemplateData("client_name", order.getClient());
        return notification(mail, personalization, to);
    }

    public Boolean notification(Mail mail, Personalization personalization, Email to){

        personalization.addTo(to);
        mail.addPersonalization(personalization);
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
}

