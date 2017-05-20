package Steps;

import org.apache.logging.log4j.*;

import static Page.ModalWindows.*;
import static Page.PekamaProjectCharges.*;
import static Page.PekamaReports.*;
import static Page.TestsStrings.*;
import static Steps.StepsModalWindows.*;
import static Steps.StepsPekamaProject.*;
import static Utils.Utils.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Created by VatslauX on 16-May-17.
 */
public class ObjectCharges {
    static final Logger rootLogger = LogManager.getRootLogger();
    public String invoice = null;
    public String invoiceFrom = null;
    public String invoiceTo = null;
    public String invoiceBy = null;
    public String invoiceStatus = null;
    public String invoiceType = null;
    public String invoiceDueDate = null;
    public Integer invoiceDateFormToday = null;
    public String invoiceItem = null;
    public String invoiceCurrency = null;
    public String invoiceQty = null;
    public String invoiceTimeHours = null;
    public String invoiceTimeMin = null;
    public String invoiceRate = null;
    public String invoicePrice = null;
    public String invoiceVat = null;
    public String invoiceDiscount = null;
    public String invoiceTotal = null;
    public Boolean invoiceZoneAll = false;
    public String[] invoiceZoneTeams = null;

    enum FormEnterPoint {PROJECT, CONTACTS}
    public void setValues(String invoiceFrom, String invoiceTo, String invoiceBy, String invoiceStatus, String invoiceType, String invoiceDueDate, Integer invoiceDateFormToday , String invoiceItem, String invoiceCurrency, String invoiceQty, String invoiceTimeHours, String invoiceTimeMin, String invoiceRate, String invoicePrice, String invoiceVat, String invoiceDiscount, String invoiceTotal, Boolean invoiceZoneAll){
        this.invoiceFrom = invoiceFrom;
        this.invoiceTo = invoiceTo;
        this.invoiceBy = invoiceBy;
        this.invoiceStatus = invoiceStatus;
        this.invoiceType = invoiceType;
        this.invoiceDueDate = invoiceDueDate;
        this.invoiceDateFormToday = invoiceDateFormToday;
        this.invoiceItem = invoiceItem;
        this.invoiceCurrency = invoiceCurrency;

        this.invoiceQty = invoiceQty;
        this.invoiceTimeHours = invoiceTimeHours;
        this.invoiceTimeMin = invoiceTimeMin;
        this.invoiceRate = invoiceRate;
        this.invoicePrice = invoicePrice;
        this.invoiceVat = invoiceVat;
        this.invoiceDiscount = invoiceDiscount;
        this.invoiceTotal = invoiceTotal;
        this.invoiceZoneAll = invoiceZoneAll;
    }

