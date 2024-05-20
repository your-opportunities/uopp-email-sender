package ed.uopp.uoppemailsender.processor.impl;

import ed.uopp.uoppemailsender.processor.NotificationMessageProcessor;
import ed.uopp.uoppemailsender.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@RequiredArgsConstructor
@Component
public class DefaultNotificationMessageProcessor implements NotificationMessageProcessor {

    private final SpringTemplateEngine templateEngine;
    private final EmailService emailService;

    @Override
    public void processNotification(Object object) {
        log.info("Processing something {}", object);

        Context context = new Context();
        context.setVariable("message", object.toString());

        String emailContent = templateEngine.process("user_notification_template", context);


        emailService.sendEmail(emailContent);
    }

}
