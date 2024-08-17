package ed.uopp.uoppemailsender.context.impl;

import ed.uopp.uoppemailsender.context.AbstractOpportunityNotifyContext;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserOpportunityNotifyContext extends AbstractOpportunityNotifyContext {

    private final UUID subscriptionUuid;
    private final String description;
    private final String opportunityLink;
    private final String opportunitySourceLink;

    public UserOpportunityNotifyContext(UUID subscriptionUuid, String description, String opportunityLink, String opportunitySourceLink) {
        this.subscriptionUuid = subscriptionUuid;
        this.description = description;
        this.opportunityLink = opportunityLink;
        this.opportunitySourceLink = opportunitySourceLink;
    }
}
