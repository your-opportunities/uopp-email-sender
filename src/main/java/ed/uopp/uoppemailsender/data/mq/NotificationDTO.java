package ed.uopp.uoppemailsender.data.mq;

import java.util.UUID;

public record NotificationDTO(
        String userId,
        UUID subscriptionUuid,
        NotificationType notificationType,
        String opportunityText,
        String opportunityLink,
        String opportunitySourceLink

) {
}