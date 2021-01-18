package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CucumberTest {

    //Definition of WebDriver
    private WebDriver driver;

    //Definition of dynamic searchText parameter
    private String searchText;

    @Given("I Search text {string}")
    public void i_search_text(String string) {
        //Gave driver path
        System.setProperty("webdriver.chrome.driver", "src" + File.separator + "test" + File.separator + "resources" + File.separator +"chromedriver.exe");

        //Definition of driver
        driver = new ChromeDriver();

        //Gave test URL
        driver.get("https://www.google.com");

        //Maximizing window
        driver.manage().window().maximize();

        //Variable definition
        searchText = string;
    }

    @When("I write text and click search button")
    public void i_write_text_and_click_search_button() throws InterruptedException {

        //Variable definition
        String cssOfInputField = "input[name='q']";

        //Writing dynamic searchText to search input box
        driver.findElement(By.cssSelector(cssOfInputField)).sendKeys(searchText);

        //Added sleep to see if filter is correct
        Thread.sleep(1000);

        //Variable definition
        String cssOfButton = "input[name='btnK']";

        //Finding search button
        WebElement searchButton = driver.findElement(By.cssSelector(cssOfButton));

        //Clicking search button
        searchButton.submit();
    }

    @Then("I return result page and captured screen as {string}")
    public void i_return_result_page_and_captured_screen_as(String filename) throws IOException, InterruptedException {

        //Definition of results elements as list
        List<WebElement> resultEntries = driver.findElements(By.cssSelector("div.g>div>div>a>h3>span"));

        //Checking if result elements are listed. result-stats only appears after searching
        assertTrue(driver.findElement(By.id("result-stats")).isDisplayed());

        //Taking screenshot
        TakesScreenshot ts = (TakesScreenshot)driver;

        //Definition screenshot file as source file
        File source = ts.getScreenshotAs(OutputType.FILE);

        //Sending file to path in project. If we have already created file, code will overwrite it
        Files.copy(source.toPath(),new File(filename).toPath(), StandardCopyOption.REPLACE_EXISTING);

        Thread.sleep(1000);

        //Closing browser
        driver.quit();
    }

    @Given("I navigate to the Expedia website")
    public void i_navigate_to_the_expedia_website() {

        //Gave driver path
        System.setProperty("webdriver.chrome.driver", "src" + File.separator + "test" + File.separator + "resources" + File.separator +"chromedriver.exe");

        //Definition of driver
        driver = new ChromeDriver();

        //Maximizing browser
        driver.manage().window().maximize();

        //Gave test URL
        driver.get("https://www.expedia.com");

    }

    @When("I am looking for a flight ticket from {string} to {string} departing for {int} adult and {string} years old {int} child")
    public void i_am_looking_for_a_flight_ticket_from_to_departing_for_adult_and_years_old_child(String fromCity, String toCity, Integer adult, String age, Integer child) throws InterruptedException {
        //Adding default timeout seconds
        WebDriverWait wait = new WebDriverWait(driver,3);

        //Clicking Flights tab
        driver.findElement(By.xpath("//*[@id=\"uitk-tabs-button-container\"]/li[2]/a")).click();

        //To avoid commercial popup added try catch
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[1]/button")));
            driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/button")).click();
        } catch (Exception ignored){};

        //Clicking Leaving from button to write current city
        driver.findElements(By.className("uitk-faux-input")).get(0).click();

        //Sending dynamic fromCity parameter to select current city from dropdown
        driver.findElement(By.cssSelector("input[id=location-field-leg1-origin]")).sendKeys(fromCity);

        //Clicking enter button to select city
        driver.findElement(By.cssSelector("input[id=location-field-leg1-origin]")).sendKeys(Keys.ENTER);

        //Clicking Going to button to write destination city
        driver.findElements(By.className("uitk-faux-input")).get(1).click();

        //Sending dynamic toCity parameter to select destination city
        driver.findElement(By.cssSelector("input[id=location-field-leg1-destination]")).sendKeys(toCity);

        //Clicking enter button to select city
        driver.findElement(By.cssSelector("input[id=location-field-leg1-destination]")).sendKeys(Keys.ENTER);

        //Clicking calendar button to select time interval for the filter
        driver.findElements(By.className("uitk-faux-input")).get(2).click();

        //Clicking one of the dates from calendar for travel
        driver.findElement(By.xpath("//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[2]/div/div/div/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[7]/button")).click();

        //Clicking Done button to save flight dates
        driver.findElement(By.xpath("//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[2]/div/div/div/div[1]/div/div[2]/div/div[3]/button")).click();

        //Clicking traveler button to add passenger information
        driver.findElement(By.xpath("//*[@id=\"adaptive-menu\"]/a")).click();

        //Clicking + button to add adult passenger number. In my case adult is 1 as already selected.
        for ( int x = 0; x < adult; x++){driver.findElement(By.xpath("//*[@id=\"adaptive-menu\"]/div/div/section/div[1]/div[1]/div/button[2]")).click();}

        //Clicking + button to add child passenger number
        for ( int i = 0; i < child; i++){driver.findElement(By.xpath("//*[@id=\"adaptive-menu\"]/div/div/section/div[1]/div[2]/div/button[2]")).click();}
        driver.findElement(By.cssSelector("select[id=child-age-input-0-0]")).click();

        //Sending child's age
        driver.findElement(By.cssSelector("select[id=child-age-input-0-0]")).sendKeys(age);

        //Clicking Enter button to save changes
        driver.findElement(By.cssSelector("select[id=child-age-input-0-0]")).sendKeys(Keys.ENTER);

        //Clicking Done button to continue journey
        driver.findElement(By.xpath("//*[@id=\"adaptive-menu\"]/div/div/div[2]/button")).click();

        //Clicking Search button to see results
        driver.findElement(By.xpath("//*[@id=\"wizard-flight-pwa-1\"]/div[3]/div[2]/button")).click();

    }

    @Then("The result page contains travel option for the chosen destination")
    public void the_result_page_contains_travel_option_for_the_chosen_destination() throws InterruptedException {

        //Allowing time for the site to load
        WebDriverWait wait = new WebDriverWait(driver,20);

        //Selecting first flight detail button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div[3]/div[1]/section/main/ol/li[1]/div/div/div/button")));
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div[3]/div[1]/section/main/ol/li[1]/div/div/div/button")).click();

        Thread.sleep(2000);

        //Closing detail menu
        driver.findElement(By.xpath("//*[@id=\"app-layer-base\"]/div[2]/div[3]/div[1]/section/main/div[5]/section/div[1]/button")).click();

        Thread.sleep(1000);

        driver.quit();
    }
}
