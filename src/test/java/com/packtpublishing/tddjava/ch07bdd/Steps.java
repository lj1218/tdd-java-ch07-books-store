package com.packtpublishing.tddjava.ch07bdd;

import com.codeborne.selenide.WebDriverRunner;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by lj1218.
 * Date: 2019/11/21
 */
public class Steps {

    private static final String URL = "http://localhost:9001";

    private WebDriver webDriver;

    @BeforeStory
    public void beforeStory() {
        if (webDriver == null) {
//            webDriver = new PhantomJSDriver(); // 使用 PhantomJS 驱动 会出现莫名其妙的出错误，导致测试失败
            webDriver = new FirefoxDriver();
            WebDriverRunner.setWebDriver(webDriver);
            webDriver.manage().window().setSize(
                    new Dimension(1024, 768)
            );
        }
    }

    @AfterStory
    public void afterStory() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    // Scenario: Book details form should have all fields

    @Given("user is on the books screen")
    public void givenUserIsOnTheBooksScreen() {
        open(URL);
        $("#books").click();
    }

    @Then("field $elementId exists")
    public void thenFieldExists(String elementId) {
        $("#" + elementId).shouldBe(visible);
    }


    // Scenario: User should be able to create a new book

    @When("user clicks the button $elementId")
    public void whenUserClicksTheButton(String elementId) {
        $("#" + elementId).click();
    }

    @When("user sets values to the book form")
    public void whenUserSetsValuesToTheBookForm() {
        $("#bookId").setValue("123");
        $("#bookTitle").setValue("BDD Assistant");
        $("#bookAuthor").setValue("Viktor Farcic");
        $("#bookDescription").setValue("Open source BDD stories editor and runner");
    }

    @Then("book is stored")
    public void thenBookIsStored() {
        $("#book123").shouldBe(exist);
    }

    // Scenario: User should be able to display book details

    @When("user selects a book")
    public void whenUserSelectsABook() {
        $("#book1").click();
    }

    @Then("book form contains all data")
    public void thenBookFormContainsAllData() {
        $("#bookId").shouldHave(value("1"));
        $("#bookTitle").shouldHave(value("TDD for Java Developers"));
        $("#bookAuthor").shouldHave(value("Viktor Farcic"));
        $("#bookDescription").shouldHave(value("Cool book!"));
    }

    // Scenario: User should be able to update book details

    @When("user sets new values to the book form")
    public void whenUserSetsNewValuesToTheBookForm() {
        $("#bookTitle").setValue("TDD for Java Developers revised");
        $("#bookAuthor").setValue("Viktor Farcic and Alex Garcia");
        $("#bookDescription").setValue("Even better book!");
        $("#saveBook").click();
    }

    @Then("book is updated")
    public void thenBookIsUpdated() {
        $("#book1").shouldHave(text("TDD for Java Developers revised"));
        $("#book1").click();
        $("#bookTitle").shouldHave(value("TDD for Java Developers revised"));
        $("#bookAuthor").shouldHave(value("Viktor Farcic and Alex Garcia"));
        $("#bookDescription").shouldHave(value("Even better book!"));
    }

    // Scenario: User should be able to delete a book

    @Then("book is removed")
    public void thenBookIsRemoved() {
        $("#book1").shouldNotBe(exist);
    }
}
