package Steps;

import com.codeborne.selenide.SelenideElement;

import static Page.ModalWindows.CSS_SelectHighlighted;
import static com.codeborne.selenide.Condition.visible;

/**
 * Created by VatslauX on 19-Jun-17.
 */
public interface ISelectDropdown {
    void selectItemInDropdown(SelenideElement uiSelectName, SelenideElement uiSelectInput, final String selectableItemPath, String inputValue);
    void selectItemInDropdown(SelenideElement uiSelectName, SelenideElement uiSelectInput, SelenideElement selectableItem, String inputValue);
}
