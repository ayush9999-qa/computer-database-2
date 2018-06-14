package Backbase.ComputerDatabase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddComputer {
	
	// Identifying web elements
	@FindBy(id = "name")
	private WebElement computerName;
	
	@FindBy(id = "introduced")
	private WebElement introducedDate;
	
	@FindBy(id = "discontinued")
	private WebElement discontinuedDate;
	
	@FindBy(id = "company")
	private WebElement companyDropdown;
	
	@FindBy(css = "input.btn.primary")
	private WebElement createComputer;
	
	@FindBy(css = "input.btn")
	private WebElement cancel;
	
	/**
	 * Enter the computer details
	 * @param compName	computer name
	 * @param intrDate	introduced date
	 * @param disDate	discontinued date
	 * @param comp		company
	 */
	public void enterComputerDetails(String compName, String intrDate, String disDate, String comp) {
		computerName.sendKeys(compName);
		introducedDate.sendKeys(intrDate);
		discontinuedDate.sendKeys(disDate);
		companyDropdown.sendKeys(comp);
	}
	
	/**
	 * Create computer
	 */
	public void createComputer() {
		createComputer.click();
	}
	
	/**
	 * Cancel computer creation
	 */
	public void cancelCreation() {
		cancel.click();
	}
}
