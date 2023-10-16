package fetch.steps;

import fetch.pages.TaskPage;
import fetch.utilities.ConfigurationReader;
import fetch.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Task_StepDefs {
    WebDriverWait webDriverWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3));
    TaskPage taskPage = new TaskPage();
    String lighterBlock ="";
    WebElement correctBlock;

    @Given("user is on the  fetch challenge page")
    public void user_is_on_the_fetch_challenge_page() {

        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

    }

    @When("user put one bar on the left bowl and comparing the weight of other bars in right bowl")
    public void userPutOneBarOnTheLeftBowlAndComparingTheWeightOfOtherBarsInRightBowl() {
        //Put first block into the left bowl
        taskPage.firstBoxOfLeftBowl.sendKeys(taskPage.allBars.get(0).getText());

        int index = 0; // index of list of weight for WebDriverWait
        //Put other blocks one by one to right bowl, and comparing their weight
        for (int i = 1; i < taskPage.allBars.size()-1; i++) {
            taskPage.firstBoxOfRightBowl.sendKeys(taskPage.allBars.get(i).getText());
            taskPage.weighButton.click();

        //we need to synchronize Selenium and Browser
           webDriverWait.until(ExpectedConditions.visibilityOf(Driver.getDriver().findElement(By.xpath("//li["+ ++index+"]"))));

        //Add some condition to check their weight
            if (taskPage.result.getText().equals(">")) {
                lighterBlock += taskPage.allBars.get(i).getText();
                break;
            } else if (taskPage.result.getText().equals("<")) {
                lighterBlock += taskPage.allBars.get(0).getText();
                break;
            }
         //clear the right bowl if weight is equals to check weight of next block
            taskPage.firstBoxOfRightBowl.clear();
        }
    }

    @And("user click the bar with lighter weight")
    public void userClickTheBarWithLighterWeight() {
        //Looking for lighter block and assign it to WebElement
        for (WebElement eachBar : taskPage.allBars) {
            if (eachBar.getText().equals(lighterBlock)) {
                correctBlock = eachBar;
                break; }
        }
        //click on lighter block
        correctBlock.click();
    }


    @Then("user must see the pop up window with message {string}")
    public void userMustSeeThePopUpWindowWithMessage(String expectedMessage) {
        //Checking if the message on javascript alert is the same as expected
        //The message from requirements was "Yay! You found it!", but actual is "Yay! You find it!" ---> found/find

        try {
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            Alert alert = Driver.getDriver().switchTo().alert();
            String actualMessage = alert.getText();
            alert.accept();
            Assert.assertEquals(expectedMessage,actualMessage);

        } catch (UnhandledAlertException e) {
            e.printStackTrace();
        }
    }



}
