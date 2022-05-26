package guru.qa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SoftAssertionsTest {
    String selenideGitUrl = "https://github.com/selenide/selenide",
           softAssertions = "Soft assertions",
           jUnitText = "3. Using JUnit5 extend test class:",
           dragAndDropUrl = "https://the-internet.herokuapp.com/drag_and_drop",
           aText = "A",
           bText = "B";

    SelenideElement wikiTab = $("#wiki-tab"),
                    wikiBody = $("#wiki-body"),
                    columnA = $("#column-a"),
                    columnB = $("#column-b");



    @Test
    void assertionsTest() {
        open(selenideGitUrl);
        wikiTab.click();
        wikiBody.shouldHave(text(softAssertions));
        wikiBody.$(byText(selenideGitUrl)).click();
        wikiBody.$(byText(jUnitText)).shouldBe(visible);
    }

    @Test
    @Disabled("Selenide.actions().dragAndDrop not working")
    void dragAndDropActionTest() {
        open(dragAndDropUrl);
        columnA.shouldHave(text(aText));
        columnB.shouldHave(text(bText));
        Selenide.actions().dragAndDrop(columnA, columnB);
        columnA.shouldHave(text(bText));
        columnB.shouldHave(text(aText));
    }

    @Test
    void dragAndDropSuccessTest() {
        open(dragAndDropUrl);
        columnA.shouldHave(text(aText));
        columnB.shouldHave(text(bText));
        columnA.dragAndDropTo(columnB);
        columnA.shouldHave(text(bText));
        columnB.shouldHave(text(aText));
    }
}
