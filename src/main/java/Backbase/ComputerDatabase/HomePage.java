package Backbase.ComputerDatabase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	// Identifying web elements
	@FindBy(xpath = "//h1/a")
	private WebElement title;
	
	@FindBy(xpath = "//section[@id='main']/h1")
	private WebElement totalComputers;
	
	@FindBy(id = "add")
	private WebElement addComputer;
	
	@FindBy(id = "searchsubmit")
	private WebElement filterByName;
	
	@FindBy(id = "searchbox")
	private WebElement searchBox;
	
	@FindBy(xpath = "//table/tbody/tr")
	private List<WebElement> containerElements;
	
	@FindBy(css = "div.alert-message.warning")
	private WebElement createComputerMsg;
	
	@FindBy(css = "div.alert-message.warning")
	private WebElement deleteComputerMsg;
	
	@FindBy(css = "div.alert-message.warning")
	private WebElement editComputerMsg;
	
	@FindBy(css = "div.well")
	private WebElement notAvailableMsg;
	
	/**
	 * Click on Add Computer
	 */
	public void clickAddComputer() {
		addComputer.click();
	}
	
	/**
	 * Filter by name
	 * @param computerName	computer name
	 */
	public void filterByName(String computerName) {
		searchBox.sendKeys(computerName);
		filterByName.click();		
	}
	
	/**
	 * Click on filtered computer
	 * @param computerName	computer name
	 */
	public void clickFilteredElement(String computerName) {
		containerElements.get(containerElements.size()-1).findElement(By.linkText(computerName)).click();
	}
	
	/**
	 * Cancel filter
	 */
	public void cancelFilter() {
		title.click();
	}
	
	/**
	 * Get total computers
	 * @return total computers
	 */
	public String getTotalComputers() {
		String computers = totalComputers.getText();
		return computers.split("\\s+")[0];
	}
	
	/**
	 * Get message after deleting computer
	 * @return	message
	 */
	public String getDeleteComputerMsg() {
		return deleteComputerMsg.getText();
	}
	
	/**
	 * Get message after editing computer
	 * @return	message
	 */
	public String getEditComputerMsg() {
		return editComputerMsg.getText();
	}
	
	/**
	 * Get message when filtered computer is not available
	 * @return	message
	 */
	public String getNotAvailableMsg() {
		return notAvailableMsg.getText();
	}
	
	/**
	 * Get message after creating computer
	 * @return	message
	 */
	public String getCreateCompMsg() {
		return createComputerMsg.getText();
	}

}
