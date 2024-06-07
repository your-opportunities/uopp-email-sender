package ed.uopp.uoppemailsender.context.impl;

import ed.uopp.uoppemailsender.context.AbstractOpportunityNotifyContext;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserOpportunityNotifyContext extends AbstractOpportunityNotifyContext {

    private final UUID subscriptionUuid;

    public UserOpportunityNotifyContext(String description, String opportunityLink, String opportunitySourceLink, UUID subscriptionUuid) {
        super(description, opportunityLink, opportunitySourceLink);
        this.subscriptionUuid = subscriptionUuid;
    }

}
