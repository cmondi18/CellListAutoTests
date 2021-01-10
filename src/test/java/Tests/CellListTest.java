package Tests;

import Pages.CellListPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static Helpers.ConfigProperties.getProperty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class CellListTest {
    CellListPage page = new CellListPage();

    private String firstName;
    private String lastName;
    private String category;
    private String birthday;
    private String address;

    public CellListTest(String firstName, String lastName, String category, String birthday, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
        this.birthday = birthday;
        this.address = address;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Name_1", "LastName_1", "Family", "January 1, 2021", "Novosibirsk" },
                { "Name_2", "LastName_2", "Friends", "January 2, 2021", "Moscow" },
                { "Name_3", "LastName_3", "Coworkers", "January 3, 2021", "Saint-Petersburg" },
                { "Name_4", "LastName_4", "Businesses", "January 4, 2021", "Tomsk" },
                { "Name_6", "LastName_6", "Contacts", "January 5, 2021", "Chelyabinsk" }
                });
    }

    @BeforeClass
    public static void preconditions() {
        String URL = getProperty("cell_list_page");
        open(URL);
    }

    @Test
    public void addManyUsers() {
        //Arrange
        int previousCount = page.getCount();

        //Act
        page.fillFirstName(firstName)
                .fillLastName(lastName)
                .selectCategory(category)
                .fillBirthday(birthday)
                .fillAddress(address)
                .createContact()
                .scrollToCellListEnd();

        int actualCount = page.getCount();

        //Assert for count increase
        assertEquals(previousCount + 1, actualCount);

        //Assert for correct created data
        page.getContactFullName(actualCount).shouldBe(text(firstName + " " + lastName));
        page.getContactAddress(actualCount).shouldBe(text(address));
    }

    @AfterClass
    public static void postconditions() {
        closeWebDriver();
    }
}
