package Backbase.ComputerDatabase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditComputer {
	
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
	private WebElement saveComputer;
	
	@FindBy(css = "input.btn")
	private WebElement cancel;

	@FindBy(css = "input.btn.danger")
	private WebElement delete;
	
	/**
	 * Edit computer details
	 * @param compName	 computer name
	 * @param introDate	 introduced date
	 * @param disDate	 discontinued date
	 * @param comp		 company
	 */
	public void editDetails(String compName, String introDate, String disDate, String comp) {
		computerName.clear();
		computerName.sendKeys(compName);
		introducedDate.clear();
		introducedDate.sendKeys(introDate);
		discontinuedDate.clear();
		discontinuedDate.sendKeys(disDate);
		companyDropdown.sendKeys(comp);
	}
	
	/**
	 * Save computer details
	 */
	public void saveComputer() {
		saveComputer.click();
	}
	
	/**
	 * Cancel current process of editing
	 */
	public void cancel() {
		cancel.click();
	}
	
	/**
	 * Delete computer
	 */
	public void deleteComputer() {
		delete.click();
	}
	
	/**
	 * Get computer name
	 * @return	computer name
	 */
	public String getComputerName() {
		return computerName.getAttribute("value");
	}
}