    public void setTeamZone(Boolean invoiceZoneAll, String...strings){
        this.invoiceZoneAll = invoiceZoneAll;
        if(invoiceZoneAll==true){
            selectAllTeams(this.invoiceZoneAll);
            return;
        }
        else {
            if(strings!=null){
               this.invoiceZoneTeams = strings;
            }
        }

    }
    public void setInvoiceInfo(String invoiceFrom,  String invoiceTo,  String invoiceBy,
                               String invoiceStatus, String invoiceType, Integer dateFromToday,
                               String invoiceItem, String invoiceCurrency){
        if(invoiceFrom!=null){
            this.invoiceFrom = invoiceFrom;
            selectItemInDropdown(MW_CHARGES_SELECT_FROM, MW_CHARGES_INPUT_FROM, this.invoiceFrom);
        }
        if(invoiceFrom==null){
            this.invoiceFrom = MW_CHARGES_SELECT_FROM.getText();
        }

        if(invoiceTo!=null){
            this.invoiceTo = invoiceTo;
            selectItemInDropdown(MW_CHARGES_SELECT_TO, MW_CHARGES_INPUT_TO, this.invoiceTo);
        }
        if(invoiceTo==null){
            this.invoiceTo = MW_CHARGES_SELECT_TO.getText();
        }

        if(invoiceBy!=null){
            this.invoiceBy = invoiceBy;
            selectItemInDropdown(MW_CHARGES_SELECT_BY, MW_CHARGES_INPUT_BY, this.invoiceBy);
        }
        if(invoiceBy==null){
            this.invoiceBy = MW_CHARGES_SELECT_BY.getText();
        }

        if(invoiceStatus!=null){
            this.invoiceStatus = invoiceStatus;
            selectItemInDropdown(MW_CHARGES_SELECT_STATUS, MW_CHARGES_INPUT_STATUS, this.invoiceStatus);
        }
        if(invoiceStatus!=null){
            this.invoiceStatus = MW_CHARGES_SELECT_STATUS.getText();
        }

        if(invoiceType!=null){
            this.invoiceType = invoiceType;
            selectItemInDropdown(MW_CHARGES_SELECT_TYPE, MW_CHARGES_INPUT_TYPE,  this.invoiceType);
        }

        if(dateFromToday!=null){
            fillField(MW_CHARGES_INPUT_DATE, getDate(dateFromToday));
            this.invoiceDueDate = MW_CHARGES_INPUT_DATE.getValue();
        }
        if(dateFromToday==null){
            this.invoiceDueDate = MW_CHARGES_INPUT_DATE.getValue();
        }
        if(invoiceItem!=null){
            this.invoiceItem = invoiceItem;
            fillField(MW_CHARGES_INPUT_ITEM, this.invoiceItem);
        }
        if(invoiceCurrency!=null){
            this.invoiceCurrency = invoiceCurrency;
            selectItemInDropdown(MW_CHARGES_SELECT_CURRENCY, MW_CHARGES_INPUT_CURRENCY, this.invoiceCurrency);
        }
        if(invoiceCurrency==null){
            this.invoiceCurrency = MW_CHARGES_SELECT_CURRENCY.getText();
        }
        rootLogger.info("============================");
        rootLogger.info("Invoice fields: ");
        rootLogger.info("From: "+this.invoiceFrom);
        rootLogger.info("To: "+this.invoiceTo);
        rootLogger.info("By: "+this.invoiceBy);
        rootLogger.info("Status: "+this.invoiceStatus);
        rootLogger.info("Type: "+this.invoiceType);
        rootLogger.info("Due Date: "+this.invoiceDueDate);
        rootLogger.info("Description: "+this.invoiceItem);
        rootLogger.info("Currency: "+this.invoiceCurrency);
        rootLogger.info("============================");
    }
    public String setPrice(Integer qty, Integer hour, Integer min,
                           Integer rate, Integer price, Integer vat, Integer disc){
        rootLogger.info("============================");
        rootLogger.info("Invoice fields: ");
        if(qty !=null){
            this.invoiceQty = new Integer(qty).toString();
            fillField(MW_CHARGES_INPUT_QTY, this.invoiceQty, "Qty: "+this.invoiceQty);
        }
        if(hour!=null){
            this.invoiceTimeHours = new Integer(hour).toString();
            fillField(MW_CHARGES_INPUT_HOUR, this.invoiceTimeHours, "Time Hours: "+this.invoiceTimeHours);
        }
        if(min!=null){
            this.invoiceTimeMin = new Integer(min).toString();
            fillField(MW_CHARGES_INPUT_MIN, this.invoiceTimeMin, "Time Min: "+this.invoiceTimeMin);
        }
        if(rate!=null){
            this.invoiceRate = new Integer(rate).toString();
            fillField(MW_CHARGES_INPUT_RATE, this.invoiceRate, "Rate per hour: "+this.invoiceRate);
        }
        if(price!=null){
            this.invoicePrice =  new Integer(price).toString();
            fillField(MW_CHARGES_INPUT_PRICE, this.invoicePrice, "Price: "+this.invoicePrice);
        }
        if(vat!=null){
            this.invoiceVat = new Integer(vat).toString();
            fillField(MW_CHARGES_INPUT_VAT, this.invoiceVat, "Vat: "+this.invoiceVat);
        }
        if(disc!=null){
            this.invoiceDiscount = new Integer(disc).toString();
            fillField(MW_CHARGES_INPUT_DISCOUNT, this.invoiceDiscount, "Discount: "+this.invoiceDiscount);
        }
        this.invoiceTotal = MW_CHARGES_TOTAL.getValue();
        rootLogger.info("Total: "+this.invoiceTotal);
        rootLogger.info("============================");
        return  this.invoiceTotal;
    }
    public void fillFormInProject(ObjectContact invoice){
        //User should be already form on page
        //Info area
        if(this.invoiceFrom!=null){
            selectItemInDropdown(TAB_CHARGES_FORM_SELECT_FROM, TAB_CHARGES_FORM_INPUT_FROM, this.invoiceFrom);
            rootLogger.info("invoiceFrom: "+this.invoiceFrom);
        }
        if(this.invoiceTo!=null){
            selectItemInDropdown(TAB_CHARGES_FORM_INPUT_TO, TAB_CHARGES_FORM_SELECT_BY, this.invoiceTo);
            rootLogger.info("invoiceTo: "+this.invoiceTo);
        }
        if(this.invoiceBy!= null){
            selectItemInDropdown(TAB_CHARGES_FORM_SELECT_BY, TAB_CHARGES_FORM_INPUT_BY, this.invoiceBy);
            rootLogger.info("invoiceBy: "+this.invoiceBy);
        }
        if(this.invoiceStatus != null){
            selectItemInDropdown(TAB_CHARGES_FORM_SELECT_STATUS, TAB_CHARGES_FORM_INPUT_STATUS, this.invoiceStatus);
            rootLogger.info("invoiceStatus: "+this.invoiceStatus);
        }
        if(this.invoiceType != null){
            selectItemInDropdown(TAB_CHARGES_FORM_SELECT_TYPE, TAB_CHARGES_FORM_INPUT_TYPE,  this.invoiceType);
            rootLogger.info("invoiceType: "+this.invoiceType);
        }
        if(this.invoiceDueDate != null){
            fillField(TAB_CHARGES_FORM_DATE, this.invoiceDueDate, "Due Date: "+this.invoiceDueDate);
        }
        if(this.invoiceItem != null){
            fillField(TAB_CHARGES_FORM_ITEM, this.invoiceItem, "Item: "+this.invoiceItem);
        }
        if(this.invoiceCurrency != null){
            selectItemInDropdown(TAB_CHARGES_FORM_SELECT_CURRENCY, TAB_CHARGES_FORM_INPUT_CURRENCY, this.invoiceCurrency);
            rootLogger.info("Currency: "+this.invoiceCurrency);
        }
        //Price area
        rootLogger.info("============================");
        rootLogger.info("Invoice fields: ");
        if(this.invoiceQty!=null){
            fillField(MW_CHARGES_INPUT_QTY, this.invoiceQty, "Qty: "+this.invoiceQty);
        }
        if(this.invoiceTimeHours!=null){
            fillField(MW_CHARGES_INPUT_HOUR, this.invoiceTimeHours, "Time Hours: "+this.invoiceTimeHours);
        }
        if(this.invoiceTimeMin!=null){
            fillField(MW_CHARGES_INPUT_MIN, this.invoiceTimeMin, "Time Min: "+this.invoiceTimeMin);
        }
        if(this.invoiceRate!=null){
            fillField(MW_CHARGES_INPUT_RATE, this.invoiceRate, "Rate per hour: "+this.invoiceRate);
        }
        if(this.invoicePrice!=null){
            fillField(MW_CHARGES_INPUT_PRICE, this.invoicePrice, "Price: "+this.invoicePrice);
        }
        if( this.invoiceVat!=null){
            fillField(MW_CHARGES_INPUT_VAT, this.invoiceVat, "Vat: "+this.invoiceVat);
        }
        if(this.invoiceDiscount!=null){
            fillField(MW_CHARGES_INPUT_DISCOUNT, this.invoiceDiscount, "Discount: "+this.invoiceDiscount);
        }
        this.invoiceTotal = MW_CHARGES_TOTAL.getValue();
        rootLogger.info("Total: "+this.invoiceTotal);
        rootLogger.info("============================");
        return;
    }

