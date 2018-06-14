package Backbase.ComputerDatabase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;


public class ComputerDatabaseTests {
	WebDriver driver;
	HomePage homePage;
	EditComputer editComputer;
	AddComputer addComputer;

	// Initialize properties and start browser
	@BeforeClass
	public void openComputerDatabase() {
		System.setProperty("webdriver.gecko.driver", "./resources/geckodriver");
		// Get driver and delete all cookies
		WebDriver driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		this.driver = driver;
		
		// Initialize page objects
		homePage = new HomePage();
		editComputer = new EditComputer();
		addComputer = new AddComputer();
		
		// Initialize page elements
		driver.get("http://computer-database.herokuapp.com/computers");
		PageFactory.initElements(driver, homePage);
		PageFactory.initElements(driver, editComputer);
		PageFactory.initElements(driver, addComputer);
	}
	
	// Close browser
	@AfterClass
	public void closeDriver() {
		driver.close();
	}
	
	// Test cases are primarily of CRUD operations
	
	@Parameters({"computerName", "introducedDate", "company"})
	@Test (description = "Verify to add a computer in computer database", priority = 1)	
	public void addTest(String computerName, String introducedDate, String company) {
		SoftAssert softAssert = new SoftAssert();
		
		// Get total computers before adding new computer
		String availableComputers = homePage.getTotalComputers();
		
		// Add computer
		homePage.clickAddComputer();
		addComputer.enterComputerDetails(computerName, introducedDate, "", company);
		addComputer.createComputer();
		
		//Get total computers after adding new computer
		String updatedComputers = homePage.getTotalComputers();
		
		// Assertions (Soft Assertion)
		softAssert.assertNotEquals(availableComputers, updatedComputers);
		softAssert.assertEquals("Done! Computer " + computerName + " has been created", homePage.getCreateCompMsg());
		softAssert.assertAll();
	}
	
	@Parameters({"computerName"})
	@Test (description = "Verify to filter added computer", priority = 2)
	public void filterTest(String computerName) {
		// Apply filter
		homePage.filterByName(computerName);
		homePage.clickFilteredElement(computerName);
		
		// Assertion (Hard Assertion)
		Assert.assertEquals(computerName, editComputer.getComputerName());
		homePage.cancelFilter();
	}
	
	@Parameters({"computerName", "editedComputerName", "editedCompany"})
	@Test (description = "Verify to edit filtered computer", priority = 3)
	public void editTest(String computerName, String editedComputerName, String editedCompany) {
		// Apply filter
		homePage.filterByName(computerName);		
		homePage.clickFilteredElement(computerName);
		
		// Edit filtered computer and save
		editComputer.editDetails(editedComputerName, "", "", editedCompany);
		editComputer.saveComputer();
		
		// Assertion
		Assert.assertEquals("Done! Computer " + editedComputerName + " has been updated", homePage.getEditComputerMsg());		 
	}
	
	@Parameters({"computerName"})
	@Test (description = "Verify when search element is not available", priority = 4)
	public void notAvailableItemTest(String computerName) {
		// Apply filter for an unavailable computer
		homePage.filterByName(computerName);
		
		// Assertion
		Assert.assertEquals("Nothing to display", homePage.getNotAvailableMsg());
		homePage.cancelFilter();
	}
	
	@Parameters({"editedComputerName"})
	@Test (description = "Verify to delete the computer", priority = 5)
	public void deleteTest(String editedComputerName) {
		SoftAssert softAssert = new SoftAssert();
		
		// Get total computers before deleting
		String availableComputers = homePage.getTotalComputers();
		
		// Apply filter and delete computer
		homePage.filterByName(editedComputerName);
		homePage.clickFilteredElement(editedComputerName);
		editComputer.deleteComputer();
		
		// Get total computers after deleting
		String updatedComputers = homePage.getTotalComputers();
		
		// Assertions
		softAssert.assertNotEquals(availableComputers, updatedComputers);
		softAssert.assertEquals("Done! Computer has been deleted", homePage.getDeleteComputerMsg());
	}	
}
