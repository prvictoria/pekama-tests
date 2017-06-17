package Steps;

import com.codeborne.selenide.SelenideElement;

/**
 * Created by VatslauX on 29-May-17.
 */
public interface IUpload {
    ObjectFile uploadFile(SelenideElement input, String fileName);
    ObjectFile uploadFile(SelenideElement input);
    ObjectFile uploadFile(String fileName);
    ObjectFile uploadFile();

}