    public void create(String invoiceType, String invoiceCurrency, Integer invoicePrice){
        callChargesModal();
        MW_BTN_OK.shouldBe(disabled);

        setInvoiceInfo(null,  null,  null,
                null, invoiceType, null,
                null, invoiceCurrency);
        setTeamZone(false, null);
        setPrice(null, null, null,
                null, invoicePrice, null, null);
        submitEnabledButton(MW_BTN_OK);
    }
    public void create(String invoiceFrom, String invoiceStatus, String invoiceType, Integer dateFormToday, String description, String invoiceCurrency, Integer invoicePrice){
        callChargesModal();
        MW_BTN_OK.shouldBe(disabled);

        setInvoiceInfo(invoiceFrom,  null,  null,
                invoiceStatus, invoiceType, dateFormToday,
                description, invoiceCurrency);
        setTeamZone(false, null);
        setPrice(null, null, null,
                null, invoicePrice, null, null);
        submitEnabledButton(MW_BTN_OK);
    }
    public void create(String invoiceFrom, String invoiceTo, String invoiceBy, String invoiceStatus, String invoiceType, Integer dateFormToday, String description, String invoiceCurrency, Integer invoicePrice){
        callChargesModal();
        MW_BTN_OK.shouldBe(disabled);

        setInvoiceInfo(invoiceFrom,  invoiceTo,  invoiceBy,
                invoiceStatus, invoiceType, dateFormToday,
                description, invoiceCurrency);
        setTeamZone(false, null);
        setPrice(null, null, null,
                null, invoicePrice, null, null);
        submitEnabledButton(MW_BTN_OK);
    }
    public static Boolean checkInvoiceRow(Integer rowCount, ObjectCharges charges){
        if(rowCount==null){
            TAB_CHARGES_LIST.shouldHaveSize(0);
            checkText(PLACEHOLDER_EMPTY_LIST);
        }
        if(rowCount != null && charges != null){
            if(charges.invoiceZoneTeams != null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_TEAM).shouldHave(text(charges.invoiceZoneTeams[0]));
            }
            if(charges.invoiceFrom  !=  null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_FROM_TO).shouldHave(text(charges.invoiceFrom));
            }
            if(charges.invoiceBy  !=  null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_FROM_TO).shouldHave(text(charges.invoiceBy));
            }
            if(charges.invoiceTo!= null && charges.invoiceTo.equals("Select billed contact...")==false){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_FROM_TO).shouldHave(text(charges.invoiceTo));
            }
            if(charges.invoiceType!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_TYPE).shouldHave(text(charges.invoiceType));
            }
            //TODO pacing string if invoice more 999
            if(charges.invoiceTotal!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_PRICE).shouldHave(text(charges.invoiceTotal));
            }
            if(charges.invoiceCurrency!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_PRICE).shouldHave(text(charges.invoiceCurrency));
            }
            if(charges.invoiceDueDate!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_DATE).shouldHave(text(charges.invoiceDueDate));
            }
       }
        return true;
    }
    public static Boolean checkInvoiceRowReports(Integer rowCount, ObjectCharges charges){
        if(charges==null){
            rootLogger.info("No invoice to validate");
            return false;
        }
        if(rowCount==null){
            TAB_CHARGES_LIST.shouldHaveSize(0);
            checkText(PLACEHOLDER_NO_DATA);
        }
        if(rowCount!=null && charges!=null){
            if(charges.invoiceItem!=null){
                REPORTS_CHARGES_DESCRIPTION(rowCount).shouldHave(text(charges.invoiceItem));
            }
            if(charges.invoiceFrom!=null){
                REPORTS_CHARGES_FROM(rowCount).shouldHave(text(charges.invoiceFrom));
            }
            if(charges.invoiceFrom!=null){
                REPORTS_CHARGES_BY(rowCount).shouldHave(text(charges.invoiceBy));
            }
            if(charges.invoiceTo!=null && charges.invoiceTo.equals("Select billed contact...")==false){
                REPORTS_CHARGES_TO(rowCount).shouldHave(text(charges.invoiceTo));
            }
            if(charges.invoiceType!=null){
                REPORTS_CHARGES_TYPE(rowCount).shouldHave(text(charges.invoiceType));
            }
            if(charges.invoiceTotal!=null){
                REPORTS_CHARGES_TOTAL(rowCount).
                        shouldHave(text(parseDecimalString(charges.invoiceTotal, "")));
            }
            if(charges.invoiceCurrency!=null){
                REPORTS_CHARGES_TOTAL(rowCount).shouldHave(text(charges.invoiceCurrency));
            }
            if(charges.invoiceDueDate!=null){
                REPORTS_CHARGES_DATE(rowCount).shouldHave(text(charges.invoiceDueDate));
            }
            if(charges.invoiceStatus!=null){
                if(charges.invoiceStatus.equals("Not Billed"))
                {REPORTS_CHARGES_STATUS(rowCount).shouldHave(text(CHARGES_STATUS_NOT_BILLED));}
                if(charges.invoiceStatus.equals("Billed"))
                {REPORTS_CHARGES_STATUS(rowCount).shouldHave(text(CHARGES_STATUS_BILLED));}
                if(charges.invoiceStatus.equals("Billed & Paid"))
                {REPORTS_CHARGES_STATUS(rowCount).shouldHave(text(CHARGES_STATUS_BILLED_AND_PAID));}
            }
        }
        rootLogger.info("Row validation Passed");
        return true;
    }
    private void logValues(ObjectCharges charges){

    }
}
