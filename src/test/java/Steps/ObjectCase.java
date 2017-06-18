package Steps;

import Steps.ObjectMessage;
import Steps.ObjectProject;

/**
 * Created by VatslauX on 25-May-17.
 */
public class ObjectCase {
    public String cas = null;
    public String caseTitle = null;
    public String caseMatterType = null;
    public String caseDefining = null;
    public String caseType = null;
    public String caseSubType = null;
    public String caseStatus = null;
    public String[] caseStatuses = null;
    public String caseOwner = null;
    public String caseExperts = null;
    public String casePretenders = null;
    public ObjectProject caseParentProject = null;
    public ObjectMessage caseMessage = null;
    public ObjectMessage[] caseMessages = null;
    public Boolean caseIsActive = null;

    public enum caseStatuses {A, B, C, D, E}

}
