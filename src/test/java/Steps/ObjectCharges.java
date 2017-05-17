package Steps;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.*;
import org.junit.Assert;

import static Page.ModalWindows.*;
import static Page.PekamaProject.*;
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
    }
    public String setPrice(Integer invoiceQty, Integer hour, Integer min,
                           Integer rate, Integer price, Integer vat, Integer disc){
        if(invoiceQty!=null){
            this.invoiceQty = new Integer(hour).toString();
            fillField(MW_CHARGES_INPUT_QTY, this.invoiceQty);
        }
        if(hour!=null){
            this.invoiceTimeHours = new Integer(hour).toString();
            fillField(MW_CHARGES_INPUT_HOUR, this.invoiceTimeHours);
        }
        if(min!=null){
            this.invoiceTimeMin = new Integer(min).toString();
            fillField(MW_CHARGES_INPUT_MIN, this.invoiceTimeMin);
        }
        if(rate!=null){
            this.invoiceRate = new Integer(rate).toString();
            fillField(MW_CHARGES_INPUT_RATE, this.invoiceRate);
        }
        if(price!=null){
            this.invoicePrice =  new Integer(price).toString();
            fillField(MW_CHARGES_INPUT_PRICE, this.invoicePrice);
        }
        if(vat!=null){
            this.invoiceVat = new Integer(vat).toString();
            fillField(MW_CHARGES_INPUT_VAT, this.invoiceVat);
        }
        if(disc!=null){
            this.invoiceDiscount = new Integer(disc).toString();
            fillField(MW_CHARGES_INPUT_DISCOUNT, this.invoiceDiscount);
        }
        this.invoiceTotal = MW_CHARGES_TOTAL.getValue();
        return  this.invoiceTotal;
    }
    public void fillFormInline(){

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

    public Boolean checkInvoiceRow(Integer rowCount, ObjectCharges charges){
        if(rowCount==null){
            TAB_CHARGES_LIST.shouldHaveSize(0);
            checkText(PLACEHOLDER_EMPTY_LIST);
        }
        if(rowCount!=null && charges!=null){
            if(charges.invoiceZoneTeams!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_TEAM).shouldHave(text(charges.invoiceZoneTeams[0]));
            }
            if(charges.invoiceFrom!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_FROM_TO).shouldHave(text(charges.invoiceFrom));
            }
            if(charges.invoiceTo!=null && charges.invoiceTo.equals("Select billed contact...")==false){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_FROM_TO).shouldHave(text(charges.invoiceTo));
            }
            if(charges.invoiceType!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_TYPE).shouldHave(text(charges.invoiceType));
            }
            //TODO pacing if invoice more 999
            if(charges.invoiceTotal!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_PRICE).shouldHave(text(invoiceTotal));
            }
            if(charges.invoiceCurrency!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_PRICE).shouldHave(text(invoiceCurrency));
            }
            if(charges.invoiceDueDate!=null){
                elementInChargesRow(rowCount, TAB_CHARGES_ROW_DATE).shouldHave(text(invoiceDueDate));
            }
       }

        return true;
    }
}
