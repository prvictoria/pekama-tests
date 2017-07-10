package Steps.Intrefaces;

import Steps.ObjectFile;
import Steps.Steps.*;
import com.codeborne.selenide.SelenideElement;

/**
 * Created by VatslauX on 29-May-17.
 */
public interface IUpload {
    ObjectFile uploadFile(SelenideElement input, String fileFullName);
    ObjectFile uploadFile(SelenideElement input);
    ObjectFile uploadFile(String fileFullName);
    ObjectFile uploadFile();

}
