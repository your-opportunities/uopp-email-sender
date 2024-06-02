package ed.uopp.uoppemailsender.processor;

import ed.uopp.uoppemailsender.data.mq.NotificationDTO;

public interface NotificationMessageProcessor {

    void processNotification(NotificationDTO notificationDTO);

}
