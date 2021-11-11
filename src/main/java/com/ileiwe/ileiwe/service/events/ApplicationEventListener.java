package com.ileiwe.ileiwe.service.events;

import com.ileiwe.ileiwe.service.mail.EmailService;
import com.ileiwe.ileiwe.service.mail.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;

@Component
public class ApplicationEventListener {

    @Autowired
    EmailService emailService;
    @Autowired
    TemplateEngine templateEngine;

    public ApplicationEventListener(EmailService emailService, TemplateEngine templateEngine) {
        this.emailService = emailService;
        this.templateEngine = templateEngine;
    }

    @EventListener
    public void handleRegistrationCompleteEvent
            (OnRegistrationCompleteEvent event){
        this.confirmRegistration(event);
    }

    private void confirmRegistration
            (OnRegistrationCompleteEvent event){
//        String verification = UUID.randomUUID().toString();
        Message message = Message.builder()
                .from("isesi")
                .to(event.getAppUser().getEmail())
                .subject("Confirm Email")
                .body(templateEngine.process("confirmation.html", new Context()))
                .build();
        emailService.send(message);
    }
}
