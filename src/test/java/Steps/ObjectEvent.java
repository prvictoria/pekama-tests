package Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Page.ModalWindows.*;
import static Page.PekamaReports.*;
import static Page.PekamaReportsEvents.*;
import static Page.PekamaReportsProjects.REPORTS_PROJECT_TITLE;
import static Page.TestsStrings.*;
import static Steps.Steps.checkTextInSelector;
import static Steps.StepsModalWindows.clickPlusButtonNewEvent;
import static Steps.StepsModalWindows.setDueDateFromToday;
import static Steps.StepsModalWindows.waitForModalWindow;
import static Steps.StepsPekama.*;
import static Utils.Utils.getDate;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.*;

/**
 * Created by VatslauX on 20-May-17.
 */
public class ObjectEvent {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String event;
    public String eventType;
    public Boolean eventIsAutoDeployed;

    public Boolean eventIsLessImportant;
    public Boolean eventIsAutoPopulated;
    public Boolean eventIsManuallyAdded;

    public String eventInfo;
    public Integer eventDateFormToday;
    public String eventDate;

    public String eventRelevantToMatterType;
    public String eventRelevantToDefining;
    public String eventRelevantToType;
    public String eventRelevantToEventType;

    public ObjectEvent(Builder builder) {
        event = builder.event;
        eventType = builder.eventType;
        eventIsAutoDeployed = builder.eventIsAutoDeployed;
        eventIsLessImportant = builder.eventIsLessImportant;
        eventIsAutoPopulated = builder.eventIsAutoPopulated;
        eventIsManuallyAdded = builder.eventIsManuallyAdded;
        eventInfo = builder.eventInfo;
        eventDateFormToday = builder.eventDateFormToday;
        eventDate = builder.eventDate;
        eventRelevantToMatterType = builder.eventRelevantToMatterType;
        eventRelevantToDefining = builder.eventRelevantToDefining;
        eventRelevantToType = builder.eventRelevantToType;
        eventRelevantToEventType = builder.eventRelevantToEventType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String event;
        private String eventType;
        private Boolean eventIsAutoDeployed;
        private Boolean eventIsLessImportant;
        private Boolean eventIsAutoPopulated;
        private Boolean eventIsManuallyAdded;
        private String eventInfo;
        private Integer eventDateFormToday;
        private String eventDate;
        private String eventRelevantToMatterType;
        private String eventRelevantToDefining;
        private String eventRelevantToType;
        private String eventRelevantToEventType;

        private Builder() {
        }

        public Builder event(String event) {
            this.event = event;
            return this;
        }

        public Builder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder eventIsAutoDeployed(Boolean eventIsAutoDeployed) {
            this.eventIsAutoDeployed = eventIsAutoDeployed;
            return this;
        }

        public Builder eventIsLessImportant(Boolean eventIsLessImportant) {
            this.eventIsLessImportant = eventIsLessImportant;
            return this;
        }

        public Builder eventIsAutoPopulated(Boolean eventIsAutoPopulated) {
            this.eventIsAutoPopulated = eventIsAutoPopulated;
            return this;
        }

        public Builder eventIsManuallyAdded(Boolean eventIsManuallyAdded) {
            this.eventIsManuallyAdded = eventIsManuallyAdded;
            return this;
        }

        public Builder eventInfo(String eventInfo) {
            this.eventInfo = eventInfo;
            return this;
        }

        public Builder eventDateFormToday(Integer eventDateFormToday) {
            this.eventDateFormToday = eventDateFormToday;
            return this;
        }

        public Builder eventDate(String eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Builder eventRelevantToMatterType(String eventRelevantToMatterType) {
            this.eventRelevantToMatterType = eventRelevantToMatterType;
            return this;
        }

        public Builder eventRelevantToDefining(String eventRelevantToDefining) {
            this.eventRelevantToDefining = eventRelevantToDefining;
            return this;
        }

        public Builder eventRelevantToType(String eventRelevantToType) {
            this.eventRelevantToType = eventRelevantToType;
            return this;
        }

        public Builder eventRelevantToEventType(String eventRelevantToEventType) {
            this.eventRelevantToEventType = eventRelevantToEventType;
            return this;
        }

        public ObjectEvent build() {
            return new ObjectEvent(this);
        }
    }
    public void logEventFields(){
        rootLogger.info("eventRelevantToMatterType: "+this.eventRelevantToMatterType);
        rootLogger.info("eventRelevantToDefining: "+this.eventRelevantToDefining);
        rootLogger.info("eventRelevantToType: "+this.eventRelevantToType);
        rootLogger.info("eventType: "+this.eventType);
        rootLogger.info("eventDateFormToday: "+this.eventDateFormToday);
        rootLogger.info("eventDate: "+this.eventDate);
        rootLogger.info("eventIsLessImportant: "+this.eventIsLessImportant);
        rootLogger.info("eventIsAutoDeployed: "+this.eventIsAutoDeployed);
        rootLogger.info("==========================");
    }
    public enum PatentEventTypes {CASE_CREATED, ABANDONMENT, CASE_CLOSED, GRANT}
    public ObjectEvent buildEventInPatent(PatentEventTypes types, Integer dateFromToday){
        ObjectEvent event = null;
        switch (types){
            case CASE_CREATED:
                event = ObjectEvent
                        .newBuilder()
                        .eventRelevantToMatterType("PATENT")
                        .eventRelevantToDefining("Canada")
                        .eventRelevantToType("Any")
                        .eventType("Case Created")
                        .eventDateFormToday(dateFromToday)
                        .eventDate(getDate (dateFromToday))
                        .eventInfo("INFO_00")
                        .eventIsLessImportant(true)
                        .eventIsAutoDeployed(true)
                        .build();
                event.logEventFields();
                break;
            case ABANDONMENT:
                event = ObjectEvent
                        .newBuilder()
                        .eventRelevantToMatterType("PATENT")
                        .eventRelevantToDefining("Canada")
                        .eventRelevantToType("Any")
                        .eventType("Abandonement")
                        .eventDateFormToday(dateFromToday)
                        .eventDate(getDate (dateFromToday))
                        .eventInfo("INFO_01")
                        .eventIsLessImportant(false)
                        .eventIsAutoDeployed(false)
                        .build();
                event.logEventFields();
                break;

            case CASE_CLOSED:
                event = ObjectEvent
                        .newBuilder()
                        .eventRelevantToMatterType("PATENT")
                        .eventRelevantToDefining("Canada")
                        .eventRelevantToType("Any")
                        .eventType("Case Closed")
                        .eventDateFormToday(dateFromToday)
                        .eventDate(getDate (dateFromToday))
                        .eventInfo("INFO_02")
                        .eventIsLessImportant(false)
                        .eventIsAutoDeployed(false)
                        .build();
                event.logEventFields();
                break;
            case GRANT:
                event = ObjectEvent
                        .newBuilder()
                        .eventRelevantToMatterType("PATENT")
                        .eventRelevantToDefining("Canada")
                        .eventRelevantToType("Any")
                        .eventType("Decision to Grant")
                        .eventDateFormToday(dateFromToday)
                        .eventDate(getDate (dateFromToday))
                        .eventInfo("INFO_03")
                        .eventIsLessImportant(false)
                        .eventIsAutoDeployed(false)
                        .build();
                event.logEventFields();
                break;
        }
        return event;
    }
    public ObjectEvent createEvent(){
        scrollUp();
        clickPlusButtonNewEvent();
        waitForModalWindow(TITLE_MW_EVENT);
        MW_BTN_SAVE.shouldBe(disabled);
        rootLogger.info("Set date today");
        MW_EVENT_INPUT_INFO.sendKeys(this.eventInfo);
        selectItemInDropdown(
                MW_EVENT_SELECT_TYPE,
                MW_EVENT_INPUT_TYPE,
                this.eventType);
        setDueDateFromToday(this.eventDateFormToday);
        submitEnabledButton(MW_BTN_SAVE);
        MW.waitUntil(not(visible), 15000);
        return this;
    }
    public static Boolean checkReportsEventRow(Integer rowCount, ObjectEvent event, ObjectUser user, ObjectProject project){
        if(event==null){
            rootLogger.info("No project row to validate");
            return false;
        }
        if(rowCount==null){
            REPORTS_LIST_ROWS.shouldHaveSize(0);
            checkTextInSelector(REPORTS_PLACEHOLDER_NO_DATA, PLACEHOLDER_NO_DATA);
            return true;
        }
        if(rowCount!=null && event!=null) {
            if (event.eventDate != null) {
                checkTextInSelector(REPORTS_EVENT_DATE(rowCount), event.eventDate);
            }
            if (event.eventInfo != null) {
                checkTextInSelector(REPORTS_EVENT_DESCRIPTION(rowCount), project.projectName);
            }
            if (event.eventInfo != null) {
                checkTextInSelector(REPORTS_EVENT_TEAM_CREATOR(rowCount), user.teamName);
            }
            if (event.eventType != null) {
                checkTextInSelector(REPORTS_EVENT_TYPE(rowCount), event.eventType);
            }
        }
        return true;
    }
}
