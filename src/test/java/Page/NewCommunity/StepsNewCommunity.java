package Page.NewCommunity;

//TODO DRAFT REFACTOR - to non-ststis
/**
 * Created by VatslauX on 05-May-17.
 */
public class StepsNewCommunity {


    public  class Wizard{
        public  void wizardSelectTabGrow () {        }
        public  void wizardTabGrowSelectMyJurisdiction(String myJurisdiction) {
        }
        public  Boolean wizardTabGrowValidateMyJurisdiction(String myJurisdiction) {
            return true;
        }
        public  void wizardTabGrowSetCardsOrder(String orderBy) {
        }
        public  Boolean wizardTabGrowValidateSelectedCardsOrder(String orderBy) {
            return true;
        }
        public  void wizardTabGrowSaveExpert() {
        }
        public  Boolean wizardTabGrowValidateIsExpertSaved(boolean saved) {
            return true;
        }
        public  Boolean wizardTabGrowValidateExpertCard(
                Integer cardPlace, String teamName, Integer scores,
                String expertNameSurname, String jurisdictionName, Integer jurisdictionQty) {
            return true;
        }
        public  void wizardTabGrowClickViewFullProfileArrow(Integer cardPlace) {
        }

        public  void wizardSelectTabSendWork(){}
        public  void wizardTabSendWorkSelectFilters(String caseType, String serviceType, String jurisdiction){}
        public  Boolean wizardTabSendWorkValidateFilters(String caseType, String serviceType, String jurisdiction){return true;}
        public  void wizardTabSendWorkSetCardsOrder(String orderBy) {
        }
        public  Boolean wizardTabSendWorkValidateSelectedCardsOrder(String orderBy) {
            return true;
        }
        public  void wizardTabSendWorkValidateSelectedExpertsQty(){}
        public  void wizardTabSendWorkClickAddNewCase(){}
        public  Boolean wizardTabSendWorkValidateAddNewCaseBtnState(boolean isEnabled){return true;}
        public  Boolean wizardTabSendWorkClickCheckboxMySavedExperts(Boolean isSelected){return true;}
        public  void wizardTabSendWorkClickSelectAll(){}
        public  void wizardTabSendWorkClickSelectNone(){}
        public  Boolean wizardTabSendWorkClickSelectExpert(Boolean isSelected){return true;}
        public  Boolean wizardTabSendWorkValidateExpertCard(
                Integer cardPlace, String teamName, Integer scores,
                String expertNameSurname, String jurisdictionName, Integer jurisdictionQty) {
            return true;
        }
        public  void wizardTabSendWorkClickViewFullProfileArrow(Integer cardPlace) {
        }
    }

    public  class DetailedProfileView{
        public  void startNewCase(){}
        public  void saveExpert(){}
        public  void clickTabProfile(){}
        public  void clickTabServices(){}
        public  void clickTabForms(){}
        public  void clickTabConversation(){}

        public  Boolean checkScores(Integer scores){return true;}
        public  Boolean checkAvatar(Integer scores){return true;}
        public  Boolean checkTeamName(Integer scores){return true;}
        public  Boolean checkNameSurname(Integer scores){return true;}
        public  Boolean checkServicesQty(Integer scores){return true;}
        public  Boolean checkTeamAvatar(Integer scores){return true;}
        public  Boolean checkTime(Integer scores){return true;}

        public  Boolean checkInTabProfileOrganisationInfo(String info){return true;}
        public  Boolean checkInTabProfileScores(Integer scores, Integer caseReceived,Integer casesSent, Integer personsInvited){return true;}
        public  Boolean checkInTabProfileExpertise(Integer qty, String...strings){return true;}
        public  Boolean checkInTabProfileSendingWorkTo(Integer qty, String...strings){return true;}
        public  Boolean checkInTabProfileAbout(String info){return true;}
        public  Boolean checkInTabProfileJurisdictionWeCanWork(String info){return true;}
        public  Boolean checkInTabProfileCertificates(String info){return true;}
        public  Boolean checkInTabProfileLocation(String info){return true;}
        public  Boolean checkInTabProfileContacts(String info){return true;}

        public  void setInTabServicesPledges(String...strings){}
        public  Boolean checkInTabServicesPledges(String...strings){return true;}
        public  Boolean checkInTabServicesFillingAnApplication(Integer price){return true;}
        public  void clickInTabServicesFillingAnApplicationPlusBtn(){}
        public  Boolean checkInTabServicesIllustrations(Integer price){return true;}
        public  void clickInTabServicesIllustrationsPlusBtn(){}
        public  Boolean checkInTabServicesTranslations(Integer price){return true;}
        public  void clickInTabServicesTranslationsPlusBtn(){}
        public  Boolean checkInTabServicesRepresentation(Integer price){return true;}
        public  void clickInTabServicesRepresentationPlusBtn(){}
        public  Boolean checkInTabServicesSearch(Integer price){return true;}
        public  void clickInTabServicesSearchPlusBtn(){}
        public  Boolean checkInTabServicesValidatingPatent(Integer price){return true;}
        public  void clickInTabServicesValidatingPatentPlusBtn(){}
    }

    public  class NewCase{
        public  Boolean caseDetailsIsActive(boolean isActive){return true;}
        public  Boolean caseChooseExpertsIsActive(boolean isActive){return true;}
        public  Boolean caseStartTalkingIsActive(boolean isActive){return true;}

