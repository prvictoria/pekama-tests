package Steps;

/**
 * Created by VatslauX on 29-May-17.
 */
public interface ISelectZone {
    ObjectZone createZone(Boolean isAllZone);
    ObjectZone editAllZone();
    ObjectZone validateZone();
    ObjectZone validateTeamsQtyInZone();
}
