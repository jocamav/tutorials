package com.jocamav.springbootmailthymeleaf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringBootMailThymeleafApplicationTests {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    @Test
    void contextLoads() {
    }

    @Test
    void sendPlainMail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("some.mail@mail.com");
        mailMessage.setTo("another.mail@mail.com");
        mailMessage.setText("This is a sample body\n\nKind regards,\nYour friend");
        mailMessage.setSubject("Sample text mail");
        emailSender.send(mailMessage);
    }

    @Test
    void sendHtmlMail_withThymeleaf() throws Exception{
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("name", "José Carlos");
        templateModel.put("senderName", "Martínez");
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);

        String htmlBody = thymeleafTemplateEngine.process("template-thymeleaf.html", thymeleafContext);

        sendHtmlMessage("some.mail@mail.com", "Mail sent with Thymeleaf template", htmlBody);
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws Exception {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        ClassPathResource classPathResource = new ClassPathResource("attachment.jpg");
        helper.addInline("attachment.jpg", classPathResource);

        emailSender.send(message);

    }
}
