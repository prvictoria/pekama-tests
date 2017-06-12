package Steps;

import com.codeborne.selenide.Condition;

import static Page.NewCommunity.Header.*;
import static Page.NewCommunity.PageMyAccount.*;
import static Page.NewCommunity.PageSignIn.*;
import static Page.TestsStrings.*;
import static Steps.Steps.clickSelector;
import static Steps.StepsNewCommunity.Header.clickMeTab;
import static Steps.StepsPekama.checkText;
import static Steps.StepsPekama.fillField;
import static Steps.StepsPekama.openUrlIfActualNotEquals;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by VatslauX on 05-May-17.
 */
public class StepsNewCommunity {

    public static class Header{
        public static void clickPekamaTab(){
            clickSelector(HEADER_TAB_PEKAMA);
        }
        public static void clickExpertsTab(){
            clickSelector(HEADER_TAB_EXPERTS);
        }
        public static void clickCasesTab(){
            clickSelector(HEADER_TAB_CASES);
        }
        public static void clickProfileTab(){
            clickSelector(HEADER_TAB_PROFILE);
        }
        public static void clickAboutTab(){
            clickSelector(HEADER_TAB_ABOUT);
        }
        public static void clickSingInTab(){
            clickSelector(HEADER_TAB_JOIN);
        }
        public static void clickMeTab(){
            clickSelector(HEADER_TAB_ME);
        }
    }

    public static class Wizard{
        public static void wizardSelectTabGrow () {        }
        public static void wizardTabGrowSelectMyJurisdiction(String myJurisdiction) {
        }
        public static Boolean wizardTabGrowValidateMyJurisdiction(String myJurisdiction) {
            return true;
        }
        public static void wizardTabGrowSetCardsOrder(String orderBy) {
        }
        public static Boolean wizardTabGrowValidateSelectedCardsOrder(String orderBy) {
            return true;
        }
        public static void wizardTabGrowSaveExpert() {
        }
        public static Boolean wizardTabGrowValidateIsExpertSaved(boolean saved) {
            return true;
        }
        public static Boolean wizardTabGrowValidateExpertCard(
                Integer cardPlace, String teamName, Integer scores,
                String expertNameSurname, String jurisdictionName, Integer jurisdictionQty) {
            return true;
        }
        public static void wizardTabGrowClickViewFullProfileArrow(Integer cardPlace) {
        }

        public static void wizardSelectTabSendWork(){}
        public static void wizardTabSendWorkSelectFilters(String caseType, String serviceType, String jurisdiction){}
        public static Boolean wizardTabSendWorkValidateFilters(String caseType, String serviceType, String jurisdiction){return true;}
        public static void wizardTabSendWorkSetCardsOrder(String orderBy) {
        }
        public static Boolean wizardTabSendWorkValidateSelectedCardsOrder(String orderBy) {
            return true;
        }
        public static void wizardTabSendWorkValidateSelectedExpertsQty(){}
        public static void wizardTabSendWorkClickAddNewCase(){}
        public static Boolean wizardTabSendWorkValidateAddNewCaseBtnState(boolean isEnabled){return true;}
        public static Boolean wizardTabSendWorkClickCheckboxMySavedExperts(Boolean isSelected){return true;}
        public static void wizardTabSendWorkClickSelectAll(){}
        public static void wizardTabSendWorkClickSelectNone(){}
        public static Boolean wizardTabSendWorkClickSelectExpert(Boolean isSelected){return true;}
        public static Boolean wizardTabSendWorkValidateExpertCard(
                Integer cardPlace, String teamName, Integer scores,
                String expertNameSurname, String jurisdictionName, Integer jurisdictionQty) {
            return true;
        }
        public static void wizardTabSendWorkClickViewFullProfileArrow(Integer cardPlace) {
        }
    }

    public static class DetailedProfileView{
        public static void startNewCase(){}
        public static void saveExpert(){}
        public static void clickTabProfile(){}
        public static void clickTabServices(){}
        public static void clickTabForms(){}
        public static void clickTabConversation(){}

        public static Boolean checkScores(Integer scores){return true;}
        public static Boolean checkAvatar(Integer scores){return true;}
        public static Boolean checkTeamName(Integer scores){return true;}
        public static Boolean checkNameSurname(Integer scores){return true;}
        public static Boolean checkServicesQty(Integer scores){return true;}
        public static Boolean checkTeamAvatar(Integer scores){return true;}
        public static Boolean checkTime(Integer scores){return true;}

