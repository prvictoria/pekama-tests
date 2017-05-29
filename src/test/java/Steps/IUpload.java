package Steps;

import com.codeborne.selenide.SelenideElement;

/**
 * Created by VatslauX on 29-May-17.
 */
public interface IUpload {
    public ObjectFile uploadFile(SelenideElement input, String fileName);
    public ObjectFile uploadFile(SelenideElement input);
    public ObjectFile uploadFile(String fileName);
    public ObjectFile uploadFile();

}
