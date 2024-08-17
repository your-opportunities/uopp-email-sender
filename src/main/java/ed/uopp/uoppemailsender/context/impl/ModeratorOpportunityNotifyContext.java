package ed.uopp.uoppemailsender.context.impl;

import ed.uopp.uoppemailsender.context.AbstractOpportunityNotifyContext;
import lombok.Getter;

@Getter
public class ModeratorOpportunityNotifyContext extends AbstractOpportunityNotifyContext {

    private final String moderatorProfileLink;
    private final String description;
    private final String opportunityLink;
    private final String opportunitySourceLink;


    public ModeratorOpportunityNotifyContext(String moderatorProfileLink, String description, String opportunityLink, String opportunitySourceLink) {
        this.moderatorProfileLink = moderatorProfileLink;
        this.description = description;
        this.opportunityLink = opportunityLink;
        this.opportunitySourceLink = opportunitySourceLink;
    }
}
