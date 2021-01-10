package Pages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;

public class CellListPage {
    private SelenideElement firstNameField = $("div.middleCenterInner tr:nth-child(2) > td:nth-child(2) > input");
    private SelenideElement lastNameField = $("div.middleCenterInner tr:nth-child(3) > td:nth-child(2) > input");
    private SelenideElement categoryField = $("div.middleCenterInner tr:nth-child(4) > td:nth-child(2) > select");
    private SelenideElement birthdayField = $("div.middleCenterInner tr:nth-child(5) > td:nth-child(2) > input");
    private SelenideElement addressField = $("div.middleCenterInner tr:nth-child(6) > td:nth-child(2) > textarea");
    private SelenideElement createButton = $("div.middleCenterInner tr:nth-child(7) > td > button:nth-child(2)");
    private SelenideElement count = $("#gwt-debug-contentPanel div.gwt-HTML");

    public CellListPage fillFirstName(String name) {
        firstNameField.val(name).pressEnter();
        return this;
    }

    public CellListPage fillLastName(String name) {
        lastNameField.val(name).pressEnter();
        return this;
    }

    public CellListPage selectCategory(String category) {
        categoryField.selectOption(category);
        return this;
    }

    public CellListPage fillBirthday(String birthday) {
        birthdayField.val(birthday).pressEnter();
        return this;
    }

    public CellListPage fillAddress(String address) {
        addressField.val(address).pressEnter();
        return this;
    }

    public CellListPage createContact() {
        createButton.click();
        return this;
    }

    public int getCount() {
        return Integer.parseInt(count.getText().split(" : ")[1]);
    }

    public int getRange() {
        return Integer.parseInt(count.getText().split(" : ")[0].split(" - ")[1]);
    }

    // crutch method, need to refactor
    public CellListPage scrollToCellListEnd() {
        while (true){
            $(String.format("[__idx='%d']", getRange() - 1)).scrollIntoView(true);
            if (getRange() == getCount()) {
                break;
            }
        }
        return this;
    }

    public SelenideElement getContactFullName(int id) {
        return $(String.format("[__idx='%d'] tbody > tr:nth-child(1) > td:nth-child(2)", id - 1));
    }

    public SelenideElement getContactAddress(int id) {
        return $(String.format("[__idx='%d'] tbody > tr:nth-child(2) > td", id - 1));
    }
}
