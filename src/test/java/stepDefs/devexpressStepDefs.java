package stepDefs;

import baseFunc.BaseFunc;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.DataValidationPage;
import pages.HomePage;

import java.util.List;

public class devexpressStepDefs {
    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage;
    private DataValidationPage dataValidationPage;

    private final String URL = "https://js.devexpress.com/demos/widgetsgallery/";

    @Given("open js.devexpress page")
    public void open_page() {
        baseFunc.openPage(URL);
        homePage = new HomePage(baseFunc);
    }

    @Then("we open {string} category")
    public void open_categorie(String categoryName) {
        homePage.openCategory(categoryName);
    }

    @Then("click {string} link")
    public void open_link_by_name(String linkName) {
        homePage.openLink(linkName);
    }

    @Then("Data Validation page should appear")
    public void data_validation_page() {
        dataValidationPage = new DataValidationPage(baseFunc);
    }

    @Then("Open {string} tab")
    public void open_tab(String tabName) {
        dataValidationPage.openTab(tabName);
    }

    @Then("we switch to iframe")
    public void switch_to_iframe() throws InterruptedException {
        dataValidationPage.switchToFrame();
    }

    @Then("wait until iframe is loaded")
    public void wait_until_iframe_is_loaded() {
        dataValidationPage.waitUntilLoaded();
    }

    @Then("push 'Add a row' button")
    public void push_add_a_row_btn() {
        dataValidationPage.pushAddARowBtn();
    }

    @Then("add new incorrect email and check correctness:")
    public void add_incorrect_email_and_check(List<String> emailList) {
        dataValidationPage.checkIncorrectEmails(emailList);
    }

    @Then("add new correct email and check correctness:")
    public void add_correct_email_and_check(List<String> emailList) {
        dataValidationPage.checkCorrectEmails(emailList);
    }

    @Then("close browser")
    public void close_browser() {
        baseFunc.quit();
    }
}
