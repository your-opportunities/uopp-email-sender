package ed.uopp.uoppemailsender.processor.impl;

import ed.uopp.uoppemailsender.context.AbstractOpportunityNotifyContext;
import ed.uopp.uoppemailsender.context.impl.ModeratorOpportunityNotifyContext;
import ed.uopp.uoppemailsender.context.impl.UserOpportunityNotifyContext;
import ed.uopp.uoppemailsender.data.mq.NotificationDTO;
import ed.uopp.uoppemailsender.processor.NotificationMessageProcessor;
import ed.uopp.uoppemailsender.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static ed.uopp.uoppemailsender.util.MarkdownUtil.convertMarkdownToHtml;

@Slf4j
@RequiredArgsConstructor
@Component
public class DefaultNotificationMessageProcessor implements NotificationMessageProcessor {

    private static final String MODERATOR_NOTIFICATION_TEMPLATE_CODE = "moderator_notification_template";
    private static final String USER_NOTIFICATION_TEMPLATE_CODE = "user_notification_template";
    @Value("${application.uopp-spa.moderatorLink}")
    private String moderatorLink;
    private final SpringTemplateEngine templateEngine;
    private final EmailService emailService;

    @Override
    public void processNotification(NotificationDTO notificationDTO) {
        log.info("Processing NotificationDTO with user userId = '{}'", notificationDTO.userId());

        String templateName;
        AbstractOpportunityNotifyContext context;

        // consider strategies in the future
        switch (notificationDTO.notificationType()) {
            case MODERATOR -> {
                templateName = MODERATOR_NOTIFICATION_TEMPLATE_CODE;
                context = new ModeratorOpportunityNotifyContext(
                        convertMarkdownToHtml(notificationDTO.opportunityText()),
                        notificationDTO.opportunityLink(),
                        notificationDTO.opportunitySourceLink(),
                        moderatorLink
                );
                log.info("Handling NotificationDTO with user userId = '{}' as 'MODERATOR' type", notificationDTO.userId());
            }
            case USER -> {
                templateName = USER_NOTIFICATION_TEMPLATE_CODE;
                context = new UserOpportunityNotifyContext(
                        convertMarkdownToHtml(notificationDTO.opportunityText()),
                        notificationDTO.opportunityLink(),
                        notificationDTO.opportunitySourceLink(),
                        notificationDTO.subscriptionUuid()
                );
                log.info("Handling NotificationDTO with user userId = '{}' as 'USER' type", notificationDTO.userId());
            }
            default -> throw new IllegalArgumentException(String.format(
                    "Unsupported notification type for NotificationDTO with user userId = '%s'", notificationDTO.userId()));
        }

        String emailContent = templateEngine.process(templateName, context);
        String userEmail = notificationDTO.userId();

        log.info("Sending NotificationDTO with user userId = '{}' with type = '{}'", notificationDTO.userId(),
                notificationDTO.notificationType());
        emailService.sendEmail(userEmail, emailContent);
    }

}