        public static Boolean checkInTabProfileOrganisationInfo(String info){return true;}
        public static Boolean checkInTabProfileScores(Integer scores, Integer caseReceived,Integer casesSent, Integer personsInvited){return true;}
        public static Boolean checkInTabProfileExpertise(Integer qty, String...strings){return true;}
        public static Boolean checkInTabProfileSendingWorkTo(Integer qty, String...strings){return true;}
        public static Boolean checkInTabProfileAbout(String info){return true;}
        public static Boolean checkInTabProfileJurisdictionWeCanWork(String info){return true;}
        public static Boolean checkInTabProfileCertificates(String info){return true;}
        public static Boolean checkInTabProfileLocation(String info){return true;}
        public static Boolean checkInTabProfileContacts(String info){return true;}

        public static void setInTabServicesPledges(String...strings){}
        public static Boolean checkInTabServicesPledges(String...strings){return true;}
        public static Boolean checkInTabServicesFillingAnApplication(Integer price){return true;}
        public static void clickInTabServicesFillingAnApplicationPlusBtn(){}
        public static Boolean checkInTabServicesIllustrations(Integer price){return true;}
        public static void clickInTabServicesIllustrationsPlusBtn(){}
        public static Boolean checkInTabServicesTranslations(Integer price){return true;}
        public static void clickInTabServicesTranslationsPlusBtn(){}
        public static Boolean checkInTabServicesRepresentation(Integer price){return true;}
        public static void clickInTabServicesRepresentationPlusBtn(){}
        public static Boolean checkInTabServicesSearch(Integer price){return true;}
        public static void clickInTabServicesSearchPlusBtn(){}
        public static Boolean checkInTabServicesValidatingPatent(Integer price){return true;}
        public static void clickInTabServicesValidatingPatentPlusBtn(){}
    }

    public static class NewCase{
        public static Boolean caseDetailsIsActive(boolean isActive){return true;}
        public static Boolean caseChooseExpertsIsActive(boolean isActive){return true;}
        public static Boolean caseStartTalkingIsActive(boolean isActive){return true;}

        public static void selectFilters(String caseType, String serviceType, String jurisdiction){}
        public static Boolean validateFilters(String caseType, String serviceType, String jurisdiction){return true;}
        public static void fillCaseName(String caseName){}
        public static Boolean checkCaseName(String caseName){return true;}
        public static void fillCaseSummary(String caseSummary){}
        public static Boolean checkCaseSummary(String caseSummary){return true;}

        public static void clickAddExpertsBtn(){}
        public static void clickAddMoreExpertsBtn(){}
        public static void clickRemoveAllBtn(){}
        public static void clickRemoveExpert(Integer cardPlace){}
        public static void clickSaveExpert(Integer cardPlace){}
        public static void clickViewFullProfile(Integer cardPlace){}
        public static Boolean validateExpertCard(
                Integer cardPlace, String teamName, Integer scores,
                String expertNameSurname, String jurisdictionName, Integer jurisdictionQty) {
            return true;
        }

        public static void clickSendCaseBtn(){}
        public static Boolean checkExpertsQty(Integer qty){return true;}
        public static Boolean checkSendCaseBtnState(boolean isEnabled){return true;}
    }

    public static class Experts{
        public static void selectFilters(String caseType, String serviceType, String jurisdiction){}
        public static Boolean validateFilters(String caseType, String serviceType, String jurisdiction){return true;}
        public static void clickExpandYourNetwork(){}
//        public static void (){}
//        public static void (){}
//        public static void (){}
//        public static void (){}
//        public static void (){}
//        public static void (){}
//
//        public static Boolean(){return true;}
//        public static Boolean(){return true;}
//        public static Boolean(){return true;}
//        public static Boolean(){return true;}
//        public static Boolean(){return true;}
    }

    public static class Cases {
        public static void selectFilters(String caseType, String serviceType, String jurisdiction) {
        }
        public static Boolean validateFilters(String caseType, String serviceType, String jurisdiction) {
            return true;
        }
        public static void findCase() {
        }
        public static void clickNewCaseBtn() {
        }
        public static void setCardsOrder(String orderBy) {
        }
        public static Boolean validateCardsOrder(String orderBy) {
            return true;
        }
        public static void selectCard(Integer cardNumber) {
        }
        public static void clickSelectAll() {
        }
        public static void clickSelectNone() {
        }
        public static void deleteCase() {
        }
        public static void duplicateCase() {
        }
        public static void tagCase() {
        }
        public static Boolean validateCard(
                Integer cardNumber, Integer days, Boolean isSelected, String name,
                String caseType, String serviceType, String jurisdiction,
                String teamName, String expertNameSurname, String caseStatus, Boolean avatarIsPresent, String tagName) {
            return true;
        }
    }

    public static class Profile{

        public static void uploadAvatar(String fileName){}
        public static void uploadCompanyLogo(String fileName){}

        public static Boolean checkProfileCompletionPercent(int percents){return true;}
        public static Boolean checkProfileCompletionScores(int scoresQty){return true;}

        public static Boolean checkCompanyName(String companyName){return true;}
        public static Boolean checkExpertNameSurname(String expertNameSurname){return true;}
        public static Boolean checkCommunityScores(int scores){return true;}

