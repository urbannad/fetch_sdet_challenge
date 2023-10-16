package fetch.pages;

import fetch.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TaskPage {

    public TaskPage() {

        PageFactory.initElements(Driver.getDriver(), this );
    }

    @FindBy(id = "left_0")
    public WebElement firstBoxOfLeftBowl;

    @FindBy(id = "right_0")
    public WebElement firstBoxOfRightBowl;

    @FindBy(xpath = "//div[@class='result']//button[@id='reset']")
    public WebElement result;

    @FindBy (xpath = "//button[@class='square']")
    public List<WebElement> allBars;

    @FindBy(id = "weigh")
    public WebElement weighButton;

}