        public  void selectFilters(String caseType, String serviceType, String jurisdiction){}
        public  Boolean validateFilters(String caseType, String serviceType, String jurisdiction){return true;}
        public  void fillCaseName(String caseName){}
        public  Boolean checkCaseName(String caseName){return true;}
        public  void fillCaseSummary(String caseSummary){}
        public  Boolean checkCaseSummary(String caseSummary){return true;}

        public  void clickAddExpertsBtn(){}
        public  void clickAddMoreExpertsBtn(){}
        public  void clickRemoveAllBtn(){}
        public  void clickRemoveExpert(Integer cardPlace){}
        public  void clickSaveExpert(Integer cardPlace){}
        public  void clickViewFullProfile(Integer cardPlace){}
        public  Boolean validateExpertCard(
                Integer cardPlace, String teamName, Integer scores,
                String expertNameSurname, String jurisdictionName, Integer jurisdictionQty) {
            return true;
        }

        public  void clickSendCaseBtn(){}
        public  Boolean checkExpertsQty(Integer qty){return true;}
        public  Boolean checkSendCaseBtnState(boolean isEnabled){return true;}
    }

    public  class Experts{
        public  void selectFilters(String caseType, String serviceType, String jurisdiction){}
        public  Boolean validateFilters(String caseType, String serviceType, String jurisdiction){return true;}
        public  void clickExpandYourNetwork(){}
//        public  void (){}
//        public  void (){}
//        public  void (){}
//        public  void (){}
//        public  void (){}
//        public  void (){}
//
//        public  Boolean(){return true;}
//        public  Boolean(){return true;}
//        public  Boolean(){return true;}
//        public  Boolean(){return true;}
//        public  Boolean(){return true;}
    }

    public  class Cases {
        public  void selectFilters(String caseType, String serviceType, String jurisdiction) {
        }
        public  Boolean validateFilters(String caseType, String serviceType, String jurisdiction) {
            return true;
        }
        public  void findCase() {
        }
        public  void clickNewCaseBtn() {
        }
        public  void setCardsOrder(String orderBy) {
        }
        public  Boolean validateCardsOrder(String orderBy) {
            return true;
        }
        public  void selectCard(Integer cardNumber) {
        }
        public  void clickSelectAll() {
        }
        public  void clickSelectNone() {
        }
        public  void deleteCase() {
        }
        public  void duplicateCase() {
        }
        public  void tagCase() {
        }
        public  Boolean validateCard(
                Integer cardNumber, Integer days, Boolean isSelected, String name,
                String caseType, String serviceType, String jurisdiction,
                String teamName, String expertNameSurname, String caseStatus, Boolean avatarIsPresent, String tagName) {
            return true;
        }
    }

    public  class Profile{

        public  void uploadAvatar(String fileName){}
        public  void uploadCompanyLogo(String fileName){}

        public  Boolean checkProfileCompletionPercent(int percents){return true;}
        public  Boolean checkProfileCompletionScores(int scoresQty){return true;}

        public  Boolean checkCompanyName(String companyName){return true;}
        public  Boolean checkExpertNameSurname(String expertNameSurname){return true;}
        public  Boolean checkCommunityScores(int scores){return true;}

        public  class TabProfile{
            public  void fillWebsite(String text){}
            public  void fillOrganisationInfo(String text){}
            public  void fillExpertise(String text){}
            public  void fillAbout(String text){}
            public  void connectLinkedIn(String url){}
            public  void uploadDocuments(String fileName){}
            public  void selectJurisdictions(){}
            public  void fillAddress(){}
            public  void fillLanguages(){}
            public  void fill(){}

            public  Boolean validateCommunityScores(){return true;}
            public  Boolean validateToolTip(){return true;}

        }
        public  class TabServices{
            public  void selectFilters(String caseType, String serviceType, String jurisdiction, Integer price) {
            }
            public  Boolean validateFilters(String caseType, String serviceType, String jurisdiction, Integer price) {              return true;            }
            public  void fillDescription(String text){}
            public  void addService(String caseType, String serviceType, String jurisdiction, Integer price, String text){}
            public  void editService(String caseType, String serviceType, String jurisdiction, Integer price, String text, Integer caseNumber){}
            public  void deleteService(Integer caseNumber){}

            public  void selectPledges(String...strings){}
            public  Boolean checkSelectedPledges(String...strings){return true;}
        }
        public  class TabForms{
            public  void uploadForm(String fileName){}
            public  void deleteForm(String fileName, Integer fileNumber){}
            public  Boolean checkUploadedForm(String fileName, Integer fileNumber){return true;}
            public  Boolean checkDefaultForm(String fileName, Integer fileNumber){return true;}
        }
        public  class TabSettings{
            public  void fillName(){}
            public  void fillSurname(){}
            public  void fillCompanyName(){}
            public  void fillEmail(){}
            public  void signOut(){}
            public  void connectLinkedIn(){}

         }
    }

    public  class About{    }

    public  class Case{
        public  Boolean checkCaseName(String caseName){return true;}
        public  Boolean checkCaseDetails(String caseType, String serviceType, String jurisdiction, String caseStatus){return true;}
        public  void clickAddExperts(){}
        public  void clickDeleteCase(){}
        public  void clickCloneCase(){}

        public  void clickTabMessage(){}
        public  void clickTabInstruct(){}
        public  void clickTabReject(){}
        public  void clickExpertLabel(String expertNameSurname){}
        public  void clickRejectBtn(){}
        public  void clickReply(){}
        public  void fillQuote(Integer price){}
        public  void click(){}

        public  Boolean checkExpert(Integer expertNumber, String companyName, String expertNameSurname, String status, Integer price){return true;}
        public  Boolean checkLastMessage(){return true;}

    }

    



}