        public static class TabProfile{
            public static void fillWebsite(String text){}
            public static void fillOrganisationInfo(String text){}
            public static void fillExpertise(String text){}
            public static void fillAbout(String text){}
            public static void connectLinkedIn(String url){}
            public static void uploadDocuments(String fileName){}
            public static void selectJurisdictions(){}
            public static void fillAddress(){}
            public static void fillLanguages(){}
            public static void fill(){}

            public static Boolean validateCommunityScores(){return true;}
            public static Boolean validateToolTip(){return true;}

        }
        public static class TabServices{
            public static void selectFilters(String caseType, String serviceType, String jurisdiction, Integer price) {
            }
            public static Boolean validateFilters(String caseType, String serviceType, String jurisdiction, Integer price) {              return true;            }
            public static void fillDescription(String text){}
            public static void addService(String caseType, String serviceType, String jurisdiction, Integer price, String text){}
            public static void editService(String caseType, String serviceType, String jurisdiction, Integer price, String text, Integer caseNumber){}
            public static void deleteService(Integer caseNumber){}

            public static void selectPledges(String...strings){}
            public static Boolean checkSelectedPledges(String...strings){return true;}
        }
        public static class TabForms{
            public static void uploadForm(String fileName){}
            public static void deleteForm(String fileName, Integer fileNumber){}
            public static Boolean checkUploadedForm(String fileName, Integer fileNumber){return true;}
            public static Boolean checkDefaultForm(String fileName, Integer fileNumber){return true;}
        }
        public static class TabSettings{
            public static void fillName(){}
            public static void fillSurname(){}
            public static void fillCompanyName(){}
            public static void fillEmail(){}
            public static void signOut(){}
            public static void connectLinkedIn(){}

         }
    }

    public static class About{    }

    public static class Case{
        public static Boolean checkCaseName(String caseName){return true;}
        public static Boolean checkCaseDetails(String caseType, String serviceType, String jurisdiction, String caseStatus){return true;}
        public static void clickAddExperts(){}
        public static void clickDeleteCase(){}
        public static void clickCloneCase(){}

        public static void clickTabMessage(){}
        public static void clickTabInstruct(){}
        public static void clickTabReject(){}
        public static void clickExpertLabel(String expertNameSurname){}
        public static void clickRejectBtn(){}
        public static void clickReply(){}
        public static void fillQuote(Integer price){}
        public static void click(){}

        public static Boolean checkExpert(Integer expertNumber, String companyName, String expertNameSurname, String status, Integer price){return true;}
        public static Boolean checkLastMessage(){return true;}

    }

    public static class Login {
        public static void submitLogin(ObjectUser user){
            openUrlIfActualNotEquals(JOIN_URL);
            if(user.email!=null){
                fillField(JOIN_LOGIN_EMAIL, user.email, "Email filled");
            }
            if(user.passwordPekama!=null){
                fillField(JOIN_LOGIN_PASSWORD, user.passwordPekama, "Password filled");
            }
            clickSelector(JOIN_LOGIN_SUBMIT);
            return;
        }
        public static Boolean validateSubmitLogin(Boolean submittedDataIsValid, String error, Integer errorQty){
            openUrlIfActualNotEquals(JOIN_URL);
            if(submittedDataIsValid==true){
                HEADER_TAB_JOIN.shouldNot(visible);
                HEADER_TAB_ME.should(visible);
                return true;
            }
            else {
                checkText(error, errorQty);
                return false;
            }
        }

        public static void submitSignUp(ObjectUser user){

        }
        public static Boolean validateSubmitSignUp(Boolean submittedDataIsValid){
            openUrlIfActualNotEquals(JOIN_URL);
            if(submittedDataIsValid==true){
                checkText("You were sent an email message with the account activation link. Please check your inbox.");
                return true;
            }
            else {

                return false;
            }
        }

        public static void submitResetPassword(ObjectUser user){
            openUrlIfActualNotEquals(JOIN_URL);
            clickSelector(JOIN_LOGIN_RESET_PASSWORD);
            if(user.email!=null){
                fillField(JOIN_RESET_EMAIL, user.email);
            }
            clickSelector(JOIN_RESET_SUBMIT);
        }
        public static Boolean validateSubmitResetPassword(Boolean submittedDataIsValid, String error){
            openUrlIfActualNotEquals(JOIN_URL);
            if(submittedDataIsValid==true){
                checkText("The password restoration instructions has been sent to your email address, please check your inbox.");
                return true;
            }
            else {
                checkText(error);
                return false;
            }
        }
    }

    public static class Account{
        public static void logout(){
            clickMeTab();
            clickSelector(ACCOUNT_LOGOUT);
            sleep(4000);
            HEADER_TAB_JOIN.should(visible);
            HEADER_TAB_ME.shouldNot(visible);
        }
    }

}
