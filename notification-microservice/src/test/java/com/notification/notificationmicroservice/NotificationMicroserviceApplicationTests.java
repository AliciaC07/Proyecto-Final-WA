package com.notification.notificationmicroservice;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class NotificationMicroserviceApplicationTests {

    @Test
    void contextLoads() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Map<String, Object> map = new HashMap<>();
        map.put("username", "mmg");
        map.put("client", "Alicec");
        Context ctx = new Context();
        ctx.setVariables(map);
        String cont = templateEngine.process("Welcome_client.html", ctx);
        System.out.printf(cont);
        Content content = new Content("text/html", cont);
        Email from = new Email("factory@em3148.evemoonindustries.me");
        Email to = new Email("alicruz0703@gmail.com");

        Mail mail = new Mail(from, "Test", to, content);
    }

}
