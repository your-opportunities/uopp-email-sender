package ed.uopp.uoppemailsender.processor.impl;

import ed.uopp.uoppemailsender.data.mq.NotificationDTO;
import ed.uopp.uoppemailsender.processor.NotificationMessageProcessor;
import ed.uopp.uoppemailsender.service.EmailService;
import ed.uopp.uoppemailsender.util.MarkdownUtil;
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
    public void processNotification(NotificationDTO notificationDTO) {
        log.info("Processing NotificationDTO for user userId = '{}'", notificationDTO.userId());

        String templateName = switch (notificationDTO.notificationType()) {
            case MODERATOR -> "moderator_notification_template";
            case USER -> "user_notification_template";
        };

        Context context = new Context();
        context.setVariable("description", getDescription(notificationDTO.opportunityText()));
        context.setVariable("opportunityLink", notificationDTO.opportunityLink());
        context.setVariable("opportunitySourceLink", notificationDTO.opportunitySourceLink());
        context.setVariable("subscriptionUuid", notificationDTO.subscriptionUuid());
        context.setVariable("moderatorProfileLink", "http://localhost:8080/moderator/sign-in/");

        String emailContent = templateEngine.process(templateName, context);

        emailService.sendEmail(emailContent);
    }

    private static String getDescription(String opportunityText) {
        return MarkdownUtil.convertMarkdownToHtml(opportunityText);
    }

}
