package ed.uopp.uoppemailsender.context.impl;

import ed.uopp.uoppemailsender.context.AbstractOpportunityNotifyContext;
import lombok.Getter;

@Getter
public class ModeratorOpportunityNotifyContext extends AbstractOpportunityNotifyContext {

    private final String moderatorProfileLink;


    public ModeratorOpportunityNotifyContext(String description, String opportunityLink, String opportunitySourceLink, String moderatorProfileLink) {
        super(description, opportunityLink, opportunitySourceLink);
        this.moderatorProfileLink = moderatorProfileLink;
    }

}
